/**
 * @author Dm.Petrov
 * DATE: 23.07.2022
 */
public class SeverMain {
    public static void main(String[] args) {
        Server server = new Server();

        new Thread(server::write).start();
        new Thread(server::read).start();

    }

}
