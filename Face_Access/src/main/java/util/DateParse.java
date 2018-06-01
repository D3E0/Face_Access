package util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author ACM-PC
 */
public class DateParse {

    /**
     * Strign(yyyy-MM-dd) 类型的日期转化为 java.sql.Date 类型日期
     *
     * @param string
     * @return
     */
    public static Date stringToSql(String string) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time = 0;
        try {
            time = sdf.parse(string).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(time);
        return sqlDate;
    }
}
