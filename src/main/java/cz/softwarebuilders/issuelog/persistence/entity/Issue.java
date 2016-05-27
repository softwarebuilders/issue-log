package cz.softwarebuilders.issuelog.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ISSUE")
public class Issue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
