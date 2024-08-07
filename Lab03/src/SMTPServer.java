import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class SMTPServer {
    private static final int PORT = 25;

    public static void main(String[] args) {
        (new SMTPServer()).start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("SMTP Server is running...");
            while (true) {
                new SMTPClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SMTPClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    HashMap<String, String> smtp;
    ArrayList<String> all_smtp = new ArrayList<>();


    public SMTPClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void insertDB() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO SMTP_DB (MAIL_FROM, RCPT_TO, DATA) " +
                    "VALUES (\""+ all_smtp.get(0) +"\", \""+all_smtp.get(1)+"\", \""+all_smtp.get(2)+"\");";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("220 Welcome to SMTP Server CC8");

            String line;
            String clientName = "";

			String mailFrom = "";
			String rcptTo = "";
			String dominio_mailFrom = "";
			String data = "";
			String dominio_rcptTo = "";
            while ((line = in.readLine()) != null) {
//                parts.add(line);

//                Aquí es donde recibe, en las lines
//                Aquí recorrer la linea y guardar los datos en una estructura
//                Ya, no solamente guardar en variables separadas, sino juntas, ya luego las guardamos en la base de datos, listo
                System.out.println("-> fromClient: " + line);
                if (line.startsWith("HELO")) {
                    clientName = line.substring(5).trim();
                    out.println("250 HELLO " + clientName + ", pleased to meet you");
                } else if (line.startsWith("MAIL FROM:")) {
                    out.println("250 OK MAIL FROM");
					mailFrom = line.substring(line.indexOf(':')+2);
					dominio_mailFrom = mailFrom.substring(mailFrom.indexOf("@")+1);
                    all_smtp.add(mailFrom);
                } else if (line.startsWith("RCPT TO:")) {

//					Verificar de donde proviene el rcpt
					rcptTo = line.substring(line.indexOf(':')+2);
					dominio_rcptTo = rcptTo.substring(rcptTo.indexOf("@")+1);
                    out.println("250 OK RCPT TO");
                    all_smtp.add(rcptTo);
                } else if (line.equals("DATA")) {
                    out.println("354 End data with <CR><LF>.<CR><LF>");
                    while ((line = in.readLine()) != null) {
                        data += line+"\n";
                        if (line.equals(".")) {
//                            Mensaje de respuesta de
                            out.println("250 OK DATA # <-- ID ROW ");
                            break;
                        }
                    }
                    System.out.println("Email data: " + data);
                    all_smtp.add(data);
                    System.out.println("dominioo: "+dominio_rcptTo);
                    if(!dominio_rcptTo.equals("celeste.com")) {
                      System.out.println("Bueno, no corresponde a este dominio. lo mando , igual lo guardo");

//                      TODO: PROBAR CON OTRO SERVER
//                      SMTPClient smtp = new SMTPClient(dominio_rcptTo, 25);
//                      smtp.start();
//                      smtp.sendMessage(mailFrom, rcptTo, data);

                    }
                    insertDB();
                } else if (line.equals("QUIT")) {

                    out.println("221 Bye " + clientName);
                    clientName = "";
                    break;
                } else {
                    out.println("500 Unknown command");
                }




            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}