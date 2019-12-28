package xyz.chaobei.webtelnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright (C), 2015-2019
 * FileName: IndexController
 * Author:   MRC
 * Date:     2019/12/27 21:55
 * Description: 前端控制器
 * History:
 */

@Controller
public class IndexController {

    /**
     * @Author MRC
     * @Description 跳转至index
     * @Date 21:55 2019/12/27
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/")
    public String toIndex() {
        return "index";
    }


}