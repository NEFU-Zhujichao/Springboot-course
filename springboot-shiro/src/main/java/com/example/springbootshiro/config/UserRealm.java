package com.example.springbootshiro.config;


import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.repository.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权->doGetAuthenticationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getRole());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userMapper.findUserByName(token.getUsername());
        if (user == null) {
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginUser",user);
        subject.getSession().setAttribute("loginUserName",user.getName());
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
