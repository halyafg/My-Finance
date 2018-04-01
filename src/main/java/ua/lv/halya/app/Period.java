package ua.lv.halya.app;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Period {
    public static Date dateFrom;
    public static Date dateTo;

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return  sdf.format(date);
    }

}
