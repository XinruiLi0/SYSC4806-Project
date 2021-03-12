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


    /**
     * name getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * email getter
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * email setter
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * experienceSymptoms getter
     * @return true if the patient has Symptoms, false if the patient does not have Symptoms
     */
    public boolean isExperienceSymptoms() {
        return experienceSymptoms;
    }

    /**
     * experienceSymptoms setter
     * @param experienceSymptoms
     */
    public void setExperienceSymptoms(boolean experienceSymptoms) {
        this.experienceSymptoms = experienceSymptoms;
    }

    /**
     * remainInResidence getter
     * @return remainInResidence
     */
    public boolean isRemainInResidence() {
        return remainInResidence;
    }

    /**
     * remainInResidence setter
     * @param remainInResidence
     */
    public void setRemainInResidence(boolean remainInResidence) {
        this.remainInResidence = remainInResidence;
    }

    /**
     * needSupport getter
     * @return needSupport
     */
    public boolean isNeedSupport() {
        return needSupport;
    }

    /**
     * needSupport setter
     * @param needSupport
     */
    public void setNeedSupport(boolean needSupport) {
        this.needSupport = needSupport;
    }

    /**
     * supportType getter
     * @return supportType
     */
    public String getSupportType() {
        return supportType;
    }

    /**
     * supportType setter
     * @param supportType
     */
    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }


    /**
     * constructor
     * @param name
     * @param email
     * @param remainInResidence
     * @param needSupport
     * @param supportType
     * @param experienceSymptoms
     */
    public Questionnaire(String name, String email, boolean remainInResidence, boolean needSupport, String supportType, boolean experienceSymptoms ){
        this.name = name;
        this.email = email;
        this.remainInResidence = remainInResidence;
        this.needSupport = needSupport;
        this.experienceSymptoms = experienceSymptoms;
        this.supportType = supportType;
    }

    /**
     * default constructor
     */
    public Questionnaire(){
//        this("unknown","unknown",true,false,null,"none");
    }


    /**
     * Id getter
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * needSupport getter, if needSupport = false, the supportType would become null
     * @return needSupport
     */
    public Boolean ifNeedSupport(){
        if(!needSupport){
            this.setSupportType(null);
            return false;
        }
        return true;
    }


    /**
     * toString method
     * @return String
     */
    public String toString(){
         String s = "Name: " + name + "\n" + " Support Type: ";
        if(ifNeedSupport()){
            s += supportType +"\n";
        }else{
            s += "no need support \n";
        }

        s += " If experiencing Symptoms: ";

        if(experienceSymptoms){
            s += "yes \n" ;
        }else{
            s += " no symptoms \n";
        }

        return s;
    }
}
