package app;

import com.project.app.Questionnaire;
import com.project.app.QuestionnaireRepo;
import com.project.app.WebController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.AssertJUnit.assertEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ApplicationTest {

    @InjectMocks
    WebController webController;

    @Mock
    QuestionnaireRepo QR;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Autowired
    private MockMvc mvc;

    @Test
    public void getQuestionnaireTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/questionnaire");
        mvc.perform(request).andDo(print()).andExpect(content().string(containsString("COVID-19 Questionaire")));

    }

    @Test
    public void postQuestionnaireTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/questionnaire");
        mvc.perform(request).andDo(print()).andExpect(content().string(equalTo(
                "{\"id\":1,\"remainInResidence\":false,\"needSupport\":false,\"experienceSymptoms\":false,\"supportType\":null,\"name\":null,\"email\":null}")));
    }

    @Test
    public void Repo_Eamil_search_Test() {
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
}
