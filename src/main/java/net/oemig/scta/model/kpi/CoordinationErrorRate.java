package net.oemig.scta.model.kpi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IRun;

public class CoordinationErrorRate {

	public static CoordinationErrorRate getInstance(List<IRun> aRunList) {
		return new CoordinationErrorRate(aRunList);
	}

	private double value;

	public CoordinationErrorRate(List<IRun> aRunList) {

		double multiCountOverCount = 0;
		for (IRun run : aRunList) {
			Map<String, Integer> letterToCountMap = new HashMap<String, Integer>();

			for (ICountData c : run.getCountData()) {
				if (letterToCountMap.get(c.getLetter()) == null) {
					letterToCountMap.put(c.getLetter(), 1);
				} else {
					int formerCountValue = letterToCountMap.get(c.getLetter());
					letterToCountMap.put(c.getLetter(), formerCountValue + 1);
				}
			}

			// how many multiple counts to we have
			int multiCount = 0;
			for (String key : letterToCountMap.keySet()) {
				if (letterToCountMap.get(key) > 1) {
					multiCount++;
				}
			}
			multiCountOverCount += multiCount
					/ letterToCountMap.keySet().size();

		}

		value = multiCountOverCount / aRunList.size();

	}

	public double getValue() {
		return value;
	}
}
