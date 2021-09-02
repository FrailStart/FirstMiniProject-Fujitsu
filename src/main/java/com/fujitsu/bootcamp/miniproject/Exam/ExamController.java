package com.fujitsu.bootcamp.miniproject.Exam;

import com.fujitsu.bootcamp.miniproject.User.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExamController {
    @Autowired
    private final ExamService examService;

    ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping(value = "/generateexam")
    public String generateExam(Model model, @ModelAttribute("user") UserModel user,
            final RedirectAttributes redirectAttributes) {
        if (user.getId() != 0) {
            model.addAttribute("question", examService.generateQuestion());
            model.addAttribute("answer", new AnswerSaver());
            
            return "exam";
        } else {
            return "redirect:/";
        }
    }

    

    @RequestMapping("/getresult")
    public String generateExamResult(Model model, @ModelAttribute(name = "answer") AnswerSaver answer,@ModelAttribute("user")UserModel user) {
        if (answer.getAnswer1() != null) {

            
            String resulTotal = Math.round(examService.getScore(answer)) + "%";
            String resultString = examService.getScore(answer) > 69 ? "Contgratulations!" : "Sorry better luck next time.";

            user.setId(1);
            model.addAttribute("result", resulTotal );
            model.addAttribute("user", user);
            model.addAttribute("resultStr", resultString);


            return "results";
        } else {
            return "redirect:/";
        }
    }

    

}
