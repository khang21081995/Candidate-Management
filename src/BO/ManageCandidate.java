/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Entity.Candidate;
import Entity.EnumCandidateType;
import Entity.Experience;
import Entity.Fresher;
import Entity.Intern;
import java.util.ArrayList;

/**
 *
 * @author khang
 */
public class ManageCandidate {

    public int getMaxID() {
        return maxID;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    private ArrayList< Candidate> candidateList;

    public ArrayList<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(ArrayList<Candidate> candidateList) {
        this.candidateList = candidateList;
    }
    private int maxID;

    public ManageCandidate() {
        candidateList = new ArrayList<>();
        maxID = 0;

    }

    public boolean addCandidate(EnumCandidateType type) {
        Candidate can;
        switch (type) {
            case Experience:
                can = new Experience();
                break;
            case Fresher:
                can = new Fresher();
                break;
            case Intern:
                can = new Intern();
                break;
            default:
                can = new Candidate();
        }

        try {
            can.inputInfomation();
            can.setID(maxID);
            maxID++;
            return candidateList.add(can);
        } catch (Exception ex) {

            return false;
        }
    }

    public void addCandidateMenu(EnumCandidateType type) {

        do {
            System.out.println("Adding candidate ID = " + getMaxID());
            if (addCandidate(type)) {
                System.out.println("Adding SUCCESSFULLY!!!");
            } else {
                System.err.println("Adding FAILED!!!");
            }
        } while (ValidationAndNormalizingText.pressYNtoContinue("Do you want to continue (Y/N): "));

    }

    private void displayByType(EnumCandidateType type) {
        for (Candidate candidate : candidateList) {
            if (candidate.getType() == type) {
                candidate.display();
            }
        }
    }

    public void displayAllCandidate() {
        if (candidateList.isEmpty()) {
            System.err.println("Database is empty!!!");
            return;
        }
        System.out.println("List of candidate:");
        System.out.println("===========EXPERIENCE CANDIDATE============");
        displayByType(EnumCandidateType.Experience);
        System.out.println("==========FRESHER CANDIDATE==============");
        displayByType(EnumCandidateType.Fresher);
        System.out.println("===========INTERN CANDIDATE==============");
        displayByType(EnumCandidateType.Intern);
    }

    public void searchAndDisplay(String searchKey, int type) {
        if (candidateList.isEmpty()) {
            System.err.println("Database is empty!!!");
            return;
        }
        int cout = 0;

        System.out.println("The candidates found:");
        System.out.println(String.format("%-20s | %-4s | %-15s | %-15s | %-20s | %-10s", "Full name", "DOB", "Address", "Phone", "Email", "Type"));
        for (Candidate candidate : candidateList) {
            if (candidate.toString().toLowerCase().contains(searchKey.toLowerCase()) && candidate.getTypeInt() == type) {
                System.out.println(candidate.toString());
                cout++;
            }
        }
        if (cout == 0) {
            System.out.println("No one found!!!");
        }
    }

}
