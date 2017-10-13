package cz.eshop.controller;

import cz.eshop.dao.FeedbackDao;
import cz.eshop.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackDao feedbackDao;

    @RequestMapping("/addFeedback")
    public String addFeedBack(Model model, @ModelAttribute Feedback feedback) {

        if (feedback != null) {
            feedbackDao.save(feedback);
        }
        return "";
    }

    @RequestMapping("/feedbackList")
    public String feedBackList(Model model) {
        model.addAttribute("feedbacks", feedbackDao.findAll());
        return "feedback";
    }

    @RequestMapping("/removeFeed")
    public String feedBackList(Model model, @RequestParam Long id) {
        feedbackDao.delete(id);
        return "feedback";
    }
}
