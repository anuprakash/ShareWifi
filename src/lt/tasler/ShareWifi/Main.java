package lt.tasler.ShareWifi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.istack.internal.logging.Logger;


public class Main {
	

	private static Process p;
	private static InputStreamReader isr;
	private static BufferedReader reader;

	public static ResourceBundle bundle;
	
	private static final String STRINGS_PATH = "lt.tasler.ShareWifi.Resources.Strings";
	
	private static final Logger LOG = Logger.getLogger(Main.class);
	
	public static void main (String[] args) {
		//init bundle
		bundle = ResourceBundle.getBundle(STRINGS_PATH); //TODO: locale
		
		
		int fWidith = 200;
		int fHeight = 500;
		GUI gui = new GUI(bundle.getString("appName"), fWidith, fHeight);
		gui.setVisible(true);
		
	}

	public static void stopHotspot() {
		String message = "";
		try {
			ProcessBuilder pb = new ProcessBuilder("netsh", "wlan", "stop", "hostednetwork");
			pb.redirectErrorStream(true);
			p = pb.start();
			
			isr = new InputStreamReader(p.getInputStream());
			reader = new BufferedReader(isr);
				
			String line = null;
			while ((line = reader.readLine()) != null) {
				if(line != null)
					message = message.concat(line + "\n");
			}
			JOptionPane.showMessageDialog(null, bundle.getString("stoppedMsg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		}
		LOG.info(message);
	}

	public static void createHotspot(String name, String pass) {
		if(name.length() < 5) {
			JOptionPane.showMessageDialog(null, bundle.getString("shortNameMsg"), bundle.getString("errorMsgTitle"), JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(pass.length() < 8) {
			JOptionPane.showMessageDialog(null, bundle.getString("shortPassMsg"), bundle.getString("errorMsgTitle"), JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String message = "";
		try {
			ProcessBuilder pb = new ProcessBuilder("netsh", "wlan", "set", "hostednetwork", "mode=allow", "ssid=" + name, "key=" + pass);
			pb.redirectErrorStream(true);
			p = pb.start();
			
			isr = new InputStreamReader(p.getInputStream());
			reader = new BufferedReader(isr);
			
			String line = null;
			while((line = reader.readLine()) != null) {
				if(line != null)
					message = message.concat(line + "\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, bundle.getString("unknowErrorMsg") , bundle.getString("errorMsgTitle"), JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		//starta hostpot
		try {
			ProcessBuilder pb = new ProcessBuilder("netsh", "wlan", "start", "hostednetwork");
			pb.redirectErrorStream(true);
			p = pb.start();
			
			isr = new InputStreamReader(p.getInputStream());
			reader = new BufferedReader(isr);
			
			String line = null;
			while((line = reader.readLine()) != null) {
				if(line != null)
					message = message.concat(line + "\n");
			}
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, bundle.getString("unknowErrorMsg"), bundle.getString("errorMsgTitle") , JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, bundle.getString("startedMsg"));
		
		LOG.info(message);
	}
}






