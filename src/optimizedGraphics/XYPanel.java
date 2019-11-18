package optimizedGraphics;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import bitalinoManager.BitalinoManager;

public class XYPanel extends JPanel implements ChartMonitor{
	private BitalinoManager model;
    private XYChart chart;
    private String plot;

    public XYPanel(BitalinoManager model, String plotName) {
        this.model = model;
        this.plot=plotName;
        chart = new XYChartBuilder().width(400).height(200).title(plotName).build();
        if(plotName.contains("ecg")||plotName.contains("ECG")) {
        	 List<Double>[] ecg = model.getECGRealTimeData();
             chart.addSeries(plotName, ecg[0], ecg[1]);
        }else {
        	List<Double>[] ecg = model.getEEGRealTimeData();
            chart.addSeries(plotName, ecg[0], ecg[1]);
        }

        setLayout(new BorderLayout());

        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);

        UpdateWorker worker = new UpdateWorker(this, plotName);
        worker.execute();
    }

    @Override
    public BitalinoManager getModel() {
        return model;
    }

    @Override
    public void updateData(List<Double>[] data) {
    	/*if(data[0].size()!=data[1].size()) {
    		if(data[0].size()>data[1].size()) {
    			while(data[0].size()!=data[1].size()) {
    				data[0].remove((data[0].size()-1));
    			}
    		}else {
    			while(data[0].size()!=data[1].size()) {
    				data[1].remove((data[1].size()-1));
    			}
    		}
    		System.out.println("I just saved a real time graph, you are wellcome");
    	}*/
    	try {
    		chart.updateXYSeries(plot, data[0], data[1], null);
            repaint();
    	}catch(Exception e) {
    		System.out.println("someone sent something weird... hold on...");
    	}
        
    }
}
