package cz.eshop.controller.admin;

import cz.eshop.dao.TrainingRepository;
import cz.eshop.model.Training;
import cz.eshop.model.Types.FilterTypePeriods;
import cz.eshop.model.Types.FilterTypes;
import cz.eshop.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.util.resources.cs.LocaleNames_cs;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingRepository trainRepo;

    private List<Training> currentTrainings;

    @RequestMapping(value = "/training", method = RequestMethod.GET)
    public String getTraining(Model model) {

        model.addAttribute("trList",trainingService.getAllTrainings());
        model.addAttribute("newTraining", new Training());
        model.addAttribute("timeType", new FilterTypePeriods());
        return "training";
    }

    @RequestMapping(value = "/saveTraining", method = RequestMethod.GET)
    public String saveTraining(Model model, @ModelAttribute("newTraining") Training training) {
        trainRepo.save(training);
        model.addAttribute("trList",trainingService.getAllTrainings());
        model.addAttribute("newTraining", new Training());
        model.addAttribute("timeType", new FilterTypePeriods());
        return "redirect:/training";
    }

    @RequestMapping(value = "/filterTraining", method = RequestMethod.GET)
    public String filterTrainings(Model model, @ModelAttribute("timeType")FilterTypePeriods timeType) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Prague"));
        switch (timeType.getTypes()) {
            case MONTH:
                model.addAttribute("trList",trainingService.getTimeTrainings(cal,Calendar.MONTH));
                break;
            case WEEK:
                model.addAttribute("trList",trainingService.getTimeTrainings(cal,Calendar.WEEK_OF_MONTH));
                break;
            case YEAR:
                model.addAttribute("trList",trainingService.getTimeTrainings(cal,Calendar.YEAR));
                break;
            default:
                model.addAttribute("trList",trainingService.getAllTrainings());
                break;
        }
        model.addAttribute("newTraining", new Training());
        model.addAttribute("timeType", new FilterTypePeriods());
        return "training";
    }

}
