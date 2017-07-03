package web.technology.selenium.framework.model;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
public class UFTScenario {

    private String title;
    private List<UFTStep> steps;

    public UFTScenario() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UFTStep> getSteps() {
        return steps;
    }

    public void setSteps(List<UFTStep> steps) {
        this.steps = steps;
    }
}
