package guiPatient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SymptomsTab {

	private JPanel contentPane;
	private JFrame f;
	private JCheckBox cbx_1;
	private JCheckBox cbx_2;
	private JCheckBox cbx_3;
	private JCheckBox cbx_4;
	private JCheckBox cbx_5;
	private JCheckBox cbx_6;
	private JCheckBox cbx_7;
	private JCheckBox cbx_8;
	private JCheckBox cbx_9;
	private JCheckBox cbx_10;
	private JCheckBox cbx_11;
	private JCheckBox cbx_12;

	public SymptomsTab(GuiPatient g) {
		f= new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 850, 500);
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setContentPane(contentPane);
		f.setResizable(false);
		
		cbx_1=new JCheckBox();
		cbx_2=new JCheckBox();
		cbx_3=new JCheckBox();
		cbx_4=new JCheckBox();
		cbx_5=new JCheckBox();
		cbx_6=new JCheckBox();
		cbx_7=new JCheckBox();
		cbx_8=new JCheckBox();
		cbx_9=new JCheckBox();
		cbx_10=new JCheckBox();
		cbx_11=new JCheckBox();
		cbx_12=new JCheckBox();
		
		JCheckBox[] lazyness= new JCheckBox[12];
		lazyness[0]=cbx_1;
		lazyness[1]=cbx_2;
		lazyness[2]=cbx_3;
		lazyness[3]=cbx_4;
		lazyness[4]=cbx_5;
		lazyness[5]=cbx_6;
		lazyness[6]=cbx_7;
		lazyness[7]=cbx_8;
		lazyness[8]=cbx_9;
		lazyness[9]=cbx_10;
		lazyness[10]=cbx_11;
		lazyness[11]=cbx_12;
		for (int i = 0; i < lazyness.length; i++) {
			lazyness[i].setBackground(Color.DARK_GRAY);
			lazyness[i].setForeground(Color.WHITE);
		}
		
		contentPane.setLayout(new BorderLayout());
		JPanel panel_1 =new JPanel();
		JPanel panel_2 =new JPanel();
		JPanel panel_3 =new JPanel();
		JPanel panel_4 =new JPanel();
		JPanel panel_5 =new JPanel();
		
		panel_1.setBackground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_3.setBackground(Color.BLACK);
		panel_4.setBackground(Color.BLACK);
		panel_5.setBackground(Color.BLACK);
		
		contentPane.add(panel_1,BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(100);
		panel_1.add(verticalStrut_1);
		contentPane.add(panel_2,BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(100);
		panel_2.add(verticalStrut);
		contentPane.add(panel_3,BorderLayout.EAST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1);
		contentPane.add(panel_4,BorderLayout.WEST);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut);
		contentPane.add(panel_5,BorderLayout.CENTER);
		
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 0, 0, 0, 0, 0};
		gbl_panel_9.rowWeights = new double[]{0, 0, 0, 0, 0, 0};
		panel_5.setLayout(gbl_panel_9);
		
		GridBagConstraints gbc_lblStatus_1 = new GridBagConstraints();
		gbc_lblStatus_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_1.gridx = 0;
		gbc_lblStatus_1.gridy = 0;
		panel_5.add(lazyness[0], gbc_lblStatus_1);
		GridBagConstraints gbc_lblStatus_2 = new GridBagConstraints();
		gbc_lblStatus_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_2.gridx = 0;
		gbc_lblStatus_2.gridy = 1;
		panel_5.add(lazyness[1], gbc_lblStatus_2);
		GridBagConstraints gbc_lblStatus_3 = new GridBagConstraints();
		gbc_lblStatus_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_3.gridx = 0;
		gbc_lblStatus_3.gridy = 2;
		panel_5.add(lazyness[2], gbc_lblStatus_3);
		GridBagConstraints gbc_lblStatus_4 = new GridBagConstraints();
		gbc_lblStatus_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_4.gridx = 0;
		gbc_lblStatus_4.gridy = 3;
		panel_5.add(lazyness[3], gbc_lblStatus_4);
		GridBagConstraints gbc_lblStatus_5 = new GridBagConstraints();
		gbc_lblStatus_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_5.gridx = 0;
		gbc_lblStatus_5.gridy = 4;
		panel_5.add(lazyness[4], gbc_lblStatus_5);
		GridBagConstraints gbc_lblStatus_6 = new GridBagConstraints();
		gbc_lblStatus_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus_6.gridx = 0;
		gbc_lblStatus_6.gridy = 5;
		panel_5.add(lazyness[5], gbc_lblStatus_6);
		GridBagConstraints gbc_lblStatus_7 = new GridBagConstraints();
		gbc_lblStatus_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_7.gridx = 2;
		gbc_lblStatus_7.gridy = 0;
		panel_5.add(lazyness[6], gbc_lblStatus_7);
		GridBagConstraints gbc_lblStatus_8 = new GridBagConstraints();
		gbc_lblStatus_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_8.gridx = 2;
		gbc_lblStatus_8.gridy = 1;
		panel_5.add(lazyness[7], gbc_lblStatus_8);
		GridBagConstraints gbc_lblStatus_9 = new GridBagConstraints();
		gbc_lblStatus_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_9.gridx = 2;
		gbc_lblStatus_9.gridy = 2;
		panel_5.add(lazyness[8], gbc_lblStatus_9);
		GridBagConstraints gbc_lblStatus_10 = new GridBagConstraints();
		gbc_lblStatus_10.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_10.gridx = 2;
		gbc_lblStatus_10.gridy = 3;
		panel_5.add(lazyness[9], gbc_lblStatus_10);
		GridBagConstraints gbc_lblStatus_11 = new GridBagConstraints();
		gbc_lblStatus_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_11.gridx = 2;
		gbc_lblStatus_11.gridy = 4;
		panel_5.add(lazyness[10], gbc_lblStatus_11);
		GridBagConstraints gbc_lblStatus_12 = new GridBagConstraints();
		gbc_lblStatus_12.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus_12.gridx = 2;
		gbc_lblStatus_12.gridy = 5;
		panel_5.add(lazyness[11], gbc_lblStatus_12);

		JLabel label_1 =new JLabel(" I am feeling odd");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_2 =new JLabel(" I am smelling / tasting something weird");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_3 =new JLabel(" I feel like something is out of place");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_4 =new JLabel(" Everything feels fuzzy");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_5 =new JLabel(" I have forgotten something recent");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_6 =new JLabel(" I've been daydreaming");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_7 =new JLabel(" I am experiencing jerking movements");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_8 =new JLabel(" I have been falling a lot");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_9 =new JLabel(" Something tingles/ feels numb");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_10 =new JLabel(" My head hurts");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_11 =new JLabel(" I feel weak / sleepy for no reason");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_12 =new JLabel(" I have shat / pissed myself");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_13 =new JLabel("Report Symptoms");
		label_13.setFont(new Font("Segoe UI", Font.PLAIN,25));
		label_13.setForeground(Color.WHITE);
		
		panel_1.add(label_13);
		
		Font ui=new Font("Segoe UI", Font.PLAIN,13);
		label_1.setFont(ui);
		label_2.setFont(ui);
		label_3.setFont(ui);
		label_4.setFont(ui);
		label_5.setFont(ui);
		label_6.setFont(ui);
		label_7.setFont(ui);
		label_8.setFont(ui);
		label_9.setFont(ui);
		label_10.setFont(ui);
		label_11.setFont(ui);
		label_12.setFont(ui);
		
		label_1.setForeground(Color.WHITE);
		label_2.setForeground(Color.WHITE);
		label_3.setForeground(Color.WHITE);
		label_4.setForeground(Color.WHITE);
		label_5.setForeground(Color.WHITE);
		label_6.setForeground(Color.WHITE);
		label_7.setForeground(Color.WHITE);
		label_8.setForeground(Color.WHITE);
		label_9.setForeground(Color.WHITE);
		label_10.setForeground(Color.WHITE);
		label_11.setForeground(Color.WHITE);
		label_12.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_lblStatus_13 = new GridBagConstraints();
		gbc_lblStatus_13.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_13.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_13.gridx = 1;
		gbc_lblStatus_13.gridy = 0;
		panel_5.add(label_1, gbc_lblStatus_13);
		GridBagConstraints gbc_lblStatus_14 = new GridBagConstraints();
		gbc_lblStatus_14.anchor = GridBagConstraints.EAST;
		gbc_lblStatus_14.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_14.gridx = 1;
		gbc_lblStatus_14.gridy = 1;
		panel_5.add(label_2, gbc_lblStatus_14);
		GridBagConstraints gbc_lblStatus_15 = new GridBagConstraints();
		gbc_lblStatus_15.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_15.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_15.gridx = 1;
		gbc_lblStatus_15.gridy = 2;
		panel_5.add(label_3, gbc_lblStatus_15);
		GridBagConstraints gbc_lblStatus_16 = new GridBagConstraints();
		gbc_lblStatus_16.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_16.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_16.gridx = 1;
		gbc_lblStatus_16.gridy = 3;
		panel_5.add(label_4, gbc_lblStatus_16);
		GridBagConstraints gbc_lblStatus_17 = new GridBagConstraints();
		gbc_lblStatus_17.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_17.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_17.gridx = 1;
		gbc_lblStatus_17.gridy = 4;
		panel_5.add(label_5, gbc_lblStatus_17);
		GridBagConstraints gbc_lblStatus_18 = new GridBagConstraints();
		gbc_lblStatus_18.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_18.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_18.gridx = 1;
		gbc_lblStatus_18.gridy = 5;
		panel_5.add(label_6, gbc_lblStatus_18);
		GridBagConstraints gbc_lblStatus_19 = new GridBagConstraints();
		gbc_lblStatus_19.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_19.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_19.gridx = 3;
		gbc_lblStatus_19.gridy = 0;
		panel_5.add(label_7, gbc_lblStatus_19);
		GridBagConstraints gbc_lblStatus_20 = new GridBagConstraints();
		gbc_lblStatus_20.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_20.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_20.gridx = 3;
		gbc_lblStatus_20.gridy = 1;
		panel_5.add(label_8, gbc_lblStatus_20);
		GridBagConstraints gbc_lblStatus_21 = new GridBagConstraints();
		gbc_lblStatus_21.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_21.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_21.gridx = 3;
		gbc_lblStatus_21.gridy = 2;
		panel_5.add(label_9, gbc_lblStatus_21);
		GridBagConstraints gbc_lblStatus_22 = new GridBagConstraints();
		gbc_lblStatus_22.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_22.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_22.gridx = 3;
		gbc_lblStatus_22.gridy = 3;
		panel_5.add(label_10, gbc_lblStatus_22);
		GridBagConstraints gbc_lblStatus_23 = new GridBagConstraints();
		gbc_lblStatus_23.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_23.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_23.gridx = 3;
		gbc_lblStatus_23.gridy = 4;
		panel_5.add(label_11, gbc_lblStatus_23);
		GridBagConstraints gbc_lblStatus_24 = new GridBagConstraints();
		gbc_lblStatus_24.anchor = GridBagConstraints.WEST;
		gbc_lblStatus_24.insets = new Insets(0, 0, 5, 10);
		gbc_lblStatus_24.gridx = 3;
		gbc_lblStatus_24.gridy = 5;
		panel_5.add(label_12, gbc_lblStatus_24);
		
		JButton button_1 = new JButton("Submit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setSymptoms(SufferedSymptoms());
				f.dispose();
			}
		});
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		button_1.setBackground(Color.DARK_GRAY);
		button_2.setBackground(Color.DARK_GRAY);
		
		button_1.setForeground(Color.WHITE);
		button_2.setForeground(Color.WHITE);
		
		button_1.setFont(ui);
		button_2.setFont(ui);
		
		panel_2.add(button_1);
		panel_2.add(button_2);
		
		f.setVisible(true);
	}
	public String SufferedSymptoms() {
		String symptomsList="\n";
		if(cbx_1.isSelected()) {
			symptomsList=symptomsList+"-Odd feelings\n";
		}
		if(cbx_2.isSelected()) {
			symptomsList=symptomsList+"-Smelt /tasted somehting anomalous\n";
		}
		if(cbx_3.isSelected()) {
			symptomsList=symptomsList+"-Felt something was out of place\n";
		}
		if(cbx_4.isSelected()) {
			symptomsList=symptomsList+"-Feelings of fuzzyness\n";
		}
		if(cbx_5.isSelected()) {
			symptomsList=symptomsList+"-Recent memory loss\n";
		}
		if(cbx_6.isSelected()) {
			symptomsList=symptomsList+"-Frequent daydreaming\n";
		}
		if(cbx_7.isSelected()) {
			symptomsList=symptomsList+"-Uncontrolled limb movements\n";
		}
		if(cbx_8.isSelected()) {
			symptomsList=symptomsList+"-Loss of equilibrium with consequential accidental falls\n";
		}
		if(cbx_9.isSelected()) {
			symptomsList=symptomsList+"-Feelings of numbness\n";
		}
		if(cbx_10.isSelected()) {
			symptomsList=symptomsList+"-Experienced Headaches\n";
		}
		if(cbx_11.isSelected()) {
			symptomsList=symptomsList+"-Feelings of weakness or Sleepyness\n";
		}
		if(cbx_12.isSelected()) {
			symptomsList=symptomsList+"-Experienced some sort of incontinence\n";
		}
		return symptomsList;
	}

}
