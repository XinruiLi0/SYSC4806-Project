package Web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
public class QuestionnaireTest {

    @Test
    public void InitializationQuestionnaire(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
        assertEquals(q.getName(),"Jake");
        assertEquals(q.getEmail(),"jake@gmail.com");
        assertEquals(q.isRemainInResidence(),false);
        assertEquals(q.isNeedSupport(),false);
        assertEquals(q.isExperienceSymptoms(),false);
    }

    @Test
    public void ifNeedSupportTest(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,"food",false);
        assertFalse(q.ifNeedSupport());
        assertEquals(q.getSupportType(),null);

        Questionnaire q1 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",false);
        assertTrue(q1.ifNeedSupport());
        assertEquals(q1.getSupportType(),"food");
    }

    @Test
    public void toStringTest(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
        String str = "Name: " + "Jake" + "\n" + " Support Type: "+ "no need support \n" + " If experiencing Symptoms: " + " no symptoms \n";
        assertEquals(q.toString(), str);

        Questionnaire q1 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",false);
        String str1 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + " no symptoms \n";
        assertEquals(q1.toString(), str1);

        Questionnaire q2 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",true);
        String str2 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + "yes \n";
        assertEquals(q2.toString(), str2);

        Questionnaire q3 = new Questionnaire("Jake","jake@gmail.com",true,true,"food",true);
        String str3 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + "yes \n";
        assertEquals(q3.toString(), str3);

        Questionnaire q4 = new Questionnaire("Jake","jake@gmail.com",true,true,"food",false);
        String str4 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + " no symptoms \n";
        assertEquals(q4.toString(), str4);

        Questionnaire q5 = new Questionnaire("Jake","jake@gmail.com",true,false,null,false);
        String str5 = "Name: " + "Jake" + "\n" + " Support Type: "+ "no need support \n" + " If experiencing Symptoms: " + " no symptoms \n";
        assertEquals(q5.toString(), str5);
    }
}
