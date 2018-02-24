package boot.security;

import boot.entity.LsysRole;
import boot.entity.LsysUser;
import boot.mybatis.mapper.db1.LsysRoleMapper;
import boot.mybatis.mapper.db1.LsysUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CwbbUserDetailsService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(getClass());


    @Autowired
    private LsysUserMapper lsysUserMapper;

    @Autowired
    private LsysRoleMapper lsysRoleMapper;




    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("--------------将要查找的用户名："+s);
        //根据登录名，查询用户（这边可以配合redis）
        LsysUser user=lsysUserMapper.getUserByLogname(s.trim());
        if(user==null){
            logger.error("-------------不存在的用户："+s);
            throw new UsernameNotFoundException("-------------不存在的用户："+s);
        }
        //根据用户获取对应的角色列表
        List<LsysRole> list=lsysRoleMapper.getUserRoles(user.getId());
        user.setRoles(list);
        //new User(user.getLogname(),user.getPassword(),user.get);
        return user;
    }


}
