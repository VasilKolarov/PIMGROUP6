import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

				// Populate the database with test data
				populateDataBase();
			}
			// Connect to the new Database
			PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);
			System.out.println("Connected to Database");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Creates the DataBase if it doesn't already exist
	 */
	public void createDataBase() {
		try {
			// Create the Database
			Statement stmt = MysqlConnection.createStatement();
			stmt.executeUpdate("CREATE DATABASE PIMDataBase");
			stmt.close();

			// Make a connection to the newly created DataBase
			PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);

			// Set up ScriptRunner and Reader to read the .sql files
			String currentDir = System.getProperty("user.dir");
			String script = currentDir + "/CreateDBTables.sql";
			ScriptRunner scriptRunner = new ScriptRunner(PIMConnection);
			Reader reader = new BufferedReader(new FileReader(script));

			// Create Database tables by executing the sql script
			scriptRunner.runScript(reader);

			// Close script runner and reader
			scriptRunner.closeConnection();
			reader.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs a populate database script to fill the Database with sample data
	 */
	public void populateDataBase() {
		// Check with the user if they want to populate the Database with sample data
		Scanner input = new Scanner(System.in);
		System.out.println("Run population script [Y/n]?");
		String a = input.next();
		if(a.equalsIgnoreCase("y")) {
			try {
				PIMConnection = DriverManager.getConnection(PIMDB_URL, USER, PASS);

				// Create the Database tables by running an sql script
				String currentDir = System.getProperty("user.dir");
				String populationScript = currentDir + "/PopulateDBWithTestData.sql";

				// Initialise object for ScripRunner
				ScriptRunner sr = new ScriptRunner(PIMConnection);

				// Give the input file to Reader
				try (Reader reader = new BufferedReader(new FileReader(populationScript))) {
					// Execute the script to populate the DataBase
					sr.runScript(reader);
				} catch (IOException e) {
					System.out.println("Unable to execute populate script");
				}
			} catch (SQLException e) {
				System.out.println("Unable to connect to the Database");
			}
		} else System.out.println("Population script was not run");
	}

	/**
	 * Returns a results set of all the rows form a given table
	 */
	public void getTableData(String table) {
		System.out.println("This is awesome" + table);
	}
}