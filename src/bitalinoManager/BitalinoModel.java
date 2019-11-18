package bitalinoManager;

import java.util.List;

public interface BitalinoModel {
	 public List<Double>[] getECGRealTimeData();
	 public List<Double>[] getEEGRealTimeData();
}
