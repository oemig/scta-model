package net.oemig.scta.model.kpi;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.impl.pojo.CountDataImpl;
import net.oemig.scta.model.impl.pojo.ParticipantImpl;
import net.oemig.scta.model.impl.pojo.ResponseDataImpl;
import net.oemig.scta.model.impl.pojo.RunImpl;

import com.google.common.collect.ImmutableList;

public class SuccessRateTest extends TestCase{

	//single run in list
	public void testSingleRun()throws Exception{
		double s=SuccessRate.of(ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow),
										ResponseDataImpl.of(Millisecond.of(11), false, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				)).getValue();
		Assert.assertEquals("unexpected success rate", 0.5,s);
	}
	
	public void testMultipleRuns()throws Exception{
		double s=SuccessRate.of(ImmutableList.of(
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
		Assert.assertEquals("unexpected success rate", 0.75,s);
	}

}
