package boot.security;

import boot.entity.LsysResource;
import boot.entity.LsysUser;
import boot.service.AuthorityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CwbbLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = Logger.getLogger(getClass());




    @Autowired
    private AuthorityService authorityService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {

        LsysUser user = (LsysUser) authentication.getPrincipal();

        if (user == null) {
            throw new RuntimeException("获取登录用户失败！");
        }
        //获取角色对应的资源（这边可以配合redis）
        List<LsysResource> list=authorityService.getRoleResources("0",user.getRoleIds());
        if(CollectionUtils.isEmpty(list)){
            throw new RuntimeException("登录用户尚未分配任何资源，请联系管理员处理！");
        }
        user.setResources(list);
        //request.getSession().setAttribute("user",user);
        //输出登录提示信息
        logger.info("" +
                "\n------------用户：" + user.getUsername() + "登录系统！" +
                "\n------------登录时间：" + new Date() + "" +
                "\n------------IP：" + getIpAddress(request) +
                "\n------------角色组：" + user.getRoleNames());

        super.onAuthenticationSuccess(request, response, authentication);
    }





    public String getIpAddress(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
