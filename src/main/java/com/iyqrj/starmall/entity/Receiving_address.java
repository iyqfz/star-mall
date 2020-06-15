package com.iyqrj.starmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
public class Receiving_address implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer rec_name;

    private Integer phone;

    private String city;

    private String district;

    private String detail_address;

    private Integer zip_code;

    private String province;

    private Date create_time;

    private Date update_time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRec_name() {
        return rec_name;
    }

    public void setRec_name(Integer rec_name) {
        this.rec_name = rec_name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public Integer getZip_code() {
        return zip_code;
    }

    public void setZip_code(Integer zip_code) {
        this.zip_code = zip_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Receiving_address{" +
        "id=" + id +
        ", rec_name=" + rec_name +
        ", phone=" + phone +
        ", city=" + city +
        ", district=" + district +
        ", detail_address=" + detail_address +
        ", zip_code=" + zip_code +
        ", province=" + province +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        "}";
    }
}
