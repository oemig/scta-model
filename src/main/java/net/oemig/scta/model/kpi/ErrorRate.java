package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;

public class ErrorRate {

	public static ErrorRate getInstance(List<IRun> aRunList) {
		return new ErrorRate(aRunList);
	}

	private double value;

	private ErrorRate(List<IRun> aRunList) {
		int errorCounter = 0;
		int correctCounter = 0;
		for (IRun run : aRunList) {
			for (IResponseData r : run.getResponseData()) {
				if (r.isCorrect()) {
					correctCounter++;
				} else {
					errorCounter++;
				}
			}
		}

		value = errorCounter / (errorCounter + correctCounter);

	}

	public double getValue() {
		return value;
	}

}
