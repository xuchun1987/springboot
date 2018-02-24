package boot.mybatis.mapper.db1;


import boot.entity.LsysUser;
import boot.mybatis.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LsysUserMapper {


    @Select("select * from lsys_user where username=#{username} and statue='0' ")
    public LsysUser getUserByLogname(@Param("username") String username);

    @Select("select * from lsys_user where id=#{id} ")
    public LsysUser getUserById(int id);

    @SelectProvider(type = UserProvider.class, method = "getPagerUsers")
    public List<LsysUser> getPagerUsers(Map map);

    @Delete("delete from  lsys_user where id = #{id}")
    public int deleteUser(int id);

}
