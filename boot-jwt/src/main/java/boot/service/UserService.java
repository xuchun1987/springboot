package boot.service;

import boot.entity.LsysUser;
import boot.mybatis.mapper.db1.LsysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private LsysUserMapper lsysUserMapper;

    /***
     * 根据条件查询用户列表信息
     * @param map
     * @return
     */
    public  PageInfo<LsysUser>   getUserLists(Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<LsysUser> list=lsysUserMapper.getPagerUsers(map);
        PageInfo page = new PageInfo(list);
        return page;
    }

    /****
     * 批量删除用户信息
     * @param ids
     * @return
     */
    public  void   deleteUserBatch(Integer[] ids){
        logger.info("删除共"+ids.length+"条信息");
        if (ids.length>0){
            for (int i = 0; i < ids.length; i++) {
                lsysUserMapper.deleteUser(ids[i]);
            }

        }
    }
    /****
     * 单条删除用户信息
     * @param id
     * @return
     */
    public  LsysUser   getUserById(int id) {
       LsysUser user=lsysUserMapper.getUserById(id);
           System.out.print("==================");
        return  user;
    }


    /****
     * 批量删除用户信息
     * @param id
     * @return
     */
    public  void   deleteUserById(int id) {
        lsysUserMapper.deleteUser(id);

    }
}
