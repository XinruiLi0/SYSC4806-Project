package app;

import app.Questionnaire;
import org.junit.Test;
import org.testng.Assert;

public class QuestionnaireTest {

    @Test
    public void InitializationQuestionnaire(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
        Assert.assertEquals(q.getName(),"Jake");
        Assert.assertEquals(q.getEmail(),"jake@gmail.com");
        Assert.assertEquals(q.isRemainInResidence(),false);
        Assert.assertEquals(q.isNeedSupport(),false);
        Assert.assertEquals(q.isExperienceSymptoms(),false);
    }

    @Test
    public void ifNeedSupportTest(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,"food",false);
        Assert.assertFalse(q.ifNeedSupport());
        Assert.assertEquals(q.getSupportType(),null);

        Questionnaire q1 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",false);
        Assert.assertTrue(q1.ifNeedSupport());
        Assert.assertEquals(q1.getSupportType(),"food");
    }

    @Test
    public void toStringTest(){
        Questionnaire q = new Questionnaire("Jake","jake@gmail.com",false,false,null,false);
        String str = "Name: " + "Jake" + "\n" + " Support Type: "+ "no need support \n" + " If experiencing Symptoms: " + " no symptoms \n";
        Assert.assertEquals(q.toString(), str);

        Questionnaire q1 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",false);
        String str1 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + " no symptoms \n";
        Assert.assertEquals(q1.toString(), str1);

        Questionnaire q2 = new Questionnaire("Jake","jake@gmail.com",false,true,"food",true);
        String str2 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + "yes \n";
        Assert.assertEquals(q2.toString(), str2);

        Questionnaire q3 = new Questionnaire("Jake","jake@gmail.com",true,true,"food",true);
        String str3 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + "yes \n";
        Assert.assertEquals(q3.toString(), str3);

        Questionnaire q4 = new Questionnaire("Jake","jake@gmail.com",true,true,"food",false);
        String str4 = "Name: " + "Jake" + "\n" + " Support Type: "+ "food\n" + " If experiencing Symptoms: " + " no symptoms \n";
        Assert.assertEquals(q4.toString(), str4);

        Questionnaire q5 = new Questionnaire("Jake","jake@gmail.com",true,false,null,false);
        String str5 = "Name: " + "Jake" + "\n" + " Support Type: "+ "no need support \n" + " If experiencing Symptoms: " + " no symptoms \n";
        Assert.assertEquals(q5.toString(), str5);
    }
}
