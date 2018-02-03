package cz.eshop.service;

import cz.eshop.dao.AttendanceRepository;
import cz.eshop.dao.TrainingRepository;
import cz.eshop.model.Attendance;
import cz.eshop.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    /**Returns list of all trainings*/
    //TODO seřadit podle data (private Date ending) od nejnovějšího
    public List<Training> getAllTrainings(){
        return (List<Training>) trainingRepository.findAll();
    }

    public void removeTraining(Long id){
        Training training = trainingRepository.findById(id);
        List<Attendance> attList = attendanceRepository.filterByTraining(training);
        for(Attendance att:attList)
            attendanceRepository.delete(att);
        trainingRepository.delete(id);
    }

    public void saveTraninig(Training training){
        trainingRepository.save(training);
    }

    public Training findTrainingById(Long id){
        return trainingRepository.findById(id);
    }

    //TODO rewrite to SQL query
    /**Returns list of training with same time(Week, Month...)
     *
     * @param myTime a start time for listing
     * @param CalendarTypeOfTime a static int of Calendar class - example Calendar.MONTH*/
    public List<Training> getTimeTrainings(Calendar myTime, int CalendarTypeOfTime){
        List<Training> allT = getAllTrainings();
        List<Training> timeT = new ArrayList<>();

        for (Training t: allT){
            Calendar trainTime = Calendar.getInstance();
            trainTime.setTime(t.getBeginning());

            if(trainTime.get(CalendarTypeOfTime) == myTime.get(CalendarTypeOfTime) &&
                    trainTime.get(Calendar.YEAR) == myTime.get(Calendar.YEAR)){
                timeT.add(t);
            }
        }
        return timeT;
    }

    /**Returns list of training reduced by the number of Time
     *
     * @param myTime a start time for listing
     * @param CalendarTypeOfTime a static int of Calendar class - example Calendar.MONTH
     * @param timeBack the number of time for reduce*/
    public List<Training> getTimeBackTrainings(Calendar myTime, int CalendarTypeOfTime, int timeBack) {
        List<Training> allT = getAllTrainings();
        List<Training> timeT = new ArrayList<>();
        Calendar reduceT = myTime;
        reduceT.add(CalendarTypeOfTime, -(timeBack));

        for (Training t: allT){
            Calendar trainTime = Calendar.getInstance();
            trainTime.setTime(t.getBeginning());

            if(reduceT.get(CalendarTypeOfTime) <= trainTime.get(CalendarTypeOfTime) &&
                    trainTime.get(CalendarTypeOfTime) <= myTime.get(CalendarTypeOfTime)){
                timeT.add(t);
            }
        }
        return  timeT;
    }

}
