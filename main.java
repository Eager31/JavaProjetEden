import java.io.IOException;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class main {
	  public long myLong = 1234;
	    //Jardin jardin = new Jardin(4,4);
	    
	    Observer obs;
	    public static void main(String[] args) throws IOException {
			//On récupère le jardin de tmp
	    	Jardin jardin = new Jardin(12,12);
			Serre serre = new Serre(jardin, jardin);
			
			//Minuteur pour passer les saisons
	        final main test = new main(); 
	        Timer timer = new Timer();
	        
	        timer.schedule(new TimerTask() {

	        	
	            @Override
	            public void run() {
	                test.doStuff(jardin);
	            }
	        }, 0, test.myLong);
	    }
	    int global = 0;
	    public void doStuff(Jardin jardin){
	    	System.out.println(global);
	        if (global == 5) {
	        	jardin.saisonSuivante();
	    		jardin.setMAJPousse(jardin.getEmplacements());
	        	System.out.println(jardin.toString());
	        	global = 0;
	        }else {
	        	global++;
	        }
	    }
	        
}
