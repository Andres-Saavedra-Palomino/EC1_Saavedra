package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class APP_SAAVEDRA_CONSULTA extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APP_SAAVEDRA_CONSULTA frame = new APP_SAAVEDRA_CONSULTA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public APP_SAAVEDRA_CONSULTA() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
