package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.*;

public class ControladorOperario implements ActionListener {

	private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
	private static ControladorOperario instance = null;
	
	private ControladorOperario() {
	}

	public static ControladorOperario getInstance() {
		if (instance == null)
			instance = new ControladorOperario();
		return instance;
	}

	public void setVista(IVistaLogin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		
	}

}
