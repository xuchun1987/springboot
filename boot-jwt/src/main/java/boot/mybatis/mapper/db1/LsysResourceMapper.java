package boot.mybatis.mapper.db1;




import boot.entity.LsysResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LsysResourceMapper {


    @Select(" SELECT * FROM lsys_resource WHERE rtype='0' " +
            " AND id IN (SELECT DISTINCT(resource_id) FROM lsys_role_resource WHERE role_id IN (${ids})) " +
            " and p_id=#{pid} "+
            " ORDER BY sort")
    public List<LsysResource> getRoleResources(@Param("pid") String pid, @Param("ids") String ids);

}
