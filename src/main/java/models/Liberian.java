package models;

public class Liberian extends Person {
    private long staffId;

    public Liberian() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param dateOfBirth
     * @param sex
     * @param staffId
     * @throws Exception
     */
    //Throws Exception form the person superclass constructor;
    public Liberian(String firstName, String lastName, String address, String dateOfBirth, String sex, long staffId) throws Exception {
        super(firstName, lastName, address, dateOfBirth, sex);
        this.staffId = staffId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }
}
