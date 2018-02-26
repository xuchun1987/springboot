package boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Sessioncontroller {

    /**
     * 可以开启双应用来测试，会发现每个请求获取的sessionId都是一样的
     * @param session
     * @return
     */
    @RequestMapping("/getSession")
    public ResponseEntity<Map<String,String>> session(HttpSession session){
        Map<String,String> res=new HashMap<>();
        res.put("sessionId",session.getId());
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }
}
