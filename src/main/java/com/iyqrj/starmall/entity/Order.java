package com.iyqrj.starmall.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单总表
 * </p>
 *
 * @author lrj
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    private Long userId;

    private BigDecimal payment;

    /**
     * 支付方式：1 在线支付 0 货到付款
     */
    private Integer payType;

    private String receivingName;

    private Integer reveivingPhone;

    private Integer receivingZipCode;

    private String receivingAddress;

    /**
     * 订单状态：0 待支付、1 已支付、2 已发货、3 确认收货、4 已完成、10 已取消
     */
    private Integer status;

    private Date payTime;

    private Date closeTime;

    private Date sendTime;

    private Date endTime;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
