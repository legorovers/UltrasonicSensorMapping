package pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frame;;
	private PlotPanel panel;
	RobotThread robot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
		
		robot = new RobotThread()
		{
			@Override
			public void sendData(float distance, int angle) 
			{			
				panel.updateUI(distance, angle);
			}				
		};
		
		Thread runningThread = new Thread(robot);
		runningThread.start();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setTitle("Lego Robot Ultrasonic Sensor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1;
		panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Ultrasonic Sensor Mapping");
		panel1.add(label);
		
		panel = new PlotPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.createGrid();
		
		JButton btnNewButton = new JButton("Disconnect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.disconnect();
			}
		});
		panel1.add(btnNewButton);
	}

}
