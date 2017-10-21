package dao;

import java.util.ArrayList;

import http.StringJsonURL;
import json_parser.YearlyClosingPriceParser;
import model.DateClosingPricePoint;
import model.DatePricePoint;

public class YearlyClosingPriceDAO implements DataAndPriceDAO<DateClosingPricePoint> {

    private static String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=";
    private static String KEY = "&apikey=CR72JXL4TE7T2WF4";
    

    @Override
    public  ArrayList<DatePricePoint<DateClosingPricePoint>> queryData(String companyCode) {
        
        return YearlyClosingPriceParser.parseJson(new StringJsonURL(URL + companyCode + KEY).getResponse());
    }

}
