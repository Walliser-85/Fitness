package viewGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Canvas;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import viewKomponenten.EinstellungenView;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controllerGUI.*;
import model.Benutzer;

import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class FitnessManager extends JFrame {

	private JPanel contentPane;
	private static FitnessManager frame;
	private static JTable tbl_Muskelgruppen,tbl_Uebungen,tbl_Kunden_1, tbl_Trainingsplan, tbl_Benutzer;
	
	private static MuskelgruppeController mc;
	private static KundeController kc;
	private static UebungController uc;
	private static TrainingsplanController tc;
	private static BenutzerController bc;
	private static Benutzer user;
	
	private static JLabel lblCountUebung,lblCountMuskelgruppe,lblCountKunden,lblCountTrianingsplaene, lblCountBenutzer;
	private static String[] columnNamesUebung = {"ID", "Name", "Beschreibung", "Muskel"};;
	private static String[] columnNamesM  = {"ID", "Name", "Beschreibung"};
	private static String[] columnNamesK  = {"ID", "Vorname", "Nachname", "Adrese", "PLZ", "Ort", "E-Mail"};
	private static String[] columNameT = {"ID", "Titel", "Beschreibung", "Datum", "Kunde", "Anzahl Übungen"};
	private static String[] columNameB = {"ID", "Vorname", "Nachname", "Benutzername", "Passwort", "Berechtigung"};
	
	private Boolean admin;
	private JLayeredPane lpContent;
	private CardLayout cardLayout;
	
	private int selectedRowUebungen, selectedRowTrainingsplan, selectedRowMuskelgruppe;
	

	public static void aktualisierenKunde() {
		System.out.println("AKTUALISIEREN");
		kc = new KundeController();
		lblCountKunden.setText(Integer.toString(kc.getCountKunde()));
		tbl_Kunden_1.setModel(new DefaultTableModel(kc.getKundeListe(),columnNamesK));
	}
	
	public static void aktualisierenUebung() {
		System.out.println("AKTUALISIEREN");
		uc = new UebungController();
		lblCountUebung.setText(Integer.toString(uc.getCountUebungen()));
		tbl_Uebungen.setModel(new DefaultTableModel(uc.getUebungenListe(),columnNamesUebung));
	}
	
	public static void aktualisierenMuskelgruppe() {
		System.out.println("AKTUALISIEREN");		
		mc = new MuskelgruppeController();
		lblCountMuskelgruppe.setText(Integer.toString(mc.getCountMuskelgruppen()));
		tbl_Muskelgruppen.setModel(new DefaultTableModel(mc.getMuskelgruppenListe(),columnNamesM));
	}
	
	public static void aktualisierenTrainingsplan() {
		System.out.println("AKTUALISIEREN");		
		tc = new TrainingsplanController();
		lblCountTrianingsplaene.setText(Integer.toString(tc.getCountTrainingsplaene()));
		tbl_Trainingsplan.setModel(new DefaultTableModel(tc.getTrainingsplanListe(),columNameT));
	}
	
	public static void aktualisierenBenutzer() {
		System.out.println("AKTUALISIEREN");		
		bc = new BenutzerController();
		lblCountBenutzer.setText(Integer.toString(bc.getCountBenutzer()));
		tbl_Benutzer.setModel(new DefaultTableModel(bc.getBenutzerListe(),columNameB));
	}
	
	public FitnessManager(Benutzer user) {
		if(user.getBerechtigung().equals("Admin")) {
			admin = true;
		}
		else {
			admin = false;
		}
		
		mc = new MuskelgruppeController();
		kc = new KundeController();
		uc = new UebungController();
		tc = new TrainingsplanController();
		bc = new BenutzerController();
		
		this.user = user;
		frame = this;
		
		selectedRowUebungen = -1;
		selectedRowTrainingsplan = -1;
		selectedRowMuskelgruppe = -1;
		
		setTitle("Fitness-Manager");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane); 
		
		JLayeredPane lpMenu = new JLayeredPane();
		lpMenu.setBackground(Color.GRAY);
		lpMenu.setOpaque(true);
		contentPane.add(lpMenu, BorderLayout.WEST);
		lpMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.GRAY);
		panelMenu.setOpaque(true);
		lpMenu.setLayer(panelMenu, 0);
		lpMenu.add(panelMenu);
		panelMenu.setLayout(new GridLayout(0, 1, 0, 15));
		
		
		JButton btnHome = new JButton("Home");
		btnHome.setForeground(Color.WHITE);
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\home-icon-silhouette.png"));
		btnHome.setOpaque(true);
		btnHome.setBackground(Color.GRAY);
		
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHome.setToolTipText("Home");
		panelMenu.add(btnHome);
		
		JButton btnUebungen = new JButton("\u00DCbungen");
		btnUebungen.setForeground(Color.WHITE);
		btnUebungen.setContentAreaFilled(false);
		btnUebungen.setBorderPainted(false);
		btnUebungen.setHorizontalAlignment(SwingConstants.LEFT);
		btnUebungen.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\weight-lifting.png"));
		
		btnUebungen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUebungen.setToolTipText("\u00DCbung");
		panelMenu.add(btnUebungen);
		
		JButton btnKunden = new JButton("Kunden");
		btnKunden.setToolTipText("Kunden");
		btnKunden.setForeground(Color.WHITE);
		btnKunden.setHorizontalAlignment(SwingConstants.LEFT);
		btnKunden.setContentAreaFilled(false);
		btnKunden.setBorderPainted(false);
		btnKunden.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\man-user.png"));
		
		btnKunden.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelMenu.add(btnKunden);
		
		JButton btnTrainingsplan = new JButton("Trainingsplan");
		btnTrainingsplan.setForeground(Color.WHITE);
		btnTrainingsplan.setHorizontalAlignment(SwingConstants.LEFT);
		btnTrainingsplan.setContentAreaFilled(false);
		btnTrainingsplan.setBorderPainted(false);
		btnTrainingsplan.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\task-complete.png"));
		
		btnTrainingsplan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTrainingsplan.setToolTipText("Trainingsplan");
		panelMenu.add(btnTrainingsplan);
		
		JButton btnMuskelgruppe = new JButton("Muskelgruppen");
		btnMuskelgruppe.setForeground(Color.WHITE);
		btnMuskelgruppe.setHorizontalAlignment(SwingConstants.LEFT);
		btnMuskelgruppe.setContentAreaFilled(false);
		btnMuskelgruppe.setBorderPainted(false);
		btnMuskelgruppe.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\arm-muscles-silhouette.png"));
		
		btnMuskelgruppe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMuskelgruppe.setToolTipText("Muskelgruppe");
		panelMenu.add(btnMuskelgruppe);
		
		if(admin) {
			JButton btnBenutzer = new JButton("Benutzer");
			
			btnBenutzer.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\menu\\white\\users-group.png"));
			btnBenutzer.setToolTipText("Benutzer");
			btnBenutzer.setHorizontalAlignment(SwingConstants.LEFT);
			btnBenutzer.setForeground(Color.WHITE);
			btnBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnBenutzer.setContentAreaFilled(false);
			btnBenutzer.setBorderPainted(false);
			panelMenu.add(btnBenutzer);
			panelMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnHome, btnUebungen, btnKunden, btnTrainingsplan, btnMuskelgruppe}));
			
			btnBenutzer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Benutzer clicked");
					cardLayout.show(lpContent, "name_263012882343690");
				}
			});
		}
		
		JLayeredPane lpHeader = new JLayeredPane();
		contentPane.add(lpHeader, BorderLayout.NORTH);
		lpHeader.setLayout(new CardLayout(0, 0));
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(SystemColor.control);
		lpHeader.add(pnlHeader, "name_150775424156513");
		pnlHeader.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblFitnessmanager = new JLabel("Warehouse Gym");
		lblFitnessmanager.setHorizontalAlignment(SwingConstants.LEFT);
		lblFitnessmanager.setForeground(Color.BLACK);
		lblFitnessmanager.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnlHeader.add(lblFitnessmanager);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		pnlHeader.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblHallo = new JLabel("Fitness-Manager");
		lblHallo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHallo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHallo);
		
		JLabel lblHalloDiesIst = new JLabel("Erstellen von Trainingspl\u00E4nen");
		lblHalloDiesIst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHalloDiesIst.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHalloDiesIst);
		
		JPanel pnlLogout = new JPanel();
		pnlLogout.setBackground(SystemColor.control);
		FlowLayout flowLayout = (FlowLayout) pnlLogout.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlHeader.add(pnlLogout);
		
		JLabel lblUsername = new JLabel(user.getBenutzername());
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlLogout.add(lblUsername);
		
		JButton btnAvatar = new JButton("");
		
		btnAvatar.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\avatarTop.png"));
		btnAvatar.setVerticalAlignment(SwingConstants.TOP);
		btnAvatar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAvatar.setContentAreaFilled(false);
		btnAvatar.setBorderPainted(false);
		btnAvatar.setBackground(Color.LIGHT_GRAY);
		pnlLogout.add(btnAvatar);
		
		JButton btnLogout = new JButton("");
		pnlLogout.add(btnLogout);
		btnLogout.setVerticalAlignment(SwingConstants.TOP);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogout.setBackground(Color.LIGHT_GRAY);
		btnLogout.setIcon(new ImageIcon("D:\\hesso\\2 Semester\\Module\\632-1-Objektorientierte-Programmierung\\Projekt\\img\\logout1.png"));
		
		JLayeredPane lpFooter = new JLayeredPane();
		lpFooter.setOpaque(true);
		lpFooter.setBackground(Color.GRAY);
		contentPane.add(lpFooter, BorderLayout.SOUTH);
		lpFooter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFooter = new JLabel("Footer");
		lblFooter.setForeground(Color.BLACK);
		lpFooter.add(lblFooter);
		lblFooter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lpContent = new JLayeredPane();
		contentPane.add(lpContent, BorderLayout.CENTER);
		lpContent.setLayout(new CardLayout(0, 0));
		
		JPanel pnlHome = new JPanel();
		pnlHome.setBackground(SystemColor.textHighlight);
		lpContent.add(pnlHome, "name_150648063723161");
		pnlHome.setLayout(new BorderLayout(0, 30));
		
		JPanel pnlDash = new JPanel();
		pnlDash.setBackground(SystemColor.textHighlightText);
		pnlHome.add(pnlDash, BorderLayout.CENTER);
		pnlDash.setLayout(null);
		
		JPanel pnlHomeUebung = new JPanel();
		pnlHomeUebung.setBounds(22, 25, 274, 75);
		pnlHomeUebung.setBackground(new Color(102, 204, 255));
		pnlDash.add(pnlHomeUebung);
		pnlHomeUebung.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblbungen = new JLabel("\u00DCbungen");
		lblbungen.setForeground(Color.WHITE);
		lblbungen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlHomeUebung.add(lblbungen);
		
		lblCountUebung = new JLabel(Integer.toString(uc.getCountUebungen()));
		lblCountUebung.setForeground(Color.WHITE);
		lblCountUebung.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHomeUebung.add(lblCountUebung);
		
		JPanel pnlHomeTrainingplan = new JPanel();
		pnlHomeTrainingplan.setBounds(399, 25, 274, 75);
		pnlHomeTrainingplan.setBackground(new Color(0, 255, 102));
		pnlDash.add(pnlHomeTrainingplan);
		
		JLabel lblTrainingsplne = new JLabel("Trainingspl\u00E4ne");
		lblTrainingsplne.setForeground(Color.WHITE);
		lblTrainingsplne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlHomeTrainingplan.add(lblTrainingsplne);
		
		lblCountTrianingsplaene = new JLabel(Integer.toString(tc.getCountTrainingsplaene()));
		lblCountTrianingsplaene.setForeground(Color.WHITE);
		lblCountTrianingsplaene.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHomeTrainingplan.add(lblCountTrianingsplaene);
		
		JPanel pnlHomeMuskelgruppe = new JPanel();
		pnlHomeMuskelgruppe.setBounds(22, 113, 274, 75);
		pnlHomeMuskelgruppe.setBackground(new Color(255, 153, 255));
		pnlDash.add(pnlHomeMuskelgruppe);
		
		JLabel lblNewLabel = new JLabel("Muskelgruppen");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlHomeMuskelgruppe.add(lblNewLabel);
		
		lblCountMuskelgruppe = new JLabel(Integer.toString(mc.getCountMuskelgruppen()));
		lblCountMuskelgruppe.setForeground(Color.WHITE);
		lblCountMuskelgruppe.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHomeMuskelgruppe.add(lblCountMuskelgruppe);
		
		if(admin) {
			JPanel pnlHomeBenutzer = new JPanel();
			pnlHomeBenutzer.setBounds(22, 202, 274, 75);
			pnlHomeBenutzer.setBackground(SystemColor.inactiveCaption);
			pnlDash.add(pnlHomeBenutzer);
			
			JLabel lblBenutzer = new JLabel("Benutzer");
			lblBenutzer.setForeground(Color.WHITE);
			lblBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pnlHomeBenutzer.add(lblBenutzer);
			
			lblCountBenutzer = new JLabel(Integer.toString(bc.getCountBenutzer()));
			lblCountBenutzer.setForeground(Color.WHITE);
			lblCountBenutzer.setFont(new Font("Tahoma", Font.BOLD, 20));
			pnlHomeBenutzer.add(lblCountBenutzer);
		}
		
		JPanel pnlHomeKunden = new JPanel();
		pnlHomeKunden.setBackground(new Color(153, 204, 204));
		pnlHomeKunden.setBounds(399, 113, 274, 75);
		pnlDash.add(pnlHomeKunden);
		
		JLabel lblKunden_1 = new JLabel("Kunden");
		lblKunden_1.setForeground(Color.WHITE);
		lblKunden_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlHomeKunden.add(lblKunden_1);
		
		lblCountKunden = new JLabel(Integer.toString(kc.getCountKunde()));
		lblCountKunden.setForeground(Color.WHITE);
		lblCountKunden.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHomeKunden.add(lblCountKunden);
		
		JPanel pnlUebung = new JPanel();
		lpContent.add(pnlUebung, "name_155180048868117");
		pnlUebung.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUebung = new JLabel("\u00DCbungen");
		lblUebung.setBorder(new EmptyBorder(5,0,5,0));
		lblUebung.setVerticalAlignment(SwingConstants.TOP);
		lblUebung.setForeground(Color.BLACK);
		lblUebung.setBackground(Color.GRAY);
		lblUebung.setOpaque(true);
		lblUebung.setFont(new Font("Tahoma", Font.PLAIN, 26));
		pnlUebung.add(lblUebung, BorderLayout.NORTH);
		
		JPanel pnlDashUebung = new JPanel();
		pnlDashUebung.setBorder(new EmptyBorder(0,10,10,10));
		pnlDashUebung.setBackground(SystemColor.textHighlightText);
		pnlUebung.add(pnlDashUebung, BorderLayout.CENTER);
		pnlDashUebung.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTopUebung = new JPanel();
		pnlTopUebung.setBorder(new EmptyBorder(10,0,10,0));
		FlowLayout fl_pnlTopUebung = (FlowLayout) pnlTopUebung.getLayout();
		fl_pnlTopUebung.setAlignment(FlowLayout.RIGHT);
		pnlTopUebung.setBackground(SystemColor.textHighlightText);
		pnlDashUebung.add(pnlTopUebung, BorderLayout.NORTH);
		
		JButton btnUebungBearbeiten = new JButton("Bearbeiten");
		
		btnUebungBearbeiten.setEnabled(false);
		btnUebungBearbeiten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTopUebung.add(btnUebungBearbeiten);
		
		JButton btnUebungErstellen = new JButton("Erstellen");
		
		btnUebungErstellen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTopUebung.add(btnUebungErstellen);
		
		JPanel pnlContentUebung = new JPanel();
		
		pnlContentUebung.setBackground(SystemColor.textHighlightText);
		pnlDashUebung.add(pnlContentUebung, BorderLayout.CENTER);

		/* TABELLE UEBUNGEN*/
		tbl_Uebungen = new JTable(uc.getUebungenListe(),columnNamesUebung);
		tbl_Uebungen.getColumnModel().getColumn(0).setResizable(false);
		pnlContentUebung.setLayout(new GridLayout(0, 1, 0, 0));
		tbl_Uebungen.setBackground(SystemColor.control);
		JScrollPane scrollpaneUebung = new JScrollPane(tbl_Uebungen);
		pnlContentUebung.add(scrollpaneUebung);
		
		tbl_Uebungen.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        selectedRowUebungen = tbl_Uebungen.rowAtPoint(evt.getPoint());
		        btnUebungBearbeiten.setEnabled(true);
		    }
		});
		
		JPanel pnlTrainingsplan = new JPanel();
		lpContent.add(pnlTrainingsplan, "name_155590057414140");
		pnlTrainingsplan.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTrainingsplan = new JLabel("Trainingsplan");
		lblTrainingsplan.setBorder(new EmptyBorder(5,0,5,0));
		lblTrainingsplan.setForeground(Color.BLACK);
		lblTrainingsplan.setOpaque(true);
		lblTrainingsplan.setBackground(Color.GRAY);
		lblTrainingsplan.setFont(new Font("Tahoma", Font.PLAIN, 26));
		pnlTrainingsplan.add(lblTrainingsplan, BorderLayout.NORTH);
		
		JPanel pnlDashTrainingsplan = new JPanel();
		pnlDashTrainingsplan.setBorder(new EmptyBorder(0,10,10,10));
		pnlDashTrainingsplan.setBackground(SystemColor.textHighlightText);
		pnlTrainingsplan.add(pnlDashTrainingsplan, BorderLayout.CENTER);
		pnlDashTrainingsplan.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTopTrainingsplan = new JPanel();
		pnlTopTrainingsplan.setBorder(new EmptyBorder(10,0,10,0));
		pnlTopTrainingsplan.setBackground(SystemColor.textHighlightText);
		FlowLayout flowLayout_1 = (FlowLayout) pnlTopTrainingsplan.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlDashTrainingsplan.add(pnlTopTrainingsplan, BorderLayout.NORTH);
		
		JButton btnTrainingsplanErstellen = new JButton("Erstellen");
		
		btnTrainingsplanErstellen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTopTrainingsplan.add(btnTrainingsplanErstellen);
		
		JButton btnTrainingsplanLöschen = new JButton("L\u00F6schen");
		
		btnTrainingsplanLöschen.setEnabled(false);
		btnTrainingsplanLöschen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTopTrainingsplan.add(btnTrainingsplanLöschen);
		
		JPanel pnlContentTrainingsplan = new JPanel();
		pnlContentTrainingsplan.setBackground(SystemColor.textHighlightText);
		pnlDashTrainingsplan.add(pnlContentTrainingsplan, BorderLayout.CENTER);
		pnlContentTrainingsplan.setLayout(new GridLayout(0, 1, 0, 0));
		
		/*TABLE TRAININGSPLAN*/
		tbl_Trainingsplan = new JTable(tc.getTrainingsplanListe(),columNameT);
		tbl_Trainingsplan.getColumnModel().getColumn(0).setResizable(false);
		pnlContentTrainingsplan.setLayout(new GridLayout(0, 1, 0, 0));
		tbl_Trainingsplan.setBackground(SystemColor.control);
		JScrollPane scrollpaneTrainingsplan = new JScrollPane(tbl_Trainingsplan);
		pnlContentTrainingsplan.add(scrollpaneTrainingsplan);
		
		tbl_Trainingsplan.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        selectedRowTrainingsplan = tbl_Trainingsplan.rowAtPoint(evt.getPoint());
		        btnTrainingsplanLöschen.setEnabled(true);
		    }
		});
		
		JPanel pnlMuskelgruppe = new JPanel();
		pnlMuskelgruppe.setBackground(SystemColor.textHighlightText);
		lpContent.add(pnlMuskelgruppe, "name_155600480462379");
		pnlMuskelgruppe.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMuskelgruppe = new JLabel("Muskelgruppen");
		lblMuskelgruppe.setBorder(new EmptyBorder(5,0,5,0));
		lblMuskelgruppe.setOpaque(true);
		lblMuskelgruppe.setForeground(Color.BLACK);
		lblMuskelgruppe.setBackground(Color.GRAY);
		lblMuskelgruppe.setFont(new Font("Tahoma", Font.PLAIN, 26));
		pnlMuskelgruppe.add(lblMuskelgruppe, BorderLayout.NORTH);
		
		JPanel pnlDashMuskelgruppe = new JPanel();
		pnlDashMuskelgruppe.setBorder(new EmptyBorder(0,10,10,10));
		pnlDashMuskelgruppe.setBackground(SystemColor.textHighlightText);
		pnlMuskelgruppe.add(pnlDashMuskelgruppe, BorderLayout.CENTER);
		pnlDashMuskelgruppe.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTopMuskelgruppe = new JPanel();
		pnlTopMuskelgruppe.setBorder(new EmptyBorder(10,0,10,0));
		FlowLayout fl_pnlTopMuskelgruppe = (FlowLayout) pnlTopMuskelgruppe.getLayout();
		fl_pnlTopMuskelgruppe.setAlignment(FlowLayout.RIGHT);
		pnlTopMuskelgruppe.setBackground(SystemColor.textHighlightText);
		pnlDashMuskelgruppe.add(pnlTopMuskelgruppe, BorderLayout.NORTH);
		
		JButton btnMuskelgruppeBearbeiten = new JButton("Bearbeiten");
		
		btnMuskelgruppeBearbeiten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMuskelgruppeBearbeiten.setEnabled(false);
		pnlTopMuskelgruppe.add(btnMuskelgruppeBearbeiten);
		
		JButton btnMuskelgruppeErstellen = new JButton("Erstellen");
		
		btnMuskelgruppeErstellen.setHorizontalAlignment(SwingConstants.RIGHT);
		btnMuskelgruppeErstellen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTopMuskelgruppe.add(btnMuskelgruppeErstellen);
		
		JPanel pnlContentMuskelgruppe = new JPanel();
		pnlContentMuskelgruppe.setBackground(SystemColor.textHighlightText);
		pnlDashMuskelgruppe.add(pnlContentMuskelgruppe, BorderLayout.CENTER);
		pnlContentMuskelgruppe.setLayout(new GridLayout(0, 1, 0, 10));
		
		/*TABELLE MUSKELGRUPPEN*/
		tbl_Muskelgruppen = new JTable(mc.getMuskelgruppenListe(),columnNamesM);
		tbl_Muskelgruppen.setBackground(SystemColor.control);
		JScrollPane scrollpaneMuskelgruppe = new JScrollPane(tbl_Muskelgruppen);
		pnlContentMuskelgruppe.add(scrollpaneMuskelgruppe);
		
		tbl_Muskelgruppen.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        selectedRowMuskelgruppe = tbl_Muskelgruppen.rowAtPoint(evt.getPoint());
		        btnMuskelgruppeBearbeiten.setEnabled(true);
		    }
		});
		
		cardLayout = (CardLayout) lpContent.getLayout();
		
		JPanel pnlKunde = new JPanel();
		pnlKunde.setBackground(SystemColor.textHighlightText);
		lpContent.add(pnlKunde, "name_5352607773574");
		pnlKunde.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKunden = new JLabel("Kunden");
		lblKunden.setBorder(new EmptyBorder(5,0,5,0));
		lblKunden.setForeground(Color.BLACK);
		lblKunden.setOpaque(true);
		lblKunden.setBackground(Color.GRAY);
		lblKunden.setFont(new Font("Tahoma", Font.PLAIN, 26));
		pnlKunde.add(lblKunden, BorderLayout.NORTH);
		
		JPanel pnlDashKunde = new JPanel();
		pnlDashKunde.setBackground(SystemColor.textHighlightText);
		pnlDashKunde.setBorder(new EmptyBorder(0,10,10,10));
		pnlKunde.add(pnlDashKunde, BorderLayout.CENTER);
		pnlDashKunde.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDashTopKunde = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlDashTopKunde.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlDashTopKunde.setBackground(SystemColor.textHighlightText);
		pnlDashTopKunde.setBorder(new EmptyBorder(10,0,10,0));
		pnlDashKunde.add(pnlDashTopKunde, BorderLayout.NORTH);
		
		JButton btnKundeErstellen = new JButton("Erstellen");
		
		btnKundeErstellen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlDashTopKunde.add(btnKundeErstellen);
		
		JPanel pnlContentKunde = new JPanel();
		pnlContentKunde.setBackground(SystemColor.textHighlightText);
		pnlDashKunde.add(pnlContentKunde, BorderLayout.CENTER);
		pnlContentKunde.setLayout(new GridLayout(0, 1, 0, 10));
		
		/*TABELLE KUNDE*/
		tbl_Kunden_1 = new JTable(kc.getKundeListe(),columnNamesK);
		tbl_Kunden_1.setBackground(SystemColor.control);
		JScrollPane scrollpaneKunde = new JScrollPane(tbl_Kunden_1);
		pnlContentKunde.add(scrollpaneKunde);
		
		JPanel pnlBenutzer = new JPanel();
		pnlBenutzer.setOpaque(true);
		pnlBenutzer.setBackground(SystemColor.textHighlightText);
		lpContent.add(pnlBenutzer, "name_263012882343690");
		pnlBenutzer.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUser = new JLabel("Benutzer");
		lblUser.setOpaque(true);
		lblUser.setBackground(Color.GRAY);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblUser.setBorder(new EmptyBorder(5,0,5,0));
		pnlBenutzer.add(lblUser, BorderLayout.NORTH);
		
		JPanel pnlDashBenutzer = new JPanel();
		pnlDashBenutzer.setBackground(SystemColor.textHighlightText);
		pnlDashBenutzer.setOpaque(true);
		pnlDashBenutzer.setBorder(new EmptyBorder(0,10,10,10));
		pnlBenutzer.add(pnlDashBenutzer, BorderLayout.CENTER);
		pnlDashBenutzer.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDashTopBenutzer = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnlDashTopBenutzer.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		pnlDashTopBenutzer.setBackground(SystemColor.textHighlightText);
		pnlDashTopBenutzer.setBorder(new EmptyBorder(10,0,10,0));
		pnlDashBenutzer.add(pnlDashTopBenutzer, BorderLayout.NORTH);
		
		JButton btnBenutzerErstellen = new JButton("Erstellen");
		
		btnBenutzerErstellen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlDashTopBenutzer.add(btnBenutzerErstellen);
		
		JPanel pnlDashContentBenutzer = new JPanel();
		pnlDashContentBenutzer.setBackground(SystemColor.textHighlightText);
		pnlDashBenutzer.add(pnlDashContentBenutzer, BorderLayout.CENTER);
		pnlDashContentBenutzer.setLayout(new GridLayout(1, 0, 0, 0));
		pnlDashContentBenutzer.setLayout(new GridLayout(0, 1, 0, 10));
		
		tbl_Benutzer = new JTable(bc.getBenutzerListe(),columNameB);
		tbl_Benutzer.setBackground(SystemColor.control);
		JScrollPane scrollpaneBenutzer = new JScrollPane(tbl_Benutzer);
		pnlDashContentBenutzer.add(scrollpaneBenutzer);
		
		JPanel pnlUserDash = new JPanel();
		pnlUserDash.setOpaque(true);
		pnlUserDash.setBackground(Color.WHITE);
		lpContent.add(pnlUserDash, "name_264817285536214");
		pnlUserDash.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDashUser = new JLabel("User Information");
		lblDashUser.setForeground(Color.BLACK);
		lblDashUser.setBackground(Color.GRAY);
		lblDashUser.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblDashUser.setOpaque(true);
		lblDashUser.setBorder(new EmptyBorder(5,0,5,0));
		pnlUserDash.add(lblDashUser, BorderLayout.NORTH);
		
		JPanel pnlUserInformation = new JPanel();
		pnlUserInformation.setBackground(SystemColor.textHighlightText);
		pnlUserDash.add(pnlUserInformation, BorderLayout.CENTER);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Home clicked");
				cardLayout.show(lpContent, "name_150648063723161");
			}
		});
		btnUebungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Uebung clicked");
				cardLayout.show(lpContent, "name_155180048868117");
				btnUebungBearbeiten.setEnabled(false);
				selectedRowUebungen = -1;
			}
		});
		
		btnTrainingsplan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trainginsplan clicked");
				cardLayout.show(lpContent, "name_155590057414140");
				btnTrainingsplanLöschen.setEnabled(false);
				selectedRowTrainingsplan = -1;
			}
		});
		
		btnTrainingsplanLöschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tc.deleteTrainingsplan(selectedRowTrainingsplan)) {
					System.out.println("Erfolgreich gelöscht");
					btnTrainingsplanLöschen.setEnabled(false);
					tbl_Trainingsplan.setModel(new DefaultTableModel(tc.getTrainingsplanListe(),columNameT));
				}
			}
		});
		
		btnMuskelgruppe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Musklegruppe clicked");
				cardLayout.show(lpContent, "name_155600480462379");
				btnMuskelgruppeBearbeiten.setEnabled(false);
				selectedRowMuskelgruppe = -1;
			}
		});
		
		btnKunden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Kunden clicked");
				cardLayout.show(lpContent, "name_5352607773574");
			}
		});
		
		
		
		btnAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Avatart clicked");
				cardLayout.show(lpContent, "name_264817285536214");
			}
		});
		
		btnUebungErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UebungErstellen dialog = new UebungErstellen(frame,uc,selectedRowUebungen);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnUebungBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRowUebungen == -1) {
					System.out.println("Bitte eine Übung auswählen!!");
				}
				else {
					btnUebungBearbeiten.setEnabled(false);
					UebungErstellen dialog = new UebungErstellen(frame,uc,selectedRowUebungen);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		
		btnMuskelgruppeErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MuskelgruppeErstellen dialog = new MuskelgruppeErstellen(frame,mc,selectedRowMuskelgruppe);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnMuskelgruppeBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRowMuskelgruppe == -1) {
					System.out.println("Bitte eine Muskelgruppe auswählen!!");
				}
				else {
					btnMuskelgruppeBearbeiten.setEnabled(false);
					MuskelgruppeErstellen dialog = new MuskelgruppeErstellen(frame,mc,selectedRowMuskelgruppe);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		
		btnTrainingsplanErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainingsplanErstellen dialog = new TrainingsplanErstellen(frame, tc);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnKundeErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KundeErstellen dialog = new KundeErstellen(frame,kc);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		btnBenutzerErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BenutzerErstellen dialog = new BenutzerErstellen(frame,bc);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
