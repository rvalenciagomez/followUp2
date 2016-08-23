package com.crmfollowup.batch;

import java.util.Collection;

import javax.mail.MessagingException;

import org.hibernate.jpa.criteria.MapJoinImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crmfollowup.domain.Oportunidad;
import com.crmfollowup.repository.OportunidadRepository;
import com.crmfollowup.service.SmtpMailSender;


@Component  
public class FollowupBatchBean 
{
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
 
  OportunidadRepository oportRepo;
  
  TaskScheduler taskScheduler;
  
  @Autowired
  private SmtpMailSender smtpMailSender;
  /**
   * Use a cron expression to execute logic on a schedule.
   * 
   * Expression: second minute hour day-of-month month weekday
   * 
   * @see http ://docs.spring.io/spring/docs/current/javadoc-api/org/
   *      springframework /scheduling/support/CronSequenceGenerator.html
   */
//  @Scheduled(cron = "0,30 * * * * *")
  public void cronJob()
  {
    logger.info("< CronJob  --");
    
    Collection<Oportunidad> oportunidades = oportRepo.findAll();
    logger.info("There are {"+ oportunidades.size() +"} number of oportunidades");
    
    logger.info("--  > CronJob");
  }

  
 
  /**
   * Execute logic beginning at fixed intervals with a delay after the
   * application starts. Use the <code>fixedRate</code> element to indicate
   * how frequently the method is to be invoked. Use the
   * <code>initialDelay</code> element to indicate how long to wait after
   * application startup to schedule the first execution.
   * @throws MessagingException 
   */
//  @Scheduled(
//          initialDelayString = "5000",
//          fixedRateString = "15000")
  public void fixedRateJobWithInitialDelay() throws MessagingException {
      logger.info("> ---------------------------");
      
             
        
      
      // Add scheduled logic here
      
      // Simulate job processing time
      long pause = 30000;
      long start = System.currentTimeMillis();
      do {
          if (start + pause < System.currentTimeMillis()) {
              break;
          }
      } while (true);
      logger.info("Processing time was {} seconds.", pause / 1000);
      
//      smtpMailSender.send("rvalenciagomez@quimicadeags.mx", "test mail from Spring", "Hola chico! como anda mae!!..");

      logger.info("< -----------------------");
  }

  
  /**
   * Execute logic with a delay between the end of the last execution and the
   * beginning of the next. Use the <code>fixedDelay</code> element to
   * indicate the time to wait between executions. Use the
   * <code>initialDelay</code> element to indicate how long to wait after
   * application startup to schedule the first execution.
   */
//  @Scheduled(
//          initialDelay = 5000,
//          fixedDelay = 15000)
  public void fixedDelayJobWithInitialDelay() {
      logger.info("> fixedDelayJobWithInitialDelay");

      // Add scheduled logic here

      // Simulate job processing time
      long pause = 5000;
      long start = System.currentTimeMillis();
      do {
          if (start + pause < System.currentTimeMillis()) {
              break;
          }
      } while (true);
      logger.info("Processing time was {} seconds.", pause / 1000);

      logger.info("< fixedDelayJobWithInitialDelay");
  }
  
  
  
  @Autowired
  public void setTaskScheduler(TaskScheduler taskScheduler) {
    this.taskScheduler = taskScheduler;
  }



  @Autowired
  public void setOportRepo(OportunidadRepository oportRepo) {
    this.oportRepo = oportRepo;
  }
    
}
