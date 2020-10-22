package com.iyqrj.starmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.iyqrj.starmall.common.Const;
import com.iyqrj.starmall.common.ServerResponse;
import com.iyqrj.starmall.common.TokenCache;
import com.iyqrj.starmall.entity.Level;
import com.iyqrj.starmall.entity.Point;
import com.iyqrj.starmall.entity.User;
import com.iyqrj.starmall.mapper.LevelMapper;
import com.iyqrj.starmall.mapper.PointMapper;
import com.iyqrj.starmall.mapper.UserMapper;
import com.iyqrj.starmall.service.ILevelService;
import com.iyqrj.starmall.service.IPointService;
import com.iyqrj.starmall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iyqrj.starmall.util.DateTimeUtil;
import com.iyqrj.starmall.util.MD5Util;
import com.iyqrj.starmall.vo.UserPointLevelVo;
import com.iyqrj.starmall.vo.UserPointVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrj
 * @since 2020-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private IPointService iPointService;
    @Autowired
    private ILevelService levelService;

    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username,md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Value("${password.salt}")
    public String passwordSalt;

    public ServerResponse<String> register(User user){
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5登陆
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        Level level = new Level();
        level.setUserId(user.getId());
        level.setExperience(new Long(0));
        levelMapper.insert(level);

        Point point = new Point();
        point.setUserId(user.getId());
        point.setTotalPoint(new Long(0));
        pointMapper.insert(point);

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<String> checkValid(String str, String type) {
        if(StringUtils.isNotBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回的密码是空的");
    }

    //    检查问题答案
    public ServerResponse<String> checkAnswer(String username, String question, String answer){
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    //    忘记密码状态中的重置密码
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if(StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }

        if(StringUtils.equals(forgetToken,token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByusername(username,md5Password);
            if(rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            } else {
                return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
            }
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        // updateByPrimaryKeySelective()     根据主键选择性更新
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    public ServerResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("email已存在,请更换email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0) {
            updateUser.setUsername(user.getUsername());
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    public ServerResponse<UserPointVo> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);

        Long totalPoint = iPointService.getPoint(userId).getData();
        UserPointVo userPointVo = assembleUserPointVo(user, totalPoint);

        return ServerResponse.createBySuccess(userPointVo);
    }

    private UserPointVo assembleUserPointVo(User user, Long totalPoint){
        UserPointVo userPointVo = new UserPointVo();
        userPointVo.setAnswer(user.getAnswer());
//        userPointVo.setCreateTime(user.getCreateTime());todo
        userPointVo.setEmail(user.getEmail());
        userPointVo.setId(user.getId());
        userPointVo.setPassword(user.getPassword());
        userPointVo.setPhone(user.getPhone());
        userPointVo.setQuestion(user.getQuestion());
        userPointVo.setRole(user.getRole());
//        userPointVo.setUpdateTime(user.getUpdateTime());todo
        userPointVo.setUsername(user.getUsername());
        userPointVo.setTotalPoint(totalPoint);

        return userPointVo;
    }

    //backend

    /**
     * 校验是否是管理员
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user){
        if(user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<PageInfo> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.selectAllUser();
        List<UserPointLevelVo> userVoList = this.assembleUserPointLevelVo(userList);
        PageInfo pageResult = new PageInfo(userList);
        pageResult.setList(userVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> searchUser(Integer userId, String username, String phone, String roleName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(username)) {
            username = new StringBuilder().append("%").append(username).append("%").toString();
        }
        if(StringUtils.isNotBlank(phone)) {
            phone = new StringBuilder().append("%").append(phone).append("%").toString();
        }
        Integer role = null;
        if(StringUtils.isNotBlank(roleName)) {
            if(StringUtils.equals(roleName, "管理员")){
                role = Const.Role.ROLE_ADMIN;
            } else if(StringUtils.equals(roleName, "普通用户")){
                role = Const.Role.ROLE_CUSTOMER;
            } else {
                return ServerResponse.createBySuccess(new PageInfo(Lists.newArrayList()));
            }
        }
        List<User> userList = userMapper.selectByIUPR(userId,username,phone,role);
        List<UserPointLevelVo> userVoList = this.assembleUserPointLevelVo(userList);
        PageInfo pageResult = new PageInfo(userVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> edit(User user) {
        int resultCount = userMapper.updateByPrimaryKeySelective(user);
        if(resultCount == 0) {
            return ServerResponse.createByErrorMessage("修改失败");
        }
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @Override
    public ServerResponse<User> getUser(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<User> addUserAdmin(User user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_ADMIN);
        //MD5登陆
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加失败");
        }

        return ServerResponse.createBySuccessMessage("添加成功");
    }

    private List<UserPointLevelVo> assembleUserPointLevelVo(List<User> userList){
        List<UserPointLevelVo> userVoList = Lists.newArrayList();
        // 去除密码，转换日期
        for(User user : userList){
            UserPointLevelVo userVo = new UserPointLevelVo();
            if(user.getRole() == 0){
                userVo.setExperience(levelMapper.getUserExperience(user.getId()) + "");
                userVo.setLevel(levelService.getUserLevel(user.getId()).getData());
                userVo.setTotalPoint(pointMapper.getUserPoint(user.getId()) + "");
                userVo.setEmail(user.getEmail());
                userVo.setQuestion(user.getQuestion());
                userVo.setAnswer(user.getAnswer());
            }
            userVo.setId(user.getId());
            userVo.setUsername(user.getUsername());
            userVo.setRoleName(user.getRole() == Const.Role.ROLE_ADMIN ? "管理员" : "普通用户");
            userVo.setPhone(user.getPhone());
//            userVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));todo

            userVoList.add(userVo);
        }
        return userVoList;
    }

}
