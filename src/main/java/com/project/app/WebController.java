package com.project.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WebController {

    @Autowired
    QuestionnaireRepo QRepo;

    /**
     * initialize Questionnaire
     * @param model
     * @return
     */
    @GetMapping("/questionnaire")
    public String questionnaire(Model model){
        model.addAttribute("Questionnaire",new Questionnaire());
        return "questionnaire";
    }

    /**
     * save
     * @param ques
     * @param model
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/questionnaire")
    public Questionnaire questionnaireForm(@ModelAttribute Questionnaire ques, Model model){
        model.addAttribute(ques);
        QRepo.save(ques);
        return ques;
    }

    /**
     * search email and send corresponding information
     * @param email
     * @return Questionnaire toString
     */
    @GetMapping("/result")
    @ResponseBody
    public String showResult(String email){
        Questionnaire q = QRepo.findByEmail(email);
        return q.toString();
    }

}