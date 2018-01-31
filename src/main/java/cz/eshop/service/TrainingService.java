package cz.eshop.service;

import cz.eshop.dao.TrainingRepository;
import cz.eshop.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> getAllTrainings(){

        return (List<Training>) trainingRepository.findAll();
    }
}
