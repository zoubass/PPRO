package cz.eshop.service;

import cz.eshop.dao.AttendanceRepository;
import cz.eshop.dao.TrainingRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Attendance;
import cz.eshop.model.AttendanceData;
import cz.eshop.model.Training;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attRepo;
    @Autowired
    private TrainingRepository trainRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Attendance> getAllAttendances(){
        return (List<Attendance>) attRepo.findAll();
    }

    public List<AttendanceData> makeAtt(List<Training> trainingList){
        //List<Training> trainingList = trainServ.getAllTrainings();
        List<AttendanceData> attData = new ArrayList<>();
        for (Training t:trainingList) {
            List<Attendance> attList = attRepo.filterByTraining(t);
            List<User> users = new ArrayList<>();
            for(Attendance att:attList){
                users.add(att.getUser());
            }
            attData.add(new AttendanceData(t, users));
        }
        return attData;
    }

    public void removeAttendance(Long userId, Long trainId){
        attRepo.delete(findAttendance(userId, trainId));
    }

    public Attendance findAttendance(Long userId, Long trainId){
        User user = userRepo.findById(userId);
        Training train = trainRepo.findById(trainId);
        Attendance att = attRepo.filterByTrainingAndUser(train,user);
        return att;
    }

    public void writeDownAttByTime(List<Long> userIdList, Training actualTraining){
        for(Long userId : userIdList){
            User user = userRepo.findById(userId);

            if (findAttendance(userId, actualTraining.getId()) != null)
                continue;

            Attendance att = new Attendance(user, actualTraining);
            attRepo.save(att);
        }
    }

    public Training findActualTraining(){
        Date now = new Date();
        Training actualTraining = trainRepo.getActualTraining(now);
        if(actualTraining == null){
            List<Training> pastTrainings = trainRepo.getLastTraining(now);
            actualTraining = pastTrainings.get(0);
        }
        return actualTraining;
    }
}
