/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.net.*;
import java.io.*;

/**
 *
 * @author Thắng
 */
public class UDPServer {
    
    DatagramSocket socket;
    DatagramPacket packet;
    
    //1: Mở cổng cho client vào.
    public void openConnect() {
        try {
            socket = new DatagramSocket(3000);           
        } catch (SocketException e) {
            System.out.println(e);
        }
    }
    
    // 4: Nhận dữ liệu từ Client gửi lên.
    public String receiveData() {
        try {
            byte[] data = new byte[1024]; // 1024 Vì không biết rõ dữ liệu của luồng đầu vào.
            packet = new DatagramPacket(data, data.length);
            
            socket.receive(packet);
            String str = new String(packet.getData()).trim(); // trim(): Dùng để xóa khoảng trắng ở đầu và cuối chuỗi.
            return str;
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }
    
    //5: Xử lí dữ liệu
    public String handle(String str) {
        return str.toUpperCase();
    }
    
    //6: Trả dữ liệu về.
    public void sendDataToClient(String str) {
        try {
            byte[] data = new byte[1024];
            data = str.getBytes();
            InetAddress ipClient = packet.getAddress();
            
            int port = packet.getPort();
            packet = new DatagramPacket(data, data.length, ipClient, port);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
    public static void main(String args[]) {
        UDPServer server = new UDPServer();
        server.openConnect();
        String str = server.receiveData();
        server.sendDataToClient(server.handle(str));
    }
    
}
