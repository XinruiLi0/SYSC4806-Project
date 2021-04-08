package Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class WebController {

    @Autowired
    QuestionnaireRepo QRepo;

    JavaEmailUnit emailUnit = new JavaEmailUnit();
    
    /**
     * initialize questionnaire
     * @param model
     * @return
     */
    @GetMapping("/questionnaire")
    public String questionnaire(Model model) {
        model.addAttribute("Questionnaire", new Questionnaire());
        return "questionnaire";
    }

    public boolean emailValidate(String email){
        String regex = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
    /**
     *
     * @param ques
     * @param model
     * @throws SQLException
     */
    @ResponseBody
    @PostMapping(value = "/questionnaire")
    public void questionnaireForm(@ModelAttribute Questionnaire ques, Model model) throws Exception {
        model.addAttribute(ques);
        String email = ques.getEmail();
        if(!emailValidate(email)){
            System.out.println("Wrong Email Format");
            return;
        }else {
            String query = "select * from Questionnaire where email = '" + email + "'";
            int inres = ques.isRemainInResidence() == true ? 1 : 0;
            int isexp = ques.isExperienceSymptoms() == true ? 1 : 0;
            int inned = ques.isNeedSupport() == true ? 1 : 0;
            String updateQuery = "update Questionnaire set RemainInResidence = '" + inres + "'," + "NeedSupport = '" + inned + "'," + "ExperienceSymptoms = '" + isexp + "'," + "SupportType ='" + ques.getSupportType() + "' where email = '" + email + "'";
            try {
                if (executeSQL(query, false).next()) {
                    executeSQL(updateQuery, true);
                } else {

                    String first = "INSERT INTO Questionnaire (RemainInResidence, NeedSupport, ExperienceSymptoms, SupportType, Name, Email) VALUES (";
                    String second = first + inres + "," + inned + "," + isexp + ", '" + ques.getSupportType() + "','" + ques.getName() + "','" + ques.getEmail() + "')";
                    executeSQL(second, true);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            emailUnit.sendEmail(email);
        }
    }

    /**
     * retrieve record from database
     * @param email
     * @return
     * @throws SQLException
     */
    @GetMapping("/result")
    @ResponseBody
    public String showResult(String email) {
        String query = "select * from Questionnaire where email = '"+ email+"'";
        ResultSet rs = executeSQL(query,false);

        String result = convertToString(rs);
        return result;

    }

    /**
     * Connect the database and execute the query
     * @param query the sql query
     * @param isUpdate true if it updates the data, false if it just queries
     * @return return result set if the action is query, null if it just update the data.
     */
    private static ResultSet executeSQL(String query, boolean isUpdate) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://ivmsdb.cs17etkshc9t.us-east-1.rds.amazonaws.com;DatabaseName=sysc4806";
        String userName = "admin";
        String userPwd = "ivmsdbadmin";

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
    
    /**
     * convert database record to String
     * @param rs
     * @return
     * @throws SQLException
     */
    public String convertToString(ResultSet rs)  {
        String remainInResidence = "Remain in residence: ";
        String needSupport = "If need support: ";
        String supportType = "Needed support type: ";
        String experienceSymptoms = "If experiencing Symptoms ";
        String name = "Name: ";
        String email = "Email: ";
        boolean ifNeedSupport = false;
        try {
            while (rs.next()) {

                name += rs.getString("Name") + "\n";
                email += rs.getString("Email") + "\n";
                if (!rs.getBoolean("RemainInResidence")) {
                    remainInResidence += "No\n";
                } else {
                    remainInResidence += "Yes\n";
                }
                if (!rs.getBoolean("NeedSupport")) {
                    needSupport += "No\n";
                } else {
                    needSupport += "Yes\n";
                    ifNeedSupport = true;
                }
                if (rs.getString("SupportType") != null) {
                    supportType += rs.getString("SupportType") + "\n";
                }
                if (!rs.getBoolean("ExperienceSymptoms")) {
                    experienceSymptoms += "No\n";
                } else {
                    experienceSymptoms += "Yes\n";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(ifNeedSupport){
            return name + email + remainInResidence + needSupport +supportType + experienceSymptoms;
        }else{
            return name + email + remainInResidence + needSupport + experienceSymptoms;
        }
    }


}
