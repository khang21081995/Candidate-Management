/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BO.ManageCandidate;
import BO.ValidationAndNormalizingText;
import Entity.Candidate;
import Entity.EnumCandidateType;
import com.sun.glass.events.DndEvent;

/**
 *
 * @author khang
 */
public class Main {

    public static void main(String[] args) {
        ManageCandidate mngc = new ManageCandidate();
        do {
            System.out.println("======CANDIDATE MANAGEMENT SYSTEM======");
            String mess = "1. Experience\n"
                    + "2. Fresher\n"
                    + "3. Internship\n"
                    + "4. Searching\n"
                    + "5. Exit\n"
                    + "(Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate, 3 to Internship Candidate, 4 to Searching and 5 to Exit program).\nYour choice: ";

            int choice = ValidationAndNormalizingText.getInt(mess, "Number from 1 to 5 only!!!", "Input number between 1 and 5", 1, 5);
            switch (choice) {
                case 1:
                    mngc.addCandidateMenu(EnumCandidateType.Experience);

                    break;
                case 2:
                    mngc.addCandidateMenu(EnumCandidateType.Fresher);
                    break;
                case 3:
                    mngc.addCandidateMenu(EnumCandidateType.Intern);
                    break;
                case 4:
                    if (mngc.getCandidateList().isEmpty()) {
                        System.err.println("Database is empty!!!");
                        break;
                    }
                    mngc.displayAllCandidate();
                    mngc.searchAndDisplay(ValidationAndNormalizingText.getStringByRegex("Input Candidate name (First name or Last name): ", "Character only!!!", "[A-Za-z ]+"),
                            ValidationAndNormalizingText.getInt("Input type of candidate: ", "Number only", "Enter number in range 0 to 2 ", 0, 2));
                    break;
                case 5:
                    System.out.println("Bye bye");
                    System.exit(0);

            }
            if (choice != 4) {
                mngc.displayAllCandidate();
            }
            System.out.println("\n\n");
        } while (true);
    }
}
