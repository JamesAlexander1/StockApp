package junit_standard_testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.WeekClosingPriceDAO;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class WeekClosingPriceDAOInitialCase {

    WeekClosingPriceDAO dao;
    String[] testStrings;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        
        dao = new WeekClosingPriceDAO();
        testStrings = new String[]{"MSFT", "ACN", "ATVI", "BAC", "BLK"};
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        
        for(String code : testStrings){
            ArrayList<DatePricePoint<DateClosingPricePoint>> list = dao.queryData(code);
            assertNotNull(list);
            
            for(DatePricePoint<DateClosingPricePoint> point : list){
                System.out.println(point.getDate().get(Calendar.DAY_OF_MONTH) +":"+ point.getDate().get(Calendar.MONTH) + " - " + point.getPrice());
            }
            System.out.println();
            
        }
    }

}
