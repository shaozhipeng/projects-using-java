package berton.spring.Task;

import org.springframework.stereotype.Component;

@Component("testTask")
public class TestTask {

	public void printMessage() {
		System.out.println("TestTask was called by SimpleTriggerFactoryBean");
	}

}
