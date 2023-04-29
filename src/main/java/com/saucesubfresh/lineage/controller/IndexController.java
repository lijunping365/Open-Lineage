package com.saucesubfresh.lineage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: 李俊平
 * @Date: 2023-03-04 20:12
 */
@Controller
public class IndexController {

    @GetMapping({"/front/**"})
    public String index(){
        return "index";
    }

}
