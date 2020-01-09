package se.irent.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue
    private int id;
    private String operator_id;
    private String action;
    private String target_kind;
    private String target_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getOperator_id() {
        return operator_id;
    }
    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getTarget_kind() {
        return target_kind;
    }
    public void setTarget_kind(String target_kind) {
        this.target_kind = target_kind;
    }

    public String getTarget_id() {
        return target_id;
    }
    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
