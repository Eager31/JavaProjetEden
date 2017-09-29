import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

//CTRL + SHIFT + O pour générer les imports nécessaires
public class Progress extends JFrame{
  private Thread t;
  private JProgressBar bar;
  private JButton launch ;
   
  public Progress(){      
    this.setSize(300, 80);
    this.setTitle("*** JProgressBar ***");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);      
      
    t = new Thread(new Traitement());
    bar  = new JProgressBar();
    bar.setMaximum(500);
    bar.setMinimum(0);
    bar.setStringPainted(true);
      
    this.getContentPane().add(bar, BorderLayout.CENTER);
      
    launch = new JButton("Lancer");
    launch.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        t = new Thread(new Traitement());
        t.start();            
      }
    });      
    this.getContentPane().add(launch, BorderLayout.SOUTH);      
    t.start();      
    this.setVisible(true);      
  }

  class Traitement implements Runnable{   
    public void run(){
      launch.setEnabled(false);
         
      for(int val = 0; val <= 500; val++){
        bar.setValue(val);
        try {
          t.sleep(10);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
        e.printStackTrace();
        }
      }
      launch.setEnabled(true);
    }   
  }

  public static void main(String[] args){
    Progress p = new Progress();
  }   
}