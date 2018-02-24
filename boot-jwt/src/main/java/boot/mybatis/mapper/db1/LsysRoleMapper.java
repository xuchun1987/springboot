package boot.mybatis.mapper.db1;


import boot.entity.LsysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LsysRoleMapper {


    @Select("select t2.* from lsys_user_role t1 join lsys_role t2 on(t1.role_id=t2.id) where user_id=#{userid}")
    public List<LsysRole> getUserRoles(@Param("userid") Integer userid);
}
