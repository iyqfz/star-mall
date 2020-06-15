package com.iyqrj.starmall.entity;

import java.math.BigDecimal;
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
public class Order_item implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long order_id;

    private Long product_id;

    private BigDecimal current_price;

    private Integer quantity;

    private BigDecimal total_price;

    private Date create_time;

    private Date update_time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(BigDecimal current_price) {
        this.current_price = current_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
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
        return "Order_item{" +
        "id=" + id +
        ", order_id=" + order_id +
        ", product_id=" + product_id +
        ", current_price=" + current_price +
        ", quantity=" + quantity +
        ", total_price=" + total_price +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        "}";
    }
}
