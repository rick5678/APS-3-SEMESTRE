package Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;


import jogo.Fase;


public class Container extends JFrame {
	private Menu menu;
	public Container() {
		add(new Fase());
		setTitle("Save the planet");
		setSize(560,580);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		ImageIcon icone = new ImageIcon("res\\iconee.png");
		setIconImage(icone.getImage());
		setResizable(false);
		setVisible(false);
		menu = new Menu();
		
		 addWindowListener(new WindowAdapter() {
		      @Override
		      public void windowOpened(WindowEvent wevt) {
		          Timer timer = new Timer(33000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		              Container.this.setVisible(false);
		              JOptionPane.showMessageDialog(null, "Obrigado por jogar, Lembre-se jogue lixo no lixo!");
		            }
		          });
		          timer.setRepeats(false);
		          timer.start();
		        }
		      });
	
	
	}
	
	public static void main (String[] args){
		new Container();
	}
		
	public void abrir(){
		this.setVisible(true);
	}
}
