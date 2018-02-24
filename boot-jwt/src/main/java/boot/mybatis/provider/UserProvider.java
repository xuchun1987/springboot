package boot.mybatis.provider;


import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class UserProvider {

    public String getPagerUsers(Map params) {
        SQL sql = new SQL()
                .SELECT(" l.*,(SELECT userDesc FROM lsys_role r LEFT JOIN lsys_user_role ur ON  r.id=ur.role_id  WHERE user_id=l.id ) role " )
                .FROM("lsys_user l");
        String username=(String) params.get("username");
        if (StringUtils.hasText(username)) {
            sql.WHERE("username LIKE #{username}");
        }
        String name=(String) params.get("name");
        if (StringUtils.hasText(name)) {
            sql.WHERE("name LIKE #{name}");
        }
        return sql.toString();
    }


}
