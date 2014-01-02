
/**
 * Connection object Socket, IPAddress, Reader, Writer Whoever calls should
 * catch IOExceptions
 */
import java.net.*;
import java.io.*;

public class Connection {

    public static int INCOMING = 1;
    public static int OUTGOING = 2;
    public static int DOWNLOADING = 3;
    public static int UPLOADING = 4;
    private Socket socket;
    private int type;
    private IPAddress ip;
    private DataInputStream textReader;
    private DataInputStream byteReader;
    private DataOutputStream byteWriter;

    public Connection(Socket socket, int type) throws IOException {
        this.socket = socket;
        this.type = type;

        int port = socket.getPort();
        byte[] ipbytes = socket.getInetAddress().getAddress();
        int[] ipints = new int[4];
        for (int i = 0; i < 4; i++) {
            ipints[i] = ((int) (ipbytes[i]) & 0xff);
        }

        ip = new IPAddress(ipints[0], ipints[1], ipints[2], ipints[3], port);
        textReader = new DataInputStream(socket.getInputStream());
        byteReader = new DataInputStream(socket.getInputStream());
        byteWriter = new DataOutputStream(socket.getOutputStream());
    }

    public String getTypeString() {
        if (type == 1) {
            return "Incoming";
        } else if (type == 2) {
            return "Outgoing";
        } else if (type == 3) {
            return "Downloading";
        } else if (type == 4) {
            return "Uploading";
        } else {
            return "";
        }
    }

    public int getType() {
        return (type);
    }

    public void changeType(int type) {
        this.type = type;
    }

    public Socket getSocket() {
        return socket;
    }

    public IPAddress getIPAddress() {
        return ip;
    }

    public DataInputStream getTextReader() {
        return textReader;
    }

    public DataInputStream getByteReader() {
        return byteReader;
    }

    public DataOutputStream getByteWriter() {
        return byteWriter;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void closeTextReader() throws IOException {
        textReader.close();
    }

    public void closeByteReader() throws IOException {
        byteReader.close();
    }

    public void closeByteWriter() throws IOException {
        byteWriter.close();
    }

    public void closeStreams() throws IOException {
        this.closeTextReader();
        this.closeByteReader();
        this.closeByteWriter();
    }

    public boolean compareConnections(IPAddress ip) {
        if (ip.equals(this.getIPAddress())) {
            return true;
        } else {
            return false;
        }
    }
}
