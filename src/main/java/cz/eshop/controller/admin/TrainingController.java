package cz.eshop.controller.admin;

import cz.eshop.dao.TrainingRepository;
import cz.eshop.model.Training;
import cz.eshop.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingRepository trainRepo;

    @RequestMapping(value = "/training", method = RequestMethod.GET)
    public String getTraining(Model model){
        model.addAttribute("trList", trainingService.getAllTrainings());
//        prázdný trénink
        model.addAttribute("newTraining", new Training());
        return "training";
    }

    @RequestMapping(value = "/saveTraining", method = RequestMethod.POST)
    public String saveTraining(Model model, @ModelAttribute("newTraining")Training training){
        trainRepo.save(training);
        return "redirect:/training";
    }
}
