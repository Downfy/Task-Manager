/*
 * Copyright Downfy Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.downfy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.downfy.domain.MessageTask;

/**
 * 
 * @author Tran Anh Tuan <tk1cntt@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-task-queue.xml",
		"classpath:applicationContext-task-schedule.xml",
		"classpath:applicationContext-task-worker-amazon.xml",
		"classpath:applicationContext-task-worker-logging.xml" })
public class MessageGatewayTest {

	@Autowired
	MessageGateway gateway;

	@Test
	public void testGatewayAmazonCategory() {
		MessageTask message = new MessageTask();
		message.setAction("amazon-category");
		message.setItem("category");
		for (int i = 0; i < 50; i++) {
			message.setMessage(i + "");
			gateway.sendMessage(message);
		}
	}

	@Test
	public void testGatewayAmazonItem() {
		MessageTask message = new MessageTask();
		message.setAction("amazon-item");
		message.setItem("item");
		for (int i = 0; i < 50; i++) {
			message.setMessage(i + "");
			gateway.sendMessage(message);
		}
	}
}
