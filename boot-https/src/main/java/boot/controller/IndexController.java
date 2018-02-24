package boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {


    @RequestMapping("/index")
    public ResponseEntity<Map<String,Object>> index(){
        Map<String,Object> res=new HashMap<String,Object>();
        res.put("code","0000");
        res.put("msg","访问https成功");
    return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
    }
}
