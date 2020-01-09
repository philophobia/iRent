package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int u_id;
    private String u_password;
    private String u_nickname;
    private String u_phone_number;
    private String u_name;
    //private String account_status;

    public int getU_id() {
        return u_id;
    }
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_password() {
        return u_password;
    }
    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_nickname() {
        return u_nickname;
    }
    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_phone_number() {
        return u_phone_number;
    }
    public void setU_phone_number(String u_phone_number) {
        this.u_phone_number = u_phone_number;
    }

    public String getU_name() {
        return u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    /*public String getAccount_status() {
        return account_status;
    }
    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }*/
}
