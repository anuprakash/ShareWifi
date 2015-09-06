package lt.tasler.ShareWifi;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame{
	
	private JPanel panel;
	
	private JButton startButton;
	private JButton createButton;
	
	private JLabel hotspotLabel;
	private JTextField hotspotText;
	
	private JLabel passwordLabel;
	private JTextField passwordText;
	
	private JLabel internetShareLabel;
	private JTextField internetShareText;
	
	private JMenuBar menuBar;
	private JMenu about;
	
	public GUI(String title, int fWidith, int fHight) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(fWidith,fHight);
		
		about = new JMenu("About");
		about.setMnemonic(KeyEvent.VK_A);
		about.add(new JMenuItem("Created by Luka Tašler"));
		
		menuBar = new JMenuBar();
		menuBar.add(about);
		
		setJMenuBar(menuBar);
		
		panel = new JPanel(new GridBagLayout());
		add(panel);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHEAST;
		
		hotspotLabel = new JLabel("Ime hotspota");
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		panel.add(hotspotLabel, c);
		
		hotspotText = new JTextField(10);
		c.gridy = 1;
		panel.add(hotspotText,c);
		
		passwordLabel = new JLabel("Geslo");
		c.gridy = 2;
		panel.add(passwordLabel, c);
		
		passwordText = new JTextField(10);
		c.gridy = 3;
		panel.add(passwordText, c);
		
		startButton = new JButton("Start Hotspot");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = hotspotText.getText();
				String pass = passwordText.getText();
				if(name.length() < 5) {
					JOptionPane.showMessageDialog(null, "Ime hotspota mora imeti ve�? kot 4 �?rke.", "ERROR: INVALID ZA EKRANOM!", JOptionPane.ERROR_MESSAGE);
					
				}
				else if(pass.length() < 8) {
					JOptionPane.showMessageDialog(null, "geslo mora bit vsaj 8 znakov ali ve�?.", "NAPAKA: Your mom just got fucked", JOptionPane.ERROR_MESSAGE);
					
				}
				else
					Main.createHotspot(name, pass);
				
			}
		});
		
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(startButton, c);
		
		createButton = new JButton("Ustavi hotspot");
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.stopHotspot();
				
			}
		});
		c.gridx = 1;
		panel.add(createButton, c);
	}
}
