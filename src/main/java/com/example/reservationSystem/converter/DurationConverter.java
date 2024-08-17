package com.example.reservationSystem.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;


@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String> {
	@Override
	public String convertToDatabaseColumn(Duration attribute) {
		return attribute != null ? attribute.toString() : null;
	}

	@Override
	public Duration convertToEntityAttribute(String dbData) {
		return dbData != null ? Duration.parse(dbData) : null;
	}
}
