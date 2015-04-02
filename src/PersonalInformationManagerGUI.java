import java.awt.*;

import javax.swing.*;

public class PersonalInformationManagerGUI {

	final static String CALENDARTAB = "Calendar";
	final static String CONTACTSTAB = "Contacts";
	final static String UNIVERSITYRESOURCESTAB = "University Resources";
	final static String NOTESTAB = "Notes";

	/**
	 * Create various components and add them to the main window pane
	 */
	public void addComponentToPane(Container pane) {
		JTabbedPane tabbedPane = new JTabbedPane();

		//Create the calendar tab
		JPanel calendarTab = new JPanel();
		calendarTab.add(new JButton("Add an appointment"));

		//Create the contacts tab
		JPanel contactsTab = new JPanel();
		contactsTab.add(new JTextField("", 20));
		contactsTab.add(new JButton("Add a contact"));

		//Create the university resources tab
		JPanel universityResourcesTab = new JPanel();
		universityResourcesTab.add(new JButton("View campus map"));
		universityResourcesTab.add(new JButton("View Univeristy website"));
		universityResourcesTab.add(new JButton("View University news"));

		//Create the university resources tab
		JPanel notesTab = new JPanel();
		notesTab.add(new JButton("Add a note"));

		//Add all the tabs to our main window pane
		tabbedPane.addTab(CALENDARTAB, calendarTab);
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

		//Set the dimensions of the window
		//TODO: add the ability to scale the window based on screen size
		frame.setPreferredSize(new Dimension(1000, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		PersonalInformationManagerGUI app = new PersonalInformationManagerGUI();
		app.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			// For Linux:GTKLookAndFeel, Windows:WindowsLookAndFeel, OSX:???
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
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
}
