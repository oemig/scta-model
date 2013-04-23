package net.oemig.scta.model.kpi;

import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.test.CountDataImpl;
import net.oemig.scta.model.test.ParticipantImpl;
import net.oemig.scta.model.test.ResponseDataImpl;
import net.oemig.scta.model.test.RunImpl;

import com.google.common.collect.ImmutableList;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AvgResponseTimeForgettingTimeRatioTest extends TestCase{
	
	public void testMultipleRuns(){
		double a=AvgResponseTimeForgettingTimeRatio.of(ImmutableList.of(
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(Millisecond.of(22), true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(Millisecond.of(22), false, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						),
				RunImpl.of(
						ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
						ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow),
								ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow)), 
						ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
						)
		), Millisecond.of(100)).getValue();
		
		Assert.assertEquals(0.165, a);
	}

}
