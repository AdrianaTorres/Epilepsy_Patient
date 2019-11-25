package guiPatient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectionManager.connectionManager;
import mainMethodPatient.MainPatient;
import mainMethodPatient.UserProfile;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen{

	private JPanel contentPane;
	private static JFrame f;
	private String rP=System.getProperty("user.dir")+"\\resources";

	public MainScreen(UserProfile up, connectionManager cm) {
		f= new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		f.setResizable(false);
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		contentPane.add(panel_1);
		contentPane.add(panel_2);
		panel_1.setLayout(new BorderLayout());
		Font ui =new Font("Segoe UI", Font.PLAIN,20);
		JLabel label_1= new JLabel("PatientMonitorer, devBuild alpha 1.0, Telemedicine 2019-2020");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(ui);
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalTextPosition(JLabel.CENTER);
		try {
			BufferedImage nominal;
			nominal = ImageIO.read(new File(rP+"\\logo.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(nominal));
			panel_1.add(picLabel,BorderLayout.CENTER);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("failed to import image");
		}
		panel_1.add(label_1,BorderLayout.SOUTH);
		JButton button_1= new JButton("Start Recording");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(up.getBitalinoManager()!=null) {				
					GuiPatient g = new GuiPatient(up,cm);
				}else {
					ConnectingToBitalino c= new ConnectingToBitalino(up,cm);
				}
				f.setVisible(false);
			}
		});
		JButton button_2= new JButton("Last report");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser(System.getProperty("user.dir")+"\\reports");
			      int rVal = c.showOpenDialog(f);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			        String path=c.getSelectedFile().getAbsolutePath();
			        MainPatient.loadReport(path,up);
					f.setVisible(false);
			      }
			}
		});
		JButton button_3= new JButton("Report Symptoms");
		JButton button_4= new JButton("Send Alert");
		
		button_1.setFont(ui);
		button_2.setFont(ui);
		button_3.setFont(ui);
		button_4.setFont(ui);
		
		button_1.setBackground(Color.DARK_GRAY);
		button_2.setBackground(Color.DARK_GRAY);
		
		button_1.setForeground(Color.WHITE);
		button_2.setForeground(Color.WHITE);
		
		panel_2.setLayout(new BorderLayout());
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JPanel panel_5 = new JPanel();
		JPanel panel_6 = new JPanel();
		
		panel_3.setBackground(Color.BLACK);
		panel_4.setBackground(Color.BLACK);
		panel_5.setBackground(Color.BLACK);
		panel_6.setBackground(Color.BLACK);
		
		Component HorizontalStrut_1 = Box.createHorizontalStrut(160);
		Component HorizontalStrut_2 = Box.createHorizontalStrut(160);
		
		panel_5.add(HorizontalStrut_2);
		panel_6.add(HorizontalStrut_1);
		
		panel_2.add(panel_3,BorderLayout.NORTH);
		panel_2.add(panel_4,BorderLayout.SOUTH);
		panel_2.add(panel_5,BorderLayout.EAST);
		panel_2.add(panel_6,BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.BLACK);
		panel_2.add(panel_7,BorderLayout.CENTER);
		
		panel_7.setLayout(new GridLayout(5,1));
		panel_7.add(new JLabel(""));
		panel_7.add(button_1);
		panel_7.add(new JLabel(""));
		panel_7.add(button_2);
		panel_7.add(new JLabel(""));
		f.setVisible(true);
	}
	public static void invokeMe() {
		f.setVisible(true);
	}
}
