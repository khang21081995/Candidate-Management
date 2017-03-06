/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import BO.ValidationAndNormalizingText;

/**
 *
 * @author khang
 */
public class Intern extends Candidate {

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = ValidationAndNormalizingText.normalFormStringAfterDot(majors);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) throws Exception {
        if (semester < 0 || semester > 21) {
            throw new Exception("Semester must be between 0 and 21");
        }
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = ValidationAndNormalizingText.removeUnnecessaryBlank(universityName.toUpperCase());
    }

    private String majors;
    private int semester;
    private String universityName;

    public Intern() {
        super();
        majors = "";
        universityName = "";
        semester = 0;

    }

    @Override
    public void inputInfomation() {
        super.inputInfomation(); //To change body of generated methods, choose Tools | Templates.
        setMajors(ValidationAndNormalizingText.getNonEmptyString("Enter majors: "));
        while (true) {
            try {
                setSemester(ValidationAndNormalizingText.getInt("Enter Semeter: ", "Number only", "Out of range", Integer.MIN_VALUE, Integer.MAX_VALUE));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        setUniversityName(ValidationAndNormalizingText.getNonEmptyString("Enter University Name: "));
    }

}
