package cz.eshop.service;

import cz.eshop.dao.*;
import cz.eshop.dto.CmprAttendanceDto;
import cz.eshop.model.*;
import cz.eshop.model.AuxObject.DateBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attRepo;
    @Autowired
    private TrainingRepository trainRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ReminderRepository reminderRepo;
    @Autowired
    private TicketRepository ticketRepo;

    public List<Attendance> getAllAttendances() {
        return (List<Attendance>) attRepo.findAll();
    }

    /**
     * Method for make data for view of Training with all users signed on trainings
     *
     * @param trainingList list of trainings which need to display
     * @return training with users signed on training
     */
    public List<AttendanceData> makeAtt(List<Training> trainingList) {
        //List<Training> trainingList = trainServ.getAllTrainings();
        List<AttendanceData> attData = new ArrayList<>();
        for (Training t : trainingList) {
            List<Attendance> attList = attRepo.filterByTraining(t);
            List<User> users = new ArrayList<>();
            for (Attendance att : attList) {
                users.add(att.getUser());
            }
            attData.add(new AttendanceData(t, users));
        }
        return attData;
    }

    /**
     * Method for remove Attendance.
     * 1. check if user have ticket. If have and it's point ticket, add extra point.
     * 2. check if user have reminder. If it's true, delete or deduct this reminder
     * 3. if haven't got ticket and reminder add one point ticket
     * @param userId User who need remove attendance
     * @param trainId Training witch remove attendance
     */
    public void removeAttendance(Long userId, Long trainId) {
        User user = userRepo.findById(userId);
        Reminder reminder = user.getReminder();
        Ticket ticket = user.getTicket();

        if (ticket != null) {
            if (!ticket.isTimeTicket()) {
                ticket.setEntry(ticket.getEntry() + 1);
                ticketRepo.save(ticket);
            }
            attRepo.delete(findAttendance(userId, trainId));
            return;
        }

        if (reminder != null) {
            int rCount = reminder.getReminderCount();
            if (rCount > 1) {
                rCount -= 1;
                reminder.setReminderCount(rCount);
               reminderRepo.save(reminder);
            } else {
                user.setReminder(null);
                userRepo.save(user);
                reminderRepo.delete(reminder);
            }
            attRepo.delete(findAttendance(userId, trainId));
            return;
        }

        ticket = new Ticket();
        ticket.setTimeTicket(false);
        ticket.setEntry(1);
        ticketRepo.save(ticket);
        user.setTicket(ticket);
        userRepo.save(user);
        attRepo.delete(findAttendance(userId, trainId));

    }

    public void removeAttendance(User user) {
        attRepo.deleteByUser(user);
    }

    public Attendance findAttendance(Long userId, Long trainId) {
        User user = userRepo.findById(userId);
        Training train = trainRepo.findById(trainId);
        Attendance att = attRepo.filterByTrainingAndUser(train, user);
        return att;
    }

    //TODO can be one method writeDownAttByTimeId and writeDownAttByTimeIdString

    /**
     * Write down users to selected training
     *
     * @param userIdList     users id number list<Long>
     * @param actualTraining selected training
     */
    public void writeDownAttByTime(List<Long> userIdList, Training actualTraining) {
        for (Long userId : userIdList) {
            User user = userRepo.findById(userId);

            if (attRepo.filterByTrainingAndUser(actualTraining, user) != null)
                continue;

            Attendance att = new Attendance(user, actualTraining);
            attRepo.save(att);
        }
    }

    public Training findActualTraining() {
        Date now = new Date();
        Training actualTraining = trainRepo.getActualTraining(now);

        if (actualTraining == null) {
            List<Training> pastTrainings = trainRepo.getLastTraining(now);
            actualTraining = pastTrainings.get(0);
        }
        return actualTraining;
    }

    /**
     * Method for counting attendance of Users on CompareAttendance page
     *
     * @param dateBox date for filters
     * @return list of users + count of entry of each user
     */
    public List<CmprAttendanceDto> getCompareAttendance(DateBox dateBox) {
        List<Attendance> findingAttendance;
        List<CmprAttendanceDto> newAttendance = new ArrayList<>();

        if (dateBox.getEndDate() == null) {
            findingAttendance = attRepo.getAttendanceFromDate(dateBox.getStartDate());
        } else {
            //Date in training is after midnight, in dateBox is in midnight, due to added one day to dateBox for success query <=
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateBox.getEndDate());
            calendar.add(Calendar.DATE, 1);

            findingAttendance = attRepo.getAttendanceFromDateToDate(dateBox.getStartDate(), calendar.getTime());
        }

        while (!findingAttendance.isEmpty()) {
            List<Attendance> toRemove = new ArrayList<>();
            User user = findingAttendance.get(0).getUser();
            int entry = 0;

            for (Attendance attendance : findingAttendance) {
                long nextUserId = attendance.getUser().getId();
                if (user.getId() == nextUserId) {
                    entry += 1;
                    toRemove.add(attendance);
                }
            }
            findingAttendance.removeAll(toRemove);
            newAttendance.add(new CmprAttendanceDto(user, entry));
        }

        newAttendance.sort(Comparator.comparingInt(CmprAttendanceDto::getEntry).reversed());
        return newAttendance;
    }
}
