package chatroom;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class RecMsg implements Runnable{

    private Socket s;
    private JTextArea messageArea;

    public RecMsg(Socket s){
        this.s = s;
        this.messageArea = setMenu.messageArea;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        System.out.println("-----------------接收----------------");
        while(true){
            try {
                System.out.println("------------来一次--------------");
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            }catch (Exception e){
                System.out.println("接收信息失败");
            }
            try {
                String recMsg = in.readLine();
                messageArea.append(recMsg+"\r\n");
            }catch (Exception e){

            }
        }
    }
}
