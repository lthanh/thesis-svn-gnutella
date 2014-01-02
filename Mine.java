/*
 Lame way to get local IP address. Java's localHost method returns
 127.0.0.1; s this opens a socket to Sun' web server.
 */

import java.net.*;

public class Mine {

    static int port = 6346;
    static String ipString = "127.0.0.1";
    static IPAddress ipObject = new IPAddress(127, 0, 0, 1, 6346);
    static byte[] serventID = new byte[16];

    public static void updateAddress() {
        try {
            Socket s = new Socket("java.sun.com", 80);
            System.out.println("Port address: " + s.getLocalPort());
            ipString = s.getLocalAddress().getHostAddress();
            
            System.out.println("+++++++++++++++++++++");
            System.out.println("IpString address: " + ipString);
            System.out.println("Local address: " + s.getLocalAddress());
            System.out.println("Inet PORT: " + s.getPort());
            System.out.println("Inet address: " + s.getInetAddress().getHostAddress());
            System.out.println("+++++++++++++++++++++");
            
            byte[] ipbytes = s.getLocalAddress().getAddress();
            ipObject = new IPAddress(ipbytes[0], ipbytes[1], ipbytes[2], ipbytes[3], port);
            for (int i = 0; i < 16; i++) {
                serventID[i] = ipbytes[i % 4];
            }
            s.close();
        } catch (Exception e) {
        }
    }

    public static IPAddress getIPAddress() {
        return ipObject;
    }

    public static byte[] getServentIdentifier() {
        return serventID;
    }

    public static int getPort() {
        return port;
    }

    public static int getSpeed() {
        return (128);
    }
}
