package cz.eshop.controller.admin;

import cz.eshop.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(value = "/allAtendances", method = RequestMethod.GET)
    public String getAllAttendance(Model model){
        model.addAttribute("attList", attendanceService.getAllAttendeces());
        return "attendance";
    }
}
