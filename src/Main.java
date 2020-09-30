import java.awt.EventQueue;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					fileHandler.openFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
