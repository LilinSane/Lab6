import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server_App_func {


    static void file_check(HashSet<LabWork> labWorks) {
        for (LabWork lab : labWorks) {
            boolean isCorrect = false;
            if (lab.getName() == null || lab.getName().equals("")) {
                System.out.println("Вы ввели некоректное имя, повторите попытку");
                while (!isCorrect) {
                    System.out.println("Введите имя: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String name = null;

                    try {
                        s = reader.readLine();
                        if (s.matches("\\w+")) {
                            name = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (name == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setName(name);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCreationDate() == null) {
                while (!isCorrect) {
                    System.out.println("Введите дату создания в формате dd.MM.yyyy: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Date date = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        s = reader.readLine();
                        if (s.matches("[0-3]{1}[0-9]{1}\\.[0-1]{1}[1-2]\\.[1-9]{1}[0-9]{3}")) {
                            date = dateFormat.parse(s);
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                    if (date == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setCreationDate(date);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCoordinates().getX() == null || lab.getCoordinates().getX() <= -28) {
                while (!isCorrect) {
                    System.out.println("Введите координату х: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double x = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("-?[0-9]+\\.?[0-9]*")) {
                            x = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (x == null || x <= -28) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getCoordinates().setX(x);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getCoordinates().getY() <= -886) {
                while (!isCorrect) {
                    System.out.println("Введите координату y: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    float y = -886;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            y = Float.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (y <= -886) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getCoordinates().setY(y);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getMinimalPoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите минимальную оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    int min = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            min = Integer.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (min <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setMinimalPoint(min);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getMaximumPoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите максимальную оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    double max = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            max = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (max <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setMaximumPoint(max);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAveragePoint() == null || lab.getAveragePoint() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите среднюю оценку: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double average = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            average = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (average == null || average <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.setAveragePoint(average);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getDifficulty() == null) {
                while (!isCorrect) {
                    System.out.println("Введите сложность:\n EASY,\n VERY_HARD,\n HOPELESS");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String diff = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[A-Z,a-z]+_?[A-Z,a-z]*")) {
                            diff = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (diff == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else if (diff.toUpperCase().equals("HOPELESS")) {
                        lab.setDifficulty(Difficulty.HOPELESS);
                        isCorrect = true;
                    } else if (diff.toUpperCase().equals("EASY")) {
                        lab.setDifficulty(Difficulty.EASY);
                        isCorrect = true;
                    } else if (diff.toUpperCase().equals("VERY_HARD")) {
                        lab.setDifficulty(Difficulty.VERY_HARD);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getName() == null) {
                while (!isCorrect) {
                    System.out.println("Введите имя автора: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    String name = null;

                    try {
                        s = reader.readLine();
                        if (s.matches("[A-Z,a-z]+")) {
                            name = s;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (name == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setName(name);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getBirthday() == null) {
                while (!isCorrect) {
                    System.out.println("Введите дату в формате dd.MM.yyyy: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Date date = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        s = reader.readLine();
                        if (s.matches("[0-3]{1}[0-9]{1}\\.[0-1]{1}[0-9]\\.[1-9]{1}[0-9]{3}")) {
                            date = dateFormat.parse(s);
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                    if (date == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setBirthday(date);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getHeight() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите высоту: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    float height = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            height = Float.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (height <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setHeight(height);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getWeight() == null || lab.getAuthor().getWeight() <= 0) {
                while (!isCorrect) {
                    System.out.println("Введите вес: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Long weight = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            weight = Long.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (weight == null || weight <= 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().setWeight(weight);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getX() == null) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации х: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Double x = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            x = Double.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (x == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setX(x);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getY() == 0) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации у: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    long y = 0;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            y = Long.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (y == 0) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setY(y);
                        isCorrect = true;
                    }
                }
                isCorrect = false;
            }

            if (lab.getAuthor().getLocation().getZ() == null) {
                while (!isCorrect) {
                    System.out.println("Введите координату локации z: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String s = null;
                    Integer z = null;
                    try {
                        s = reader.readLine();
                        if (s.matches("[0-9]+\\.?[0-9]*")) {
                            z = Integer.valueOf(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (z == null) {
                        System.out.println("Вы ввели некоректные данные, повторите попытку");
                    } else {
                        lab.getAuthor().getLocation().setZ(z);
                        isCorrect = true;
                    }
                }
            }
        }
    }

    static void start(HashSet<LabWork> labWorks, BufferedReader reader, Date ini_date, String filename) {

        ServerModule serverModule = new ServerModule();
        byte[] receiving_data = new byte[1024];
        ByteBuffer buffer;
        Command client_cm = new Command();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Server_response response = new Server_response();
        SocketData socketData = null;
        SocketAddress clientSocketAddress = null;
        DatagramChannel dc = null;
        Selector selector = null;
        SocketAddress socketAddress = null;
        byte[] sending_data = new byte[1024];
        int timeout = 60000;

        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 9254);
            dc = DatagramChannel.open();
            dc.socket().setSoTimeout(timeout);
            dc.configureBlocking(false);
            selector = Selector.open();
            dc.register(selector, SelectionKey.OP_WRITE);
            dc.bind(socketAddress);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
                while (true){

                try {
                    socketData = serverModule.receive(dc, selector, clientSocketAddress);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                client_cm = Serializator.serialize_receiving_data(socketData.getData());
                System.out.println(client_cm.getCm_name());

                if (client_cm.getCm_name().equals("add")) {

                    labWorks.add(client_cm.getLabWork());
                    response.setReport("Элемент успешно добавлен: ");
                    response.setContent(gson.toJson(client_cm.getLabWork()));

                }
                else if (client_cm.getCm_name().matches("update +[0-9]{6}")) {
                    String[] composite_command = client_cm.getCm_name().split(" +");
                    long id = Long.valueOf(composite_command[1]);
                    LabWork client_labwork = client_cm.getLabWork();
                    response = Interactive_App.update_id(labWorks, id, client_labwork);


                }
                else if (client_cm.getCm_name().equals("add_if_max")) {
                    LabWork client_labwork = client_cm.getLabWork();

                    response = Interactive_App.add_if_max(labWorks,client_labwork);
                }
                else if (client_cm.getCm_name().equals("add_if_min")) {
                    LabWork client_labwork = client_cm.getLabWork();

                    response = Interactive_App.add_if_min(labWorks, client_labwork);
                }
                else if (client_cm.getCm_name().equals("remove_lower")) {
                    LabWork client_labwork = client_cm.getLabWork();
                    response = Interactive_App.remove_lower(labWorks, client_labwork);
                }
                else if (client_cm.getCm_name().matches("remove_by_id +[0-9]{6}")) {
                    String[] composite_command = client_cm.getCm_name().split(" +");
                    long id = Long.valueOf(composite_command[1]);
                    response = Interactive_App.remove_by_id(labWorks, id);
                }
                else if (client_cm.getCm_name().equals("clear")) {
                    response = Interactive_App.clear(labWorks);
                }
                else if (client_cm.getCm_name().equals("min_by_creation_date")) {
                    response = Interactive_App.min_by_creation_date(labWorks);
                }
                else if (client_cm.getCm_name().equals("print_field_descending_average_point")) {
                    response = Interactive_App.print_field_descending_average_point(labWorks);
                }
                else if (client_cm.getCm_name().matches("filter_starts_with_name +[a-z,A-Z]+")) {
                    String[] composite_command = client_cm.getCm_name().split(" +");
                    String name = composite_command[1];
                    response = Interactive_App.filter_starts_with_name(labWorks, name);
                }
                else if (client_cm.getCm_name().equals("show")) {
                    response = Interactive_App.show(labWorks);
                }
                else if (client_cm.getCm_name().equals("info")) {
                    response = Interactive_App.info(labWorks, ini_date);
                }
                else if (client_cm.getCm_name().equals("help")) {
                    response = Interactive_App.help();
                }
                else if (client_cm.getCm_name().matches("execute_script")) {

                }

                sending_data = Serializator.serialize_sending_data(response);



                int offset = 1024;
                int i = 0;
                byte[] stopw = "Done".getBytes(StandardCharsets.UTF_8);
                while (offset * i < sending_data.length){
                    byte[] sending_buf = new byte[1024];
                    if (sending_data.length - i * offset <= offset){
                        for (int j = 0; j < sending_data.length - i * offset; j++){
                            sending_buf[j] = sending_data[i * offset + j];
                        }
                        serverModule.send(sending_buf, dc, selector, socketData.getSocketAddress());

                        serverModule.send(stopw, dc, selector, socketData.getSocketAddress());
                        break;
                    }
                    else {
                        for (int j = 0; j < offset; j++){
                            sending_buf[j] = sending_data[i * offset + j];
                        }
                        serverModule.send(sending_buf, dc, selector, socketData.getSocketAddress());
                        i++;
                    }
                }
            }
        }
    }
}

