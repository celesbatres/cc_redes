import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SMTPClient {


    public String SERVER = "localhost";
    public int PORT = 25;
    public String DOMAIN = "celeste.com";

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public SMTPClient() {
    }

    public SMTPClient(String server, int port) {
        this.SERVER = server;
        this.PORT = port;
    }

//	Idea: Crear un metodo send mail, fuera del start y mandar a llamar al send mail hasta de último

    public void sendMessage(String from, String to, String body) throws IOException {
        ArrayList<String> commands = new ArrayList<>();
        String data = "From: " + from;
        data+="\nTo: " + to;

        String[] bodyLines = body.split("\\\\n");
        for (String line : bodyLines) {
            data += "\n"+ line;
        }
//        data+=body;
//       String body
        commands.add("HELO: " + SERVER);
        commands.add("MAIL FROM: " + from);
        commands.add("RCPT TO: " + to);
        commands.add("DATA");
        commands.add(data);

        commands.add(".");

        commands.forEach(command -> {
            try {
                if (!command.equals(".")) {
                    System.out.println("-> " + in.readLine());
                }
                out.println(command);
                System.out.println("<- " + command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    //Crear un arrayList de Strings que lleven cada una de las lineas
    public boolean start() {
        try {
            this.socket = new Socket(SERVER, PORT);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}