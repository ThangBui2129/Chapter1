/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * this is my kingdom come, 
 * @author Thắng
 */
public class UDPClient {

    DatagramSocket socket;
    DatagramPacket packet;

    //2: Mở một socket để kết nối.
    public void openConnect() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(e);
        }
    }
    

    //3: Đóng gói thông tin vào gói tin DatagramPacket để gửi đi Server.
    public void sendData(String str) {
        try {
            byte[] data = new byte[1024]; // Bộ đếm gửi dữ liệu
            data = str.getBytes();
            //InetAddress: Quản lý địa chỉ IP và tên máy tính.
            InetAddress ipServer = InetAddress.getByName("localhost");
            packet = new DatagramPacket(data, data.length, ipServer, 3000);
            //Tạo một DatagramPacket chứa dữ liệu và cả địa chỉ của máy nhận dữ liệu.
            //Phương thức trả về một đối tượng thuộc lớp DatagramPacket
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //7: Nhận dữ liệu về.
    public String getDataFromServer() {
        try {
            byte[] data = new byte[1024];
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);

            String str = new String(packet.getData()).trim();
            return str;
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }

    // Tương tác với người dùng.
    public static void main(String args[]) {

        UDPClient student = new UDPClient();
        student.openConnect();
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhập vào một ký tự thường: ");
        String str = scan.nextLine();
        student.sendData(str);
        System.out.println("Ký tự sau khi in hoa: " + student.getDataFromServer());

    }

}
