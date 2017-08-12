package web.technology.selenium.framework.service.api;

import web.technology.selenium.framework.model.task.Task;

import java.util.Collection;

public interface SchedulerService {
	
	void add(Task task);
	void remove(Task task);
	boolean hasTasks();
	Collection<Task> getAllTasks();

}
