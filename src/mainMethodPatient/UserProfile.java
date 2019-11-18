package mainMethodPatient;

import java.util.List;
import bitalinoManager.BitalinoManager;
import fileManager.FileManager;

/*this class only exists to facilitate the existence of the bitalino and the personal data all mashed into a blob that 
 * can be tossed around without having to pass 200 arguments at the same time...
 * Future Rodri, this serves the purpose of simplifying the GUI do NOT repent of this later on, this is actually good.
 * Sincerely: past Rodri*/
public class UserProfile {
	private BitalinoManager m;

	private String name;
	private String surname;
	private int age;
	private int weight;
	private char gender;
	private boolean exists;
	private Thread bitalinoThread;
	private String macAddress;
	
	public UserProfile(String name, String surname, int weight, int age, char gender) {
		this.m=null;
		this.name=name;
		this.surname=surname;
		this.age=age;
		this.weight=weight;
		this.gender=gender;
		exists=true;
		this.bitalinoThread=null;
		this.macAddress="";
	}
	/*Starts the bitalino on another thread, this is not done it will get stuck in a forever loop in the run method of the bitalino:
	 * NOT recommended*/
	public void startBitalino(String mac) {
		m=new BitalinoManager(mac);
		this.macAddress=mac;
		bitalinoThread = new Thread(m);
		bitalinoThread.start();
	}
	/*kills the bitalino thread, once this method is called the information of all the session should be stored in lists which should be
	 * accessible through the getECGdata and getEEGdata methods.*/
	public void stopBitalino() {
		this.bitalinoThread.interrupt();
		this.bitalinoThread=null;
		m.stopThread();
	}
	public boolean bitalinoIsconnected() {
		if(m.isConnected()) {
			return true;
		}else{
			return false;
		}
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getAge() {
		return age;
	}
	public int getWeight() {
		return weight;
	}
	public char getGender() {
		return gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public BitalinoManager getBitalinoManager() {
		return this.m;
	}
	public boolean configExists() {
		return exists;
	}
	public String getMacAddress() {
		return(this.macAddress);
	}

}
