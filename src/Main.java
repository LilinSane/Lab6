import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.net.*;


public class Main {
    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Названия файла не содержится в командной строке, пожалуйста, запустите программу с именем файла");
        return;
        }

        String filename = args[0];
        try {
            FileReader fileReader = new FileReader(filename);
            fileReader.read();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Файл " + filename + " не удаётся открыть");
            return;
        }

        HashSet<LabWork> labWorks = JsonReader.fromDataFile(filename);
        Date ini_date = new Date();
        if (labWorks == null){
            System.out.println("В файле отсутствуют элементы");
            return;
        }

        Server_App_func.file_check(labWorks);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Server_App_func.start(labWorks, reader, ini_date, filename);

        Interactive_App.save(labWorks, filename);

    }


}
