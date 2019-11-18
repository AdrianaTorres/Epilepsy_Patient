package bitalinoManager;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BitalinoManager implements BitalinoModel, Runnable{
	
	private boolean connected;
	private BITalino bitalino = null;
	
	private List<Double> ECGtime;
	private List<Double> EEGtime;
	private List<Double> ECGdata;
	private List<Double> EEGdata;
	
	private List<Double> ECGtimeShort;
	private List<Double> EEGtimeShort;
	private List<Double> ECGdataShort;
	private List<Double> EEGdataShort;
	
	private long time;
	private static Frame[] frame;
	
	/*Bitalino model explained: */
	/*basic constructor, does all the instructions needed to connect to the bitalino and then declares the following:
	 * ECG and EEG variables and variants are used to store all the data since the beginning of the recording. ECG and EEG
	 * short variables are used to store the last X values of the recording. Why? because the real time plot compresses the more
	 * points you add to it, which means that at some point in time it just won't be recognizable at all. */
	public BitalinoManager(String Mac) {
		connected=false;
		try {
			bitalino= new BITalino();
			String macAddress = Mac;
			int SamplingRate = 10;
			System.out.println("connecting...");
			System.out.println(macAddress);
			bitalino.open(macAddress, SamplingRate);
			System.out.println("connected!");
			int[] channelsToAcquire = {1,3};
			bitalino.start(channelsToAcquire);
			connected =true;
			ECGtime=new ArrayList<>();
			EEGtime=new ArrayList<>();
			ECGdata=new ArrayList<>();
			EEGdata=new ArrayList<>();
			ECGtimeShort=new ArrayList<>();
			EEGtimeShort=new ArrayList<>();
			ECGdataShort=new ArrayList<>();
			EEGdataShort=new ArrayList<>();
		}catch (Exception e) {
			Logger.getLogger(BitalinoManager.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("could not connect to the bitalino, please try again");
		} catch (Throwable e) {
			Logger.getLogger(BitalinoManager.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public boolean isConnected() {
		return connected;
	}
	//these 2 next methods are used to return the last X values of the bitalino, has an override because the real time plot requires it so

	@Override
	public List<Double>[] getECGRealTimeData() {
		return new List[]{this.ECGtimeShort, this.ECGdataShort};
	}

	@Override
	public List<Double>[] getEEGRealTimeData() {
			return new List[]{this.EEGtimeShort, this.EEGdataShort};
	}
	//this method is the main thing, it samples values from the bitalino and ensures that the maximum amount of values in the short lists is always
	//under X, in this case X is defined as 80. When we have 80 data points stored, it removes the last one before adding the next one. Keeping 
	//things constant.
	@Override
	public void run() {
		try {
			this.time=System.currentTimeMillis();
			while(true) {
				frame = bitalino.read(1);
				for (int i = 0; i < frame.length; i++) {
					if(ECGtimeShort.size()>80) {
						this.ECGdata.add(this.ECGdataShort.remove(0));
						this.ECGtime.add(this.ECGtimeShort.remove(0));
					}
					if(EEGtimeShort.size()>80) {
						double exitVal1=this.EEGdataShort.remove(0);
						this.EEGdata.add(exitVal1);
						exitVal1=this.EEGtimeShort.remove(0);
						this.EEGtime.add(exitVal1);
					}
					double actualTime=(double)(System.currentTimeMillis()-time);
					this.ECGtimeShort.add(actualTime);
					this.EEGtimeShort.add(actualTime);
					this.ECGdataShort.add((double) frame[i].analog[0]);
					this.EEGdataShort.add((double) frame[i].analog[1]);
				}
			}
		}catch(BITalinoException e) {
			System.out.println("could not read the bitalino.");
		}

	}
	/*a bit of a counter intuitive name here... The name implies it has to be called when the bitalino thread is interrupted, not that 
	 * this thread interrupts it, it just does some things which ought to be done once the thread ends. Confusing? hell yes.
	 * so, allow me: The short variables have always 80 data points stored, which means that when we stop the bitalino and reset the short
	 * variables so that we start on a clean slate next time we push the start recording button we loose 80 points. This is a big no no for me
	 * so what I do is to store the 80 values stored in the short variables into the long term variables at the end of the recording o that
	 * no data points are lost.*/
	public void stopThread(){
		try {
			this.bitalino.stop();
			this.bitalino.close();
			Iterator iterator_1= ECGtimeShort.iterator();
			for (Iterator iterator = ECGdataShort.iterator(); iterator.hasNext();) {
				double data = (double) iterator.next();
				double time = (double) iterator_1.next();
				this.ECGdata.add(data);
				this.ECGtime.add(time);
			}
			ECGtimeShort.removeAll(ECGtimeShort);
			ECGdataShort.removeAll(ECGdataShort);
			iterator_1= EEGtimeShort.iterator();
			for (Iterator iterator = EEGdataShort.iterator(); iterator.hasNext();) {
				double data = (double) iterator.next();
				double time = (double) iterator_1.next();
				this.EEGdata.add(data);
				this.EEGtime.add(time);
			}
			EEGtimeShort.removeAll(EEGtimeShort);
			EEGdataShort.removeAll(EEGdataShort);
			this.connected=false;
		} catch (BITalinoException e) {
			System.out.println("failed to close properly bitalino");
			e.printStackTrace();
		}
	}
	/*this is the method which resets the long term variables.*/
	public void purgeData() {
		ECGtime=new ArrayList<>();
		EEGtime=new ArrayList<>();
		ECGdata=new ArrayList<>();
		EEGdata=new ArrayList<>();
	}
	//methods to get the complete set of data.
	public List <Double>[] getECGFull(){
		return new List[] {this.ECGtime, this.ECGdata};
	}
	public List <Double>[] getEEGFull(){
		return new List[] {this.EEGtime, this.EEGdata};
	}

}
