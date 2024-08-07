import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
* javac UpdateDB.java
* java -cp ".;sqlite-jdbc-3.7.2.jar" UpdateDB
* */

public class UpdateDB {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        String sql;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            sql = "DROP TABLE IF EXISTS MAILBOX";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS FLAG";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS FLAG ( " +
                    " FLAG    INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAME    TEXT NOT NULL) ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Recent\") ";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Seen\") ";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Answered\") ";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Flagged\") ";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Deleted\") ";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FLAG (NAME) VALUES(\"\\Draft\") ";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS MAILBOX ( " +
                    " MAILBOX    INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " MAIL_FROM TEXT    NOT NULL, " +
                    " RCPT_TO   TEXT    NOT NULL, " +
                    " DATA      TEXT, " +
                    " DATE      DATETIME  default current_timestamp," +
                    " STATUS   INTEGER    NOT NULL DEFAULT 0," +
                    " FLAG INTEGER DEFAULT 1," +
                    " CONSTRAINT FK_FLAG" +
                    " FOREIGN KEY (FLAG)" +
                    " REFERENCES FLAG(FLAG)) ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO MAILBOX (MAIL_FROM, RCPT_TO, DATA) VALUES(\"curso_cc8@lab03.edu\", " +
                    "\"celes@celeste.com\" , \"From: " +
                    "curso_cc8@lab03.edu\n" +
                    "To: correo_externo@smtp.celeste.com\n" +
                    "Lab 03 - SMTP\n" +
                    "Hola Mundo!\\nasdflkjasdf\\nasdflkjasdlfkj\\nasdflkjasd;lf\\naslkdfja\n" +
                    "\n" +
                    ".\")";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database updated successfully");
    }
}
