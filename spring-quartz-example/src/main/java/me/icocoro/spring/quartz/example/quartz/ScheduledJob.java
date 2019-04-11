package me.icocoro.spring.quartz.example.quartz;

import me.icocoro.spring.quartz.example.Task.BackupTask;
import me.icocoro.spring.quartz.example.Task.ScoreTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean {
	private ScoreTask scoreTask;
	private BackupTask backupTask;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		scoreTask.processBusiness();
		backupTask.processBackup();
	}

	public void setScoreTask(ScoreTask scoreTask) {
		this.scoreTask = scoreTask;
	}

	public void setBackupTask(BackupTask backupTask) {
		this.backupTask = backupTask;
	}

}
