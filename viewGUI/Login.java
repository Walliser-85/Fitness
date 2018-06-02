package viewGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controllerGUI.LoginController;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private static Login frame;
	private LoginController lc;
	private JLabel lblMeldung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		lc = new LoginController();
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 750, 400);
		contentPane = new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new LineBorder(Color.WHITE, 4));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBackground(Color.GRAY);
		contentPane.add(pnlLeft);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\login_dark.png"));
		GroupLayout gl_pnlLeft = new GroupLayout(pnlLeft);
		gl_pnlLeft.setHorizontalGroup(
			gl_pnlLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLeft.createSequentialGroup()
					.addGap(55)
					.addComponent(lblNewLabel)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_pnlLeft.setVerticalGroup(
			gl_pnlLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlLeft.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(60))
		);
		pnlLeft.setLayout(gl_pnlLeft);
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBackground(Color.GRAY);
		contentPane.add(pnlRight);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFitnessmanager = new JLabel("FITNESS-MANAGER");
		lblFitnessmanager.setBorder(new EmptyBorder(10,0,10,0));
		lblFitnessmanager.setHorizontalAlignment(SwingConstants.CENTER);
		lblFitnessmanager.setForeground(Color.WHITE);
		lblFitnessmanager.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlRight.add(lblFitnessmanager, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		pnlRight.add(panel, BorderLayout.CENTER);
		
		JLabel lblUser = new JLabel("User Login");
		lblUser.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\user.png"));
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setColumns(10);
		
		JButton btnCloses = new JButton("Close");
		btnCloses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCloses.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		btnCloses.setBackground(Color.GRAY);
		btnCloses.setForeground(Color.WHITE);
		btnCloses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(username.getText());
				System.out.println(password.getPassword());
				if(check()) {
					setMeldung(lc.checkLogin(username.getText(), password.getText(), frame));
				}else {
					setMeldung("Füllen Sie alle Login Felder aus");
				}
			}
		});
		btnLogin.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\avatar.png"));
		
		JLabel label = new JLabel("");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\key.png"));
		
		lblMeldung = new JLabel("");
		lblMeldung.setForeground(Color.WHITE);
		lblMeldung.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2))
									.addGap(5)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
											.addGap(31)
											.addComponent(btnCloses, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
										.addComponent(username, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
										.addComponent(password, 268, 268, 268))
									.addContainerGap(25, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMeldung)
									.addContainerGap())))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblUser)
							.addGap(128))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUser)
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(username, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCloses)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
					.addGap(39)
					.addComponent(lblMeldung)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	private void setMeldung(String meldungStr){
		lblMeldung.setText("<html>"+meldungStr+"</html>");
	}
	
	private void borderNull() {
		username.setBorder(null);
		password.setBorder(null);
	}

	private boolean check(){
		borderNull();
		boolean ausgefuellt = true;
		if(username.getText().equals("")) {
			username.setBorder(new LineBorder(Color.RED));
			ausgefuellt = false;
		}
		if(password.getText().equals("")) {
			password.setBorder(new LineBorder(Color.RED));
			ausgefuellt = false;
		}
		return ausgefuellt;
	}
}
