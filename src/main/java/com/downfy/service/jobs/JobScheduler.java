package com.downfy.service.jobs;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

	private static Log logger = LogFactory.getLog(JobScheduler.class);
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("loggingJob")
	private Job loggingJob;

	public void run() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Start job schedule launcher at "
						+ new Timestamp(System.currentTimeMillis()));
			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date",
					dateParam).toJobParameters();
			JobExecution execution = jobLauncher.run(loggingJob, param);
			if (logger.isInfoEnabled())
				logger.info("Job Status : " + execution.getStatus());
		} catch (Exception ex) {
			logger.error("Start job schedule error", ex);
		}
	}
}