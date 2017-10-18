package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.rsiDaily;
import json_parser.rsiWeek;

import model.DateClosingPricePoint;
import model.DatePricePoint;
import model.NumeratedTimePeriods;

public class RSIChartDAO implements DataAndPriceDAO<DateClosingPricePoint>  {
    
        private static String URL = "https://www.alphavantage.co/query?function=RSI&symbol=";
	    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
	    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
	    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
	    private static String TYPE = "&series_type=close";
	    
	    private String timePeriod;
	    	
	    	
	    public RSIChartDAO(String val) {
	    		timePeriod = val;
	    }
	    
	    @Override
	    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
	    		if (timePeriod.equals(NumeratedTimePeriods.YEARLY.name()) || timePeriod.equals(NumeratedTimePeriods.HALF_YEARLY.name())) {
	        		return rsiWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
	    		} else {
	        		return rsiDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
	    		}
	    }
}
