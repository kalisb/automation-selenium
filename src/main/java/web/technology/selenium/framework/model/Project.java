package web.technology.selenium.framework.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by kalisb on 01.07.17.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="title")
    @NotEmpty(message = "Please enter project title.")
    @Size(min = 6, max = 250, message = "Invalid project name! Your project title must be between 6 and 250 characters")
    private String title;

    @Column(name ="url")
    @Size(min = 10, max = 250)
    @NotBlank
    private String url;

    public Project() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
