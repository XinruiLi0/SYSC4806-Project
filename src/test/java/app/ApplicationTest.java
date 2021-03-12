package app;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getQuestionnaireTest() throws Exception {
        this.mvc.perform(get("/questionnaire")).andDo(print()).andExpect(content().string(containsString("COVID-19 Questionaire")));
    }
//
//    @Test
//    public void getQuestionnaireTest() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/questionnaire");
//        mvc.perform(request).andDo(print()).andExpect(content().string(containsString("COVID-19 Questionaire")));
//
//    }
//
//    @Test
//    public void postQuestionnaireTest() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.post("/questionnaire");
//        mvc.perform(request).andDo(print()).andExpect(content().string(equalTo(
//                "{\"id\":1,\"remainInResidence\":false,\"needSupport\":false,\"experienceSymptoms\":false,\"supportType\":null,\"name\":null,\"email\":null}")));
//    }

//    @Test
//    public void repoEamilSearchTest() {
//        Questionnaire ques = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
//        QR.save(ques);
//        Questionnaire result = QR.findByEmail("jake@gmail.com");
//        assertEquals("Jake",ques.getName());
//    }

//    @Test
//    public void getResultTest() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.post("/result");
//        mvc.perform(request).andDo(print()).andExpect(content().string(equalTo(
//                "")));
//    }

}