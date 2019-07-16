package com.red.code.onlineshopping.database.entity.utils;

import org.springframework.core.convert.converter.Converter;

import java.sql.Time;
import java.time.*;
import java.util.Date;

/**
 * https://github.com/spring-projects/spring-data-jpa/blob/703f5f812c0b517b900296a97440911fe3c5173d/src/main/java/org/springframework/data/jpa/domain/support/Jsr310JpaConverters.java
 * https://github.com/axboot/ax-boot-framework/tree/master/ax-boot-core/src/main/java/com/chequer/axboot/core/jpa
 */
public final class JSR310DateConverters {

    private JSR310DateConverters() {
    }

    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

        public static final LocalDateToDateConverter INSTANCE = new LocalDateToDateConverter();

        private LocalDateToDateConverter() {
        }

        @Override
        public Date convert(LocalDate source) {
            return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {
        public static final DateToLocalDateConverter INSTANCE = new DateToLocalDateConverter();

        private DateToLocalDateConverter() {
        }

        @Override
        public LocalDate convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

        private ZonedDateTimeToDateConverter() {
        }

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();

        private DateToZonedDateTimeConverter() {
        }

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
        public static final LocalDateTimeToDateConverter INSTANCE = new LocalDateTimeToDateConverter();

        private LocalDateTimeToDateConverter() {
        }

        @Override
        public Date convert(LocalDateTime source) {
            return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
        public static final DateToLocalDateTimeConverter INSTANCE = new DateToLocalDateTimeConverter();

        private DateToLocalDateTimeConverter() {
        }

        @Override
        public LocalDateTime convert(Date source) {
            return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    public static class TimeToLocalTimeConverter implements Converter<Time, LocalTime> {
        public static final TimeToLocalTimeConverter INSTANCE = new TimeToLocalTimeConverter();

        private TimeToLocalTimeConverter() {
        }

        @Override
        public LocalTime convert(Time source) {
            return source == null ? null : source.toLocalTime();
        }
    }

    public static class LocalTimeToTimeConverter implements Converter<LocalTime, Time> {
        public static final LocalTimeToTimeConverter INSTANCE = new LocalTimeToTimeConverter();

        private LocalTimeToTimeConverter() {
        }

        @Override
        public Time convert(LocalTime source) {
            return source == null ? null : Time.valueOf(source);
        }
    }

    public static class DateToInstantConverter implements Converter<Date, Instant> {
        public static final DateToInstantConverter INSTANCE = new DateToInstantConverter();

        private DateToInstantConverter() {
        }

        @Override
        public Instant convert(Date source) {
            return source == null ? null : source.toInstant();
        }
    }

    public static class InstantToDateConverter implements Converter<Instant, Date> {
        public static final InstantToDateConverter INSTANCE = new InstantToDateConverter();

        private InstantToDateConverter() {
        }

        @Override
        public Date convert(Instant source) {
            return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        }
    }
}

