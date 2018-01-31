package cz.eshop.service;

import cz.eshop.dao.AttendanceRepository;
import cz.eshop.dao.TrainingRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Attendance;
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

    public List<Attendance> getAllAttendeces(){
        return (List<Attendance>) attRepo.findAll();
    }
}
