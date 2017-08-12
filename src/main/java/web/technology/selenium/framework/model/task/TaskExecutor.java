package web.technology.selenium.framework.model.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import web.technology.selenium.framework.service.api.SchedulerService;

import java.util.Collection;
import java.util.logging.Logger;

@Component
public class TaskExecutor {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	SchedulerService scheduler;
	
	@Scheduled(cron = "* * * * * *")
	public void run() {
		logger.info("[Task-Scheduler] Check task queue.");
		if (scheduler.hasTasks()) {
			Collection<Task> tasks = scheduler.getAllTasks();
			for (Task task : tasks) {
				logger.warning("[Task-Scheduler] Starting task: " + task.getName());
				task.run();
				while (task.getStatus().equals(DriverTask.Status.RUNNING)) {
					//do nothing
				}
				scheduler.remove(task);
			}
		} else {
			logger.info("[Task-Scheduler] No tasks to run!");
		}
	}

}
