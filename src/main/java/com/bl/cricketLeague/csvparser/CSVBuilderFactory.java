package com.bl.cricketLeague.csvparser;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder()
    {
        return new OpenCSVBuilder();
    }
}
