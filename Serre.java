import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class Serre extends JFrame implements ActionListener, MouseListener, Observer {

	/* ===========COMPOSANTES GRAPHIQUES================= */
	//Le jardin
	private Jardin jardin;
	
	//Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenuItem nouveau = new JMenuItem("Nouvelle Partie"), quitter = new JMenuItem("Quitter");
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem JMail = new JMenuItem("Ail");
	private JMenuItem JMBetterave = new JMenuItem("Betterave");
	private JMenuItem JMCarotte = new JMenuItem("Carotte");
	private JMenuItem JMTomate = new JMenuItem("Tomate");

	//Label pour les menus
	private JLabel indications = new JLabel("Que voulez-vous faire ?");
	private JLabel aAil = new JLabel("_");
	private JLabel aBetterave = new JLabel("_");
	private JLabel aCarotte = new JLabel("_");
	private JLabel aTomate = new JLabel("_");
	
	//Variables globale des boucles
	private int[] tmpLongeurEtLargeur = new int[2];

	//Patern Observer
	private Emplacement[][] derniersEmplacementsConnus;
	Observable observable;
	JButton[][] emplacements;
	
	/* ============================================================== */

	public Serre(Observable observable, Jardin jardin) throws IOException { //
		//On utilise le pattern observateur
		observable.addObserver(this);
		this.jardin = jardin;
		this.emplacements = new JButton[jardin.getLongueur()][jardin.getLargeur()];
		// Propriétés générales de la fenêtre
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Le jardin d'Eden");
		
		//Menu
		this.initMenu(); //MenuFenêtre
		jpm.add(JMail);
		JMail.addActionListener(this);
		jpm.add(JMBetterave);
		JMBetterave.addActionListener(this);
		jpm.add(JMCarotte);
		JMCarotte.addActionListener(this);
		jpm.add(JMTomate);
		JMTomate.addActionListener(this);
		
		//Container
		Container pane = getContentPane();
		BorderLayout Container = new BorderLayout();
		pane.setLayout(Container);
		//Sous-Contenainer
		pane.add(GrilleEmplacements(), BorderLayout.CENTER);
		pane.add(Informations(), BorderLayout.NORTH);
		pane.add(Affichage(), BorderLayout.EAST);
		this.setVisible(true); // Activer la fenêtre
		
	}
	
	//Initialisation du menu
	private void initMenu() {
		partie.add(nouveau);
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK)); // Racourcie
		partie.add(quitter);
		partie.setMnemonic('F');
		menuBar.add(partie);
		this.setJMenuBar(menuBar);
	}
	
	//Initilaisation de la barre informative
	private Component Informations() {
		JPanel infos = new JPanel(); 
		infos.setBackground(Color.ORANGE);
		infos.add(indications);
		return infos;
	}

	//Initialisation des parcelles graphiques
	protected JComponent GrilleEmplacements() throws IOException { // Va contenir notre jardin
		JPanel Ijardin = new JPanel();
		Ijardin.setLayout(new GridLayout(this.jardin.getLongueur(), this.jardin.getLargeur()));
		for (int x = 0; x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				JButton vegetal = new JButton(new ImageIcon("terreau.jpg"));
				this.emplacements[x][y] = vegetal;
				Ijardin.add(vegetal);
				this.emplacements[x][y].addActionListener(this);
				this.emplacements[x][y].addMouseListener(this);
			}
		}
		return Ijardin;
	}
	
	private Component Affichage() {
		JPanel affichage = new JPanel(); // Container
		affichage.setLayout(new BorderLayout());
		affichage.add(AffichageGraines(), BorderLayout.NORTH);
		return affichage;
	}
	private Component AffichageGraines() {
		JPanel affichageGraines = new JPanel();
		affichageGraines.setLayout(new GridLayout(5, 2));
		affichageGraines.add(new JLabel("Ail :"));
		String s = "" + jardin.getPanier().get("Ail");
		aAil.setText(s);
		affichageGraines.add(aAil);
		affichageGraines.add(new JLabel("Betterave :"));
		s = "" + jardin.getPanier().get("Betterave");
		aBetterave.setText(s);
		affichageGraines.add(aBetterave);
		affichageGraines.add(new JLabel("Carotte :"));
		s = "" + jardin.getPanier().get("Carotte");
		aCarotte.setText(s);
		affichageGraines.add(aCarotte);
		affichageGraines.add(new JLabel("Tomate :"));
		s = "" + jardin.getPanier().get("Tomate");
		aTomate.setText(s);
		affichageGraines.add(aTomate);
		return affichageGraines;
	}
	
	
	
	
	
	
	

	//Actions sur les boutons
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource(); // On récupère toutes les infos de l'objet sur lequel on clique
		/* SEMER */
		//1 - On clique sur une parcelle pour choisir quel légume planter
		for (int x = 0; x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				if (source == this.emplacements[x][y]) {
					tmpLongeurEtLargeur[0] = x; // Stocke la position où on plante
					tmpLongeurEtLargeur[1] = y;
					indications.setText("Quel Légume voulez-vous planter ?");
					jpm.show(this, (int) (((JButton) source).getLocation()).getX(),(int) (((JButton) source).getLocation()).getY());
				}
			}
		}
		
		// 2 - Si on sélectionne un légume
		if (source == JMail || source == JMBetterave || source == JMCarotte || source == JMTomate) {
			this.emplacements[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].setIcon(new ImageIcon("graine.jpg"));
			if (source == JMail) {
				this.jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 1);
			}
			if (source == JMBetterave) {
				this.jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 2);
			}
			if (source == JMCarotte) {
				this.jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 3);
			}
			if (source == JMTomate) {
				this.jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 4);
			}
			MAJAffichageGraines(); // On met à jour graphiquement les graines
			// PAS BON
			char dessin = this.jardin.getEmplacements()[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].getVegetal().getChar(this.jardin.getEmplacements()[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].getVegetal().getEtat().ordinal());
			String s = "" + dessin;
			this.emplacements[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].setText(s);
			indications.setText("Que voulez-vous faire ?");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// POUR RECOLTER - On utilise le CLIC DROIT
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON3)
			return;
			JButton tmp = (JButton) e.getComponent();
			String txt = tmp.getText();
			System.out.println(txt);		
			if (txt.equals("A") || txt.equals("B") || txt.equals("C") || txt.equals("T")) {
				for (int x = 0; x < this.jardin.getLongueur(); x++) {
					for (int y = 0; y < this.jardin.getLargeur(); y++) {
						if (this.emplacements[x][y].equals(tmp)) {
							jardin.recolter(x, y);
						}
					}
				}
				// On réinitialise tout ceux qui sont mures
				MAJAffichageGraines();
				tmp.setIcon(new ImageIcon("terreau.jpg"));
				tmp.setText(" ");
				indications.setText("Que voulez-vous faire ?"); //Forcer l'affichage par défaut
			} else {
			indications.setText("\\!/ Vos plantes ne sont pas encores mures");
			}
		}
	
	
	// Active les végétaux, permet de cliquer
	public void activerEmplacements() {
		for (int x = 0; x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				this.emplacements[x][y].setEnabled(true);
			}
		}
	}

	public void desactiverEmplacements() {
		for (int x = 0; x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				if (this.emplacements[x][y].getBackground() != Color.BLUE) {
					this.emplacements[x][y].setEnabled(false);
				}
			}
		}
	}

	public void desactiverEmplacementsAll() {
		for (int x = 0; x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				this.emplacements[x][y].setEnabled(false);
			}
		}
	}

	public void MAJAffichageGraines() {
		String s = "" + this.jardin.getPanier().get("Ail");
		aAil.setText(s);
		s = "" + this.jardin.getPanier().get("Betterave");
		aBetterave.setText(s);
		s = "" + this.jardin.getPanier().get("Carotte");
		aCarotte.setText(s);
		s = "" + this.jardin.getPanier().get("Tomate");
		aTomate.setText(s);
	}
	
	//Permet d'appeller la MAJ et de récupérer les dernières valeurs pour afficher
	public void update(Observable obs, Object arg) {
		if (obs instanceof Jardin) {
			Jardin jardin = (Jardin) obs;
			derniersEmplacementsConnus = jardin.getEmplacements();
			//System.out.println(derniersEmplacementsConnus[0][0]);
			MAJaffichageSaison();
		}
	}

	/**On récupère le caractère mis à jour du Jardin pour mettre à jour l'interface graphique**/ 
	private void MAJaffichageSaison() {
		for (int x = 0;  x < this.jardin.getLongueur(); x++) {
			for (int y = 0; y < this.jardin.getLargeur(); y++) {
				if (derniersEmplacementsConnus[x][y] != null) {
					System.out.println(derniersEmplacementsConnus[x][y].getVegetal());
					char dessin = derniersEmplacementsConnus[x][y].getVegetal().getChar(derniersEmplacementsConnus[x][y].getVegetal().getEtat().ordinal());
					String s = "" + dessin;
					this.emplacements[x][y].setText(s);
					// MAJ DESSIN
					// {'_','.','i',' ',' ','#'}
					switch (s) {
					case ".": // Germe
						this.emplacements[x][y].setIcon(new ImageIcon("germe.jpg"));
						break;
					case "i": // Tige
						this.emplacements[x][y].setIcon(new ImageIcon("tige.jpg"));
						break;
					case "a": // Petit Ail
						this.emplacements[x][y].setIcon(new ImageIcon("pail.jpg"));
						break;
					case "A": // Ail
						this.emplacements[x][y].setIcon(new ImageIcon("ail.jpg"));
						break;
					case "b": // Petite betterave
						this.emplacements[x][y].setIcon(new ImageIcon("pbetterave.jpg"));
						break;
					case "B": // Betterave
						this.emplacements[x][y].setIcon(new ImageIcon("betterave.jpg"));
						break;
					case "c": // Petite carotte
						this.emplacements[x][y].setIcon(new ImageIcon("pcarotte.jpg"));
						break;
					case "C": // Carotte
						this.emplacements[x][y].setIcon(new ImageIcon("carotte.jpg"));
						break;
					case "t": // Petite tomate
						this.emplacements[x][y].setIcon(new ImageIcon("ptomate.jpg"));
						break;
					case "T": // Tomate
						this.emplacements[x][y].setIcon(new ImageIcon("tomate.jpg"));
						break;
					case "#": // Plante DCD
						this.emplacements[x][y].setIcon(new ImageIcon("death.jpg"));
						break;

					}
				}
				
			}
		}
	}

	public Jardin getJardin() {
		return jardin;
	}

}
    