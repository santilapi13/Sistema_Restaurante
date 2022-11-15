package presentacion;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MiOptionPane implements InterfazOptionPanel {
	
	public MiOptionPane() {
		super();
	}

	public void ShowMessage(Component parent, String mensaje) {

		JOptionPane.showMessageDialog(parent, mensaje);
	}
	

}