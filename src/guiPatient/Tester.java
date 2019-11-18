package guiPatient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tester {

	public static void main(String[] args) {
		/*String name="Pepe";
		String surname="The Frog";
		int age=34;
		char gender ='f';
		int weight=75;
		double [] time=new double[1000];
		double [] yAxis=new double[1000];
		double [] yAxis2=new double[1000];
		
		double t=0;
		for (int i = 0; i < 1000; i++) {
			time[i]=t;
			t+=0.1;
			yAxis[i]=Math.random()*10;
			yAxis[i]=Math.random()*100;
		}
		GuiPatient patientViewer=new  GuiPatient(time, yAxis,time, yAxis2, name, surname, weight, gender, age);
		while(true) {
			for (int i = 0; i < 1000; i++) {
				time[i]=t;
				t+=0.1;
				yAxis[i]=Math.random()*10;
				yAxis2[i]=Math.random()*100;
			}
		}*/
		/*FileManager f= new FileManager();
		f.writeUserConfig("Fat", "Joe", 34, 120, 'm');
		f.readUserConfig();*/
		/*UserProfile up = new UserProfile();
		up.setName("Test");
		up.setSurname("Tester");
		up.setAge(34);
		up.setWeight(80);
		up.setGender('f');*/
		//GuiPatient p = new GuiPatient(up);
		/*BitalinoManager m = new BitalinoManager();
		m.connect("20:16:07:18:17:85");
		m.start();
		m.run();
		while(true) {
		}*/
		ServerSocket serverSocket=null;
		try {
			serverSocket = new ServerSocket(9009);
		} catch (IOException e1) {
			System.out.println("could not create server socket");
			e1.printStackTrace();
		}
		Socket socket=null;
		while(true) {
			
			try {
		        socket = serverSocket.accept();
		        System.out.println("Connection client created");
		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
		        String line;
		        while ((line = bufferedReader.readLine()) != null) {
		        	System.out.println(line);
		            if(line.equals("USER REQUESTING LOGIN")) {
		            	System.out.println("server acknowledges");
		            	pw.println("ACCEPTED");
		            	pw.println("Sloth");
		            	pw.println("Thy Lord");
		            	pw.println("80");
		            	pw.println("35");
		            	pw.println("m");
		            }
		            if(line.equals("USER REQUESTING NEW PROFILE")) {
		            	pw.println("CONFIRM");
		            }
		            if(line.equals("USER REQUESTING MONITORING")) {
		            	boolean ecg;
		            	List <Double> ecgData=new ArrayList<Double>();
						List <Double> eegData=new ArrayList<Double>();
						List <Double> time1=new ArrayList<Double>();
						List <Double> time2=new ArrayList<Double>();
		            	while(true) {
		            		String data= bufferedReader.readLine();
		            		System.out.println(data);
		            		int counter=0;
		            		if(data.equals("FINISHED MONITORING")) {
		            			break;
		            		}
		            		else {
		            			if(data.contains("ECG")) {
		            				ecg=true;
		            				counter=0;
		            				if(!eegData.isEmpty()) {
		            					Iterator iterator1= time2.iterator();
		            					for (Iterator iterator = eegData.iterator(); iterator.hasNext();) {
											double eeg = (double) iterator.next();
											double time = (double) iterator1.next();
											System.out.println(time+"   "+eeg);
										}
		            					eegData.removeAll(eegData);
		            					time2.removeAll(time2);
		            				}
		            			}
		            			if(data.contains("EEG")) {
		            				ecg=false;
		            				counter=0;
		            				if(!ecgData.isEmpty()) {
		            					Iterator iterator1= time1.iterator();
		            					for (Iterator iterator = ecgData.iterator(); iterator.hasNext();) {
											double ecgd = (double) iterator.next();
											double time = (double) iterator1.next();
											System.out.println(time+"   "+ecgd);
										}
		            					eegData.removeAll(ecgData);
		            					time2.removeAll(time1);
		            				}
		            				try {
			            				double blob= Double.parseDouble(data);
			            				if(ecg) {
			            					if(counter%2==0) {
			            						time1.add(blob);
			            					}else {
			            						ecgData.add(blob);
			            					}
			            				}else {
			            					if(counter%2==0) {
			            						time2.add(blob);
			            					}else {
			            						eegData.add(blob);
			            					}
			            				}
			            				counter++;
			            			}catch(Exception e) {
			            				continue;
			            			}
		            			}
		            		}
		            	}
		            }
		            if(line.equals("USER REQUESTING NEW REPORT")) {
		            	System.out.println("paso!");
		            	while(!line.equals("DONE")) {
		            		line=bufferedReader.readLine();
		            		System.out.println(line);
		            	}
		            }
		        }
			}catch(Exception e) {
				System.out.println("connection ended");
				try {
					socket.close();
				}catch(Exception ex) {
					System.out.println("could not close socket!");
					System.exit(0);
				}
			}
		}
		
		
		
	}
	private static void releaseResources(BufferedReader bufferedReader,
            Socket socket, ServerSocket socketServidor) {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }

        try {
            socketServidor.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }

}
