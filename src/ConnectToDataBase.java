import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class ConnectToDataBase {
	// JDBC driver name and database URL
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String PIMDB_URL = "jdbc:mysql://localhost/PIMDataBase";

	// Change this to whatever you had on your local machines
	private static final String USER = "root";
	private static final String PASS = "root";
	Connection MysqlConnection = null;

	public void makeConnection() {
		Boolean DBExists = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			MysqlConnection = DriverManager.getConnection(DB_URL, USER, PASS);
			// Connection connection = <your java.sql.Connection>
			ResultSet resultSet = MysqlConnection.getMetaData().getCatalogs();
			// Loop through results set of database names to check if the database already exists
			while (resultSet.next()) {
				// Get the database name, which is at position 1
				String databaseName = resultSet.getString(1);
				if (databaseName.equals("PIMDataBase")) {
					DBExists = true;
				}
			}
			resultSet.close();
			// If the database doesn't exist locally then we need to create it
			if (!DBExists) {
				createDataBase();
			}
			else {
				System.out.println("Database already exists");
			}
		} catch (SQLException | ClassNotFoundException se) {
			se.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (MysqlConnection != null)
					MysqlConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void createDataBase() {
		Statement stmt;
		Connection PIMConnection;
		try {
			// Run sql statement to create
			stmt = MysqlConnection.createStatement();
			stmt.executeUpdate("CREATE DATABASE PIMDataBase");
			System.out.println("Created Database");

			// Make a connection to the new database make this a separate function
			PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);

			//Instead we can run .sql files using the mybatis library
			String currentDir = System.getProperty("user.dir");
			String script;
			script = currentDir + "/src/CreateDBTables.sql";

            // Initialise object for ScripRunner
            ScriptRunner sr = new ScriptRunner(PIMConnection);

            // Give the input file to Reader
            Reader reader = new BufferedReader(new FileReader(script));

            // Execute the script
            sr.runScript(reader);
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}