/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thắng Bùi
 */
public class Server {

    public static void main(String[] args) {
        ArrayList<Quiz> listqz = new ArrayList<Quiz>();
        File file = new File("src\\tcp\\quiz.dat");
        //Read file
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String a[] = s.split("\\$");
                Quiz q = new Quiz(a[0], a[1], a[2]);
                listqz.add(q);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            //System.out.println(e);
        }

        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Server OK");
            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client connected!");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                //Handle               
                for (int i = 0; i < listqz.size(); i++) {
                    String message = "";
                    Quiz q = listqz.get(i);

                    String s = q.getQuestion() + "\n" + q.getSuggestion();
                    dos.writeUTF(s);

                    String ans = dis.readUTF();

                    if (ans.equalsIgnoreCase(q.getAnswer())) {
                        message += "true";
                    } else {
                        message += "false";
                    }
                    dos.writeUTF(message);
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
