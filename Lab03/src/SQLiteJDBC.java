import java.sql.*;

public class SQLiteJDBC {

	public static void main( String args[]) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:SMTP_SERVER.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS SMTP_DB ( " +
			" IDmail    INTEGER PRIMARY KEY AUTOINCREMENT," +
			" MAIL_FROM TEXT    NOT NULL, " + 
			" RCPT_TO   TEXT    NOT NULL, " + 
			" DATA      TEXT, " + 
			" DATE      DATETIME  default current_timestamp," +
					" STATUS   TEXT    NOT NULL DEFAULT '0' )";
			stmt.executeUpdate(sql);

//			sql = "INSERT INTO SMTP_DB (MAIL_FROM, RCPT_TO, DATA) " +
//			"VALUES (\"MAIL_FROM@lab03.com\", \"RCPT_TO@gmail.com\", \"HOLA MUNDO!\");";
//
//			stmt.executeUpdate(sql);
//			stmt.executeUpdate(sql);
//			stmt.executeUpdate(sql);
//			stmt.executeUpdate(sql);

			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void insertMessage(){

	}

	public void selectInbox(String folder){
//Obtener los registros que se encuentran en la misma
		String sql = "SELECT COUNT(*) FROM SMTP_DB WHERE RCPT_TO = \"MAIL_FROM@gmail.com\"";
		try (var conn = DriverManager.getConnection("");
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

	public void selectOutbox(String folder){

	}

	public void closeConnection(){

	}

}