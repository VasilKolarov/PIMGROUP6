import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main class to draw the windows for the Personal Information Manager Application
 * @author	Jack Evans
 */
public class PersonalInformationManagerGUI {
	final static String APPOINTEMENTSTAB = "Appointments";
	final static String CONTACTSTAB = "Contacts";
	final static String UNIVERSITYRESOURCESTAB = "University Resources";
	final static String NOTESTAB = "Notes";

	static DataBaseConnection dataBaseConncetion = new DataBaseConnection();

	public static void main(String[] args) {
		// Establish a connection to the DataBase
		dataBaseConncetion.makeConnection();
		try {
			// For Linux:GTKLookAndFeel, Windows:WindowsLookAndFeel, OSX:???
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException | InstantiationException ex) {
			ex.printStackTrace();
		}

		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Create various components and add them to the main window pane
	 */
	public void addComponentToPane(Container pane) {
		JTabbedPane tabbedPane = new JTabbedPane();

		//Create the calendar tab
		JPanel appoinmentsTab = new JPanel();
		fetchAppointmentsData(appoinmentsTab);

		//Create the contacts tab
		JPanel contactsTab = new JPanel();
		fetchContactsData(contactsTab);

		//Create the notes tab
		JPanel notesTab = new JPanel();
		fetchNotesData(notesTab);

		//Create the university resources tab
		JPanel universityResourcesTab = new JPanel();
		universityResourcesTab.add(new JButton("View campus map"));
		universityResourcesTab.add(new JButton("View Univeristy website"));
		universityResourcesTab.add(new JButton("View University news"));


		//Add all the tabs to our main window pane
		tabbedPane.addTab(APPOINTEMENTSTAB, appoinmentsTab);
		tabbedPane.addTab(CONTACTSTAB, contactsTab);
		tabbedPane.addTab(UNIVERSITYRESOURCESTAB, universityResourcesTab);
		tabbedPane.addTab(NOTESTAB, notesTab);

		pane.add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("PerosnalInformationManager");

		// Get the quote of the day and make it appear in pop up window dialog
		QuoteOfTheDay quoteOfTheDay = new QuoteOfTheDay();
		JOptionPane.showMessageDialog(frame, quoteOfTheDay.getQuoteOfTheDay() + ' ' + quoteOfTheDay.getAuthor(), "QuoteOfTheDay", JOptionPane.PLAIN_MESSAGE);

		//Set the dimensions of the window
		frame.setPreferredSize(new Dimension(1000, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		PersonalInformationManagerGUI app = new PersonalInformationManagerGUI();
		app.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 *  Method to fetch and display Contact information
	 */
	public void fetchContactsData(JPanel contactsTab) {
		try {
			ResultSet results = dataBaseConncetion.getTableData("CONTACTS");
			while (results.next()) {
				contactsTab.setLayout(new GridLayout(0, 2));

				contactsTab.add(new JLabel("Forename: "));
				contactsTab.add(new JTextField(results.getString("CONTACT_FORENAME")));

				contactsTab.add(new JLabel("Surname: "));
				contactsTab.add(new JTextField(results.getString("CONTACT_SURNAME")));

				contactsTab.add(new JLabel("Phone Number: "));
				contactsTab.add(new JTextField(results.getString("CONTACT_PHONE")));

				contactsTab.add(new JLabel("Email: "));
				contactsTab.add(new JTextField(results.getString("CONTACT_EMAIL")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Method to fetch and display Appointment information
	 */
	public void fetchAppointmentsData(JPanel appoinmentsTab) {
		try {
			ResultSet results = dataBaseConncetion.getTableData("APPOINTMENTS");
			while (results.next()) {

				appoinmentsTab.setLayout(new GridLayout(0, 2));

				appoinmentsTab.add(new JLabel("Appointment: "));
				appoinmentsTab.add(new JTextField(results.getString("APPOINTMENT_TITLE")));

				appoinmentsTab.add(new JLabel("Category: "));
				appoinmentsTab.add(new JTextField(results.getString("APPOINTMENT_CATEGORY")));

				appoinmentsTab.add(new JLabel("Description: "));
				appoinmentsTab.add(new JTextField(results.getString("APPOINTMENT_DESCRIPTION")));

				appoinmentsTab.add(new JLabel("Location: "));
				appoinmentsTab.add(new JTextField(results.getString("APPOINTMENT_LOCATION")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Method to fetch and display note information
	 */
	private void fetchNotesData(JPanel notesTab) {
		try {
			ResultSet results = dataBaseConncetion.getTableData("NOTES");
			while (results.next()) {

				notesTab.setLayout(new GridLayout(0, 1));

				notesTab.add(new JLabel(results.getString("NOTE_TITLE")));
				notesTab.add(new JTextField(results.getString("NOTE_CONTENT")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}