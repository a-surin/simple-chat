import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static final int PORT = 1234;
    private static final String WELCOME = "Server is running! Waiting...";

    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        // создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket(PORT);
        System.out.println(WELCOME);
    }

    public void sendAll(String message) {
        for(Client client: clients) {
            client.send(message);
        }    
    }

    public void run()  {
        while(true) {
            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket, this));
            } catch (IOException e)  {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // создаем серверный сокет на порту 1234
        new ChatServer().run();
     }
}
