package net.oemig.scta.model.kpi;

import java.util.List;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.exception.ResponseDataMissingException;

/**
 * 
 * @author chris
 */
public final class ErrorRate implements IKeyPerformanceIndicator{

	public static ErrorRate of(List<IRun> aRunList) throws ResponseDataMissingException {
		return new ErrorRate(aRunList);
	}

	private double value;

	private ErrorRate(List<IRun> aRunList) throws ResponseDataMissingException {
		int errorCounter = 0;
		int totalNumber=0;
		for (IRun run : aRunList) {
			for (IResponseData r : run.getResponseData()) {
				if (!r.isCorrect()) {
					errorCounter++;
				}
				totalNumber++;
			}
		}

		//division by 0?
		if(totalNumber>0){
			value = (double) errorCounter / totalNumber;
		}else{
			throw new ResponseDataMissingException("Runs contained no appropriate response data.");
		}

	}

	public double getValue() {
		return value;
	}

}
