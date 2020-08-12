package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiberianTest {

    Liberian liberian;

    @Test
    void testConstructor() {
        try{
            liberian = new Liberian("Obrien", "Longe", "6 Asanjo", "2011-02-28", "Male", 1234L);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertAll(
                () -> assertEquals("Obrien", liberian.getFirstName()),
                () -> assertEquals("Longe", liberian.getLastName()),
                () -> assertEquals("6 Asanjo", liberian.getAddress()),
                () -> assertEquals("2011-02-28", liberian.getDateOfBirth()),
                () -> assertEquals(1234L, liberian.getStaffId()),
                () -> assertEquals(9, liberian.getAge())
        );

    }

    @Test
    void testGettersAndSetters(){
        try {
            liberian = new Liberian();
            liberian.setFirstName("Obrien");
            liberian.setLastName("Longe");
            liberian.setAddress("6 Asanjo");
            liberian.setDateOfBirth("2011-02-28");
            liberian.setSex("Male");
            liberian.setStaffId(1234L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals("Obrien", liberian.getFirstName()),
                () -> assertEquals("Longe", liberian.getLastName()),
                () -> assertEquals("6 Asanjo", liberian.getAddress()),
                () -> assertEquals("2011-02-28", liberian.getDateOfBirth()),
                () -> assertEquals(1234L, liberian.getStaffId()),
                () -> assertEquals(9, liberian.getAge())

        );
    }


}