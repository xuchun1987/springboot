package boot.service;

import boot.entity.LsysResource;
import boot.mybatis.mapper.db1.LsysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private LsysResourceMapper lsysResourceMapper;


    /**
     * 递归获取角色组菜单
     * @param pid
     * @param roles
     * @return
     */
    public List<LsysResource> getRoleResources(String pid, String roles){
        List<LsysResource> list=lsysResourceMapper.getRoleResources(pid,roles);
        if(!CollectionUtils.isEmpty(list)){
            for(LsysResource lr:list){
                lr.setList(getRoleResources(String.valueOf(lr.getId()),roles));
            }
        }
        return list;
    }
}
