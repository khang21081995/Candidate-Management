/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import BO.ValidationAndNormalizingText;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khang
 */
public class Experience extends Candidate {

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) throws Exception {
        int maxExpInYear = LocalDateTime.now().getYear() - super.getBirthDate();
        if (expInYear < 0 || expInYear > 100) {
            throw new Exception("Year of experience must be >= 0 and <=100");
        }
        if (maxExpInYear < expInYear) {
            throw new Exception("Year of experience must be <= Age(" + maxExpInYear + ")");
        }
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String ProSkill) {
        this.proSkill = ValidationAndNormalizingText.removeUnnecessaryBlank(ProSkill);
    }
    private int expInYear;
    private String proSkill;

    public Experience() {
        super();
        setType(EnumCandidateType.Experience);
        proSkill = "";
        expInYear = 0;
    }

    @Override
    public void inputInfomation() {
        super.inputInfomation();
        while (true) {
            try {
                setExpInYear(ValidationAndNormalizingText.getInt("Enter year of experience: ", "Number only!", "Out of Range", Integer.MIN_VALUE, Integer.MAX_VALUE));
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        setProSkill(ValidationAndNormalizingText.getNonEmptyString("Enter Pro skill: "));
        super.setType(EnumCandidateType.Experience);
    }

    @Override
    public void display() {
        super.display(); //To change body of generated methods, choose Tools | Templates.

    }

}
