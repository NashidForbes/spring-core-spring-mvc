package guru.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// @Controller designed to return views
// while @RequestController designed to return literal responses text, json etc
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index(){
       return "index";
    }

}
