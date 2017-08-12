package web.technology.selenium.framework.service.impl;

import org.springframework.stereotype.Service;
import web.technology.selenium.framework.model.task.Task;
import web.technology.selenium.framework.service.api.SchedulerService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	
	private Queue<Task> taskQueue = new LinkedList<>();

	@Override
	public void add(Task task) {
		taskQueue.add(task);
	}

	@Override
	public void remove(Task task) {
		taskQueue.remove(task);
	}

	@Override
	public boolean hasTasks() {
		return !taskQueue.isEmpty();
	}

	@Override
	public Collection<Task> getAllTasks() {
		return new LinkedList<>(taskQueue);
	}

}
