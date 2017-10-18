package dao;

import java.util.ArrayList;

import http.StringJsonURL;



import json_parser.YearlyClosingPriceParser;
import json_parser.smaDaily;

import json_parser.smaWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;
import model.NumeratedTimePeriods;

public class SMAChartDAO implements DataAndPriceDAO<DateClosingPricePoint> {
	

    private static String URL = "https://www.alphavantage.co/query?function=SMA&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
    private static String TYPE = "&series_type=close";
    
    	private String timePeriod;
    	
    public SMAChartDAO(String time) {
    		timePeriod = time;
    }
    
    @Override
    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
        
    		if (timePeriod.equals(NumeratedTimePeriods.YEARLY.name()) || timePeriod.equals(NumeratedTimePeriods.HALF_YEARLY.name())) {
    		    
        		return smaWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
        		
    		} else {
        		return smaDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
    		}
    }

}
