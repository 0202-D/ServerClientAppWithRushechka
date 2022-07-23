/**
 * @author Dm.Petrov
 * DATE: 23.07.2022
 */
public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        new Thread(client::write).start();
        new Thread(client::read).start();

    }
}
