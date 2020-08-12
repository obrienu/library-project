package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    User student;
    Order order;
    @BeforeEach
    void setUp() {
        try{
            student = new User("Obrien", "Longe", "6 Asanjo", "2011-02-28",
                    "Male", 1234L, "junior_student");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Constructor and getters")
    void testConstructorAndGetters(){
        order = new Order(student, "PHY102");
        assertAll(
                ()->assertEquals(student, order.getUser(), "Should return name of user who made request"),
                ()-> assertEquals("PHY102", order.getBookCode(), "Should return id of the book requested for")
        );
    }

    @Test
    @DisplayName("Test Constructor and getters")
    void testConstructorAndSetters(){
        order = new Order();
        order.setUser(student);
        order.setBookCode("PHY102");
        assertAll(
                ()->assertEquals(student, order.getUser(), "Should return user set via the setters"),
                ()-> assertEquals("PHY102", order.getBookCode(), "Should return id of the book set via setter")
        );
    }

}