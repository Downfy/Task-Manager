package com.downfy.service.jobs;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.downfy.domain.MessageTask;
import com.downfy.service.MessageGateway;

public class LoggingTasklet implements Tasklet {

	@Autowired
	MessageGateway gateway;

	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		MessageTask message = new MessageTask();
		message.setAction("job-tasklet");
		message.setItem("logging");
		for (int i = 0; i < 50; i++) {
			message.setMessage(i + "");
			gateway.sendMessage(message);
		}
		return RepeatStatus.FINISHED;
	}

}
