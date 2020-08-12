package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User student;
    User teacher = null;

    @BeforeEach
    void init() {
        teacher = new User();
        student = new User();
    }

    @Test
    @DisplayName("Test User Constructor")
    void testConstructorAndGetters() {
        try {
            teacher = new User("Obrien", "Longe", "6 Asanjo", "2011-02-28", "Male", 1234L, "teacher");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals("Obrien", teacher.getFirstName()),
                () -> assertEquals("Longe", teacher.getLastName()),
                () -> assertEquals("6 Asanjo", teacher.getAddress()),
                () -> assertEquals("2011-02-28", teacher.getDateOfBirth()),
                () -> assertEquals(1234L, teacher.getLibraryId()),
                () -> assertEquals("TEACHER", teacher.getPosition()),
                () -> assertEquals(9, teacher.getAge()),
                () -> assertEquals(3, teacher.getLevel())
        );
    }

    @Test
    @DisplayName("Test User setters")
    void testSetters() {
        try {
            teacher = new User();
            teacher.setFirstName("Obrien");
            teacher.setLastName("Longe");
            teacher.setAddress("6 Asanjo");
            teacher.setDateOfBirth("2011-02-28");
            teacher.setSex("Male");
            teacher.setLibraryId(1234L);
            teacher.setPosition("teacher");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals("Obrien", teacher.getFirstName()),
                () -> assertEquals("Longe", teacher.getLastName()),
                () -> assertEquals("6 Asanjo", teacher.getAddress()),
                () -> assertEquals("2011-02-28", teacher.getDateOfBirth()),
                () -> assertEquals(1234L, teacher.getLibraryId()),
                () -> assertEquals("TEACHER", teacher.getPosition()),
                () -> assertEquals(3, teacher.getLevel()),
                () -> assertEquals(9, teacher.getAge())

        );
    }

    @Test
    @DisplayName("Test setPosition Method")
    void setPosition() {
        try {
            teacher.setPosition("teacher");
            student.setPosition("junior_student");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals("TEACHER", teacher.getPosition(), "Method should return the correct teacher position"),
                () -> assertEquals("JUNIOR_STUDENT", student.getPosition(), "Method should return correct student position")
        );

    }

    @Test
    @DisplayName("Test setPosition Method for exceptions")
    void setPositionEdgeCase() {
        assertThrows(Exception.class, () -> teacher.setPosition("doctor"));
    }

    @Test
    @DisplayName("Test getLevel Method")
    void setLevel() {
        try {
            teacher.setPosition("teacher");
            student.setPosition("junior_student");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals(3, teacher.getLevel(), "Should return the correct teacher level"),
                () -> assertEquals(1, student.getLevel(), "Should return the correct teacher level")
        );
    }


    @Test
    @DisplayName("Test setSex Method")
    void testSetSex() {
        try {
            teacher.setSex("male");
            student.setSex("F");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertAll(
                () -> assertEquals("MALE", teacher.getSex(), "Method should set the right gender"),
                () -> assertEquals("FEMALE", student.getSex(), "Method should set the right gender")
        );
    }

    @Test
    @DisplayName("Test setSex Method for Exceptions")
    void testSetSexForException() {
        assertAll(
                () -> assertThrows(Exception.class, () -> teacher.setSex("maledf"), "Method should throw an exception for wrong gender entry"),
                () -> assertThrows(Exception.class, () -> student.setSex("Feman"), "Method should throw an exception for wrong gender entry")
        );
    }


}
