package web.technology.selenium.framework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.nio.charset.Charset;
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

    @NotNull
    @Column(name ="featureName")
    private String title;

    @Transient
    private String description;

    @Column(name ="featureContent")
    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] data;

    @Transient
    private String dataStr;

    @Column(name = "featureImpl")
    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] dataImpl;

    @Transient
    private String dataImplStr;

    @Transient
    private List<UFTScenario> scenarios;

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

    public String readData(byte[] stream) {
        return new String(stream, Charset.defaultCharset());
    }

    public void setDataImpl(byte[] dataImpl) {
        this.dataImpl = dataImpl;
    }

    public String getDataImplStr() {
        return dataImplStr;
    }

    public void setDataImplStr(String dataImplStr) {
        this.dataImplStr = dataImplStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public String getDataStr() {
        return dataStr;
    }
}
