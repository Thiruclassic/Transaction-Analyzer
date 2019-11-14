package com.hoolah.coding.challenge.library;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateTimeUtils is the utility class to handle the dates
 *
 * @author thirumaldhanasekaran
 */
public class DateTimeUtils {

  /**
   * Convert java.util.Date to java.time.LocalDateTime
   *
   * @param date the date
   * @return the local date time
   */
  public static LocalDateTime convertDateToLocalDate(Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  /**
   * Convert string to LocalDateTime based on the pattern
   *
   * @param date the date
   * @param pattern the pattern
   * @return the local date time
   */
  public static LocalDateTime convertStringToLocalDate(String date, String pattern) {
    return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
  }
}
