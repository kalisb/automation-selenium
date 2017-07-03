package web.technology.selenium.framework.model;

/**
 * Created by kalisb on 01.07.17.
 */
public class UFTStep {

    private String type;
    private String title;
    private String code;

    public UFTStep() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
