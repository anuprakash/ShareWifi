package lt.tasler.ShareWifi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;


public class Main {
	

	private static Process p;
	private static InputStreamReader isr;
	private static BufferedReader reader;
	
	public static void main (String[] args) {
		int fWidith = 200;
		int fHeight = 500;
		GUI gui = new GUI("ShareWifi", fWidith, fHeight);
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
				System.out.println(line);
				if(line != null)
					message = message.concat(line + "\n");
			}
			JOptionPane.showMessageDialog(null, "Bravo! ratalo ti je ugasnt proklet hotspot :/");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		} 		
	}

	public static void createHotspot(String name, String pass) {
		String message = "";
		try {
			ProcessBuilder pb = new ProcessBuilder("netsh", "wlan", "set", "hostednetwork", "mode=allow", "ssid=" + name, "key=" + pass);
			pb.redirectErrorStream(true);
			p = pb.start();
			
			isr = new InputStreamReader(p.getInputStream());
			reader = new BufferedReader(isr);
			
			String line = null;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
				if(line != null)
					message = message.concat(line + "\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Nešto ne radi.. obrni se na najbližjega psihijatra za pomo�?.", "ERROR: PSYCHO", JOptionPane.WARNING_MESSAGE);
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
				System.out.println(line);
				if(line != null)
					message = message.concat(line + "\n");
			}
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Nešto ne radi.. obrni se na najbližjega psihijatra za pomo�?.", "ERROR: PSYCHO", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, message );
	}
}






