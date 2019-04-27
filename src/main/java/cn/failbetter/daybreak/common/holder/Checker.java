package cn.failbetter.daybreak.common.holder;

import cn.failbetter.daybreak.common.exception.DomainException;

import javax.annotation.Nullable;

public final class Checker {
	private Checker() {
	}

	public static void expressionIsTrue(boolean expression, @Nullable String errorMessageTemplate,
			@Nullable Object... errorMessageArgs) {
		check(expression, errorMessageTemplate, errorMessageArgs);
	}

	private static void check(boolean expression, @Nullable String errorMessageTemplate,
			@Nullable Object... errorMessageArgs) {
		if (!expression) {
			throw new DomainException(format(errorMessageTemplate, errorMessageArgs));
		}
	}

	/**
	 * Substitutes each {@code %s} in {@code template} with an argument. These are matched by
	 * position: the first {@code %s} gets {@code args[0]}, etc. If there are more arguments than
	 * placeholders, the unmatched arguments will be appended to the end of the formatted message in
	 * square braces.
	 *
	 * @param template a non-null string containing 0 or more {@code %s} placeholders.
	 * @param args the arguments to be substituted into the message template. Arguments are converted
	 *     to strings using {@link String#valueOf(Object)}. Arguments can be null.
	 */
	// Note that this is somewhat-improperly used from Verify.java as well.
	static String format(String template, @Nullable Object... args) {
		template = String.valueOf(template); // null -> "null"

		// start substituting the arguments into the '%s' placeholders
		StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
		int templateStart = 0;
		int i = 0;
		while (i < args.length) {
			int placeholderStart = template.indexOf("%s", templateStart);
			if (placeholderStart == -1) {
				break;
			}
			builder.append(template, templateStart, placeholderStart);
			builder.append(args[i++]);
			templateStart = placeholderStart + 2;
		}
		builder.append(template, templateStart, template.length());

		// if we run out of placeholders, append the extra args in square braces
		if (i < args.length) {
			builder.append(" [");
			builder.append(args[i++]);
			while (i < args.length) {
				builder.append(", ");
				builder.append(args[i++]);
			}
			builder.append(']');
		}

		return builder.toString();
	}

}
