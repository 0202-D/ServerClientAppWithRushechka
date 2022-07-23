import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Dm.Petrov
 * DATE: 23.07.2022
 */
public class Client {
    private final String SEVER_ADDRESS = "netology.homework";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public Client() {
        init();
    }

    private void init() {
        try {
            socket = new Socket(SEVER_ADDRESS,8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     void read(){
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (true){
                String message = in.readUTF();
                if (message.equalsIgnoreCase("/end")) {
                    System.out.println("Server say goodBay");
                    closeConnections();
                    break;
                }
                System.out.println("ServerMessage : " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void write() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                if (out != null) {
                    out.writeUTF(message);
                }
                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void closeConnections() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
