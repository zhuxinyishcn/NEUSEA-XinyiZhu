package edu.neu.mad_sea.xinyizhu.TodoApp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

  public static String getCurrentTimestamp() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return simpleDateFormat.format(new Date());

  }
}
