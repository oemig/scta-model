package net.oemig.scta.model.kpi.result;

import java.util.List;
import java.util.Set;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.exception.ResponseDataMissingException;
import net.oemig.scta.model.impl.pojo.PojoRunImpl;
import net.oemig.scta.model.kpi.AvgResponseTimeForgettingTimeRatio;
import net.oemig.scta.model.kpi.CoordinationErrorRate;
import net.oemig.scta.model.kpi.ErrorRate;
import net.oemig.scta.model.kpi.Performance;
import net.oemig.scta.model.kpi.SuccessRate;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * The {@link KpiResult} is a wrapper to generate
 * all kpis for a given list of {@link IRun} instances.
 * Thus it can be used for a single run or the runs of an 
 * entire session.
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
	private double individualErrorRate;
	private double groupErrorRate;
	private double individualSuccessRate;
	private double groupSuccessRate;

	public static KpiResult getInstance(String aTitle, List<IRun> aRunList,
			Millisecond aRunDuration, Millisecond aForgettingTime) throws ResponseDataMissingException {
		return new KpiResult(aTitle,aRunList, aRunDuration,aForgettingTime);
	}

	public KpiResult(String aTitle, List<IRun> aRunList, Millisecond aRunDuration, Millisecond aForgettingTime) throws ResponseDataMissingException {
		name = aTitle;

		averageResponseTimeForgettingTimeRatio = AvgResponseTimeForgettingTimeRatio
				.of(aRunList, aForgettingTime).getValue();

		Set<QuestionType>indQt=ImmutableSet.of(QuestionType.IndividualHow,QuestionType.IndividualWhat,QuestionType.IndividualWho);
		Set<QuestionType>grpQt=ImmutableSet.of(QuestionType.GroupHow,QuestionType.GroupWhat,QuestionType.GroupWho);

		errorRate = ErrorRate.of(aRunList).getValue();
		individualErrorRate=ErrorRate.of(filterQuestionType(aRunList, indQt)).getValue();
		groupErrorRate=ErrorRate.of(filterQuestionType(aRunList,grpQt )).getValue();
		
		successRate=SuccessRate.of(aRunList).getValue();
		individualSuccessRate=SuccessRate.of(filterQuestionType(aRunList, indQt)).getValue();
		groupSuccessRate=SuccessRate.of(filterQuestionType(aRunList, grpQt)).getValue();
		
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
	
	public double getIndividualErrorRate(){
		return individualErrorRate;
	}
	
	public double getGroupErrorRate(){
		return groupErrorRate;
	}
	
	public double getSuccessRate(){
		return successRate;
	}
	
	public double getIndividualSuccessRate(){
		return individualSuccessRate;
	}
	
	public double getGroupSuccessRate(){
		return groupSuccessRate;
	}

	public double getCoordinationErrorRate() {
		return coordinationErrorRate;
	}

	public double getPerformance() {
		return performance;
	}
	
	private List<IRun>filterQuestionType(final List<IRun> aRunList, final Set<QuestionType> filterSet){
		List<IRun> result=Lists.newArrayList();
		for(IRun r:aRunList){
			
			List<IResponseData>l=Lists.newArrayList(Collections2.filter(r.getResponseData(), new Predicate<IResponseData>() {
				public boolean apply(IResponseData rd) {
					return filterSet.contains(rd.getQuestionType());
				};}
			));
			
			result.add(PojoRunImpl.create(r.getCountData(), l,r.getParticipants()));
		}
		
		return result;
	}

}
