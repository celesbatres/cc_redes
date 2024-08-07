import java.sql.*;
import java.util.*;

public class SQLite {
    private static final String URL = "jdbc:sqlite:SMTP_SERVER.db";
    public static Connection connection = null;

    public static void main(String[] args) {

    }


    // Constructor que abre la conexión
    public SQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void startConnection(){

    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(String sql) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String sql) {//Reciba los parámetros para la consulta: key, value: Estructura
        ResultSet resultSet = null;
//        Consulta aquí .replace()
        try (Statement stmt = connection.createStatement()) {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet selectInbox(String mailAddress) {//Reciba los parámetros para la consulta: key, value: Estructura
        ResultSet resultSet = null;
        String sql = "SELECT * FROM MAILBOX WHERE RCPT_TO="+mailAddress;
        try (Statement stmt = connection.createStatement()) {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Integer countInbox(String mailAddress) {
        Integer count = 0;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) AS COUNT FROM MAILBOX WHERE RCPT_TO=\""+mailAddress+"\"";
        try (Statement stmt = connection.createStatement()) {
            resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                count = resultSet.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }




}
