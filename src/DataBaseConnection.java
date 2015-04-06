import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

public class DataBaseConnection {
	// JDBC driver name and database URL
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String PIMDB_URL = "jdbc:mysql://localhost/PIMDataBase";

	// Change this to whatever you had on your local machines
	private static final String USER = "root";
	private static final String PASS = "root";
	Connection MysqlConnection = null;
	Connection PIMConnection = null;

	/**
	 * Connects Java to the MySql Databsae
	 */
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
			} System.out.println("Database already exists");

			// Connect to the new Database
			PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);
			System.out.println("Connected to Database");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(MysqlConnection != null) {
					MysqlConnection.close();
				}
				if(PIMConnection != null) {
					PIMConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  Creates the DataBase if it doesn't already exist
	 */
	public void createDataBase() {
		Statement stmt;
		try {
			// Create the Database
			stmt = MysqlConnection.createStatement();
			stmt.executeUpdate("CREATE DATABASE PIMDataBase");
			System.out.println("Successfully created Database");

			// Make a connection to the newly created DataBase
			PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);

			// Create the Database tables by running an sql script
			String currentDir = System.getProperty("user.dir");
			String script;
			script = currentDir + "CreateDBTables.sql";

            // Initialise object for ScripRunner
            ScriptRunner sr = new ScriptRunner(PIMConnection);

            // Give the input file to Reader
            Reader reader = new BufferedReader(new FileReader(script));

            // Execute the script
            sr.runScript(reader);

			System.out.println("Successfully created Database tables");

			// Ask if user wishes to automatically populate the database with test data
			Scanner input = new Scanner(System.in);
            System.out.println("Run population script [Y/n]?");
            String a = input.next();
            if(a.equalsIgnoreCase("y")) {
				populateDataBase();
			}
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(PIMConnection != null) {
					PIMConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Runs a populate database script to fill the database with sample data
	 */
	public void populateDataBase() {
		// Create the Database tables by running an sql script
		String currentDir = System.getProperty("user.dir");
		String script;
		script = currentDir + "PopulateDBWithTestData.sql";

		// Initialise object for ScripRunner
		ScriptRunner sr = new ScriptRunner(PIMConnection);

		// Give the input file to Reader
		Reader reader = null;
		try {
			reader = new BufferedReader(new FileReader(script));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Execute the script to populate the DataBase
		sr.runScript(reader);
	}
}