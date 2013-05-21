package net.oemig.scta.model.kpi;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exception.ResponseDataMissingException;
import net.oemig.scta.model.impl.pojo.PojoCountDataImpl;
import net.oemig.scta.model.impl.pojo.PojoParticipantImpl;
import net.oemig.scta.model.impl.pojo.PojoResponseDataImpl;
import net.oemig.scta.model.impl.pojo.PojoRunImpl;

import com.google.common.collect.ImmutableList;

public class ErrorRateTest extends TestCase{
	
	//single run in list
	public void testSingleRun()throws Exception{
		double e=ErrorRate.of(ImmutableList.of(
						PojoRunImpl.builder().
							countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
							responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
										PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.GroupHow))).
							participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.of("tim"),ExperiementId.of("DD")))).
							build()
				)).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.5,e);
	}
	
	public void testMultipleRuns()throws Exception{
		double e=ErrorRate.of(ImmutableList.of(
				PojoRunImpl.builder().
					countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
					responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
								PojoResponseDataImpl.of(Millisecond.of(11), false, UserName.JEFF, QuestionType.GroupHow))).
					participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))).
					build()
				,
				PojoRunImpl.builder().
					countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
					responseData(ImmutableList.of(PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow),
								PojoResponseDataImpl.of(Millisecond.of(11), true, UserName.JEFF, QuestionType.GroupHow))).
					participants(ImmutableList.of(PojoParticipantImpl.create(UserName.TIM, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))).
					build()
						)
		).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.25,e);
	}
	
	public void testNoResponseData()throws Exception{
		try{
			ErrorRate.of(ImmutableList.of(
						PojoRunImpl.builder().
							countData(ImmutableList.of(PojoCountDataImpl.of("A",11,UserName.JEFF), PojoCountDataImpl.of("B", 22, UserName.TIM))).
							responseData(new ArrayList<IResponseData>()).
							participants(ImmutableList.of(PojoParticipantImpl.create(UserName.JEFF, ExperiementId.of("DD")), PojoParticipantImpl.create(UserName.TIM,ExperiementId.of("DD")))).
							build()
				)).getValue();
			Assert.fail("Expected exception not thrown");
		}catch(ResponseDataMissingException e){
			System.out.println(e.getLocalizedMessage());
		}
	}

}
