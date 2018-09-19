package cz.eshop.controller.admin;

import cz.eshop.dto.CmprAttendanceDto;
import cz.eshop.model.AuxObject.DateBox;
import cz.eshop.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping("/web")
public class cmprAttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/comparingAttendance")
    public String initComparing(Model model) {

        model.addAttribute("cmprAttendanceDto", new ArrayList<CmprAttendanceDto>());
        model.addAttribute("dateBox", new DateBox());
        return "comparingAttendance";
    }

    @RequestMapping(value = "/searchCompare", method = RequestMethod.GET)
    public String searchCompare(Model model, @ModelAttribute("dateBox") DateBox dateBox) {

        model.addAttribute("cmprAttendanceDto", attendanceService.getCompareAttendance(dateBox));
        return "comparingAttendance";
    }
}
