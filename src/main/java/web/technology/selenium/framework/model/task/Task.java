package web.technology.selenium.framework.model.task;

public interface Task {
		
	String getStatus();
	String getName();
	String getTime();
	void run();
}
