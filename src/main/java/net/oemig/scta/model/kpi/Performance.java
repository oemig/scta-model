package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.data.Millisecond;

/**
 * 
 * @author chris
 */
public final class Performance implements IKeyPerformanceIndicator{

	//average performance of all runs of the session
	public static Performance of(List<IRun> aRunList,
			Millisecond aRunDuration) {
		return new Performance(aRunList, aRunDuration);
	}
	
	private double value;

	public Performance(List<IRun> aRunList, Millisecond aRunDuration) {
		int countDataCounter = 0;
		for (IRun run : aRunList) {
			countDataCounter += run.getCountData().size();
		}

		double avgCountDataPerRun = countDataCounter / aRunList.size();

		value = avgCountDataPerRun / aRunDuration.intValue();
	}
	
	public double getValue() {
		return value;
	}
}
