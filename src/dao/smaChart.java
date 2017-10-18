package dao;

import java.util.ArrayList;

import http.StringJsonURL;



import json_parser.YearlyClosingPriceParser;
import json_parser.smaDaily;

import json_parser.smaWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class smaChart implements DataAndPriceDAO<DateClosingPricePoint> {
	/**
	 * * 0 = yearly
         * 1 = half yearly
         * 2 = quarterly
         * 3 = monthly
         * 4 = weekly
	 */

    private static String URL = "https://www.alphavantage.co/query?function=SMA&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    private static String OPTIONSWEEKLY = "&interval=weekly&time_period=10";
    private static String OPTIONSDAILY = "&interval=daily&time_period=10";
    private static String TYPE = "&series_type=close";
    	String timePeriod;
    public smaChart(String time) {
    		timePeriod = time;
    }
    @Override
    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
    		if (timePeriod.equals("0") || timePeriod.equals("1")) {
        		return smaWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONSWEEKLY + TYPE + KEY).getResponse(), timePeriod);
    		} else {
        		return smaDaily.parseJson(new StringJsonURL(URL + companyCode + OPTIONSDAILY + TYPE + KEY).getResponse(), timePeriod);
    		}
    }

}
