package optimizedGraphics;

import java.util.List;

import bitalinoManager.BitalinoManager;

public interface ChartMonitor {
	public BitalinoManager getModel();
    public void updateData(List<Double>[] data);
}
