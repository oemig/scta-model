package net.oemig.scta.model.kpi;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
//FIXME revise to single interface using Runs as in error rate
public class Performance {

	//average performance of all runs of the session
	public static Performance getInstance(ISession aSession,
			int aRunDuration) {
		return new Performance(aSession, aRunDuration);
	}
	
	//performance for a single run
	public static Performance getInstance(IRun aRun, int aRunDuration){
		return new Performance(aRun, aRunDuration);
	}

	private double value;

	public Performance(ISession aSession, int aRunDuration) {
		int countDataCounter = 0;
		for (IRun run : aSession.getRuns()) {
			countDataCounter += run.getCountData().size();
		}

		double avgCountDataPerRun = countDataCounter / aSession.getRuns().size();

		value = avgCountDataPerRun / aRunDuration;
	}
	
	public Performance(IRun aRun, int aRunDuration){
		value=(double) aRun.getCountData().size()/aRunDuration;
	}

	public double getValue() {
		return value;
	}
}
