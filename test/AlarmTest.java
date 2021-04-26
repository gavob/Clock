/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gav
 */
public class AlarmTest {
    private Alarm a;
    private Model m;
    
    @Before
    public void setUp() {
        m = new Model();
        a = new Alarm(m,10,53,"am",true);
    }
    
    @After
    public void tearDown() {
        a = null;
    }

    /**
     * Test of update method, of class Alarm.
     
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object o1 = null;
        Alarm instance = null;
        instance.update(o, o1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of setActive method, of class Alarm.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive Test:");
        
        boolean expResult = true;
        boolean result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setActive(false);
        expResult = false;
        result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setActive(true);
        expResult = true;
        result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }

    /**
     * Test of getHour method, of class Alarm.
     */
    @Test
    public void testGetHour() {
        System.out.println("getHour Test:");
        
        int expResult = 10;
        int result = a.getHour();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(4);
        expResult = 4;
        result = a.getHour();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(12);
        expResult = 12;
        result = a.getHour();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(6);
        expResult = 6;
        result = a.getHour();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }

    /**
     * Test of getMinute method, of class Alarm.
     */
    @Test
    public void testGetMinute() {
        System.out.println("getMinute Test:");
       
        int expResult = 53;
        int result = a.getMinute();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setMinute(1);
        expResult = 1;
        result = a.getMinute();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setMinute(33);
        expResult = 33;
        result = a.getMinute();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setMinute(15);
        expResult = 15;
        result = a.getMinute();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }

    /**
     * Test of getAmPm method, of class Alarm.
     */
    @Test
    public void testGetAmPm() {
        System.out.println("getAmPm Test:");
        
        String expResult = "am";
        String result = a.getAmPm();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setAmPm("pm");
        expResult = "pm";
        result = a.getAmPm();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setAmPm("am");
        expResult = "am";
        result = a.getAmPm();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }

    /**
     * Test of getActive method, of class Alarm.
     */
    @Test
    public void testGetActive() {
        System.out.println("getActive Test:");
        
        boolean expResult = true;
        boolean result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setActive(false);
        expResult = false;
        result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setActive(true);
        expResult = true;
        result = a.getActive();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }
    
    /**
     * Test of getTime method, of class Alarm.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime Test:");
        
        String expResult = "10:53am (Active)";
        String result = a.getTime();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(3);
        a.setMinute(7);
        a.setAmPm("am");
        a.setActive(false);
        expResult = "03:07am (Disabled)";
        result = a.getTime();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(9);
        a.setMinute(25);
        a.setAmPm("pm");
        a.setActive(true);
        expResult = "09:25pm (Active)";
        result = a.getTime();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
        
        a.setHour(12);
        a.setMinute(9);
        a.setAmPm("am");
        a.setActive(false);
        expResult = "12:09am (Disabled)";
        result = a.getTime();
        assertEquals(expResult, result);
        System.out.println(expResult + " == " + result);
    }
    
}
