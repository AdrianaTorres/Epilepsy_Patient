package connectionManager;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import fileManager.Report;
import mainMethodPatient.UserProfile;
import security.Security;

public class connectionManager {
	private boolean requestedMonitoring;
	private Socket manager;
	private PrintWriter pw;
	private BufferedReader bf;
	private Thread t;
	private UserProfile up;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private PrivateKey serverPC;

	public connectionManager(String ip) throws Exception{
		try {
			manager= new Socket(ip,9000);
			pw= new PrintWriter(manager.getOutputStream(),true);
			bf= new BufferedReader(new InputStreamReader(manager.getInputStream()));
			requestedMonitoring=false;
		} catch (Exception e) {
			System.out.println("Could not connect to server!");
			manager=null;
			pw=null;
			bf=null;
			e.printStackTrace();
			throw new Exception();
		}
	}

	public UserProfile login(String UserName, String Password) throws Exception {
		handshake();
		String petition=Security.encryptMessage("USER REQUESTING LOGIN", serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(UserName, serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(Password, serverPC);
		pw.println(petition);
		String serverAnswer = bf.readLine();
		serverAnswer=Security.decryptMessage(serverAnswer, publicKey);
		if (serverAnswer.contains("REJECTED")) {
			if (serverAnswer.contains("404")) {
				throw new Exception();
			}else {
				return null;
			}
		} else {
			String name = bf.readLine();
			name=Security.decryptMessage(name, publicKey);
			String surname = bf.readLine();
			surname=Security.decryptMessage(surname, publicKey);
			String temp=bf.readLine();
			temp=Security.decryptMessage(temp, publicKey);
			int weight = Integer.parseInt(temp);
			temp=bf.readLine();
			temp=Security.decryptMessage(temp, publicKey);
			int age = Integer.parseInt(temp);
			temp=bf.readLine();
			temp=Security.decryptMessage(temp, publicKey);
			char gender = temp.toCharArray()[0];
			UserProfile login = new UserProfile(name, surname, weight, age, gender);
			System.out.println(login.getName());
			this.up=login;
			return login;
		}
	}

	public void createProfile(String userName, String password) throws Exception {
		handshake();
		String petition="USER REQUESTING NEW PROFILE";
		petition=Security.encryptMessage(petition, serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(userName, serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(password, serverPC);
		pw.println(petition);
		String serverReply = bf.readLine();
		serverReply=Security.decryptMessage(serverReply, publicKey);
		if (!serverReply.equals("CONFIRM")) {
			throw new Exception();
		}
	}

	public void sendRealTimeFeed() {
		String petition="USER REQUESTING MONITORING";
		petition= Security.encryptMessage(petition, serverPC);
		this.pw.println(petition);
		this.requestedMonitoring = true;
		t = new Thread(new Runnable() {
			public void run() {
				while(true) {
					List <Double> ecg=up.getBitalinoManager().getECGRealTimeData()[1];
					List <Double> eeg=up.getBitalinoManager().getEEGRealTimeData()[1];
					List <Double> time1=up.getBitalinoManager().getECGRealTimeData()[0];
					List <Double> time2=up.getBitalinoManager().getEEGRealTimeData()[0];
					Iterator iterator_1 = eeg.iterator();
					String petition="PREPARE TO RECIEVE EEG";
					petition=Security.encryptMessage(petition, serverPC);
					pw.println(petition);
					
					for (Iterator iterator = time2.iterator(); iterator.hasNext();) {
						petition=Security.encryptMessage(""+iterator.next(), serverPC);
						pw.println(petition); // time valor impar
						petition=Security.encryptMessage(""+iterator_1.next(), serverPC);
						pw.println(petition); // data valor par
					}
					petition="PREPARE TO RECIEVE ECG";
					petition=Security.encryptMessage(petition, serverPC);
					pw.println(petition);
					iterator_1 = ecg.iterator();
					for (Iterator iterator = time1.iterator(); iterator.hasNext();) {
						petition=Security.encryptMessage(""+iterator.next(), serverPC);
						pw.println(petition);
						petition=Security.encryptMessage(""+iterator_1.next(), serverPC);
						pw.println(petition);
					}
				}
			}
		});
	}
	public void sendReport(Report rp) {
		String petition="USER REQUESTING NEW REPORT";
		this.pw.println(petition);
		List<Double> time = rp.getEcgData()[0];
		List<Double> data = rp.getEcgData()[1];
		System.out.println("sending report now!");
		petition="SENDING ECG";
		petition=Security.encryptMessage(petition, serverPC);
		pw.println(petition);
		Iterator iterator_1 = time.iterator();
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			petition=Security.encryptMessage(""+iterator_1.next(), serverPC);
			pw.println(petition);
			petition=Security.encryptMessage(""+iterator.next(), serverPC);
			pw.println(petition);
		}
		petition="SENDING EEG";
		petition=Security.encryptMessage(petition, serverPC);
		pw.println(petition);
		time = rp.getEegData()[0];
		data = rp.getEegData()[1];
		iterator_1 = time.iterator();
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			petition=Security.encryptMessage(""+iterator_1.next(), serverPC);
			pw.println(petition);
			petition=Security.encryptMessage(""+iterator.next(), serverPC);
			pw.println(petition);
		}
		petition="SENDING COMMENTS";
		petition=Security.encryptMessage(petition, serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(rp.getComments(), serverPC);
		pw.println(petition);
		petition=Security.encryptMessage("DONE", serverPC);
		pw.println(petition);
	}
	public void sendAlert() {
		String petition="USER REQUESTING ASSISTANCE";
		petition=Security.encryptMessage(petition, serverPC);
		this.pw.println(petition);
		this.sendRealTimeFeed();
	}
	
	public void sendProfile(UserProfile up) {
		String petition="USER REQUESTING NEW USER PROFILE";
		petition=Security.encryptMessage(petition, serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(up.getName(), serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(up.getSurname(), serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(""+up.getWeight(), serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(""+up.getAge(), serverPC);
		pw.println(petition);
		petition=Security.encryptMessage(""+up.getGender(), serverPC);
		pw.println(petition);

	}

	public void terminateSession() {
		pw.println("FINISHED MONITORING");
	}
}
