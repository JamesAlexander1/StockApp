/**
 * 
 */
package junit_standard_testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import http.*;
/**
 * @author james
 *
 */
public class StringJsonURLInitialTestCase {

    StringURL test = null;
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
       
        
        
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        test = new StringJsonURL("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        
        String output = test.getResponse();
        assertTrue("ERROR: output equal to null", output != null);
        System.out.println(output);
        
    }

}
