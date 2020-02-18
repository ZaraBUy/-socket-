package chatroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class Server {

    ArrayList<Socket> sockets = new ArrayList<>();

    public void TCP_server_last() {
        System.out.println("------------服务器监听消息------------");
        ServerSocket ss;
        try {
            ss = new ServerSocket(6789);
            while (true) {
                Socket accept = ss.accept();
                sockets.add(accept);
                String ip = accept.getInetAddress().getHostAddress();
                System.out.println(accept + "上线！！");
                new Thread(new AcceptClient(accept, ip)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().TCP_server_last();
    }

    class AcceptClient implements Runnable {
        Socket s;
        String ip;

        AcceptClient(Socket s, String ip) {
            this.s = s;
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < sockets.size(); i++) {
                    Socket socket = sockets.get(i);
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(socket.getInetAddress().getHostAddress() + "已上线.....");
                }
                while (true) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String line = bufferedReader.readLine();
                    for (int j = 0; j < sockets.size(); j++) {
                        Socket client = sockets.get(j);
                        PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
                        printWriter.println(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
