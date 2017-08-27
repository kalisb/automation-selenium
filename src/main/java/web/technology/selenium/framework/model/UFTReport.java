package web.technology.selenium.framework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by kalisb on 27.08.17.
 */
@Entity
@Table(name = "reports")
public class UFTReport {

    @Id
    @GeneratedValue
    private int id;

    @Column(name ="projectId")
    private int projectId;

    @Column(name ="reportContent")
    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
