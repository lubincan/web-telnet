package xyz.chaobei.webtelnet.component;

import xyz.chaobei.webtelnet.enums.SocketIdEnum;
import xyz.chaobei.webtelnet.service.WebSocketServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Copyright (C), 2015-2019
 * FileName: InputPrintThread
 * Author:   MRC
 * Date:     2019/12/27 14:31
 * Description:
 * History:
 */
public class InputPrintThread extends Thread {

    private final InputStream inputStream;

    public InputPrintThread(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {

        int num = 0;

        char[] bytes = new char[1024];

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        try {
            //这里会发生阻塞，通过websocket推送进行
            while (!interrupted() && (num = inputStreamReader.read(bytes)) != -1) {

                for (int i = 0; i < num; i++) {
                    char ab = bytes[i];
                    WebSocketServer.sendInfo(ab + "", SocketIdEnum.TELNET.getValue());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}