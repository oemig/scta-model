package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IRun;

/**
 * The {@link SuccessRate} is the inverse of the {@link ErrorRate},
 * i.e. sr=1-er.
 * 
 * @author chris
 *
 */
public final class SuccessRate implements IKeyPerformanceIndicator{

	public static SuccessRate of(List<IRun> aRunList){
		return new SuccessRate(aRunList);
	}

	private double value;
	
	private SuccessRate(List<IRun> aRunList){
		value=(double)1-ErrorRate.of(aRunList).getValue();
	}
	
	@Override
	public double getValue() {
		return value;
	}

}
