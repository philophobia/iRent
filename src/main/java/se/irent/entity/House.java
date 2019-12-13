package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class House {
    @Id
    private String id;
    private String owner_id;
    private String address;
    private String profile;
    private boolean status;
    private String price;
    private String type;
    private String urls;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getUrls() {
        return urls;
    }
    public void setUrls(String urls) {
        this.urls = urls;
    }
}
