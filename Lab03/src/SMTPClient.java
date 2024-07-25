import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SMTPClient {


    public String SERVER = "localhost";
    public int PORT = 25;


    public String from;
    public String to;
    public String data;
    public Socket socket;
    public BufferedReader in;

    public SMTPClient(String from, String to, String body) {
        this.from = from;
        this.to = to;
        this.data = body;
    }

//	Idea: Crear un metodo send mail, fuera del start y mandar a llamar al send mail hasta de último

    public void sendMessage(PrintWriter out, String from, String[] to, String body) {
        out.println("MAIL FROM: "+from);
        System.out.println("<- MAIL FROM: "+from);
        for (int i = 0; i < to.length; i++) {
            out.println("RCPT TO: "+to[i]);
            System.out.println("<- RCPT TO: "+to[i]);
        }
        out.println("DATA");

        out.println(body);
        out.println(".");
        System.out.println("<- DATA: "+body);
    }


//Crear un arrayList de Strings que lleven cada una de las lineas
    public PrintWriter start(){
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String fromServer;
            int i = 0;


            ArrayList<String> lines = new ArrayList<>();

//          Linea de comandos
            while ((fromServer = in.readLine()) != null) {
                System.out.println("-> fromServer = " + fromServer);
                if (fromServer.startsWith("220")) {
                    out.println("HELO DEMO");
                    System.out.println("<- HELO DEMO");
                } else if (fromServer.startsWith("250")) {
                    out.println("QUIT");
                    System.out.println("<- QUIT");
                } else if (fromServer.equals("221")) {
                    i = 0;
                    break;
                }
            }

            return out;

//			Recorrer estructura

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}