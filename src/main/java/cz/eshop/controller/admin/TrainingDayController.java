package cz.eshop.controller.admin;

import cz.eshop.dto.UserDto;
import cz.eshop.model.*;
import cz.eshop.service.AttendanceService;
import cz.eshop.service.ReminderService;
import cz.eshop.service.TrainingService;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web")
public class TrainingDayController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReminderService remService;
    @Autowired
    private AttendanceService attService;
    @Autowired
    private TrainingService trainingService;

    private Training currentTraining;

    @RequestMapping("/trainingDay")
    public String trainingDayPreview(Model model) {
        currentTraining = trainingService.getActualTraining();

        if (currentTraining == null){
            model.addAttribute("actTraining", null);
            return "trainingDay";
        }

        List<User> signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
        List<User> usersForPick = userService.getUsersForPickOnTraining(signedUsers);

        model.addAttribute("usersForPick", usersForPick);
        model.addAttribute("actTraining", currentTraining);
        model.addAttribute("signedUsers", signedUsers);
        model.addAttribute("styleAddUser", "none");
        model.addAttribute("showUsersInDebt", false);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("showModal", false);
        return "trainingDay";
    }

    @RequestMapping(value = "/addUserToTraining", method = RequestMethod.POST)
    public String addUserToTraining(Model model, @RequestParam(value = "present", required = false) List<String> userIds) {
        List<User> usersWithReminder = new ArrayList<>();
        if (currentTraining == null)
            currentTraining = trainingService.getActualTraining();

        if (userIds != null) {
            //sign users to training
            List<Long> userIdsLong = userIds.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
            usersWithReminder = remService.doReminder(userIdsLong, currentTraining);
            attService.writeDownAttByTime(userIdsLong, currentTraining);

            //control popup window
            if (usersWithReminder.size() > 0){
                model.addAttribute("showModal", true);
            }else {
                model.addAttribute("showModal", false);
            }
        } else {
            model.addAttribute("showModal", false);
        }

        List<User> signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
        List<User> usersForPick = userService.getUsersForPickOnTraining(signedUsers);

        model.addAttribute("usersForPick", usersForPick);
        model.addAttribute("actTraining", currentTraining);
        model.addAttribute("signedUsers", signedUsers);
        model.addAttribute("usersWithReminder", usersWithReminder);
        model.addAttribute("styleAddUser", "none");
        model.addAttribute("userDto", new UserDto());
        return "trainingDay";
    }

    @RequestMapping("/registerUser")
    public String registerNewUser(Model model, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {
        List<User> signedUsers;
        List<User> usersForPick;

        if (userDto.getUser().getUsername() != null) {
            Authorities authorities = new Authorities();
            authorities.setUsername(userDto.getUser().getUsername());
            authorities.setAuthority(AuthoritiesEnum.ROLE_USER);

            userDto.getUser().setEnabled(true);
            userDto.setAuthorities(authorities);
        }

        if (bindingResult.hasErrors()) {
            signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
            usersForPick = userService.getUsersForPickOnTraining(signedUsers);
            model.addAttribute("usersForPick", usersForPick);
            model.addAttribute("actTraining", currentTraining);
            model.addAttribute("signedUsers", signedUsers);
            model.addAttribute("userDto", userDto);
            model.addAttribute("styleAddUser", "block");
            model.addAttribute("showModal", false);

            return "trainingDay";
        }

        if (userService.isNotUniqueUsername(userDto)) {
            signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
            usersForPick = userService.getUsersForPickOnTraining(signedUsers);
            model.addAttribute("usersForPick", usersForPick);
            model.addAttribute("actTraining", currentTraining);
            model.addAttribute("signedUsers", signedUsers);
            model.addAttribute("userDto", userDto);
            model.addAttribute("styleAddUser", "block");
            model.addAttribute("isUsernameUsed", true);
            model.addAttribute("showModal", false);
            return "trainingDay";
        }

        userService.saveNewlyRegisteredUser(userDto);
        List<Long> userIds = new ArrayList<Long>(){{add(userDto.getUser().getId());}};
        remService.doReminder(userIds, currentTraining);
        attService.writeDownAttByTime(userIds, currentTraining);
        signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
        usersForPick = userService.getUsersForPickOnTraining(signedUsers);
        model.addAttribute("usersForPick", usersForPick);
        model.addAttribute("actTraining", currentTraining);
        model.addAttribute("signedUsers", signedUsers);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("styleAddUser", "none");
        model.addAttribute("showModal", false);

        return "trainingDay";
    }

    @RequestMapping(value = "/removeFromTraining")
    public String removeFromTraining(Model model, @RequestParam("userId") Long userId) {

        if (currentTraining == null)
            currentTraining = trainingService.getActualTraining();

        attService.removeAttendance(userId, currentTraining.getId());
        List<User> signedUsers = userService.getUsersOnCurrentTraining(currentTraining);
        List<User> usersForPick = userService.getUsersForPickOnTraining(signedUsers);

        model.addAttribute("usersForPick", usersForPick);
        model.addAttribute("actTraining", currentTraining);
        model.addAttribute("signedUsers", signedUsers);
        model.addAttribute("styleAddUser", "none");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("showModal", false);
        return "trainingDay";
    }

    @RequestMapping(value = "/redirectToTraining")
    public String redirectToTraining(){
        return "redirect:/web/training";
    }

}


























