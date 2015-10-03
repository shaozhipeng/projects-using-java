package berton.spring.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import berton.spring.Task.BackupTask;
import berton.spring.Task.ScoreTask;

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
