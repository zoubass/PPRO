package cz.eshop.controller.admin;

import cz.eshop.model.Training;
import cz.eshop.model.Types.FilterTypePeriods;
import cz.eshop.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.TimeZone;

@Controller
@RequestMapping("/web")
public class TrainingController {

	@Autowired
	private TrainingService trainingService;

	@RequestMapping(value = "/training", method = RequestMethod.GET)
	public String getTraining(Model model) {
		prepareModel(model);
		model.addAttribute("isEditOp", false);
		return "training";
	}

	@RequestMapping(value = "/addTraining", method = RequestMethod.POST)
	public String saveTraining(Model model, @ModelAttribute("newTraining") @Valid Training training,
			BindingResult bindingResult,
			@RequestParam(value = "isEditOp", required = false) boolean isEditOp) {
		
		if (bindingResult.hasErrors()) {
			return preapreModel(model, training, isEditOp);
		}
		
		if (!trainingService.isTrainingInOneDay(training)) {
			return preapreModel(model, training, isEditOp);
		}
		
		trainingService.saveTraninig(training);
		model.addAttribute("trList", trainingService.getAllTrainings());
		model.addAttribute("newTraining", new Training());
		model.addAttribute("timeType", new FilterTypePeriods());
		model.addAttribute("isEditOp", isEditOp ? false : isEditOp);

		return "training";
	}

	private String preapreModel(Model model, @ModelAttribute("newTraining") @Valid Training training,
			@RequestParam(value = "isEditOp", required = false) boolean isEditOp) {
		model.addAttribute("trList", trainingService.getAllTrainings());
		model.addAttribute("newTraining", training);
		model.addAttribute("timeType", new FilterTypePeriods());
		model.addAttribute("isEditOp", isEditOp);
		model.addAttribute("datesError", true);
		return "training";
	}

	private void prepareModel(Model model) {
		model.addAttribute("trList", trainingService.getAllTrainings());
		model.addAttribute("newTraining", new Training());
		model.addAttribute("timeType", new FilterTypePeriods());
	}

	@RequestMapping(value = "/removeTraining", method = RequestMethod.GET)
	public String removeTraining(Model model, @RequestParam Long id) {
		trainingService.removeTraining(id);
		prepareModel(model);
		model.addAttribute("isEditOp", false);
		return "training";
	}

	@RequestMapping(value = "/editTraining", method = RequestMethod.GET)
	public String editTraining(Model model, @RequestParam Long id) {
		Training training = trainingService.findTrainingById(id);

		model.addAttribute("trList", trainingService.getAllTrainings());
		model.addAttribute("newTraining", training);
		model.addAttribute("timeType", new FilterTypePeriods());
		model.addAttribute("isEditOp", true);
		return "training";
	}

	@RequestMapping(value = "/filterTraining", method = RequestMethod.GET)
	public String filterTrainings(Model model, @ModelAttribute("timeType") FilterTypePeriods timeType) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Prague"));
		switch (timeType.getTypes()) {
		case MONTH:
			model.addAttribute("trList", trainingService.getTimeTrainings(cal, Calendar.MONTH));
			break;
		case WEEK:
			model.addAttribute("trList", trainingService.getTimeTrainings(cal, Calendar.WEEK_OF_MONTH));
			break;
		case YEAR:
			model.addAttribute("trList", trainingService.getTimeTrainings(cal, Calendar.YEAR));
			break;
		default:
			model.addAttribute("trList", trainingService.getAllTrainings());
			break;
		}
		model.addAttribute("newTraining", new Training());
		model.addAttribute("timeType", new FilterTypePeriods());
		model.addAttribute("isEditOp", false);
		return "training";
	}

}
