package com.zyg.me.shiro;

import com.google.common.base.Preconditions;
import com.zyg.me.commons.JwtUtil;
import com.zyg.me.domain.model.RolesPermissions;
import com.zyg.me.domain.model.UserRoles;
import com.zyg.me.domain.model.UserRolesExample;
import com.zyg.me.service.Exception.UserNotFoundException;
import com.zyg.me.service.Impl.UserServiceImpl;
import com.zyg.me.service.RolesPermissionsService;
import com.zyg.me.service.UserRolesService;
import com.zyg.me.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private  UserService userService;
    @Autowired
    private UserRolesService userRolesService;
    @Autowired
    private RolesPermissionsService rolesPermissionsService;


    @Override
    public boolean supports(AuthenticationToken token) {
        /*
         * 逻辑上表示，JwtRealm 是专门用来验证 JwtToken 合法性的。
         * 它不负责验证 UsernamePasswordToken 等其他 Token 的合法性。
         */
        return token instanceof JwtToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String jwt = (String) principals.getPrimaryPrincipal();
        String username = getJwtUserName(jwt);
        List<UserRoles> roleList = userRolesService.selectByUserName(username);
            log.info(roleList.toString());
        Set<String> roleNameSet = new HashSet<>();
        for (UserRoles role : roleList) {
            roleNameSet.add(role.getRoleName());
        }

        // 去数据库中查，用户名为【username】的用户的相关权限信息
        // 但是，权限不是直接与用户有关系，所以这里是通过 用户的角色来查这个用户的所有权限。
        Set<String> permissionNameSet = new HashSet<>();
        for (String roleName: roleNameSet) {
            // 用每一个角色名，去查这种角色所具有的权限
           List<RolesPermissions> permissionList = rolesPermissionsService.selectByRoleName(roleName);
            for (RolesPermissions permission : permissionList) {
                permissionNameSet.add(permission.getPermission());
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // Shiro 需要的关于这个人的角色的信息的“格式”，是一个角色名的集合（Set）。
        // Shiro 需要的关于这个人的权限的信息的“格式”，也是一个权限名的集合（Set）。
        info.setRoles(roleNameSet);
        info.setStringPermissions(permissionNameSet);

        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        if (jwt == null)
            throw new NullPointerException("jwt token 不允许为空");

        String username = getJwtUserName(jwt);
        // 去数据库判断 username 是否存在
        if(userService.selectByName(username).size()==0)
            throw new UserNotFoundException("用户名为"+username+"的人不存在");
        if(userService.selectByName(username).size()>1)
            throw new UnknownAccountException("数据库错误。存在多位用户名为[" + username + "]的用户");

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
        return authenticationInfo;
    }


    private String getJwtUserName(String jwt) {
        JwtUtil jwtUtil = new JwtUtil();
        if (!jwtUtil.isVerify(jwt))
            throw new UnknownAccountException();
        Claims dmap=jwtUtil.decode(jwt);
        log.info("jwt包含内容:{}"+dmap.toString());
        String username =(String) dmap.get("username");
        return username;
    }
}
