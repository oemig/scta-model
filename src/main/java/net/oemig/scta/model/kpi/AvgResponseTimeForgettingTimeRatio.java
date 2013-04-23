package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.data.Millisecond;


public final class AvgResponseTimeForgettingTimeRatio implements IKeyPerformanceIndicator{

	public static AvgResponseTimeForgettingTimeRatio of(
			List<IRun> aRunList, Millisecond aForgettingTime) {

		return new AvgResponseTimeForgettingTimeRatio(aRunList,
				aForgettingTime);
	}

	private double value;
	private double avgResponseTime;

	//private constructor
	private AvgResponseTimeForgettingTimeRatio(List<IRun> aRunList,
			Millisecond aForgettingTime) {

		Millisecond responseTimeSum = Millisecond.of(0);
		int responseTimeSize = 0;
		for (IRun run : aRunList) {
			for (IResponseData r : run.getResponseData()) {
				//FIXME put millisecond on run interface
				responseTimeSum.plus(r.getResponseTime());
			}
			responseTimeSize += run.getResponseData().size();
		}
		avgResponseTime = (double)responseTimeSum.intValue() / responseTimeSize;

		value = (double) avgResponseTime / aForgettingTime.intValue();
	}
    @Override
	public double getValue() {
		return this.value;
	}

}
