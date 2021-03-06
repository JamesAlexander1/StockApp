package junit_standard_testing;



import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import http.StringJsonURL;
import http.StringURL;
import json_parser.WeeklyClosingPriceParser;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class WeeklyClosingPriceParserInitialCase {

    StringURL apiResponse;
    String json;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        apiResponse = new StringJsonURL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=CR72JXL4TE7T2WF4");
        json = apiResponse.getResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
       ArrayList<DatePricePoint<DateClosingPricePoint>> list = WeeklyClosingPriceParser.parseJson(json);
       for(DatePricePoint<DateClosingPricePoint> point : list){
           System.out.println(point.getDate().get(Calendar.DAY_OF_MONTH) +":"+ point.getDate().get(Calendar.MONTH) + " - " + point.getPrice());
       }
       
    }

}
