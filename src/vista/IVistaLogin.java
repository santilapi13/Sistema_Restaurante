package vista;

import java.awt.event.ActionListener;

public interface IVistaLogin {

	void setActionListener(ActionListener actionListener);
	
	void cerrarse();
	
	String getUsername();
	
	String getPassword();
	
	String getFecha();
	
	String getNombre();
	
	int getRubro();
	
	String getTipo();
	
	
}