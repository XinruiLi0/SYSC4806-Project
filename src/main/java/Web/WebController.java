package Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;


@Controller
public class WebController {

    @Autowired
    QuestionnaireRepo QRepo;

    @GetMapping("/questionnaire")
    public String questionnaire(Model model) {
        model.addAttribute("Questionnaire", new Questionnaire());
        return "questionnaire";
    }

    @ResponseBody
    @PostMapping(value = "/questionnaire")
    public Questionnaire questionnaireForm(@ModelAttribute Questionnaire ques, Model model) {
        model.addAttribute(ques);
        QRepo.save(ques);
        return ques;
    }


    @GetMapping("/result")
    @ResponseBody
    public String showResult(String email) {
        Questionnaire q = QRepo.findByEmail(email);
        return q.toString();
    }

    /**
     * Connect the database and execute the query
     * @param query the sql query
     * @param isUpdate true if it updates the data, false if it just queries
     * @return return result set if the action is query, null if it just update the data.
     */
    private static ResultSet executeSQL(String query, boolean isUpdate) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://174.115.241.123;DatabaseName=project";
        String userName = "admin";
        String userPwd = "admin";

        ResultSet res = null;
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
            String sql = query;
            Statement statement = conn.createStatement();
            if (isUpdate) {
                statement.executeUpdate(sql);
            } else {
                res = statement.executeQuery(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

//    public static void main(String[] args) {
//        executeSQL("select * from Questionnaire", false);
//        // executeSQL("insert into Questionnaire (EemainInResidence, EeedSupport, ExperienceSymptoms, SupportType, Name, Email) values (0, 0, 0, 'b', 'b', 'b')", true);
//    }
}