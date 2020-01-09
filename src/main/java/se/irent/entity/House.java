package se.irent.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class House {
    @Id
    @GeneratedValue
    private int h_id;
    private String house_address;
    private String house_desc;
    private String house_model;
    private String house_area;
    private String house_type;
    private String house_floor;
    private int house_price;
    private String house_image;
    private String community_name;
    private String house_linkman;
    private String house_oriented;
    private String house_detailes_img;
    private String publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publish_time;

    public int getH_id() {
        return h_id;
    }
    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public String getHouse_address() {
        return house_address;
    }
    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getHouse_desc() {
        return house_desc;
    }
    public void setHouse_desc(String house_desc) {
        this.house_desc = house_desc;
    }

    public String getHouse_model() {
        return house_model;
    }
    public void setHouse_model(String house_model) {
        this.house_model = house_model;
    }

    public String getHouse_area() {
        return house_area;
    }
    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_type() {
        return house_type;
    }
    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getHouse_floor() {
        return house_floor;
    }
    public void setHouse_floor(String house_floor) {
        this.house_floor = house_floor;
    }

    public int getHouse_price() {
        return house_price;
    }
    public void setHouse_price(int house_price) {
        this.house_price = house_price;
    }

    public String getHouse_image() {
        return house_image;
    }
    public void setHouse_image(String house_image) {
        this.house_image = house_image;
    }

    public String getCommunity_name() {
        return community_name;
    }
    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getHouse_linkman() {
        return house_linkman;
    }
    public void setHouse_linkman(String house_linkman) {
        this.house_linkman = house_linkman;
    }

    public String getHouse_oriented() {
        return house_oriented;
    }
    public void setHouse_oriented(String house_oriented) {
        this.house_oriented = house_oriented;
    }

    public String getHouse_detailes_img() {
        return house_detailes_img;
    }
    public void setHouse_detailes_img(String house_detailes_img) {
        this.house_detailes_img = house_detailes_img;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublish_time() {
        return publish_time;
    }
    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }
}
