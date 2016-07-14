package com.smluv82.file2compare.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UsefulUtil {
	/**
	 * String 형으로 printStackTrace message 를 돌려 주는 method.
	 *
	 * @param e exception 객체
	 * @return exception의 printStackTrace 메세지
	 */
	public static String getPrintStacTraceString(final Exception e) {
		String returnValue = "";

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(out);
		e.printStackTrace(printStream);
		returnValue = out.toString();
		return returnValue;
	}
}
