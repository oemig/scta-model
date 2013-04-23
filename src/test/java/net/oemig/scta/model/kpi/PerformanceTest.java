package net.oemig.scta.model.kpi;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.test.CountDataImpl;
import net.oemig.scta.model.test.ParticipantImpl;
import net.oemig.scta.model.test.ResponseDataImpl;
import net.oemig.scta.model.test.RunImpl;

import com.google.common.collect.ImmutableList;

public class PerformanceTest extends TestCase {
	
	/**
	 * one run in one session
	 */
	public void testForSession(){
		double p=Performance.of(
				ImmutableList.of(
						RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				),Millisecond.of(20)).getValue();
		
		Assert.assertEquals((double)2/20, p);
	}

	public void testForRun(){
		double p=Performance.of(ImmutableList.of(
				RunImpl.of(
								ImmutableList.of(CountDataImpl.of("A",11,"jeff"), CountDataImpl.of("B", 22, "tim")), 
								ImmutableList.of(ResponseDataImpl.of(Millisecond.of(11), true, "jeff", QuestionType.GroupHow)), 
								ImmutableList.of(ParticipantImpl.of("jeff", ExperiementId.of("DD")), ParticipantImpl.of("tim",ExperiementId.of("DD")))
								) 
				),Millisecond.of(20)).getValue();
		System.out.println("p="+p);
		Assert.assertEquals((double)2/20, p);
	}
}
