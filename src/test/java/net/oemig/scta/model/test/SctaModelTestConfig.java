package net.oemig.scta.model.test;

import net.oemig.scta.model.data.Millisecond;

public class SctaModelTestConfig {
	
	public static final Millisecond RUN_DURATION=Millisecond.of(100);
	public static final Millisecond FORGETTING_TIME=Millisecond.of(20);
	
	public static String TRACE_NAME="Testtrace";
	public static String SESSION_NAME="TestSession";
}
