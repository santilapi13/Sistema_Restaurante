package presentacion;

import java.awt.event.ActionListener;

public interface IVistaLogin {

	void setActionListener(ActionListener actionListener);
	
	void cerrarse();
	
	String getUsername();
	
	String getPassword();
		
}