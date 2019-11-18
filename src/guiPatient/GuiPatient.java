package guiPatient;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import connectionManager.connectionManager;
import mainMethodPatient.MainPatient;
import mainMethodPatient.UserProfile;
import optimizedGraphics.XYPanel;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;


public class GuiPatient {
	private JFrame f =new JFrame();
	private JPanel contentPane;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextArea textArea; 
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	public GuiPatient(UserProfile user, connectionManager cm) {
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		f.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		if(!user.bitalinoIsconnected()) {
			user.startBitalino(user.getMacAddress());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.windowBorder);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout());
		XYPanel xypanel_1 = new XYPanel(user.getBitalinoManager(), "ECG monitor");
		panel_6.add(xypanel_1);

		panel_5.add(panel_6);

		panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setLayout(new BorderLayout());
		XYPanel xypanel_2 = new XYPanel(user.getBitalinoManager(), "EEG monitor");
		panel_7.add(xypanel_2);

		panel_5.add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_8.setBackground(SystemColor.windowBorder);
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel_10.setLayout(new GridLayout(11,10));

		JLabel label1=new JLabel("Name");
		JLabel label2=new JLabel("Surname");
		JLabel label3=new JLabel("Age");
		JLabel label4=new JLabel("Weight");

		Font ui=new Font("Segoe UI",Font.PLAIN,14);
		label1.setFont(ui);
		label2.setFont(ui);
		label3.setFont(ui);
		label4.setFont(ui);
		text1=new JTextField();
		text2=new JTextField();
		text3=new JTextField();
		text4=new JTextField();
		text1.setBackground(Color.GRAY);
		text2.setBackground(Color.GRAY);
		text3.setBackground(Color.GRAY);
		text4.setBackground(Color.GRAY);
		text1.setBorder(new LineBorder(Color.DARK_GRAY));
		text2.setBorder(new LineBorder(Color.DARK_GRAY));
		text3.setBorder(new LineBorder(Color.DARK_GRAY));
		text4.setBorder(new LineBorder(Color.DARK_GRAY));
		text1.setFont(ui);
		text2.setFont(ui);
		text3.setFont(ui);
		text4.setFont(ui);
		

		JLabel filler=new JLabel();

		panel_10.add(label1);
		panel_10.add(text1);
		panel_10.add(label2);
		panel_10.add(text2);

		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);

		panel_10.add(label3);
		panel_10.add(text3);
		panel_10.add(label4);
		panel_10.add(text4);

		panel_8.add(panel_10);
		textArea = new JTextArea();
		textArea.setFont(ui);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textArea.setBackground(new Color(64, 64, 64));
		panel_8.add(textArea);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.desktop);
		JLabel title =new JLabel("Patient Monotorer 3000");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Segoe UI",80,15));
		panel_1.setLayout(new GridLayout(1,3));
		panel_1.add(title,Component.LEFT_ALIGNMENT);
		JLabel devBuild=new JLabel("devBuild alpha 2.0");
		devBuild.setForeground(Color.white);
		devBuild.setFont(new Font("Segoe UI",80,15));
		panel_1.add(devBuild);
		JLabel stats=new JLabel("Telemedicine 2019-2020");
		stats.setForeground(Color.white);
		stats.setFont(new Font("Segoe UI",80,15));
		panel_1.add(stats);
		contentPane.add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.desktop);
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.desktop);
		contentPane.add(panel_3, BorderLayout.WEST);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.desktop);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(panel_4, BorderLayout.EAST);

		try {
			BufferedImage nominal;
			if(user.getGender()=='m') {
				nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\NominalMale.jpg"));
			}else {
				nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\NominalFemale.jpg"));
			}
			JLabel picLabel = new JLabel(new ImageIcon(nominal));
			panel_4.add(picLabel);
		} catch (IOException e) {
			System.out.println("failed to import image!");
			e.printStackTrace();
		}

		JPanel panel_9=new JPanel();
		panel_9.setBackground(Color.BLACK);


		panel_4.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] {1};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.0};
		panel_9.setLayout(gbl_panel_9);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblStatus.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		panel_9.add(lblStatus, gbc_lblStatus);
		
		JButton stop = new JButton("Stop recording");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
				MainScreen.invokeMe();
				MainPatient.stopRecording(user, textArea.getText(),cm);
			}
		});
		stop.setForeground(Color.BLACK);
		stop.setBackground(Color.GRAY);
		stop.setFont(new Font("Segoe UI",Font.PLAIN,11));
		GridBagConstraints gbc_lblStop = new GridBagConstraints();
		gbc_lblStop.insets = new Insets(0, 0, 5, 0);
		gbc_lblStop.gridx = 0;
		gbc_lblStop.gridy = 2;
		gbc_lblStop.fill = GridBagConstraints.HORIZONTAL;
		panel_9.add(stop, gbc_lblStop);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_lbVS_1 = new GridBagConstraints();
		gbc_lbVS_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_1.gridx = 0;
		gbc_lbVS_1.gridy = 1;
		panel_9.add(verticalStrut_1, gbc_lbVS_1);
		GridBagConstraints gbc_lbVS_2 = new GridBagConstraints();
		gbc_lbVS_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_2.gridx = 0;
		gbc_lbVS_2.gridy = 3;
		panel_9.add(verticalStrut_2, gbc_lbVS_2);
		GridBagConstraints gbc_lbVS_3 = new GridBagConstraints();
		gbc_lbVS_3.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_3.gridx = 0;
		gbc_lbVS_3.gridy = 5;
		panel_9.add(verticalStrut_3, gbc_lbVS_3);

		JButton btnNewButton_1 = new JButton("ALERT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPatient.requestMonitoring(cm);
				try {
					BufferedImage nominal;
					if(user.getGender()=='m') {
						nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\SpasticMale.jpg"));
					}else {
						nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\SpasticFemale.jpg"));
					}
					
					
					JLabel picLabel = new JLabel(new ImageIcon(nominal));
					panel_4.remove(0);;
					panel_4.add(picLabel,0);
					panel_4.setVisible(false);
					panel_4.setVisible(true);
					
				} catch (IOException e) {
					System.out.println("failed to import image!");
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1.setBackground(Color.RED);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 4;
		panel_9.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Report Symptoms");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SymptomsTab s= new SymptomsTab(GuiPatient.this);
				s.SufferedSymptoms();
				try {
					BufferedImage nominal;
					if(user.getGender()=='m') {
						nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\SymptomsMale.jpg"));
					}else {
						nominal = ImageIO.read(new File("C:\\Users\\Sloth Thy Lord\\Documents\\Sloth thy lord\\Biomédica\\quinto año\\Telemedicina\\SymptomsFemale.jpg"));
					}
					
					
					JLabel picLabel = new JLabel(new ImageIcon(nominal));
					panel_4.remove(0);;
					panel_4.add(picLabel,0);
					panel_4.setVisible(false);
					panel_4.setVisible(true);
					
				} catch (IOException ex) {
					System.out.println("failed to import image!");
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		panel_9.add(btnNewButton, gbc_btnNewButton);
		this.setAge(user.getAge());
		this.setWeight(user.getWeight());
		this.setName(user.getName());
		this.setSurName(user.getSurname());
		f.setVisible(true);
	}
	public void setName(String name) {
		this.text1.setText(name);
		text1.setVisible(false);
		text1.setVisible(true);
	}
	public void setSurName(String surname) {
		this.text2.setText(surname);
		text2.setVisible(false);
		text2.setVisible(true);
	}
	public void setAge(int age) {
		this.text3.setText(""+age);
		text3.setVisible(false);
		text3.setVisible(true);
	}
	public void setWeight(int weight) {
		this.text4.setText(""+weight);
		text4.setVisible(false);
		text4.setVisible(true);
	}
	public void setComment(String comment) {
		this.textArea.setText(comment);
		textArea.setVisible(false);
		textArea.setVisible(true);
	}
	public void setSymptoms(String s) {
		GuiPatient.this.textArea.setText(GuiPatient.this.textArea.getText()+s);
		textArea.setVisible(false);
		textArea.setVisible(true);
	}

}
