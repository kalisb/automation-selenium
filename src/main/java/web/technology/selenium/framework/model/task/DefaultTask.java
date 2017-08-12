package web.technology.selenium.framework.model.task;

public abstract class DefaultTask implements Task {
	
	private enum Status {
		CORRECT, ERROR
	}
	
	private Status status;
	private boolean finished = false;
	private String name;
	private String time;
	
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String getStatus() {
		return this.status.name();
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String getTime() {
		return this.time;
	}

}
