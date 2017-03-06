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
public class Fresher extends Candidate {

    public int getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(int graduationDate) throws Exception {//XAC dinh tuoi ra truong toi thieu la 21
        if (graduationDate < 1921) {
            throw new Exception("Graduation Date must be >= 1921");
        }
        if (graduationDate < getBirthDate() + 21) {
            throw new Exception("Graduation Date must be >= BirthDate+21 (" + (getBirthDate() + 21) + ")");
        }
        this.graduationDate = graduationDate;
    }

    public EnumGraduationRank getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(EnumGraduationRank graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = ValidationAndNormalizingText.removeUnnecessaryBlank(university.toUpperCase());
    }
    private int graduationDate;
    private EnumGraduationRank graduationRank;
    private String university;

    public Fresher() {
        super();
        setType(EnumCandidateType.Fresher);
        university = "";
        graduationRank = EnumGraduationRank.Poor;
    }

    @Override
    public void inputInfomation() {
        super.inputInfomation(); //To change body of generated methods, choose Tools | Templates.
        setType(EnumCandidateType.Fresher);
        while (true) {
            try {
                setGraduationDate(ValidationAndNormalizingText.getInt("Enter Graduation Time: ", "Number only!!!", "Out of range!!!", Integer.MIN_VALUE, Integer.MAX_VALUE));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        setUniversity(ValidationAndNormalizingText.getNonEmptyString("Enter University name: "));
        int rank = ValidationAndNormalizingText.getInt("Enter rank (1- Excellence, 2- Good,3- Fair, 4- Poor): ", "Input Number only!!!", "Must be inrange 1 to 4", 1, 4);
        setGraduationRank(rank);
    }

    private void setGraduationRank(int rank) {
        switch (rank) {
            case 1:
                setGraduationRank(EnumGraduationRank.Excellence);
                break;
            case 2:
                setGraduationRank(EnumGraduationRank.Good);
                break;
            case 3:
                setGraduationRank(EnumGraduationRank.Fair);
                break;
            case 4:
                setGraduationRank(EnumGraduationRank.Poor);
                break;
        }
    }
    
}
