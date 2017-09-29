import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame implements ActionListener{
 
	
	
	/*===========DECLARATION DE TOUT LES COMPOSANTS=================*/
	private Jardin jardin = new Jardin(10,8);
	private JLabel indications = new JLabel("Que voulez-vous faire ?");
	
	
  public Fenetre(){ //<=> Main
	  //Propriétés générales de la fenêtre
    this.setSize(800, 700);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Le jardin d'Eden");
    
    this.initMenu(); //Initialise la barre de Menu
    
    /*Définition du conteneur*/
    Container pane = getContentPane();
    
    /*On fixe le Layout principal dans le container*/
    BorderLayout Container = new BorderLayout();
    pane.setLayout(Container);
    
    /*Ajout des sous conteneurs*/
    pane.add(Informations(), BorderLayout.NORTH);
    pane.add(getGrid(), BorderLayout.CENTER);
    pane.add(Affichage(), BorderLayout.EAST);
    pane.add(Commandes(), BorderLayout.WEST);
    pane.add(LaunchBar(), BorderLayout.SOUTH);
    
    
    this.setVisible(true); //Activer la fen
  }
  

/*================================================DEFINITION DES SOUS CONTENEURS & OBJETS==============================================*/
  
  
  private Component Informations() {
	  JPanel infos = new JPanel(); //InfosPrincipales
	  infos.setBackground(Color.ORANGE);
	   //Va contenir toute la trame du jeu
	  infos.add(indications);
		return infos;
	}
  
  private Component Commandes() {
	  JPanel commandes = new JPanel(); //Container
	  commandes.setLayout(new BorderLayout());
	  commandes.add(CommandesGenerales(), BorderLayout.CENTER);
	  commandes.add(Legumes(), BorderLayout.SOUTH);
	return commandes;
}
 
  
  private JButton Bsemer = new JButton("Semer");
  private JButton Brecolter = new JButton("Recolter");
  private Component CommandesGenerales() {
	  JPanel commandesGenerales = new JPanel(); //Container
	  commandesGenerales.setLayout(new GridLayout(2,1));
	  Bsemer.setBackground(Color.GREEN);
	  Bsemer.addActionListener(this); //Ajout du bouton à l'écouteur
	  Brecolter.setBackground(Color.RED);
	  Brecolter.addActionListener(this);
	  commandesGenerales.add(Bsemer);
	  commandesGenerales.add(Brecolter);
	  
  return commandesGenerales;
  }
  
  
  
  private JButton ail = new JButton(new ImageIcon("ail.jpeg"));
  private JButton betterave = new JButton("Betterave");
  private JButton carotte = new JButton("Carotte");
  private JButton tomate = new JButton("Tomate");
  
  private Component Legumes() {
	  JPanel legumes = new JPanel(); //Container
	  legumes.setLayout(new GridLayout(2,2));  
	  //On récupère les images
	  legumes.add(ail);
	  ail.setEnabled(false);
	  ail.addActionListener(this);
	  legumes.add(betterave);
	  betterave.setEnabled(false);
	  betterave.addActionListener(this);
	  legumes.add(carotte);
	  carotte.setEnabled(false);
	  carotte.addActionListener(this);
	  legumes.add(tomate);
	  tomate.setEnabled(false);
	  tomate.addActionListener(this);
  return legumes;
}


private Component LaunchBar() {
	JProgressBar BarreDeProgression = new JProgressBar();
	BarreDeProgression.setForeground(Color.BLACK);
	BarreDeProgression.setBackground(Color.WHITE);
	BarreDeProgression.setStringPainted(true);
	return BarreDeProgression;
 }

private Component Affichage() {
	  JPanel affichage = new JPanel(); //Container
	  affichage.setLayout(new BorderLayout());
	  affichage.add(AffichageSaison(), BorderLayout.CENTER);
	  affichage.add(AffichageGraines(), BorderLayout.SOUTH);
	return affichage;
}


private JButton BsaisonSuivante = new JButton("Passer à la saison suivante");
private Component AffichageSaison() {
	JPanel saison = new JPanel();
	saison.setLayout(new GridLayout(1,1));
	BsaisonSuivante.setBackground(Color.YELLOW);
	BsaisonSuivante.addActionListener(this);
	
	saison.add(BsaisonSuivante);
	return saison;
}

private JLabel aAil = new JLabel("_");
private JLabel aBetterave = new JLabel("_");
private JLabel aCarotte = new JLabel("_");
private JLabel aTomate = new JLabel("_");
private Component AffichageGraines() {
	  JPanel affichageGraines = new JPanel();
	  affichageGraines.setLayout(new GridLayout(5,2));
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

private int longueurJardin = jardin.getLongueur();
private int largeurJardin =jardin.getLargeur();
JButton[][] emplacements = new JButton[longueurJardin][largeurJardin];

protected JComponent getGrid() { //Va contenir notre jardin
      JPanel Ijardin = new JPanel();
      Ijardin.setLayout(new GridLayout(jardin.getLongueur(),jardin.getLargeur())); //A redéfinir en taille X/Y via une boucle for
      for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
    	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
    		 JButton vegetal = new JButton(" ");
    		 emplacements[longueurJardin][largeurJardin] = vegetal; 
    		 Ijardin.add(vegetal).setEnabled(false);
    		 emplacements[longueurJardin][largeurJardin].addActionListener(this);
    	  }
      }
  return Ijardin;
}
  

/*================================================BARRE DE MENU==============================================*/
//Menu
JMenuBar menuBar = new JMenuBar();
JMenu   partie = new JMenu("Partie");
//Items
JMenuItem   nouveau = new JMenuItem("Nouvelle Partie"),
quitter = new JMenuItem("Quitter");

	private void initMenu() {
		partie.add(nouveau);
 
    quitter.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
          System.exit(0);
        }      
      });
      quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK)); //Racourcie

    partie.add(quitter);
    partie.setMnemonic('F');
    menuBar.add(partie);

    this.setJMenuBar(menuBar);
	}

  //Initialise la barre d'outils

  //ÉCOUTEUR POUR LE CHANGEMENT DE FORME
  class FormeListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")){
      }
      else{
      }
    }    
  }

  //ÉCOUTEUR POUR LE CHANGEMENT DE COULEUR
  class CouleurListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.out.println(e.getSource().getClass().getName());
      if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")){
        System.out.println("OK !");
      }
      else{
      }
    }    
  }

	private int[] tmpLongeurEtLargeur = new int[2];
	private SimpleEntry<Integer,Integer> NouvellesVal;
/*================================================ACTION PERFORMED==============================================*/
@Override
	public void actionPerformed(ActionEvent arg0) {

	Object source = arg0.getSource(); //On récupèr toutes les infos de l'objet sur lequel on clique
	
	/* SEMER */
	if (source == Bsemer) {
		Bsemer.setEnabled(false);
		Brecolter.setEnabled(false);
		BsaisonSuivante.setEnabled(false);
		indications.setText("Veuillez choisir l'emplacement où vous voulez planter");
		activerEmplacements(); 
	}
	for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
  	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
  		 if(source == emplacements[longueurJardin][largeurJardin] ) {
  			emplacements[longueurJardin][largeurJardin].setBackground(Color.BLUE);
  			tmpLongeurEtLargeur[0] = longueurJardin; //Stocke la position où on plante
  			tmpLongeurEtLargeur[1] = largeurJardin;
  			desactiverEmplacements();
  			indications.setText("Quel Légume voulez-vous planter ?");
  			ActiverChoixLegumes();
  		 }
  	  }		
  		}		
	
	//C'est super moche mais pas le choix -- Impossible d'inclure dans Switch case un objet ni un float (Hashcode)
	if (source == ail || source == betterave || source == carotte || source == tomate) {	
	if (source == ail) {
		jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 1);
	}
	if (source == betterave) {
		jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 2);
	}
	if (source == carotte) {
		jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 3);
	}
	if (source == tomate) {
		jardin.semer(tmpLongeurEtLargeur[0], tmpLongeurEtLargeur[1], 4);
	}
	DesactiverChoixLegumes();
	MAJAffichageGraines();
	
	//On récupère le symbole de Jardin
	
	
	char dessin = jardin.getEmplacements()[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].getVegetal().getChar(jardin.getEmplacements()[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].getVegetal().getEtat().ordinal());
	String s = "" + dessin;
	emplacements[tmpLongeurEtLargeur[0]][tmpLongeurEtLargeur[1]].setText(s);
	desactiverEmplacementsAll();
	Bsemer.setEnabled(true);
	Brecolter.setEnabled(true);
	BsaisonSuivante.setEnabled(true);
	indications.setText("Que voulez-vous faire ?");
	}
	if (source == BsaisonSuivante) {
		jardin.saisonSuivante(); // On passe à la saison suivante - Les caractères changent
		for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
		  	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
		  		if (emplacements[longueurJardin][largeurJardin].getBackground() == Color.BLUE) {
		  			char dessin = jardin.getEmplacements()[longueurJardin][largeurJardin].getVegetal().getChar(jardin.getEmplacements()[longueurJardin][largeurJardin].getVegetal().getEtat().ordinal());
		  			String s = "" + dessin;		  			
		  			emplacements[longueurJardin][largeurJardin].setText(s);
		  		}
		  	 }
		}
	}
	if (source == Brecolter) {
		NouvellesVal = jardin.recolter(); //On récupère les coordonées au cas ou Betterave OGM
		for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
		  	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
		  		MAJAffichageGraines();  //Met à jour les quantités de graines spéciales
		  		if (emplacements[longueurJardin][largeurJardin].getBackground() == Color.BLUE) {
		  			String s = emplacements[longueurJardin][largeurJardin].getText();
		  			if (s.equals("A") || s.equals("B") || s.equals("C") || s.equals(("T"))) { //On réinitialise tout ceux qui sont mures
		  				emplacements[longueurJardin][largeurJardin].setBackground(null);
		  				emplacements[longueurJardin][largeurJardin].setText(null);
		  					if (s.equals("B")) { // On plante la plante aux nouvelles coordonnées
		  						int NouveauX = NouvellesVal.getKey();
		  						int NouveauY = NouvellesVal.getValue();
		  						emplacements[NouveauX][NouveauY].setBackground(Color.BLUE);
		  						emplacements[NouveauX][NouveauY].setText("_");
		  					}
		  			}else {
		  				indications.setText("\\!/ Vos plantes ne sont pas encores mures");
		  			}
		  		}
		  	}
		}
	}
}

	//Active les végétaux, permet de cliquer
	public void activerEmplacements() {
		for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
	    	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
	    		 emplacements[longueurJardin][largeurJardin].setEnabled(true);
	    	  }
	      }
	}
	
	public void desactiverEmplacements() {
		for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
	    	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
	    		  if(emplacements[longueurJardin][largeurJardin].getBackground() != Color.BLUE ) {
	    		 emplacements[longueurJardin][largeurJardin].setEnabled(false);
	    	  }
	      }
		}
	}
	
	public void desactiverEmplacementsAll() {
		for (longueurJardin = 0; longueurJardin < jardin.getLongueur(); longueurJardin++ ) {
	    	  for (largeurJardin = 0; largeurJardin < jardin.getLargeur(); largeurJardin++ ) {
	    		 emplacements[longueurJardin][largeurJardin].setEnabled(false);
	      }
		}
	}

	public void ActiverChoixLegumes() {
		ail.setEnabled(true);
		betterave.setEnabled(true);
		carotte.setEnabled(true);
		tomate.setEnabled(true);		  
	}
	
	public void DesactiverChoixLegumes() {
		ail.setEnabled(false);
		betterave.setEnabled(false);
		carotte.setEnabled(false);
		tomate.setEnabled(false);		  
	}
	
	public void MAJAffichageGraines() {
		  String s = "" + jardin.getPanier().get("Ail");
		  aAil.setText(s);
		  s = "" + jardin.getPanier().get("Betterave");
		  aBetterave.setText(s);
		  s = "" + jardin.getPanier().get("Carotte");
		  aCarotte.setText(s);
		  s = "" + jardin.getPanier().get("Tomate");
		  aTomate.setText(s);
	}
/*================================================LACEMENT DE L'EXCECUTION DU PROGRAMME==============================================*/

public static void main(String[] args) {
	Fenetre fen = new Fenetre();
}
}