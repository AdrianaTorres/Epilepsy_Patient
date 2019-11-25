package connectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import fileManager.Report;
import mainMethodPatient.UserProfile;

public class connectionManager {
	private boolean requestedMonitoring;
	private Socket manager;
	private PrintWriter pw;
	private BufferedReader bf;
	private Thread t;
	private UserProfile up;

	public connectionManager(String ip) throws Exception{
		try {
			manager= new Socket(ip,9000);
			pw= new PrintWriter(manager.getOutputStream(),true);
			bf= new BufferedReader(new InputStreamReader(manager.getInputStream()));
			requestedMonitoring=false;
		} catch (Exception e) {
			System.out.println("could not connect to server!");
			manager=null;
			pw=null;
			bf=null;
			e.printStackTrace();
			throw new Exception();
		} 
	}
	
	public UserProfile login (String UserName, String Password) throws Exception {
		pw.println("USER REQUESTING LOGIN");
		pw.println(UserName);
		pw.println(Password);
		Thread.sleep(100);
		String serverAnswer=bf.readLine();
		if(serverAnswer.contains("REJECTED")) {
			if(serverAnswer.contains("404")) {
				throw new Exception();
			}else {
				return null;
			}
		}else {
			String name=bf.readLine();
			String surname=bf.readLine();
			int weight = Integer.parseInt(bf.readLine());
			int age = Integer.parseInt(bf.readLine());
			char gender = bf.readLine().toCharArray()[0];
			UserProfile login = new UserProfile(name, surname, weight, age, gender);
			System.out.println(login.getName());
			this.up=login;
			return login;
		}
	}
	public void createProfile(String userName, String password) throws Exception{
		pw.println("USER REQUESTING NEW PROFILE");
		pw.println(userName);
		pw.println(password);
		String serverReply=bf.readLine();
		if (!serverReply.equals("CONFIRM")) {
			throw new Exception();
		}
	}
	public void sendRealTimeFeed() {
		this.pw.println("USER REQUESTING MONITORING");
		this.requestedMonitoring=true;
		t = new Thread(new Runnable() {
			public void run() {
				while(true) {
					List <Double> ecg=up.getBitalinoManager().getECGRealTimeData()[1];
					List <Double> eeg=up.getBitalinoManager().getEEGRealTimeData()[1];
					List <Double> time1=up.getBitalinoManager().getECGRealTimeData()[0];
					List <Double> time2=up.getBitalinoManager().getEEGRealTimeData()[0];
					Iterator iterator_1 = eeg.iterator();
					pw.println("PREPARE TO RECIEVE EEG");
					for (Iterator iterator = time2.iterator(); iterator.hasNext();) {
						pw.println(iterator.next()); //time valor impar
						pw.println(iterator_1.next()); //data valor par
					}
					pw.println("PREPARE TO RECIEVE ECG");
					iterator_1 = ecg.iterator();
					for (Iterator iterator = time1.iterator(); iterator.hasNext();) {
						pw.println(iterator.next());
						pw.println(iterator_1.next());
					}
				}
			}
		});
	}
	public void sendReport(Report rp) {
		this.pw.println("USER REQUESTING NEW REPORT");
		List <Double> time=rp.getEcgData()[0];
		List <Double> data=rp.getEcgData()[1];
		System.out.println("sending report now!");
		pw.println("SENDING ECG");
		Iterator iterator_1=time.iterator();
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			pw.println(iterator_1.next());
			pw.println(iterator.next());
		}
		pw.println("SENDING EEG");
		time=rp.getEegData()[0];
		data=rp.getEegData()[1];
		iterator_1=time.iterator();
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			pw.println(iterator_1.next());
			pw.println(iterator.next());
		}
		pw.println("SENDING COMMENTS");
		pw.println(rp.getComments());
		pw.println("DONE");
	}
	public void sendAlert() {
		this.pw.println("USER REQUESTING ASSISTANCE");
		this.sendRealTimeFeed();
	}
	
	public void sendProfile(UserProfile up) {
		pw.println("USER REQUESTING NEW USER PROFILE");
		pw.println(up.getName());
		pw.println(up.getSurname());
		pw.println(up.getWeight());
		pw.println(up.getAge());
		pw.println(up.getGender());
		
	}
	public void terminateSession() {
		pw.println("FINISHED MONITORING");
	}
}
