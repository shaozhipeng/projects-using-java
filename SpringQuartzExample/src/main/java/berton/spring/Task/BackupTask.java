package berton.spring.Task;

import org.springframework.stereotype.Component;

@Component("backupTask")
public class BackupTask {

	public void processBackup() {
		System.out
				.println("BackupTask was called using CronTriggerFactoryBean");
	}

}
