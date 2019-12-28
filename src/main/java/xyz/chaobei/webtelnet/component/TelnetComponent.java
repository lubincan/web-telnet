package xyz.chaobei.webtelnet.component;

import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Copyright (C), 2015-2019
 * FileName: TelnetClient
 * Author:   MRC
 * Date:     2019/12/26 16:07
 * Description: telnet 客户端
 * History:
 */
@Component
public class TelnetComponent {

    private TelnetClient telnetClient = null;

    private InputStream inputStream;

    private OutputStream outputStream;

    private Thread outputThread;

    /**
     * @return void
     * @Author MRC
     * @Description 打开telnet 连接
     * @Date 16:46 2019/12/26
     * @Param [user, pass]
     **/
    public void openSession(String ip,Integer port) throws IOException, InterruptedException {

        if (outputThread != null) {
            //关闭旧的
            outputThread.interrupt();
        }

        telnetClient = new TelnetClient();
        telnetClient.connect(ip,port);

        inputStream = telnetClient.getInputStream();
        outputStream = telnetClient.getOutputStream();

        outputThread = new InputPrintThread(inputStream);

        //守护线程
        outputThread.setDaemon(true);
        outputThread.start();
    }

    /**
     * @return java.lang.String
     * @Author MRC
     * @Description 发送命令
     * @Date 16:53 2019/12/26
     * @Param [send]
     **/
    public String sendCommand(String send) throws IOException {

        //加入换行符
         send = send + "\n";

        if (null == telnetClient) {
            return "连接已关闭";
        }
        outputStream.write(send.getBytes());
        outputStream.flush();

        return "发送成功";
    }

    /**
     * @return void
     * @Author MRC
     * @Description 关闭telnet连接
     * @Date 16:46 2019/12/26
     * @Param []
     **/
    public void closeSession() throws IOException {

        if (telnetClient != null) {
            telnetClient.disconnect();

            //关闭后台thread
            outputThread.interrupt();

            telnetClient = null;
            //告知其进行中断
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        TelnetComponent telnetComponent = new TelnetComponent();

        telnetComponent.openSession("192.168.10.1",23);

        telnetComponent.sendCommand("admin");

        telnetComponent.closeSession();
    }


}