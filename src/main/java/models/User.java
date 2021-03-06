package models;

import user_utils.UserPosition;

public class User extends Person{
    private long libraryId;
    private UserPosition position;


    public User() {

    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param dateOfBirth
     * @param sex
     * @param libraryId
     * @param position position can either be "teacher", "junior_student", or "senior_student"
     * @throws Exception for invalid position entry
     */
    public User(String firstName, String lastName, String address, String dateOfBirth, String sex, long libraryId, String position) throws Exception {
        super(firstName, lastName, address, dateOfBirth, sex);
        this.libraryId = libraryId;

        if("teacher".equals(position.toLowerCase().trim())){
            this.position = UserPosition.TEACHER;
        }else if("senior_student".equals(position.toLowerCase().trim())){
            this.position = UserPosition.SENIOR_STUDENT;
        } else if("junior_student".equals(position.toLowerCase().trim())){
            this.position = UserPosition.JUNIOR_STUDENT;
        }
        else{
            throw new Exception("Invalid Position Entry");
        }

    }

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public String getPosition() {
        return String.valueOf(position);
    }

    /**
     * Sets the position of the user
     * @param position can either be "teacher", "junior_student", or "senior_student"
     * @throws Exception for invalid position entry
     */
    public void setPosition(String position) throws Exception {
        if("teacher".equals(position.toLowerCase().trim())){
            this.position = UserPosition.TEACHER;
        }else if("senior_student".equals(position.toLowerCase().trim())){
            this.position = UserPosition.SENIOR_STUDENT;
        } else if("junior_student".equals(position.toLowerCase().trim())){
            this.position = UserPosition.JUNIOR_STUDENT;
        }
        else{
            throw new Exception("Invalid Position Entry");
        }
    }


    public int getLevel() {
        return position.getLevel();
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "libraryId=" + libraryId +
                ", position=" + position +
                "\n";
    }
}
