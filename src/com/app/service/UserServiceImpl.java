package com.app.service;

import com.smbms.mapper.UserMapper;
import com.smbms.pojo.SmbmsRole;
import com.smbms.pojo.SmbmsUser;
import com.smbms.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public PageBean<SmbmsUser> findUserPage(Integer pageIndex, Integer pageSize,String queryname,Integer queryUserRole) {
        // 1.返回 pageBean对象
        PageBean<SmbmsUser> pageBean = new PageBean<>();
        // 设置当前页
        pageBean.setCurrentPageNo(pageIndex);
        // 设置每页显示的行数
        pageBean.setPageSize(pageSize);
        // 2.pageBean   totalCount
        Integer count =  userMapper.getUserCount(queryname,queryUserRole);
        pageBean.setTotalCount(count);
        // 3.结果集  每页显示的内容 result
        List<SmbmsUser> userList  =  userMapper.findUserPage(pageBean.getStartIndex(),pageSize,queryname,queryUserRole);
        pageBean.setResult(userList);
        return pageBean;
    }

    @Override
    public List<SmbmsRole> findRoleList() {

        return userMapper.findRoleList();
    }

    @Override
    public boolean addUserSave(SmbmsUser user) {
        if (userMapper.addUserSave(user) > 0){
            return true;
        }
        return false;
    }

    @Override
    public SmbmsUser findUserByUserCode(String userCode) {


        return userMapper.findUserByUserCode(userCode);
    }

    @Override
    public SmbmsUser findUserById(Integer id) {
        return userMapper.findUserById(id);
    }
}
