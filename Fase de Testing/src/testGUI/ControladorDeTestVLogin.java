package testGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.Sistema;
import presentacion.IVistaLogin;
import presentacion.InterfazOptionPanel;
import presentacion.MiOptionPane;
import presentacion.VLogin;

public class ControladorDeTestVLogin  implements ActionListener{



		private VLogin vista = null;
		
		

		public ControladorDeTestVLogin(VLogin ventana) {
			Sistema.getInstance().setOpLogueado(null);
			this.vista = ventana;
			this.vista.setActionListener(this);
		}
		

      

		public VLogin getVista() {
			return vista;
		}




		public void setVista() {
			this.vista = null;
		}




		@Override
		public void actionPerformed(ActionEvent e) { //lanzar Excepciones?
			String comando = e.getActionCommand();
			if (comando.equalsIgnoreCase("Ingresar")) {
				String user = this.vista.getUsername();
				String pass = this.vista.getPassword();
				Sistema.getInstance().loguear(user,pass);
			}
		}
}	
	
	
