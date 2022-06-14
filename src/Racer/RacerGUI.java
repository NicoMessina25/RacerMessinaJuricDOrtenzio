package Racer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.*;
public class RacerGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RacerGUI frame = new RacerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String name="";
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        name = teclado.nextLine();
		if (name != null && name != "") {
			System.out.println("Hola " + name + "!");
		}
	}

	/**
	 * Create the frame.
	 */
	public RacerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
