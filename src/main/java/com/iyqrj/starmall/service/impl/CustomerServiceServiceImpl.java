package com.iyqrj.starmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.entity.CustomerService;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.mapper.CustomerServiceMapper;
import com.iyqrj.starmall.mapper.OrdersMapper;
import com.iyqrj.starmall.mapper.UserMapper;
import com.iyqrj.starmall.service.ICustomerServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iyqrj.starmall.util.DateTimeUtil;
import com.iyqrj.starmall.vo.CustomerServiceUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
@Service
public class CustomerServiceServiceImpl extends ServiceImpl<CustomerServiceMapper, CustomerService> implements ICustomerServiceService {

    @Autowired
    private CustomerServiceMapper customerServiceMapper;
    @Autowired
    private OrdersMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse add(Integer userId, Long orderNo, String title, String mainContent) {
        CustomerService customerService = new CustomerService();
        customerService.setUserId(userId);
        customerService.setOrderNo(orderNo);
        customerService.setTitle(title);
        customerService.setMainContent(mainContent);
        customerService.setStatus("已提交");
        customerServiceMapper.insert(customerService);
        orderMapper.updateStatusByOrderNo(userId, orderNo, Const.OrderStatusEnum.ORDER_SERVICE.getCode());
        return ServerResponse.createBySuccessMessage("提交成功");
    }

    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CustomerService> customerServicesList = customerServiceMapper.selectByUserId(userId);
        PageInfo pageResult = new PageInfo(customerServicesList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> deleteById(Integer userId, Integer id, Long orderNo) {
        customerServiceMapper.deleteByPrimaryKeyAndUserId(userId, id);
        orderMapper.updateStatus(userId, orderNo, Const.OrderStatusEnum.ORDER_SUCCESS.getCode());
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse<String> update(Integer userId, CustomerService customerService) {
        customerService.setUserId(userId);
        customerServiceMapper.update(customerService);
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse<PageInfo> listAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CustomerService> customerServicesList = customerServiceMapper.selectAll();
        List<CustomerServiceUserVo> csUserVo = this.assembleCSUserVo(customerServicesList);
        PageInfo pageResult = new PageInfo(csUserVo);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> searchCustomerService(Integer id, String username, String orderNo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Integer userId = null;
        if(StringUtils.isNotBlank(username)) {
            userId = userMapper.selectByUsername(username);
            if(userId == null){
                return ServerResponse.createBySuccess(new PageInfo(Lists.newArrayList()));
            }
        }
        List<CustomerService> customerServiceList = customerServiceMapper.selectbyIUO(id,userId,orderNo);
        List<CustomerServiceUserVo> csUserVo = this.assembleCSUserVo(customerServiceList);
        PageInfo pageResult = new PageInfo(csUserVo);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<CustomerService> get(Integer csId) {
        CustomerService customerService = customerServiceMapper.selectByPrimaryKey(csId);
        return ServerResponse.createBySuccess(customerService);
    }

    @Override
    public ServerResponse<CustomerService> replyService(CustomerService customerService) {
        if(StringUtils.equals(customerService.getStatus(),"结束")){
            orderMapper.updateStatusByOrderNo(customerService.getUserId(),customerService.getOrderNo(),Const.OrderStatusEnum.ORDER_SUCCESS.getCode());
        }
        customerServiceMapper.updateByPrimaryKeySelective(customerService);
        return ServerResponse.createBySuccessMessage("处理成功");
    }

    private List<CustomerServiceUserVo> assembleCSUserVo(List<CustomerService> customerServiceList){
        List<CustomerServiceUserVo> csUserVoList = Lists.newArrayList();
        for(CustomerService cs : customerServiceList){
            CustomerServiceUserVo csUserVo = new CustomerServiceUserVo();
            User user = userMapper.selectByPrimaryKey(cs.getUserId());
            csUserVo.setId(cs.getId());
            csUserVo.setUsername(user.getUsername());
            csUserVo.setTitle(cs.getTitle());
            csUserVo.setMainContent(cs.getMainContent());
            csUserVo.setOrderNo(cs.getOrderNo().toString());
            csUserVo.setReply(cs.getReply());
            csUserVo.setCreateTime(DateTimeUtil.dateToStr(cs.getCreateTime()));
//            csUserVo.setCreateTime(DateTimeUtil.dateToStr(Date.from(cs.getCreateTime().atZone(ZoneId.systemDefault()).toInstant())));
            csUserVo.setStatus(cs.getStatus());
            csUserVoList.add(csUserVo);
        }
        return csUserVoList;
    }
}
