package com.downfy.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.downfy.domain.MessageTask;
import com.downfy.service.MessageGateway;

public class MainSchedule extends Thread {

	public static final Logger logger = LoggerFactory
			.getLogger(MainSchedule.class);
	public static boolean isRunning = true;
	private static BufferedReader keyboard = new BufferedReader(
			new InputStreamReader(System.in));
	private static AbstractApplicationContext context;

	private void menu() {
		String option = "";
		while (isRunning) {
			System.out.println();
			System.out
					.println("Cac tham so khi su dung chuong trinh. Click vao man hinh chon phim can dung");
			System.out
					.println("-  Q Ket thuc chuong tring. Khong duoc tat truc tiep phai dung Q de luu du lieu.");
			System.out.println();
			try {
				option = keyboard.readLine();
			} catch (Exception e) {
			}
			if ("Q".equals(option.toUpperCase())) {
				doExit();
			} else {
				System.out.println("Invalid option. Choose between Q to exit.");
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		MainSchedule execute = new MainSchedule();
		execute.start();
	}

	@Override
	public void run() {
		try {
			context = new ClassPathXmlApplicationContext(
					"classpath:applicationContext-task-queue.xml",
					// "classpath:applicationContext-task-schedule.xml",
					// "classpath:applicationContext-task-worker-logging.xml",
					"classpath:applicationContext-task-worker-amazon.xml");
			context.registerShutdownHook();
		} catch (Exception ex) {
		}
		// MessageGateway gateway = context.getBean(MessageGateway.class);
		// MessageTask message = new MessageTask();
		// message.setAction("amazon-category");
		// message.setItem("category");
		// for (int i = 0; i < 500000; i++) {
		// message.setMessage(i + "");
		// gateway.sendMessage(message);
		// }
		menu();
	}

	private void doExit() {
		isRunning = false;
		try {
			context.close();
			Thread.sleep(3000);
		} catch (Exception ex) {
		}
		System.exit(0);
	}
}