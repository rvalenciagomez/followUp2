package com.crmfollowup.batch;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {
	
  public void main() throws Exception {
		
		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    scheduler.start();

	// JobDetail job = new JobDetail();
    // job.setName("dummyJobName");
    // job.setJobClass(HelloJob.class);

    JobDetail job = JobBuilder.newJob(HelloJob.class)
        .withIdentity("dummyJobName", "group1").build();

		
 // SimpleTrigger trigger = new SimpleTrigger();
    // trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
    // trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    // trigger.setRepeatInterval(30000);

 // compute a time that is on the next round minute
//    Date runTime = evenMinuteDate(new Date());
    
    // Trigger the job to run now, and then every 5 seconds
    Trigger trigger = TriggerBuilder
        .newTrigger()
        .withIdentity("dummyTriggerName", "group1")
        .startNow()
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())
//        .withIdentity("trigger1", "group1")
//        .startAt(runTime)
        .build();
    
    // Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(job, trigger);
		
//		Thread.sleep(90L * 1000L);
//		sched.shutdown(true);

	}
}
