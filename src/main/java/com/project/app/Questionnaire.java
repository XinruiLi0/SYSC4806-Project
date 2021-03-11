package com.project.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean remainInResidence;
    private boolean needSupport;
    private boolean experienceSymptoms;
    private String supportType;
    private String name;
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExperienceSymptoms() {
        return experienceSymptoms;
    }

    public void setExperienceSymptoms(boolean experienceSymptoms) {
        this.experienceSymptoms = experienceSymptoms;
    }

    public boolean isRemainInResidence() {
        return remainInResidence;
    }

    public void setRemainInResidence(boolean remainInResidence) {
        this.remainInResidence = remainInResidence;
    }

    public boolean isNeedSupport() {
        return needSupport;
    }

    public void setNeedSupport(boolean needSupport) {
        this.needSupport = needSupport;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }





    public Questionnaire(String name, String email, boolean remainInResidence, boolean needSupport,String supportType, boolean experienceSymptoms ){
        this.name = name;
        this.email = email;
        this.remainInResidence = remainInResidence;
        this.needSupport = needSupport;
        this.experienceSymptoms = experienceSymptoms;
        this.supportType = supportType;
    }

    public Questionnaire(){
//        this("unknown","unknown",true,false,null,"none");
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean ifNeedSupport(){
        if(!needSupport){
            this.setSupportType(null);
            return false;
        }
        return true;
    }



    public String toString(){
         String s = "Name: " + name + " Support Type: ";
        if(ifNeedSupport()){
            s += supportType;
        }else{
            s += "no need support";
        }

        s += " If experiencing Symptoms: ";

        if(experienceSymptoms){
            s += "yes";
        }else{
            s += " no symptoms \n";
        }

        return s;
    }
}
