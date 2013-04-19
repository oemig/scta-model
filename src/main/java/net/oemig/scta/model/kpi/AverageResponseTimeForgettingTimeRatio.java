package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;

public class AverageResponseTimeForgettingTimeRatio {

	public static AverageResponseTimeForgettingTimeRatio getInstance(
			List<IRun> aRunList, int aForgettingTime) {

		return new AverageResponseTimeForgettingTimeRatio(aRunList,
				aForgettingTime);
	}

	private double value;
	private double avgResponseTime;

	//private constructor
	private AverageResponseTimeForgettingTimeRatio(List<IRun> aRunList,
			int aForgettingTime) {

		int responseTimeSum = 0;
		int responseTimeSize = 0;
		for (IRun run : aRunList) {
			for (IResponseData r : run.getResponseData()) {
				responseTimeSum += r.getResponseTime();
			}
			responseTimeSize += run.getResponseData().size();
		}
		avgResponseTime = responseTimeSum / responseTimeSize;

		value = avgResponseTime / aForgettingTime;
	}

	public double getValue() {
		return this.value;
	}

	public double getAverageResponseTime() {
		return avgResponseTime;
	}

}
