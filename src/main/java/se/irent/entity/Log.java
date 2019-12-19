package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Log {
    @Id
    private String id;
    private String operator_id;
    private String action;
    private String target_kind;
    private String target_id;
    private String time;

    public String getId() {
        return id;
    }
    public void setId(String id) {
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

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
