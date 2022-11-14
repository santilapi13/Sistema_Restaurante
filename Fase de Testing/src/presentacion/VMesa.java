package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

@SuppressWarnings("serial")
public class VMesa extends JFrame implements IVistaAdmin {

	private Mesa mesa = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerCant;
	private JButton btnEnviar;
	private ActionListener actionListener;
	private JButton btnSalir;
	private JSpinner spinnerNro;

	public VMesa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 322);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("NUEVO MESA");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(6, 0, 0, 0));

		this.lblNewLabel_1 = new JLabel("Numero de mesa");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_1);

		this.spinnerNro = new JSpinner();
		this.spinnerNro
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		this.panel.add(this.spinnerNro);

		this.lblNewLabel_3 = new JLabel("Cantidad de comensales");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_3);

		this.spinnerCant = new JSpinner();
		this.spinnerCant
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		this.panel.add(this.spinnerCant);

		this.btnEnviar = new JButton("REGISTRAR");
		this.btnEnviar.setActionCommand("REGISTRAR_MESA");
		this.btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnEnviar);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnSalir);
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnEnviar.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void cerrarse() {
		this.dispose();
	}

	@Override
	public int getNroComensales() {
		return (Integer) this.spinnerNro.getValue();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPVenta() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Operario getSelectedOperario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mozo getSelectedMozo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mesa getSelectedMesa() {
		return this.mesa;
	}

	@Override
	public PromoTemporal getSelectedPromoTemp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromoProducto getSelectedPromoProd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto getSelectedProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizaListaProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMesas(ArrayList<Mesa> mesas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaPromoTemp(ArrayList<PromoTemporal> promos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaPromoProd(ArrayList<PromoProducto> promos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMozos(ArrayList<Mozo> mozos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notificar(String noti) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMozo(Mozo mozo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
		this.spinnerCant.setValue(mesa.getCantComensales());
		this.spinnerNro.setValue(mesa.getNroMesa());
		this.spinnerNro.setEnabled(false);
	}

	@Override
	public void setOperario(Operario op) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProducto(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getActivo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCant() {
		return (Integer) this.spinnerCant.getValue();
	}

}
