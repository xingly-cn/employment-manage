package com.asugar.employmentmanage.controller;

import com.asugar.employmentmanage.common.BackResult;
import com.asugar.employmentmanage.common.ResultCode;
import com.asugar.employmentmanage.entity.User;
import com.asugar.employmentmanage.mapper.UserMapper;
import com.asugar.employmentmanage.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 管理员控制
 */
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    // 管理员页面
    @RequestMapping("/employment/usermanage")
    public String index(){
        return "system/usermanage/usermanage";
    }
    // 获取所有管理员信息
    @RequestMapping("/employment/getallusers")
    public BackResult getAllUsers(User user, @RequestParam("limit") int pageSize, @RequestParam("page") int pageNum){
        List<User> result = userMapper.getAllUsers(user, pageNum, pageSize);
        return new BackResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), result.size(),result);
    }

    @RequestMapping("/employment/getuserbyaccount/{userAccount}")
    public BackResult getUserByAccount(@PathVariable("userAccount") String userAccount){
        return new BackResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), 1,userMapper.getUserByAccount(userAccount));
    }

    @RequestMapping("/employment/adduser")
    public BackResult addUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setUserPwd(MD5Util.getMD5(user.getUserPwd()));
        userMapper.addUser(user);
        return new BackResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), 1,1);
    }

    @RequestMapping("/employment/updateuser")
    public BackResult updateUser(User user){
        userMapper.updateUser(user);
        return new BackResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), 1,1);
    }

    @RequestMapping("/employment/deluser/{userId}")
    public BackResult delInfo(@PathVariable("userId") String userId){
        userMapper.deleteUser(userId);
        return new BackResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), 1,1);
    }

}
