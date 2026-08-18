package vpn;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Main {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8080);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        
        System.out.println("Esperando paquete UDP en el puerto 8080...");
        socket.receive(packet);
        System.out.println("Cliente conectado desde: " + packet.getAddress() + ":" + packet.getPort());
        
        socket.close();
    }

    //probar con nc -u 127.0.0.1 8080

    public static byte[] encrypt(String data, SecretKey key) throws Exception {
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }
}