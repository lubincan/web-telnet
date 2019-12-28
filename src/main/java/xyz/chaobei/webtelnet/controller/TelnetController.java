package xyz.chaobei.webtelnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chaobei.webtelnet.component.TelnetComponent;

import java.io.IOException;

/**
 * Copyright (C), 2015-2019
 * FileName: TelnetController
 * Author:   MRC
 * Date:     2019/12/27 23:31
 * Description:
 * History:
 */
@RestController
@RequestMapping("telnet")
public class TelnetController {

    @Autowired
    private TelnetComponent telnetComponent;

    /**
     * @Author MRC
     * @Description //TODO 打开telnet连接
     * @Date 23:33 2019/12/27
     * @Param [ip, port]
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("open")
    public String openSession(String ip,Integer port){

        try {
            telnetComponent.openSession(ip,port);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    /**
     * @Author MRC
     * @Description 关闭telnet
     * @Date 10:54 2019/12/28
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("close")
    public String closeSession() {
        try {
            telnetComponent.closeSession();
        } catch (IOException e) {

            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    /**
     * @Author MRC
     * @Description 发送命令
     * @Date 11:01 2019/12/28
     * @Param [common]
     * @return java.lang.String
     **/
    @GetMapping("sendCommon")
    public String sendCommon(String common) {

        if (null == common) {
            return "error";
        }
        try {
            telnetComponent.sendCommand(common);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


}