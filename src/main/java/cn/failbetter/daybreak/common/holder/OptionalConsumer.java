package cn.failbetter.daybreak.common.holder;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author sun
 */
public class OptionalConsumer<T> {

	private Optional<T> optional;

	private OptionalConsumer(Optional<T> optional) {
		this.optional = optional;
	}

	public static <T> OptionalConsumer<T> of(T optional) {
		return new OptionalConsumer<>(Optional.ofNullable(optional));
	}

	public OptionalConsumer<T> ifPresent(Consumer<T> c) {
		optional.ifPresent(c);
		return this;
	}

	public OptionalConsumer<T> ifNotPresent(Runnable r) {
		if (!optional.isPresent())
			r.run();
		return this;
	}
}
