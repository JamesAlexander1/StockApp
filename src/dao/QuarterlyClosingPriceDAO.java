package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.QuarterlyClosingPriceParser;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class QuarterlyClosingPriceDAO implements DataAndPriceDAO<DateClosingPricePoint> {

    private static String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    

    @Override
    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
        try {
        	return QuarterlyClosingPriceParser.parseJson(new StringJsonURL(URL + companyCode + KEY).getResponse());
        } catch (Exception e) {
        	return new ArrayList<DatePricePoint<DateClosingPricePoint>>();
        }
    }

}
