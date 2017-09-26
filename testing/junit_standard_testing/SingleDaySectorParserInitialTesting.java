package junit_standard_testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import http.*;
import json_parser.SingleDaySectorParser;
import model.OneDaySector;
import model.Sector;


public class SingleDaySectorParserInitialTesting {

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
        apiResponse = new StringJsonURL("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
        json = apiResponse.getResponse();
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        
        OneDaySector model = SingleDaySectorParser.parseJson(json);
        
        assertTrue("ERROR: model is null or Sector list missing", model != null && model.getSectors() != null);
        
        List<Sector> sectors = model.getSectors();
        for(Sector s : sectors){
            System.out.println(s.getName() + " : " + s.getValue());
        }
        
    }

   

}
