package edu.neu.mad_sea.xinyizhu.TodoApp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

  public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss");

  public static String getCurrentTimestamp() {
    return simpleDateFormat.format(new Date());
  }

  public static Calendar date2Cal(String time) {
    Calendar cal = Calendar.getInstance();
    Date d = new Date();
    try {
      d = simpleDateFormat.parse(time);
    } catch (ParseException e) {
      System.out.println("date issue: please check date format");
    }
    if (d != null) {
      cal.setTime(d);
    }
    return cal;
  }

  public static String futureTime() {
    Calendar cal = date2Cal(getCurrentTimestamp());
    cal.add(Calendar.MINUTE, 1);
    return simpleDateFormat.format(cal.getTime());
  }
}
