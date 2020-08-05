/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thắng Bùi
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2811);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Handle
            Scanner scan = new Scanner(System.in);
            String message = "";
            while (true) {
                String s = dis.readUTF();
                System.out.println(s);

                System.out.print("Your answer: ");
                String ans = scan.nextLine();
                dos.writeUTF(ans);

                message = dis.readUTF();
                System.out.println(message);
                System.out.println("");
                
                if (message.equalsIgnoreCase("false")) {
                    System.out.println("Bye");
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
