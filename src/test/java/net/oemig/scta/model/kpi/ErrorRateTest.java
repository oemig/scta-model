package net.oemig.scta.model.kpi;

import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.test.CountDataImpl;
import net.oemig.scta.model.test.ParticipantImpl;
import net.oemig.scta.model.test.ResponseDataImpl;
import net.oemig.scta.model.test.RunImpl;

import com.google.common.collect.ImmutableList;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ErrorRateTest extends TestCase{
	
	//single run in list
	public void testSingleRun(){
		double e=ErrorRate.getInstance(ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(11, true, "jeff", QuestionType.GroupHow),
										ResponseDataImpl.of(11, false, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				)).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.5,e);
	}
	
	public void testMultipleRuns(){
		double e=ErrorRate.getInstance(ImmutableList.of(
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(11, true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(11, false, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						),
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(11, true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(11, true, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						)
		)).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.25,e);
	}

}
