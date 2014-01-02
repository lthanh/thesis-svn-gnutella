
import java.io.*;
import java.util.*;

public class Preferences {

    //public static String FILE_NAME = "C:\\Users\\admin\\Desktop\\Gnutella_Project SVN\\src\\preferences.txt";
    public static String FILE_NAME = "./src/preferences.txt";
    public static int MAX_LIVE = 5;
    public static int MAX_CACHE = 100;
    public static boolean AUTO_CONNECT = true;
    public static int PINGER_TIME = 10000;
    public static int CONNECTOR_TIME = 10000;
    public static String SHAREPATH = "C:\\Users\\admin\\Desktop\\ShareFile";
    public static String SAVEPATH = "C:\\Users\\admin\\Desktop\\DownloadFile";
   // public static String SHAREPATH = "C:\\ShareFile";
   // public static String SAVEPATH = "C:\\DownloadFile";

    public static void readFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            DataInputStream dis = new DataInputStream(fileIn);
            String line;

            while ((line = dis.readLine()) != null) {
                if (line.startsWith("Host: ")) {
                    String address = line.substring(6);
                    StringTokenizer t = new StringTokenizer(address, ":");
                    Host h = new Host(t.nextToken(), Integer.parseInt(t.nextToken()));
                    HostCache.addHost(h);

                    //   System.out.println(h.getPort() +"========"+ h.getName());

                    continue;
                } else if (line.startsWith("Max-Live: ")) {
                    MAX_LIVE = Integer.parseInt(line.substring(10));
                    System.out.println(MAX_LIVE);
                    continue;
                } else if (line.startsWith("Max-Cache: ")) {
                    MAX_CACHE = Integer.parseInt(line.substring(11));
                    System.out.println(MAX_CACHE);
                    continue;
                } else if (line.startsWith("Auto-Connect: ")) {
                    AUTO_CONNECT = ((Boolean.valueOf(line.substring(14))).booleanValue());
                    System.out.println(AUTO_CONNECT);
                    continue;
                } else if (line.startsWith("Pinger-Time: ")) {
                    PINGER_TIME = Integer.parseInt(line.substring(13));
                    System.out.println(PINGER_TIME);
                    continue;
                } else if (line.startsWith("Connector-Time: ")) {
                    CONNECTOR_TIME = Integer.parseInt(line.substring(16));
                    System.out.println(CONNECTOR_TIME);
                    continue;
                } else if (line.startsWith("Shared-Directory: ")) {
                    SHAREPATH = line.substring(18);
                    System.out.println("Shared-Directory is " + SHAREPATH);
                    continue;
                } else if (line.startsWith("Download-Directory: ")) {
                    SAVEPATH = line.substring(20);
                    System.out.println("Download-Directory is " + SAVEPATH);
                    continue;
                }
            }
            dis.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("Unable to read preferences file");
        }
    }

    public static void writeToFile() {
        try {
            PrintWriter fileOut = new PrintWriter(new FileWriter(FILE_NAME));
            for (int i = 0; i < HostCache.getCount(); i++) {
                fileOut.println("Host: " + HostCache.hosts[i].getName() + ":" + HostCache.hosts[i].getPort());
            }
            fileOut.println("Max-Live: " + MAX_LIVE);
            fileOut.println("Max-Cache: " + MAX_CACHE);
            fileOut.println("Auto-Connect: " + AUTO_CONNECT);
            fileOut.println("Shared-Directory: " + SHAREPATH);
            fileOut.println("Download-Directory: " + SAVEPATH);

            System.out.println("Written to file");
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Unable to write to preferences file");
        }
    }
}
