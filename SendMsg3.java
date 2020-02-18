package chatroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class SendMsg3  implements ActionListener, KeyListener {
    Socket s;

    SendMsg3(Socket s){
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sendMsg();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            sendMsg();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void sendMsg(){
    }
}
