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

public class PerformanceTest extends TestCase {
	
	/**
	 * one run in one session
	 */
	public void testForSession(){
		double p=Performance.of(
				ImmutableList.of(
						PojoRunImpl.create(
								ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM)), 
								ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow)), 
								ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))
								) 
				),Millisecond.of(20)).getValue();
		
		Assert.assertEquals((double)2/20, p);
	}

	public void testForRun(){
		double p=Performance.of(ImmutableList.of(
				PojoRunImpl.create(
								ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM)), 
								ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow)), 
								ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))
								) 
				),Millisecond.of(20)).getValue();
		System.out.println("p="+p);
		Assert.assertEquals((double)2/20, p);
	}
}
