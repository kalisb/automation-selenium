package web.technology.selenium.framework.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
@Entity
@Table(name = "features")
public class UFTFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="projectId")
    private int projectId;

    @Column(name ="featureName")
    private String title;

    @Transient
    private String description;

    @Column(name ="featureContent")
    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] data;

    @Column(name = "featureImpl")
    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] dataImpl;

    @Transient
    private List<UFTScenario> scenarios;


    public UFTFeature() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public List<UFTScenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<UFTScenario> scenarios) {
        this.scenarios = scenarios;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public byte[] getDataImpl() {
        return dataImpl;
    }

    public void setDataImpl(byte[] dataImpl) {
        this.dataImpl = dataImpl;
    }
}
