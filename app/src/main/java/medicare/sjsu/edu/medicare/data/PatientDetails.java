package medicare.sjsu.edu.medicare.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Shriaithal on 4/9/2018.
 */

public class PatientDetails implements Serializable{

    String policyNbr;
    String firstName;
    String lastName;
    String sex;
    Integer age;
    Integer weight;
    Integer height;
    List<PatientDetailLines> patientDetails;

    public String getPolicyNbr() {
        return policyNbr;
    }

    public void setPolicyNbr(String policyNbr) {
        this.policyNbr = policyNbr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<PatientDetailLines> getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(List<PatientDetailLines> patientDetails) {
        this.patientDetails = patientDetails;
    }
}
