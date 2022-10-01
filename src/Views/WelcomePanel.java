package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class WelcomePanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public WelcomePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][][][][][][]", "[][][][]"));
		setContentPane(contentPane);
		
		JLabel lblWelcome = new JLabel("Bienvenido al mejor juego del mundo!");
		contentPane.add(lblWelcome, "cell 4 3");
	}

}
