package nl.kristalsoftware.ddd.domain.base;

import nl.kristalsoftware.ddd.domain.base.annotations.ValueObject;

@ValueObject
public abstract class TinyStringType extends TinyType<String> {

	protected TinyStringType(String value) {
		super(value);
	}

	@Override
	public Boolean isEmpty() {
		return getValue() == null || getValue().isEmpty();
	}
}
