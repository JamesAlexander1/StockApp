package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.YearlyClosingPriceParser;
import json_parser.smaWeek;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class smaChart implements DataAndPriceDAO<DateClosingPricePoint> {

    private static String URL = "https://www.alphavantage.co/query?function=SMA&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    private static String OPTIONS = "&interval=weekly&time_period=10";
    private static String TYPE = "&series_type=close";
    	String timePeriod;
    public smaChart(String time) {
    		timePeriod = time;
    		if (timePeriod.equals("0")) {
        		System.out.println("HELLO YEA");
        }
    }
    @Override
    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
        System.out.println(companyCode);
        return smaWeek.parseJson(new StringJsonURL(URL + companyCode + OPTIONS + TYPE + KEY).getResponse(), timePeriod);
    }

}
