package net.oemig.scta.model.kpi.result;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.impl.pojo.CountDataImpl;
import net.oemig.scta.model.impl.pojo.ParticipantImpl;
import net.oemig.scta.model.impl.pojo.ResponseDataImpl;
import net.oemig.scta.model.impl.pojo.RunImpl;
import net.oemig.scta.model.test.SctaModelTestConfig;

import com.google.common.collect.ImmutableList;

public class KpiResultTest extends TestCase{

	private static final String RESULT_NAME="Testing";
	
	public void test() throws Exception{
		KpiResult k=KpiResult.getInstance(
				RESULT_NAME, 
				ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), false, "jeff", QuestionType.GroupHow),
										ResponseDataImpl.of(Millisecond.of(11), false, "jeff", QuestionType.IndividualHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								),
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.IndividualHow),
										ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								)
				), 
				SctaModelTestConfig.RUN_DURATION, 
				SctaModelTestConfig.FORGETTING_TIME);
		
		Assert.assertEquals((double) 0.55, k.getAverageResponseTimeForgettingTimeRatio());
		Assert.assertEquals((double) 0.0, k.getCoordinationErrorRate());
		Assert.assertEquals((double) 0.5, k.getErrorRate());
		Assert.assertEquals((double) 0.5, k.getGroupErrorRate());
		Assert.assertEquals((double) 0.5, k.getGroupSuccessRate());
		Assert.assertEquals((double) 0.5, k.getIndividualErrorRate());
		Assert.assertEquals((double) 0.5, k.getIndividualSuccessRate());
		Assert.assertEquals((double) 2/SctaModelTestConfig.RUN_DURATION.intValue(), k.getPerformance());
		Assert.assertEquals((double) 0.5, k.getSuccessRate());
		Assert.assertEquals(RESULT_NAME, k.getName());
	}
}
