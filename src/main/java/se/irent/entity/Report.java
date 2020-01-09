package se.irent.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private int r_id;
    private int h_id;
    private int u_id;
    private String report_user;
    private String report_reason;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date report_time;

    public int getR_id() {
        return r_id;
    }
    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public int getH_id() {
        return h_id;
    }
    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public int getU_id() {
        return u_id;
    }
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getReport_user() {
        return report_user;
    }
    public void setReport_user(String report_user) {
        this.report_user = report_user;
    }

    public String getReport_reason() {
        return report_reason;
    }
    public void setReport_reason(String report_reason) {
        this.report_reason = report_reason;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReport_time() {
        return report_time;
    }
    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }
}
