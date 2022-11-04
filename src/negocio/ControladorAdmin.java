package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import vista.*;

public class ControladorAdmin  implements ActionListener {

	private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
	private static ControladorAdmin instance = null;

	private ControladorAdmin() {
	}

	public static ControladorAdmin getInstance() {
		if (instance == null)
			instance = new ControladorAdmin();
		return instance;
	}

	public void setVista(IVistaLogin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
