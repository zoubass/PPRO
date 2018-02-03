package cz.eshop.controller.admin;

import cz.eshop.model.Training;
import cz.eshop.model.Types.FilterTypePeriods;
import cz.eshop.service.AttendanceService;
import cz.eshop.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/web")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private TrainingService trainingService;

	@RequestMapping(value = "/attendance", method = RequestMethod.GET)
	public String attendancePage(Model model) {
		model.addAttribute("attDataList", attendanceService.makeAtt(trainingService.getAllTrainings()));
		model.addAttribute("timeType", new FilterTypePeriods());
		return "attendance";
	}

	@RequestMapping(value = "/removeAttendance", method = RequestMethod.GET)
	public String removeAttendance(Model model, @RequestParam Long userId, @RequestParam Long trainId) {
		attendanceService.removeAttendance(userId, trainId);
		model.addAttribute("attDataList", attendanceService.makeAtt(trainingService.getAllTrainings()));
		model.addAttribute("timeType", new FilterTypePeriods());
		return "attendance";
	}

	@RequestMapping(value = "/filterAttendance", method = RequestMethod.GET)
	public String filterAttendance(Model model, @ModelAttribute("timeType") FilterTypePeriods timeType) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Prague"));
		List<Training> myList;
		switch (timeType.getTypes()) {
		case MONTH:
			myList = trainingService.getTimeTrainings(cal, Calendar.MONTH);
			break;
		case WEEK:
			myList = trainingService.getTimeTrainings(cal, Calendar.WEEK_OF_MONTH);
			break;
		case YEAR:
			myList = trainingService.getTimeTrainings(cal, Calendar.YEAR);
			break;
		default:
			myList = trainingService.getAllTrainings();
			break;
		}
		model.addAttribute("attDataList", attendanceService.makeAtt(myList));
		model.addAttribute("timeType", new FilterTypePeriods());
		return "attendance";
	}

}
