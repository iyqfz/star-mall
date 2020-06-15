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
public class Pay_info implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付平台：1 支付宝、2 微信、3 银联
     */
    private Integer pay_platform;

    private String platform_code;

    private Date create_time;

    private Date update_time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(Integer pay_platform) {
        this.pay_platform = pay_platform;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
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
        return "Pay_info{" +
        "id=" + id +
        ", pay_platform=" + pay_platform +
        ", platform_code=" + platform_code +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        "}";
    }
}
