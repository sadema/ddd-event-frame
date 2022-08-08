package nl.kristalsoftware.ddd.domain.base;


import nl.kristalsoftware.ddd.domain.base.annotations.ValueObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@ValueObject
public abstract class TinyDateType extends TinyType<LocalDate> {

	protected TinyDateType(LocalDate value) {
		super(value);
	}

	protected TinyDateType(Long value) {
		super(null);
	}

	@Override
	public Boolean isEmpty() {
		return getValue() == null;
	}

	public Instant getInstant() {
		return getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	}

	public long getDateInMillis() {
		return getEpochMilliFromLocalDate(getValue());
	}

	private long getEpochMilliFromLocalDate(LocalDate localDate) {
		return getInstantFromLocalDate(localDate).toEpochMilli();
	}

	public static Instant getInstantFromMillis(Long date) {
		return Instant.ofEpochMilli(date);
	}

	public static Instant getInstantFromLocalDate(LocalDate localDate) {
		return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	}

	public static LocalDate getLocalDateFromMillis(Long date) {
		Instant instant = Instant.ofEpochMilli(date);
		return LocalDate.ofInstant(instant, ZoneId.systemDefault());
	}

	public static LocalDate getLocalDateFromInstant(Instant instant) {
		return LocalDate.ofInstant(instant, ZoneId.systemDefault());
	}

}
