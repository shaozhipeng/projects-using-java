package me.icocoro.spring.quartz.example.Task;

import org.springframework.stereotype.Component;

@Component("scoreTask")
public class ScoreTask {

	public void processBusiness() {
		// 查询数据库数据，进行相关判断，更新数据库数据状态等
		System.out.println("ScoreTask was called using CronTriggerFactoryBean");
	}

}
