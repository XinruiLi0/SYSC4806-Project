package Web;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

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

}