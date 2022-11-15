package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import excepciones.MesaInexistenteException;
import excepciones.ProductoInexistenteException;
import modelo.Cerveceria;
import vista.IVistaLogin;
import vista.VOperario;


public class ControladorComanda implements ActionListener {

    private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
    private static ControladorComanda instance = null;
    int nromesa;


    public static ControladorComanda getInstance() {
        if (instance == null) {
            instance = new ControladorComanda();
        }
        return instance;
    }

    public void setVista(IVistaLogin vista, int nromesa) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.nromesa = nromesa;

        this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
        this.vista.ActualizarPedidos(Cerveceria.getInstance().getComandaDeMesa(nromesa));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        try {
            if (comando.equalsIgnoreCase("AGREGAR") && !this.vista.getIsProductoEmpty()) {
                int nromesa = this.nromesa;
                int cantidad = this.vista.getCantMinima();
                String nombreProd = this.vista.getProdSeleccionado().getNombre();

                if (cantidad <= this.vista.getProdSeleccionado().getStock())
                {
                 Cerveceria.getInstance().getOperarioLogueado().tomarComanda(nromesa, nombreProd, cantidad);
                 this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
                 this.vista.ActualizarPedidos(Cerveceria.getInstance().getComandaDeMesa(nromesa));
                 }
                else
                	JOptionPane.showMessageDialog(null, "La cantidad pedida no puede superar al stock del producto.");

            } else if (comando.equalsIgnoreCase("SALIR")) {
                this.vista.cerrarse();
                ControladorOperario.getInstance().setVista(new VOperario(Cerveceria.getInstance().getOperarioLogueado().getUsername()));
            }

			Cerveceria.getInstance().persistirCerveceria();

        } catch (ProductoInexistenteException | MesaInexistenteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException ex) {
			throw new RuntimeException(ex);
		}


	}


}
