package net.oemig.scta.model.kpi;

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

import com.google.common.collect.ImmutableList;

public class CoordinationErrorRateTest extends TestCase{

	public void testSingleRun(){
		double c=CoordinationErrorRate.of(
				ImmutableList.of(
						PojoRunImpl.builder().
							countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
							responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
										PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.GroupHow))).
							participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))).
							build()
				) 
		).getValue();
		
		Assert.assertEquals(0.0, c);
	}
	
	public void testMultipleRuns(){
		double c=CoordinationErrorRate.of(
				ImmutableList.of(
						PojoRunImpl.builder().
						countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
						responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
										PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.GroupHow))).
						participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.of("tim"),ExperiementId.of("DD")))).
						build()
				,
						PojoRunImpl.builder().
							countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("A", 22, UserName.TIM))).
							responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
										PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow))).
							participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.of("tim"),ExperiementId.of("DD")))).
							build()
				)		
		
		).getValue();
		
		Assert.assertEquals(0.5, c);
	}
}
