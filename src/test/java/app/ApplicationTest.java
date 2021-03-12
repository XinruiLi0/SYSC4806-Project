package com.project.app;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


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
    //this can be used for further improvement

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
    public void postQuestionnaireTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/questionnaire");
        mvc.perform(request).andDo(print()).andExpect(content().string(equalTo(
                "{\"id\":1,\"remainInResidence\":false,\"needSupport\":false,\"experienceSymptoms\":false,\"supportType\":null,\"name\":null,\"email\":null}")));
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

}