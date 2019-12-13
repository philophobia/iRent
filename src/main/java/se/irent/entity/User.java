package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String password;
    private String nickname;
    private String telephone;
    private String permission_level;
    private boolean real_name_status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPermission_level() {
        return permission_level;
    }
    public void setPermission_level(String permission_level) {
        this.permission_level = permission_level;
    }

    public boolean isReal_name_status() {
        return real_name_status;
    }
    public void setReal_name_status(boolean real_name_status) {
        this.real_name_status = real_name_status;
    }
}
