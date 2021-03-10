package com.project.app;
import com.project.app.Questionnaire;
import com.project.app.QuestionnaireRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WebController {

    QuestionnaireRepo QRepository;

    @GetMapping("/index")
    public String questionnaire(Model model){
        model.addAttribute("questionnaire",new Questionnaire());
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "/submitQuestionnaire")
    public Questionnaire questionnaireForm(@ModelAttribute Questionnaire ques, Model model){
        System.out.println(ques.getEmail() + ques.getName());
        model.addAttribute(ques);
        QRepository.save(ques);
        return ques;
    }

}