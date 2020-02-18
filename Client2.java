package chatroom;

import java.net.Socket;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class Client2 {
    private static Socket s;

    private static void connectServer(){
        try {
            s = new Socket("127.0.0.1",6789);
        }catch (Exception e){
            System.out.println("服务器连接失败....");
        }
    }

    public static void main(String[] args) {
        connectServer();
        new setMenu(s);
        new Thread(new RecMsg(s)).start();
    }

}
