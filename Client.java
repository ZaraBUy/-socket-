package chatroom;

import java.net.Socket;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class Client {

    private Socket s;

    private void connectServer(){
        try {
            s = new Socket("127.0.0.1",6789);
        }catch (Exception e){
            System.out.println("服务器连接失败....");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connectServer();
        new setMenu(client.s);
        new Thread(new RecMsg(client.s)).start();
    }



}
