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
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attRepo;

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
}
