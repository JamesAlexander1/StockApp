package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.macdDaily;
import json_parser.macdWeek;
import json_parser.rsiDaily;
import json_parser.rsiWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;
import model.NumeratedTimePeriods;
import model.macdDateClosingPrice;

public class MACDChartDAO implements DataAndPriceDAO<macdDateClosingPrice>{
	private static String URL = "https://www.alphavantage.co/query?function=MACD&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
    private static String TYPE = "&series_type=close";
    
    	private String timePeriod;
    	
    public MACDChartDAO(String time) {
    		timePeriod = time;
    }
    
    public  ArrayList<DatePricePoint<macdDateClosingPrice>> queryData(String companyCode) {
        
    		if (timePeriod.equals(NumeratedTimePeriods.YEARLY.name()) || timePeriod.equals(NumeratedTimePeriods.HALF_YEARLY.name())) {
    		    
        		return macdWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
        		
    		} else {
    		    
        		return macdDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
        		
    		}
    }
}
