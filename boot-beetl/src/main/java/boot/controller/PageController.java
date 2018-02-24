package boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/authorization")
    public ModelAndView authorization(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", "apk2sf@163.com");
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        Map<String,String> map=new HashMap<>();
        map.put("a","aa");
        Map<String,String> map2=new HashMap<>();
        map2.put("a","bb");
        list.add(map);
        list.add(map2);
        modelAndView.addObject("list",list);
        modelAndView.setViewName("login/authorization.btl");
        logger.info("---------------authorization");
        return modelAndView;

    }

    @GetMapping("/index")
    public String index(){
        return "/page/index.html";

    }
}
