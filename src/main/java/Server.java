import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Dm.Petrov
 * DATE: 23.07.2022
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private Scanner scanner;

    public Server() {
        init();
    }

    private void init() {
        try {
            serverSocket = new ServerSocket(8088);
            System.out.println("Server starts... Waiting to connect");
            client = serverSocket.accept();
            System.out.println("Client connected "+client.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     void write(){
        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            scanner = new Scanner(System.in);
            while (true){
               String message = scanner.nextLine();
                if (out != null) {
                    out.writeUTF(message);
                }
                if (message.equalsIgnoreCase("/end")) {
                    closeConnections();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void read(){
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            while (true){
                String message = in.readUTF();
                if (message.equalsIgnoreCase("/exit")) {
                    System.out.println("Client say goodBay");
                    out.writeUTF("/end");
                    break;
                }
                System.out.println("The client said :"+message);}
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
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
          scanner.close();
    }
}
