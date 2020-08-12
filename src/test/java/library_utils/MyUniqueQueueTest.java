package library_utils;

import models.Order;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyUniqueQueueTest {
    User teacher1, teacher2, seniorStudent1, seniorStudent2, juniorStudent, juniorStudent2;
    Order order1, order2, order3, order4, order5, order6;
    MyUniqueQueue list = null;

    @BeforeEach
    void setUp() {
        list = new MyUniqueQueue();
        try{
            teacher1 = new User("O'Brien", "Longe", "6 Asanjo", "2011-02-28",
                    "Male", 1234L, "teacher");
            teacher2 = new User("John", "Kimber", "6 Asanjo", "2011-02-28",
                    "Male", 1434L, "teacher");
            seniorStudent1 = new User("Kelvin", "Kim", "6 Asanjo", "2011-02-23",
                    "F", 134L, "senior_student");
            seniorStudent2 = new User("Harry", "Kim", "6 Asanjo", "2001-02-23",
                    "F", 137784L, "senior_student");
            juniorStudent = new User("Harry", "Kim", "6 Asanjo", "2001-02-23",
                    "F", 132784L, "junior_student");
            juniorStudent2 = new User("James", "Okon", "6 Asanjo", "2001-02-23",
                    "F", 184L, "junior_student");

            order1 = new Order(seniorStudent2, "PHY110");
            order2 = new Order(seniorStudent1, "PHY110");
            order3 = new Order(juniorStudent2, "PHY114");
            order4 = new Order(juniorStudent, "PHY110");
            order5 = new Order(teacher1, "PHY114");
            order6 = new Order (teacher2, "PHY114");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void add() {
        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        list.add(order6);

        int expected = 6;
        int actual = list.size();
        Assertions.assertEquals(expected, actual, "Method should return correct number of values added");

    }

    @Test
    @DisplayName("Test add method for no duplicate")
    void testAddForDuplicates() {
        list.add(order4);
        boolean actual = list.add(order4);
        Assertions.assertFalse( actual, "Method should return false for duplicate entry");
        Assertions.assertEquals(1, list.size(), "Method should not add duplicate entries");
    }

    @Test
    @DisplayName("Test remove method to ensure it removes orders in the first come first serve bases")
    void testRemoveMethod() {

        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        list.add(order6);

        Assertions.assertEquals(order1, list.remove());
        Assertions.assertEquals(order2, list.remove());
        Assertions.assertEquals(order3, list.remove());
        Assertions.assertEquals(order4, list.remove());
        Assertions.assertEquals(order5, list.remove());
        Assertions.assertEquals(order6, list.remove());
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(list.isEmpty(), "method should return true for an empty list");
    }

}