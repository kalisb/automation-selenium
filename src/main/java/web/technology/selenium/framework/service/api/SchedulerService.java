package web.technology.selenium.framework.service.api;

import java.util.Collection;

import web.technology.selenium.framework.model.task.Task;

public interface SchedulerService {
	
	void add(Task task);
	boolean hasTasks();
	Collection<Task> getAllTasks();

}
