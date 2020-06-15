package com.iyqrj.starmall.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单总表
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long user_id;

    private BigDecimal payment;

    /**
     * 支付方式：1 在线支付 0 货到付款
     */
    private Integer pay_type;

    private String receiving_name;

    private Integer reveiving_phone;

    private Integer receiving_zip_code;

    private String receiving_address;

    /**
     * 订单状态：0 待支付、1 已支付、2 已发货、3 确认收货、4 已完成、10 已取消
     */
    private Integer status;

    private Date pay_time;

    private Date close_time;

    private Date send_time;

    private Date update_time;

    private Date create_time;

    private Date end_time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public String getReceiving_name() {
        return receiving_name;
    }

    public void setReceiving_name(String receiving_name) {
        this.receiving_name = receiving_name;
    }

    public Integer getReveiving_phone() {
        return reveiving_phone;
    }

    public void setReveiving_phone(Integer reveiving_phone) {
        this.reveiving_phone = reveiving_phone;
    }

    public Integer getReceiving_zip_code() {
        return receiving_zip_code;
    }

    public void setReceiving_zip_code(Integer receiving_zip_code) {
        this.receiving_zip_code = receiving_zip_code;
    }

    public String getReceiving_address() {
        return receiving_address;
    }

    public void setReceiving_address(String receiving_address) {
        this.receiving_address = receiving_address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", user_id=" + user_id +
        ", payment=" + payment +
        ", pay_type=" + pay_type +
        ", receiving_name=" + receiving_name +
        ", reveiving_phone=" + reveiving_phone +
        ", receiving_zip_code=" + receiving_zip_code +
        ", receiving_address=" + receiving_address +
        ", status=" + status +
        ", pay_time=" + pay_time +
        ", close_time=" + close_time +
        ", send_time=" + send_time +
        ", update_time=" + update_time +
        ", create_time=" + create_time +
        ", end_time=" + end_time +
        "}";
    }
}
