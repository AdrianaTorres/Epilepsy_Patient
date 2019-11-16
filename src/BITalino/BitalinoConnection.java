package BITalino;

import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoConnection {

	    public static Frame[] frame;

	    public static void main(String[] args) {

	        BITalino bitalino = null;
	        try {
	            bitalino = new BITalino();
	            // find devices
	            Vector<RemoteDevice> devices = bitalino.findDevices();
	            System.out.println(devices);

	            //MAC address need to be changed if connecting to another Bitalino.
	            String macAddress = "20:16:07:18:17:85";
	            int SamplingRate = 10;
	            bitalino.open(macAddress, SamplingRate);

	            // start acquisition on analog channels A1 and A2 (EMG, ECG)
	            int[] channelsToAcquire = {1, 2};
	            bitalino.start(channelsToAcquire);

	            //read 10000 samples
	            for (int j = 0; j < 10000000; j++) {

	                //Read a block of 100 samples 
	                frame = bitalino.read(100);
	            }
	            //stop acquisition
	            bitalino.stop();
	        } catch (BITalinoException ex) {
	            Logger.getLogger(BitalinoConnection.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (Throwable ex) {
	            Logger.getLogger(BitalinoConnection.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                //close bluetooth connection
	                if (bitalino != null) {
	                    bitalino.close();
	                }
	            } catch (BITalinoException ex) {
	                Logger.getLogger(BitalinoConnection.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }

	    }

	}

	
	
	

