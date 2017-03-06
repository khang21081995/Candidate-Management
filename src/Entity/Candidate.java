/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import BO.ValidationAndNormalizingText;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khang
 */
public class Candidate {

    public int getID() {
        return ID;
    }

    public void setID(int ID) throws Exception {
        if (ID < 0) {
            throw new Exception("ID Must be >= 0");
        }
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = ValidationAndNormalizingText.normalFormName(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = ValidationAndNormalizingText.normalFormName(lastName);
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if (birthDate < 1900 || birthDate > now.getYear()) {
            throw new Exception("Birth date must be >= 1900 and <= " + now.getYear());
        }
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = ValidationAndNormalizingText.normalFormStringAfterDot(address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = ValidationAndNormalizingText.removeAllBlank(phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumCandidateType getType() {
        return type;
    }

    public void setType(EnumCandidateType type) {
        this.type = type;
    }

    public int getTypeInt() {
        switch (type) {
            case Experience:
                return 0;
            case Fresher:
                return 1;
            case Intern:
                return 2;
            default:
                return -1;
        }
    }

    public void setType(int type) {
        switch (type) {
            case 0:
                this.type = EnumCandidateType.Experience;
                break;
            case 1:
                this.type = EnumCandidateType.Fresher;
                break;
            case 2:
                this.type = EnumCandidateType.Intern;
                break;
            case -1:
                this.type = EnumCandidateType.Candidate;
                break;
        }
    }

    private int ID;
    private String firstName;
    private String lastName;
    private int birthDate;
    private String address;
    private String phone;
    private String email;
    private EnumCandidateType type;

    public Candidate() {
        ID = 0;
        firstName = "";
        lastName = "";
        birthDate = 1900;
        address = "";
        phone = "";
        email = "";
        type = EnumCandidateType.Intern;
    }

    public void inputInfomation() {
        ValidationAndNormalizingText val = new ValidationAndNormalizingText();
        setFirstName(ValidationAndNormalizingText.getStringByRegex("Enter First Name: ", "Please enter character only!", "[A-Za-z ]+"));
        setLastName(ValidationAndNormalizingText.getStringByRegex("Enter Last Name: ", "Please enter character only!", "[A-Za-z ]+"));
        try {
            setBirthDate(ValidationAndNormalizingText.getInt("Enter Birth Date: ", "Input number only!", "It must be >= 1900 and <= " + LocalDateTime.now().getYear(), 1900, LocalDateTime.now().getYear()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        setAddress(ValidationAndNormalizingText.getNonEmptyString("Enter Address: "));
        setPhone(val.getPhone(10, "Enter phone: "));
        setEmail(val.getEmail("Enter email: "));
        setType(EnumCandidateType.Intern);
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-4d | %-15s | %-15s | %-20s | %-10s", firstName + " " + lastName, birthDate, address, phone, email, type);
//     return firstName + " " + lastName + " | " + birthDate + " | " + address + " | " + phone + " | " + email + " | " + type.name();
    }

    public void display() {
        System.out.println(firstName + " " + lastName);
    }

}
