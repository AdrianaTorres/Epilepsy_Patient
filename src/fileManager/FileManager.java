package fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
	public static String Defaultpath=System.getProperty("user.dir")+"\\reports\\LastReport.txt";
	public static void configure(){
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		String reportDir=path+"\\reports";
		System.out.println(configLog);
		File conf= new File(configLog);
		File report= new File(reportDir);
		if(!conf.isFile()|| !report.isDirectory()) {
			try {
				conf.createNewFile();
				if(!report.isDirectory()) {
					System.out.println("Creating report Directory...");
					report.mkdir();
				}
				System.out.println(conf.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("not possible to create config File or folder...");
			}
		}
		if(!report.isDirectory()) {
			report.mkdir();
		}
	}
	
	public static void writeUserConfig(String name, String surname, int age, int weight,char gender, String serverIP) {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		PrintWriter data = null;
		try {
			data = new PrintWriter(manager);
			data.println(name);
			data.println(surname);
			data.println(age);
			data.println(weight);
			data.println(gender);
			data.println(serverIP);
			data.close();
		} catch (Exception e) {
			System.out.println("could not write user config data");
			e.printStackTrace();
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close properly the file");
				e.printStackTrace();
			}
		}
	}
	public static boolean isConfigured() {
		String path=System.getProperty("user.dir");		
		String configLog=path+"\\config log.txt";
		System.out.println(configLog);
		File conf= new File(configLog);
		return conf.isFile();
	}
	public static String[] readUserConfig() {
		File manager= new File(System.getProperty("user.dir")+"\\config log.txt");
		String[] record= new String[6];
		BufferedReader data=null;
		try {
			data= new BufferedReader(new InputStreamReader(new FileInputStream(manager)));
			String readLine="";
			int i=0;
			while((readLine=data.readLine())!= null) {
				if(readLine!=null) {
					record[i]=readLine;
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			record=null;
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not read user config");
				e.printStackTrace();
			}
		}
		return record;
	}
	public static void writeData(List<Double>[]ecg, List<Double>[]eeg, String comments) {
		File manager = new File(System.getProperty("user.dir")+"\\reports\\LastReport.txt");
		System.out.println("I am summoned!");
		PrintWriter data =null;
		try {
			System.out.println(manager.getAbsolutePath());
			manager.createNewFile();
			data= new PrintWriter(new FileOutputStream(manager), true);
			Iterator iterator1= eeg[1].iterator();
			Iterator iterator3= ecg[1].iterator();
			
			data.println("EEG DATA");
			for (Iterator iterator = eeg[0].iterator(); iterator.hasNext();) {
				data.print(iterator.next());
				data.print(" ");
				data.print(iterator1.next());
				data.println();
			}
			data.println("ECG DATA");
			for (Iterator iterator2 = ecg[0].iterator(); iterator2.hasNext();) {
				data.print(iterator2.next());
				data.print(" ");
				data.print(iterator3.next());
				data.println();
			}
			data.println("COMMENTS");
			data.println(comments);
		}catch(Exception e) {
			System.out.println("could not create report file");
			e.printStackTrace();
		}finally {
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close report file");
				e.printStackTrace();
			}
			
		}
	}
	public static Report readData(String path) {
		List<Double> time1 = new ArrayList <Double>();
		List<Double> time2 = new ArrayList <Double>();
		List<Double> ecg = new ArrayList <Double>();
		List<Double> eeg = new ArrayList <Double>();
		String comment="";
		
		File manager = new File(path);
		BufferedReader data= null;
		try {
			System.out.println(manager.getAbsolutePath());
			data = new BufferedReader(new InputStreamReader(new FileInputStream(manager)));
			boolean phaseOneComplete=false;
			boolean comments=false;
			String inputRead;
			while((inputRead=data.readLine())!=null) {
				try {
					parser(inputRead);
					if(!phaseOneComplete) {
						time1.add(parser(inputRead)[0]);
						eeg.add(parser(inputRead)[1]);
					}
					if(phaseOneComplete && ! comments) {
						time2.add(parser(inputRead)[0]);
						ecg.add(parser(inputRead)[1]);
					}
				}catch(Exception e) {
					if(inputRead.contains("ECG")) {
						phaseOneComplete=true;
					}
					if(inputRead.contains("COMMENTS")) {
						comments=true;
					}
					if(comments && !inputRead.contains("COMMENTS")) {
						comment=comment+"\n"+inputRead;
					}
				}
			}
			try {
				data.close();
			}catch(Exception e) {
				System.out.println("could not close reader");
				e.printStackTrace();
			}
			return new Report ((new List[]{time2, ecg}),(new List[]{time1, eeg}),comment);
		} catch (Exception e) {
			System.out.println("could not read report");
			e.printStackTrace();
			return null;
		}
	}
	private static double[]parser(String data){
		char[] temp=data.toCharArray();
		double time=0;
		double input=0;
		String helper="";
		for (int i = 0; i < temp.length; i++) {
			if(temp[i]!=' ') {
				helper=helper+temp[i];
			}else {
				time=Double.parseDouble(helper);
				helper="";
			}
			if(i==temp.length-1) {
				input=Double.parseDouble(helper);
			}
		}
		return new double[] {time, input};
	}
}
