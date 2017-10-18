package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.rsiDaily;
import json_parser.rsiWeek;
import json_parser.smaDaily;
import json_parser.smaWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class rsiChart implements DataAndPriceDAO<DateClosingPricePoint>  {
	 private static String URL = "https://www.alphavantage.co/query?function=RSI&symbol=";
	    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
	    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
	    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
	    private static String TYPE = "&series_type=close";
	    	String timePeriod;
	    public rsiChart(String time) {
	    		timePeriod = time;
	    }
	    @Override
	    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
	    		if (timePeriod.equals("0") || timePeriod.equals("1")) {
	        		return rsiWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
	    		} else {
	        		return rsiDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
	    		}
	    }
}
