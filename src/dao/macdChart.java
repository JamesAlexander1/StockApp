package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.macdDaily;
import json_parser.macdWeek;
import json_parser.rsiDaily;
import json_parser.rsiWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;
import model.macdDateClosingPrice;

public class macdChart {
	private static String URL = "https://www.alphavantage.co/query?function=MACD&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
    private static String TYPE = "&series_type=close";
    	String timePeriod;
    public macdChart(String time) {
    		timePeriod = time;
    }
    
    public  ArrayList<macdDateClosingPrice> queryData(String companyCode) {
    		if (timePeriod.equals("0") || timePeriod.equals("1")) {
        		return macdWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
    		} else {
        		return macdDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
    		}
    }
}
