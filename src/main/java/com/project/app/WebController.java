package com.project.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WebController {

    @Autowired
    QuestionnaireRepo QRepo;

    @GetMapping("/questionnaire")
    public String questionnaire(Model model){
        model.addAttribute("Questionnaire",new Questionnaire());
        return "questionnaire";
    }

    @ResponseBody
    @PostMapping(value = "/questionnaire")
    public Questionnaire questionnaireForm(@ModelAttribute Questionnaire ques, Model model){
        Questionnaire q = ques;
        System.out.println(q.getEmail() + q.getName());
        model.addAttribute(q);
        QRepo.save(q);
        return q;
    }

}