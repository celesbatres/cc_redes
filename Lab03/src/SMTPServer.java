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

    public void insertDB(String mail_from, String rcpt_to, String data) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO SMTP_DB (MAIL_FROM, RCPT_TO, DATA) " +
                    "VALUES (\""+ mail_from +"\", \""+rcpt_to+"\", \""+data+"\");";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}

class SMTPClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SMTPClientHandler(Socket socket) {
        this.socket = socket;
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
                System.out.println("-> fromClient: " + line);
                if (line.startsWith("HELO")) {
                    clientName = line.substring(5);
                    out.println("250 HELLO " + clientName + ", pleased to meet you");
                } else if (line.startsWith("MAIL FROM:")) {
                    out.println("250 OK MAIL FROM");
					mailFrom = line.substring(line.indexOf(':')+2);
					dominio_mailFrom = mailFrom.substring(mailFrom.indexOf("@")+1);
                } else if (line.startsWith("RCPT TO:")) {
//					Verificar de donde proviene el rcpt
					rcptTo = line.substring(line.indexOf(':')+2);
					dominio_rcptTo = rcptTo.substring(rcptTo.indexOf("@")+1);
                    out.println("250 OK RCPT TO");


                } else if (line.equals("DATA")) {
                    out.println("354 End data with <CR><LF>.<CR><LF>");
                    while ((line = in.readLine()) != null) {
                        if (line.equals(".")) {

                            out.println("250 OK DATA # <-- ID ROW ");
                            break;
                        }
						data = line;
                        System.out.println("Email data: " + line);
                    }
                } else if (line.equals("QUIT")) {
                    out.println("221 Bye " + clientName);
                    clientName = "";
                    break;
                } else {
                    out.println("500 Unknown command");
                }

				if(dominio_rcptTo.equals(clientName)) {
//					Guardar en BD
				}else{
//					Separar data en subject y mensaje
					SMTPClient smtpClient = new SMTPClient(rcptTo, rcptTo, data);
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