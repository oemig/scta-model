package net.oemig.scta.model.kpi.result;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.impl.pojo.PojoCountDataImpl;
import net.oemig.scta.model.impl.pojo.PojoParticipantImpl;
import net.oemig.scta.model.impl.pojo.PojoResponseDataImpl;
import net.oemig.scta.model.impl.pojo.PojoRunImpl;
import net.oemig.scta.model.test.SctaModelTestConfig;

import com.google.common.collect.ImmutableList;

public class KpiResultTest extends TestCase{

	private static final String RESULT_NAME="Testing";
	
	public void test() throws Exception{
		KpiResult k=KpiResult.getInstance(
				RESULT_NAME, 
				ImmutableList.of(
						PojoRunImpl.create(
								ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM)), 
								ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.GroupHow),
										PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.IndividualHow)), 
								ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))
								),
						PojoRunImpl.create(
								ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM)), 
								ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.IndividualHow),
										PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow)), 
								ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))
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
