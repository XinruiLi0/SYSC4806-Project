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
        model.addAttribute("Questionnaire",new Questionnaire());
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "/index")
    public Questionnaire questionnaireForm(@ModelAttribute Questionnaire ques, Model model){
        Questionnaire q = new Questionnaire();
        q = ques;
        System.out.println(q.getEmail() + q.getName());
        model.addAttribute(q);
        //QRepository.save(q);
        return q;
    }

}