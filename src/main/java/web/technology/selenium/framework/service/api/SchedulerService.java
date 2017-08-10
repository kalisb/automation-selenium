package web.technology.selenium.framework.service.api;

import java.util.Collection;

import web.technology.selenium.framework.model.Task;

public interface SchedulerService {
	
	void add(Task task);
	boolean hasTasks();
	Collection<Task> getAllTasks();

}
