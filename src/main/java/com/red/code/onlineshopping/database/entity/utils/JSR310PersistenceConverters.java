package com.red.code.onlineshopping.database.entity.utils;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.red.code.onlineshopping.database.entity.utils.JSR310DateConverters.*;

import java.sql.Time;
import java.time.*;
import java.util.Date;
import java.util.Objects;

/**
 * https://github.com/spring-projects/spring-data-jpa/blob/703f5f812c0b517b900296a97440911fe3c5173d/src/main/java/org/springframework/data/jpa/domain/support/Jsr310JpaConverters.java
 * https://github.com/axboot/ax-boot-framework/tree/master/ax-boot-core/src/main/java/com/chequer/axboot/core/jpa
 */
public final class JSR310PersistenceConverters {

    private JSR310PersistenceConverters() {
    }

    @Converter(autoApply = true)
    public static class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

        @Override
        public Date convertToDatabaseColumn(LocalDate date) {
            return LocalDateToDateConverter.INSTANCE.convert(date);
        }

        @Override
        public LocalDate convertToEntityAttribute(Date date) {
            return DateToLocalDateConverter.INSTANCE.convert(date);
        }
    }

    @Converter(autoApply = true)
    public static class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Date> {

        @Override
        public Date convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
            return ZonedDateTimeToDateConverter.INSTANCE.convert(zonedDateTime);
        }

        @Override
        public ZonedDateTime convertToEntityAttribute(Date date) {
            return DateToZonedDateTimeConverter.INSTANCE.convert(date);
        }
    }

    @Converter(autoApply = true)
    public static class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

        @Override
        public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
            return LocalDateTimeToDateConverter.INSTANCE.convert(localDateTime);
        }

        @Override
        public LocalDateTime convertToEntityAttribute(Date date) {
            return DateToLocalDateTimeConverter.INSTANCE.convert(date);
        }
    }

    /**
     * Converts {@link LocalTime} to {@link java.sql.Time} and back
     * in support of JPA persistence.
     * <p>
     * The existence of this class in the classpath and it being known by the persistence unit
     * is sufficient
     * to allow you to use the as-of Java SE 8 {@link LocalTime} class in
     * an {@link javax.persistence.Entity} or in other persistable classes.
     * <p>
     * Important: the setting of <code>@Converter(autoApply = true)</code>
     * in this class will make this conversion
     * effective for all Entities that have one or more
     * persistent {@link java.time.LocalTime} properties.
     * <p>
     * The persistence provider must minimally support
     * <a href="https://jcp.org/aboutJava/communityprocess/final/jsr338/index.html">JPA 2.1</a>
     * for this to work.
     */
    @Converter(autoApply = true)
    public static class LocalTimePersistenceConverter implements AttributeConverter<LocalTime, Time> {

        @Override
        public Time convertToDatabaseColumn(LocalTime entityValue) {
            return LocalTimeToTimeConverter.INSTANCE.convert(entityValue);
        }

        @Override
        public LocalTime convertToEntityAttribute(Time databaseValue) {
            return TimeToLocalTimeConverter.INSTANCE.convert(databaseValue);
        }

    }

    /**
     * Converts {@link java.time.DayOfWeek} to {@link String} and back
     * in support of JPA persistence.
     * <p>
     * The existence of this class in the classpath and it being known by the persistence unit
     * is sufficient
     * to allow you to use the as-of Java SE 8 {@link java.time.DayOfWeek} class in
     * an {@link javax.persistence.Entity} or in other persistable classes.
     * <p>
     * Important: the setting of <code>@Converter(autoApply = true)</code>
     * in this class will make this conversion
     * automatically effective for all Entities that have one or more
     * persistent {@link java.time.DayOfWeek} properties.
     * <p>
     * The persistence provider must minimally support
     * <a href="https://jcp.org/aboutJava/communityprocess/final/jsr338/index.html">JPA 2.1</a>
     * for this to work.
     */
    @Converter(autoApply = true)
    public static class DayOfWeekPersistenceConverter implements AttributeConverter<DayOfWeek, Integer> {

        /**
         * @return The int value follows the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).
         * @see DayOfWeek#of
         */
        @Override
        public Integer convertToDatabaseColumn(DayOfWeek entityValue) {
            return (entityValue == null) ? null : entityValue.getValue();
        }

        /**
         * @param databaseValue The int value follows the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).
         * @see DayOfWeek#of
         */
        @Override
        public DayOfWeek convertToEntityAttribute(Integer databaseValue) {
            return DayOfWeek.of(databaseValue);
        }

    }

    /**
     * Converts {@link java.time.ZoneId} to {@link String} and back
     * in support of JPA persistence.
     * <p>
     * The existence of this class in the classpath and it being known by the persistence unit
     * is sufficient
     * to allow you to use the as-of Java SE 8 {@link java.time.ZoneId} class in
     * an {@link javax.persistence.Entity} or in other persistable classes.
     * <p>
     * Important: the setting of <code>@Converter(autoApply = true)</code>
     * in this class will make this conversion
     * automatically effective for all Entities that have one or more
     * persistent {@link java.time.ZoneId} properties.
     * <p>
     * The persistence provider must minimally support
     * <a href="https://jcp.org/aboutJava/communityprocess/final/jsr338/index.html">JPA 2.1</a>
     * for this to work.
     */
    @Converter(autoApply = true)
    public static class ZoneIdPersistenceConverter implements AttributeConverter<ZoneId, String> {

        /**
         * @return Outputs this zone as a String, using the ID
         * @see java.time.ZoneId#toString()
         */
        @Override
        public String convertToDatabaseColumn(ZoneId entityValue) {
            return Objects.toString(entityValue, null);
        }


        @Override
        public ZoneId convertToEntityAttribute(String databaseValue) {
            return ZoneId.of(databaseValue);
        }

    }

    /**
     * Converts {@link java.time.MonthDay} to {@link String} and back
     * in support of JPA persistence.
     * <p>
     * The existence of this class in the classpath and it being known by the persistence unit
     * is sufficient
     * to allow you to use the as-of Java SE 8 {@link java.time.MonthDay} class in
     * an {@link javax.persistence.Entity} or in other persistable classes.
     * <p>
     * Important: the setting of <code>@Converter(autoApply = true)</code>
     * in this class will make this conversion
     * automatically effective for all Entities that have one or more
     * persistent {@link java.time.MonthDay} properties.
     * <p>
     * The persistence provider must minimally support
     * <a href="https://jcp.org/aboutJava/communityprocess/final/jsr338/index.html">JPA 2.1</a>
     * for this to work.
     */
    @Converter(autoApply = true)
    public static class MonthDayPersistenceConverter implements AttributeConverter<MonthDay, String> {

        /**
         * @return Outputs this zone as a String, using the ID
         * @see java.time.ZoneId#toString()
         */
        @Override
        public String convertToDatabaseColumn(MonthDay entityValue) {
            return Objects.toString(entityValue, null);
        }


        @Override
        public MonthDay convertToEntityAttribute(String databaseValue) {
            return MonthDay.parse(databaseValue);
        }
    }

    @Converter(autoApply = true)
    public static class InstantPersistenceConverter implements AttributeConverter<Instant, Date> {

        @Override
        public Date convertToDatabaseColumn(Instant entityValue) {
            return InstantToDateConverter.INSTANCE.convert(entityValue);
        }

        @Override
        public Instant convertToEntityAttribute(Date databaseValue) {
            return DateToInstantConverter.INSTANCE.convert(databaseValue);
        }

    }
}
