package mainMethodPatient;

import connectionManager.connectionManager;
import fileManager.FileManager;
import guiPatient.ConnectingToBitalino;
import guiPatient.GuiPatient;
import guiPatient.Login;
import guiPatient.MainScreen;
import guiPatient.PatientViewer;
import guiPatient.UserConfiguration;

public class MainPatient {
	/*ok kids, now some lessons about semantics: I feel very disappointed with the English language, as the word clusterfuck is not a word 
	 * of its own but if it were, the description you would find attached to it would be this god forsaken, awful, atrocious and ridiculously
	 * complicated mess of a main method. The idea was to keep the GUI interfaces as dumb as they come and shove all the functionality to another
	 * method, usually I have an intermediate class that acts as interface between the GUI and the back end of the application. For some dumb 
	 * reason I cannot explain, I thought this time it would be a good idea to do this on the main method.
	 * BOI was I wrong...*/
	
	/*When you start the application as a client the first thing that should pop up is a login page to connect to the server so the main method
	 * does exatly that, calls a login page.*/
	public static void main(String[] args) {
		FileManager.configure();
		Login login = new Login();
	}
	public static void loadReport(String path,UserProfile up) {
		PatientViewer p= new PatientViewer(up, FileManager.readData(path));
	}
	public static void stopRecording(UserProfile up, String comments,connectionManager cm) {
		FileManager.writeData(up.getBitalinoManager().getECGFull(), up.getBitalinoManager().getEEGFull(), comments);
		up.stopBitalino();
		up.getBitalinoManager().purgeData();
		cm.terminateSession();
		cm.sendReport(FileManager.readData(FileManager.Defaultpath));
		
	}
	/*the login page has 3 buttons: cancel, login and create a new profile. if it selects login it calls this method. 
	 * This method connects to the server and oversees that the password and user name passed down are correct.
	 * If they are, it dismisses the login page and calls in for the application main screen.*/
	public static void login(String userName, String password, String ip, Login window) {
		connectionManager cm=null;
		try {
			//Connection manager method which connects to the server
			cm = new connectionManager(ip);
			try {
				//it tries login in...
				UserProfile up=cm.login(userName, password);
				if(up==null) {
					//if the connection manager returns a null value it means the server did not find the credentials.
					window.incorrectPassword();
				}else {
					//if the connection manager returns a User Profile it means the server DID find the credentials.
					window.dispose();
					MainScreen ms =  new MainScreen(up, cm);
				}
			} catch (Exception e) {
				//if the server found the some of the credentials but the password was wrong it displays this.
				/*Side note, I thing this ought to be changed later on, this could be a security threat...*/
				window.invalidUsername();
				e.printStackTrace();
			}
		} catch (Exception e1) {
			//if the server failed to answer back, display an error message.
			window.failedConnection();
			System.out.println("could not connect to the server.");
		}
	}
	/*This method is the second option of the login page which doesn't imply terminating the application or logging in (Retarded laugh)
	 * if the user wants to create new credentials it passes down the desired user name and password and this method does the magic:
	 * it contacts the server and relays the answer from the server to the user.*/
	public static void createProfile(String userName, String password, String ip, Login window) {
		connectionManager cm=null;
		try {
			cm= new connectionManager(ip);
			try {
				/*the create profile method tries creating the credentials if the server doesn't allow this it will throw an exception.
				 * if everything goes fine it will open a tab so that the user can write his name, weight surname and whatever not.*/
				cm.createProfile(userName, password);
				UserConfiguration uc = new UserConfiguration(cm);
				window.dispose();
			}catch(Exception e1) {
				/*if the server response is not valid, the login window will display an error message saying it didn't like what it saw.*/
				window.profileNotValid();
				e1.printStackTrace();
			}
		} catch (Exception e) {
			/*same thing as before, if the server doesn't answer back tell the user the connection failed or something.*/
			window.failedConnection();
			e.printStackTrace();
		}
	}
	/*once the user has completed all the information needed to create a new profile it selects the send button and this method does the 
	 * magic of contacting the server and sending the information. Once this is done the login page will reopen so that the user may login
	 * with his brand new credentials and retrieve his new profile.*/
	public static void requestNewProfile(UserProfile up, connectionManager cm) {
		cm.sendProfile(up);
		Login l = new Login();
	}
	/*this method is activated when the user presses the symptoms tab, it will tell the server the patient is feeling unwell and will send real 
	 * time data so that the server can view in real time what the user is seeing */
	public static void requestMonitoring(connectionManager cm) {
		cm.sendRealTimeFeed();
	}
	/*the next method should be activated when the user presses the alert button it should tell the connection manager to send an alert to the 
	 * server, not implemented yet...*/
	
	
	public static void connectingToBitalino(UserProfile up,connectionManager cm, String mac) throws Exception{
		up.startBitalino(mac);
		if(!up.bitalinoIsconnected()) {
			throw new Exception();
		}
		System.out.println("success!");
		GuiPatient g = new GuiPatient(up, cm);
	}
	
}
