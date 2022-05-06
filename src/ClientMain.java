import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;

public class ClientMain {
    public static void main(String[] args) {

        HashSet<LabWork> labWorks = new HashSet<>();
        Date ini_date = new Date();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        Client_App_Func.start(labWorks, reader, ini_date);


    }
}