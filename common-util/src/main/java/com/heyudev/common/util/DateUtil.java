package com.heyudev.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * date
 * @author heyudev
 * @date 2019/06/19
 */
public final class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String HH_MM_SS = "HH:mm:ss";

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);

    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(HH_MM_SS);

    private static final Map<String, DateTimeFormatter> PATTEN_FORMATTER_MAPPER = new HashMap<>();

    static {
        PATTEN_FORMATTER_MAPPER.put(YYYY_MM_DD_HH_MM_SS, DEFAULT_DATE_TIME_FORMATTER);
        PATTEN_FORMATTER_MAPPER.put(YYYY_MM_DD, DEFAULT_DATE_FORMATTER);
        PATTEN_FORMATTER_MAPPER.put(HH_MM_SS, DEFAULT_TIME_FORMATTER);
    }

    private static DateTimeFormatter cacheFormatterAndGet(String patten) {
        DateTimeFormatter dateTimeFormatter = PATTEN_FORMATTER_MAPPER.get(patten);
        if (dateTimeFormatter == null) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
            PATTEN_FORMATTER_MAPPER.put(patten, dateTimeFormatter);
        }
        return dateTimeFormatter;
    }

    /**
     * @param localDateTime date time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * @param localDateTime time
     * @param patten        yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String patten) {
        DateTimeFormatter dateTimeFormatter = cacheFormatterAndGet(patten);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * @param localDate date
     * @param patten    only date patten
     * @return yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate, String patten) {
        DateTimeFormatter dateTimeFormatter = cacheFormatterAndGet(patten);
        return localDate.format(dateTimeFormatter);
    }

    /**
     * @param localDate localDate
     * @return yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate) {
        return localDate.format(DEFAULT_DATE_FORMATTER);
    }

    /**
     * @param localTime localTime
     * @param patten    patten
     * @return HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime, String patten) {
        DateTimeFormatter dateTimeFormatter = cacheFormatterAndGet(patten);
        return localTime.format(dateTimeFormatter);
    }

    /**
     * @param localTime localTime
     * @return HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime) {
        return localTime.format(DEFAULT_TIME_FORMATTER);
    }

    /**
     * @param date   date time
     * @param patten patten
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String patten) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(cacheFormatterAndGet(patten));
    }

    /**
     * @param date date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param date date
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, YYYY_MM_DD);
    }

    /**
     * @param date date
     * @return HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, HH_MM_SS);
    }

    /**
     * @param mills mills
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatMills(long mills, String patten) {
        Instant instant = Instant.ofEpochMilli(mills);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatLocalDateTime(localDateTime, patten);
    }

    /**
     * @param mills mills
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatMills(long mills) {
        return formatMills(mills, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param mills mills
     * @return yyyy-MM-dd
     */
    public static String formatMillsDate(long mills) {
        return formatMills(mills, YYYY_MM_DD);
    }

    /**
     * @param date date
     * @return HH:mm:ss
     */
    public static String formatMillsTime(long date) {
        return formatMills(date, HH_MM_SS);
    }

    /**
     * @param date yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date parse(String date) {
        LocalDateTime localDateTime = parseToLocalDateTime(date);
        Instant instant = localDateTime.toInstant(OffsetDateTime.now().getOffset());
        return Date.from(instant);
    }

    /**
     * @param date   string date
     * @param patten formatter patten
     * @return LocalDateTime
     */
    public static LocalDateTime parseToLocalDateTime(String date, String patten) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(patten));
    }

    /**
     * @param date yyyy-MM-dd HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime parseToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * @param date yyyy-MM-dd
     * @return LocalDate
     */
    public static LocalDate parseToLocalDate(String date) {
        return LocalDate.parse(date, DEFAULT_DATE_FORMATTER);
    }

    /**
     * @param date HH:mm:ss
     * @return LocalTime
     */
    public static LocalTime parseToLocalTime(String date) {
        return LocalTime.parse(date, DEFAULT_TIME_FORMATTER);
    }

    /**
     * @param dayStart date
     * @param dayEnd   date
     * @return
     */
    public static Period betweenDays(Date dayStart, Date dayEnd) {
        LocalDateTime localDateTimeStart = LocalDateTime.ofInstant(dayStart.toInstant(), OffsetDateTime.now().getOffset());
        LocalDateTime localDateTimeEnd = LocalDateTime.ofInstant(dayEnd.toInstant(), OffsetDateTime.now().getOffset());
        return Period.between(localDateTimeStart.toLocalDate(), localDateTimeEnd.toLocalDate());
    }

    /**
     * @param dayStart date
     * @param dayEnd   date
     * @return
     */
    public static Duration betweenTimes(Date dayStart, Date dayEnd) {
        LocalDateTime localDateTimeStart = LocalDateTime.ofInstant(dayStart.toInstant(), OffsetDateTime.now().getOffset());
        LocalDateTime localDateTimeEnd = LocalDateTime.ofInstant(dayEnd.toInstant(), OffsetDateTime.now().getOffset());
        return Duration.between(localDateTimeStart, localDateTimeEnd);
    }

    /**
     * @param dayStart date
     * @param dayEnd   date
     * @return
     */
    public static Period betweenDays(String dayStart, String dayEnd) {
        return Period.between(parseToLocalDate(dayStart), parseToLocalDate(dayEnd));
    }

    /**
     * @param dayStart date
     * @param dayEnd   date
     * @return
     */
    public static Duration betweenTimes(String dayStart, String dayEnd) {
        return Duration.between(parseToLocalDateTime(dayStart), parseToLocalDateTime(dayEnd));
    }


    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//        int dayOfWeek = localDateTime.getDayOfWeek().getValue();
//        System.out.println(dayOfWeek);
//        System.out.println("-----");
//        LocalDateTime localDateTime1 = localDateTime.plusDays(-4);
//        LocalDateTime localDateTime2 = localDateTime.plusDays(-3);
//        System.out.println(localDateTime1);
//        System.out.println(localDateTime2);
//
//        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(date);
//        System.out.println("-----");
//
//        LocalDateTime mondayLocalDateTime1 = localDateTime2.plusDays(-7).withHour(0).withMinute(0).withSecond(0);
//        Date monday1 = Date.from(mondayLocalDateTime1.atZone(ZoneId.systemDefault()).toInstant());
//        LocalDateTime sundayLocalDateTime1 = localDateTime1.withHour(23).withMinute(59).withSecond(59);
//        Date sunday1 = Date.from(sundayLocalDateTime1.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(monday1);
//        System.out.println(sunday1);
//
//        System.out.println("-----");
//        LocalDateTime mondayLocalDateTime = localDateTime.plusDays(1-dayOfWeek).withHour(0).withMinute(0).withSecond(0);
//        Date monday = Date.from(mondayLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        LocalDateTime sundayLocalDateTime = localDateTime.plusDays(7-dayOfWeek).withHour(23).withMinute(59).withSecond(59);
//        Date sunday = Date.from(sundayLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
//
//        System.out.println(monday);
//        System.out.println(sunday);


        AtomicReference<String> noShowAdGroup = new AtomicReference<>();

        System.out.println(noShowAdGroup.get()==null);



    }
}