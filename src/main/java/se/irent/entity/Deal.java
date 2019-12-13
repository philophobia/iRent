package se.irent.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deal {
    @Id
    private String id;
    private String landlord_id;
    private String tenant_id;
    private String house_id;
    private String status;
    private String create_time;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLandlord_id() {
        return landlord_id;
    }
    public void setLandlord_id(String landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }
    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getHouse_id() {
        return house_id;
    }
    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
