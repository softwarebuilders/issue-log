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
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRIORITY")
    private int priority;
    @Column(name = "MESSAGE_BODY", columnDefinition = "CLOB")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String messageBody;
    @Column(name = "STACK_TRACE", columnDefinition = "CLOB")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String stackTrace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return Priority.fromCode(priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority.getCode();
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
