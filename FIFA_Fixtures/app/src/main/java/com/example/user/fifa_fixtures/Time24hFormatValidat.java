package com.example.user.fifa_fixtures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Time24hFormatValidat{
    private Pattern pattern;
    private Matcher matcher;
    private static final String TIME12HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public Time24hFormatValidat(){
        pattern = Pattern.compile(TIME12HOURS_PATTERN);
    }
    public boolean validate(final String time){
        matcher = pattern.matcher(time);
        return matcher.matches();
    }
}
