package nl.kristalsoftware.ddd.domain.base;

import nl.kristalsoftware.ddd.domain.base.annotations.ValueObject;

@ValueObject
public abstract class TinyIntegerType<T> extends TinyType<Integer> {

	protected TinyIntegerType(Integer value) {
		super(value);
	}

	@Override
	public Boolean isEmpty() {
		return getValue() == null || getValue() == 0;
	}

	protected abstract T create(int value);

	public T add(TinyType<Integer> addedQuantity) {
		return create(getValue() + addedQuantity.getValue());
	}

	public T substract(TinyType<Integer> substractedQuantity) {
		return create(getValue() - substractedQuantity.getValue());
	}

	public boolean lessThan(int i) {
		return getValue() < i;
	}

	public boolean greaterThan(TinyType<Integer> toUseForTicket) {
		return getValue() > toUseForTicket.getValue();
	}

	public boolean greaterThan(int i) {
		return getValue() > i;
	}

	public T inverse() {
		return create(0 - getValue());
	}

}
