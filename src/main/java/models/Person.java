package models;

import user_utils.UsersSex;

import java.time.LocalDate;
import java.time.Period;

abstract public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate dateOfBirth;
    private int age;
    UsersSex sex;

    public Person() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param dateOfBirth
     * @param sex can either be male, female, m or f. where m = male and f= female
     * @throws Exception for invalid gender input
     */
    public Person(String firstName, String lastName, String address, String dateOfBirth, String sex) throws Exception {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);

        if("male".equals(sex.toLowerCase().trim()) || "m".equals(sex.toLowerCase().trim())){
            this.sex = UsersSex.MALE;
        } else if("female".equals(sex.toLowerCase().trim()) || "f".equals(sex.toLowerCase().trim())){
            this.sex = UsersSex.FEMALE;
        } else{
            throw new Exception("Invalid sex entry");
        }

        //Sets the Persons age off the date of birth;
        LocalDate curDate = LocalDate.now();
        this.age = Period.between(this.dateOfBirth, curDate).getYears();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    //Sets the Persons age off the date of birth;
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        LocalDate curDate = LocalDate.now();
        this.age = Period.between(this.dateOfBirth, curDate).getYears();
    }

    public String getSex() {
        return String.valueOf(sex);
    }

    /**
     *
     * @param sex can either be male, female, m or f. where m = male and f= female
     * @throws Exception for invalid gender entry
     */
    public void setSex(String sex) throws Exception {
        if("male".equals(sex.toLowerCase().trim()) || "m".equals(sex.toLowerCase().trim())){
            this.sex = UsersSex.MALE;
        } else if("female".equals(sex.toLowerCase().trim()) || "f".equals(sex.toLowerCase().trim())){
            this.sex = UsersSex.FEMALE;
        } else{
            throw new Exception("Invalid Sex Entry");
        }
    }


    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", sex=" + sex ;
    }
}
