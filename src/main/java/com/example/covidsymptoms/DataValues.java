package com.example.covidsymptoms;

public class DataValues {
    Integer HEART_RATE;
    Integer RESPIRATORY_RATE;
    float HEADACHE;
    float DIARRHEA;
    float SORE_THROAT;
    float FEVER;
    float MUSCLE_ACHE;
    float LOSS_SMELL_TASTE;
    float COUGH;
    float SHORTNESS_BREATH;
    float FEELING_TIRED;
    float NAUSEA;
    String USER_NAME;

    public DataValues(Integer HEART_RATE, Integer RESPIRATORY_RATE, float HEADACHE, float DIARRHEA, float SORE_THROAT, float FEVER, float MUSCLE_ACHE, float LOSS_SMELL_TASTE, float COUGH, float SHORTNESS_BREATH, float FEELING_TIRED, float NAUSEA, String USER_NAME) {
        this.HEART_RATE = HEART_RATE;
        this.RESPIRATORY_RATE = RESPIRATORY_RATE;
        this.HEADACHE = HEADACHE;
        this.DIARRHEA = DIARRHEA;
        this.SORE_THROAT = SORE_THROAT;
        this.FEVER = FEVER;
        this.MUSCLE_ACHE = MUSCLE_ACHE;
        this.LOSS_SMELL_TASTE = LOSS_SMELL_TASTE;
        this.COUGH = COUGH;
        this.SHORTNESS_BREATH = SHORTNESS_BREATH;
        this.FEELING_TIRED = FEELING_TIRED;
        this.NAUSEA = NAUSEA;
        this.USER_NAME = USER_NAME;
    }

    public Integer getHEART_RATE() {
        return HEART_RATE;
    }

    public void setHEART_RATE(Integer HEART_RATE) {
        this.HEART_RATE = HEART_RATE;
    }

    public Integer getRESPIRATORY_RATE() {
        return RESPIRATORY_RATE;
    }

    public void setRESPIRATORY_RATE(Integer RESPIRATORY_RATE) {
        this.RESPIRATORY_RATE = RESPIRATORY_RATE;
    }

    public float getHEADACHE() {
        return HEADACHE;
    }

    public void setHEADACHE(float HEADACHE) {
        this.HEADACHE = HEADACHE;
    }

    public float getDIARRHEA() {
        return DIARRHEA;
    }

    public void setDIARRHEA(float DIARRHEA) {
        this.DIARRHEA = DIARRHEA;
    }

    public float getSORE_THROAT() {
        return SORE_THROAT;
    }

    public void setSORE_THROAT(float SORE_THROAT) {
        this.SORE_THROAT = SORE_THROAT;
    }

    public float getFEVER() {
        return FEVER;
    }

    public void setFEVER(float FEVER) {
        this.FEVER = FEVER;
    }

    public float getMUSCLE_ACHE() {
        return MUSCLE_ACHE;
    }

    public void setMUSCLE_ACHE(float MUSCLE_ACHE) {
        this.MUSCLE_ACHE = MUSCLE_ACHE;
    }

    public float getLOSS_SMELL_TASTE() {
        return LOSS_SMELL_TASTE;
    }

    public void setLOSS_SMELL_TASTE(float LOSS_SMELL_TASTE) {
        this.LOSS_SMELL_TASTE = LOSS_SMELL_TASTE;
    }

    public float getCOUGH() {
        return COUGH;
    }

    public void setCOUGH(float COUGH) {
        this.COUGH = COUGH;
    }

    public float getSHORTNESS_BREATH() {
        return SHORTNESS_BREATH;
    }

    public void setSHORTNESS_BREATH(float SHORTNESS_BREATH) {
        this.SHORTNESS_BREATH = SHORTNESS_BREATH;
    }

    public float getFEELING_TIRED() {
        return FEELING_TIRED;
    }

    public void setFEELING_TIRED(float FEELING_TIRED) {
        this.FEELING_TIRED = FEELING_TIRED;
    }

    public float getNAUSEA() {
        return NAUSEA;
    }

    public void setNAUSEA(float NAUSEA) {
        this.NAUSEA = NAUSEA;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }
}
