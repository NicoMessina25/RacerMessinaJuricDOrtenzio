package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SoundClip {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private Clip audioClip;
	private AudioInputStream audioStream;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * clip method
	 * @param path
	 */
	public SoundClip(String path) {
		// create an AudioInputStream from a given sound file
		File audioFile = new File(path);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
		} catch (IOException | UnsupportedAudioFileException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error en el archivo de sonido. \nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		// obtain the Clip
		try {
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
		} catch (LineUnavailableException | IOException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al obtener el archivo de sonido."
					+ "(Puede que otra aplicación lo este usando) . \nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 */
	public void play() {
		new Thread(() -> {
			audioClip.setFramePosition(0);
			audioClip.start();
		}) {
		}.start();
	}

	/**
	 * 
	 */
	public void loop() {
		new Thread(() -> {
			audioClip.setFramePosition(0);
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		}) {
		}.start();
	}

	/**
	 * 
	 */
	public void stop() {
		audioClip.stop();
	}

	/**
	 * 
	 * @param miliSenconds
	 */
	/*public void play(int miliSenconds) {
		
		
		Timer timer = new Timer((int) miliSenconds, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					audioClip.setFramePosition(0);
					audioClip.start();
	
				}).start();
			}
			
		});
		
		timer.setRepeats(false);
		timer.start();
		
	}*/
	

	

}
