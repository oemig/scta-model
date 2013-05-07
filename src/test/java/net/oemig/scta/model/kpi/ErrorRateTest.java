package net.oemig.scta.model.kpi;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.exception.ResponseDataMissingException;
import net.oemig.scta.model.impl.pojo.CountDataImpl;
import net.oemig.scta.model.impl.pojo.ParticipantImpl;
import net.oemig.scta.model.impl.pojo.ResponseDataImpl;
import net.oemig.scta.model.impl.pojo.RunImpl;

import com.google.common.collect.ImmutableList;

public class ErrorRateTest extends TestCase{
	
	//single run in list
	public void testSingleRun()throws Exception{
		double e=ErrorRate.of(ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow),
										ResponseDataImpl.of(Millisecond.of(11), false, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				)).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.5,e);
	}
	
	public void testMultipleRuns()throws Exception{
		double e=ErrorRate.of(ImmutableList.of(
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(Millisecond.of(11), false, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						),
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						)
		)).getValue();
		System.out.println("e="+e);
		Assert.assertEquals("unexpected error rate", 0.25,e);
	}
	
	public void testNoResponseData()throws Exception{
		try{
			ErrorRate.of(ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								new ArrayList<IResponseData>(), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				)).getValue();
			Assert.fail("Expected exception not thrown");
		}catch(ResponseDataMissingException e){
			System.out.println(e.getLocalizedMessage());
		}
	}

}
