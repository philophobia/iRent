package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {
    @Id
    private String id;
    private String complainant_id;
    private String being_reported_id;
    private String reason;
    private String status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getComplainant_id() {
        return complainant_id;
    }
    public void setComplainant_id(String complainant_id) {
        this.complainant_id = complainant_id;
    }

    public String getBeing_reported_id() {
        return being_reported_id;
    }
    public void setBeing_reported_id(String being_reported_id) {
        this.being_reported_id = being_reported_id;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
