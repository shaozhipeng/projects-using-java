package berton.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainLancher {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"quartz-context.xml");
	}

}
