package chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author zhoucx
 * @Description
 * @Date $ $
 **/
public class setMenu {
    static JFrame f;
    static JButton btn_send;
    static JButton btn_clear;
    static JButton btn_close;
    static JTextField txf_name;
    static JTextField txf_send;
    static JTextArea messageArea;
    Socket s;

    setMenu(Socket s) {
        this.s = s;
        createMenu();
        someListener();
    }

    private void someListener() {
        btn_send.addActionListener(new SendMsg3(s));
        btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        txf_send.addKeyListener(new SendMsg3(s));
        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txf_send.setText("");
            }
        });
    }

    private void createMenu() {
        f = new JFrame("聊天室");
        f.setBounds(50, 50, 700, 600);
        f.setLayout(new GridBagLayout());
        JPanel panel_Name = new JPanel();
        JLabel label_name = new JLabel("昵称：");

        txf_name = new JTextField(10);
        panel_Name.add(label_name);
        panel_Name.add(txf_name);
        GridBagConstraints gbc_name = new GridBagConstraints();

        gbc_name.gridx = 2;
        gbc_name.gridy = 0;
        messageArea = new JTextArea("", 20, 50);

        GridBagConstraints gbc_msg = new GridBagConstraints();
        gbc_msg.gridx = 0;
        gbc_msg.gridy = 1;
        gbc_msg.gridwidth = 3;

        JPanel panel_send = new JPanel(new FlowLayout());
        JLabel label_send = new JLabel("发送内容：");
        txf_send = new JTextField(30);
        panel_send.add(label_send);
        panel_send.add(txf_send);
        GridBagConstraints gbc_send = new GridBagConstraints();
        gbc_send.gridx = 0;
        gbc_send.gridy = 2;

        JPanel panel_button = new JPanel(new FlowLayout());
        btn_send = new JButton("发送");
        btn_clear = new JButton("清除");
        btn_close = new JButton("关闭");
        panel_button.add(btn_send);
        panel_button.add(btn_clear);
        panel_button.add(btn_close);
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.fill = GridBagConstraints.BOTH;
        gbc_button.gridx = 0;
        gbc_button.gridy = 3;

        f.add(panel_Name, gbc_name);
        f.add(messageArea, gbc_msg);
        f.add(panel_send, gbc_send);
        f.add(panel_button, gbc_button);
        f.setVisible(true);
    }

    class SendMsg3 implements ActionListener, KeyListener {
        Socket s;

        SendMsg3(Socket s) {
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
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMsg();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        private void sendMsg() {
            String line = txf_name.getText() + ": " + txf_send.getText();
            try {
                PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
                printWriter.println(line);
                System.out.println(line);
                txf_send.setText("");
            } catch (Exception e) {

            }
        }
    }
}
