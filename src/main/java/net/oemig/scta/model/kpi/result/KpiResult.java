package net.oemig.scta.model.kpi.result;

import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.kpi.AvgResponseTimeForgettingTimeRatio;
import net.oemig.scta.model.kpi.CoordinationErrorRate;
import net.oemig.scta.model.kpi.ErrorRate;
import net.oemig.scta.model.kpi.Performance;
import net.oemig.scta.model.kpi.SuccessRate;

/**
 * The {@link KpiResult} is a wrapper to generate
 * all kpis for a given list of {@link IRun} instances.
 * Thus it can be used for a single run or an entire session.
 * 
 * @author chris
 */
public class KpiResult {

	private String name;
	private double averageResponseTimeForgettingTimeRatio;
	private double errorRate;
	private double coordinationErrorRate;
	private double performance;
	private double successRate;

	public static KpiResult getInstance(String aTitle, List<IRun> aRunList,
			Millisecond aRunDuration, Millisecond aForgettingTime) {
		return new KpiResult(aTitle,aRunList, aRunDuration,aForgettingTime);
	}

	public KpiResult(String aTitle, List<IRun> aRunList, Millisecond aRunDuration, Millisecond aForgettingTime) {
		name = aTitle;

		averageResponseTimeForgettingTimeRatio = AvgResponseTimeForgettingTimeRatio
				.of(aRunList, aForgettingTime).getValue();

		errorRate = ErrorRate.of(aRunList).getValue();
		
		successRate=SuccessRate.of(aRunList).getValue();

		coordinationErrorRate = CoordinationErrorRate.of(
				aRunList).getValue();

		performance = Performance.of(aRunList, aRunDuration)
				.getValue();
	}

	public String getName() {
		return name;
	}

	public double getAverageResponseTimeForgettingTimeRatio() {
		return averageResponseTimeForgettingTimeRatio;
	}

	public double getErrorRate() {
		return errorRate;
	}
	
	public double getSuccessRate(){
		return successRate;
	}

	public double getCoordinationErrorRate() {
		return coordinationErrorRate;
	}

	public double getPerformance() {
		return performance;
	}

}
