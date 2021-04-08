package Web;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;


import java.sql.*;
import java.util.Collection;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
* Test for WebController Class(Milestone #2)
* @author Zewen
* @version #2
* */

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://ivmsdb.cs17etkshc9t.us-east-1.rds.amazonaws.com;DatabaseName=sysc4806";
    String userName = "admin";
    String userPwd = "ivmsdbadmin";

    Model model;
    @Mock
    QuestionnaireRepo QR;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getQuestionnaireTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/questionnaire");
        mvc.perform(request).andDo(print()).andExpect(content().string(containsString("COVID-19 Questionaire")));
    }

    @Test
    public void repoEamilSearchTest() {
        Questionnaire ques = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
        QR.save(ques);
        Questionnaire result = QR.findByEmail("jake@gmail.com");
        assertEquals("Jake",ques.getName());
    }

    @Test
    public void getResultTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/result");
        mvc.perform(request).andDo(print()).andExpect(content().string(equalTo(
                "")));
    }


    @Test
    public void showResultTest() throws SQLException {
        ResultSet rs =  SQLSetUp();
        WebController webController = new WebController();
        String testResult = webController.showResult("mmm");
        System.out.print(testResult);
        assertEquals(testResult,"Name: zzz\n" +
                "Email: mmm\n" +
                "Remain in residence: Yes\n" +
                "If need support: Yes\n" +
                "Needed support type: hi\n" +
                "If experiencing Symptoms No\n");
    }

  /*  @Test
    public  void showResultTest() throws SQLException {
    // this is a private method it has been tested by the  showResult()
    }
  */

    @Test
    public void  convertToStringTest() throws SQLException {
        ResultSet rs =  SQLSetUp();
        WebController webController = new WebController();
        String testResult = webController.convertToString(rs);
        assertEquals(testResult,"Name: zzz\n" +
                "Email: mmm\n" +
                "Remain in residence: Yes\n" +
                "If need support: Yes\n" +
                "Needed support type: hi\n" +
                "If experiencing Symptoms No\n");
    }

    //helper function that build up connection with database
    private ResultSet SQLSetUp() throws SQLException {
        Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Questionnaire where email='mmm'");
        return resultSet;
    }

}