import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class IMAPServer {

    private static final int PORT = 143; // Puerto IMAP
    private static Map<String, String> users = new HashMap<>(); // Usuarios y contraseñas
//    private static HashMap<String, String> response = new HashMap<>();

//    docker imap devel

    public static void main(String[] args) {
        // Agregar un usuario para la autenticación
        users.put("celes@celeste.com", "admin"); // Esto debe ser del SQLite


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor IMAP iniciado en el puerto " + PORT);

            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private ArrayList<String> responses = new ArrayList<>();
        String mailAddress = "celes@celeste.com";

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                out.println("* OK [CAPABILITY IMAP4rev1] IMAP Server Ready");
                String inputLine;
                boolean authenticated = false;


                while ((inputLine = in.readLine()) != null) {
                    System.out.println("> " + inputLine);

                    String[] parts = inputLine.split(" ");
                    String tag = parts[0];

                    if (inputLine.matches("(?i)\\d+ login \"[^\"]+\" \"[^\"]+\"")) {
                        login(tag);
                    } else if (inputLine.matches("(?i)\\d+ select \"inbox\"")) {
                        select(tag);
                    } else if (inputLine.matches("(?i)\\d+ capability")) {
                        capability(tag);
                    } else if (inputLine.matches("(?i)\\d+ uid fetch .*")) {
                        uid_fetch(tag);
                    } else if (inputLine.matches("(?i)\\d+ logout")) {
                        logout(tag);
                    } else {
                        another_req(tag, parts[1]);
                    }
                    response();
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

        // Cosas generales que tengo que recuperar: mensajes: meter un campo extra
        public void response() {
            responses.forEach(line -> {
                out.println(line);
                System.out.println("< " + line);
            });
        }

        public void capability(String tag) {
            responses.clear();
            responses.add("* OK [CAPABILITY IMAP4rev1] IMAP Server Ready");
            responses.add(tag + " OK completed");
        }

        public void login(String tag) {
            responses.clear();
            responses.add(tag + " OK LOGIN completed");
        }

        public void logout(String tag) {
            responses.clear();
            users.clear();
            responses.add("* BYE IMAP4rev1 Server logging out");
            responses.add(tag + " OK LOGOUT completed");
        }

        public void select(String tag) {
            int exists = countInbox("celes@celeste.com");

            responses.clear();
            responses.add("* " + exists + " EXISTS");
            responses.add("* 1 RECENT");
            responses.add("* OK [UNSEEN 2] Message 2 is the first unseen message");
            responses.add(tag + " OK [READ-WRITE] SELECT completed");
        }

        public void fetch(String tag) {
            responses.clear();
            responses.add("* 2 EXISTS");
            responses.add("* 1 RECENT");
            responses.add("* OK [UNSEEN 2] Message 2 is the first unseen message");
            responses.add(tag + " OK [READ-WRITE] SELECT completed");
        }

        /*
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
        }*/
        public void uid_fetch(String tag) {
            String fetch_req = "";
            String requests[] = fetch_req.split(" ");

            String cant = requests[3];//Esto separarlos por (,), luego
            String tokens[] = requests[3].split(",");
            String sql_msg = "";
            for (String token : tokens) {
                if(token.contains(":")){
                    //Token is a range va
                    String[] parts = token.split(":");
                    if(parts[1].equals("*")){
                        sql_msg = "SELECT * FROM MAILBOX WHERE MAILBOX>"+parts[0];
                    }else{
                        sql_msg = "SELECT * FROM MAILBOX WHERE MAILBOX>"+parts[0]+" AND MAILBOX<"+parts[1];
                    }
                }else{
                    //Token is n
                    sql_msg = "SELECT * FROM MAILBOX WHERE MAILBOX="+token;
                }
//                Consulta de select
//                ResultSet resultSet = null;
//                resultSet = stmt.executeQuery(sql);
//                while (resultSet.next()) {
//                    count = resultSet.getInt("COUNT");
//                }
//                stmt.close();
//                c.close();
            }
            int msg =  Integer.parseInt(cant);



            responses.clear();
            responses.add("* 1 FETCH (FLAGS (\\Seen) UID 2024)");
            responses.add(tag + " OK UID completed");
            var url = "jdbc:sqlite:my.db";
            var sql = "SELECT id, name, capacity FROM SMTP_DB wh";
            try (var conn = DriverManager.getConnection(url);
                 var stmt = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    System.out.printf("%-5s%-25s%-10s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("capacity")
                    );
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        public void another_req(String tag, String req) {
            responses.clear();
            responses.add(tag + " OK " + req + " completed");
        }

        public void select_inbox(String tag) {
            responses.clear();
        }


//SQLite queries
        public int countInbox(String mailAddress) {
            Connection c = null;
            Statement stmt = null;
            int count = 0;
            ResultSet resultSet = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");

                stmt = c.createStatement();

                String sql = "SELECT COUNT(*) AS COUNT FROM MAILBOX WHERE RCPT_TO=\""+mailAddress+"\"";
                resultSet = stmt.executeQuery(sql);
                while (resultSet.next()) {
                    count = resultSet.getInt("COUNT");
                }
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            return count;
        }
    }
}