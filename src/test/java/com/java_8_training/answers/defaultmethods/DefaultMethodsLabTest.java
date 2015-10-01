package com.java_8_training.answers.defaultmethods;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultMethodsLabTest {

    //TODO Part 1. Add a static method to the interface SimpleNumberOp to add to numbers together from the getValue method
    @Test
    public void static_method_can_add_two_simple_number_classes_values_together() {
        SimpleNumberOp lhs = new SimpleNumberOpImpl(16);
        SimpleNumberOp rhs = new SimpleNumberOpImpl(10);
        assertEquals(26, SimpleNumberOp.add(lhs, rhs));
    }

    /*TODO Part 2. Add a default method to existing interface SimpleNumberOp for getValueAsDouble that returns a double,
    throw an UnsupportedException and modify the test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void add_default_method_to_return_value_as_double_but_throws_an_exception() {
        SimpleNumberOp lhs = new SimpleNumberOpImpl(15);
        lhs.getValueAsDouble();
    }

    //Note that we didn't have to change the Impl class.

    //TODO Part 3. A and B have a colliding definition of the method printHello. Fix the tests below

    @Test
    public void can_print_the_result_from_A() {
        //1. Create a class called ABBA that implements both A and B
        ABBA abba = new ABBA();
        //2. Initialise the class here and assert that Hello A is what is returned.
        assertEquals("Hello A", abba.printHello());
    }

}
