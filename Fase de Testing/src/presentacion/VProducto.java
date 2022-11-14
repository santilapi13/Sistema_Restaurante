package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VProducto extends JFrame implements IVistaAdmin, KeyListener {

	private Producto producto = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textNombre;
	private JLabel lblNewLabel_2;
	private JTextField textCosto;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerStock;
	private JButton btnEnviar;
	private JTextField textVenta;
	private JLabel lblNewLabel_4;
	private ActionListener actionListener;
	private JButton btnSalir;

	public VProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 467);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("NUEVO PRODUCTO");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(9, 0, 0, 0));

		this.lblNewLabel_1 = new JLabel("Nombre");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_1);

		this.textNombre = new JTextField();
		this.textNombre.addKeyListener(this);
		this.panel.add(this.textNombre);
		this.textNombre.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Precio de costo");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_2);

		this.textCosto = new JTextField();
		this.textCosto.addKeyListener(this);
		this.panel.add(this.textCosto);
		this.textCosto.setColumns(10);

		this.lblNewLabel_3 = new JLabel("Precio de venta");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_3);

		this.textVenta = new JTextField();
		this.textVenta.addKeyListener(this);
		this.panel.add(this.textVenta);
		this.textVenta.setColumns(10);

		this.lblNewLabel_4 = new JLabel("Stock inicial");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_4);

		this.spinnerStock = new JSpinner();
		this.spinnerStock.addKeyListener(this);
		this.spinnerStock
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		this.panel.add(this.spinnerStock);

		this.btnEnviar = new JButton("REGISTRAR");
		this.btnEnviar.setActionCommand("REGISTRAR_PRODUCTO");
		this.btnEnviar.setEnabled(false);
		this.btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnEnviar);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.contentPane.add(this.btnSalir, BorderLayout.SOUTH);
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
	public double getPVenta() {
		return Double.valueOf(this.textVenta.getText());
	}

	@Override
	public double getPCosto() {
		return Double.valueOf(this.textCosto.getText());
	}

	@Override
	public String getNombre() {
		return this.textNombre.getText();
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public Date getFecha() {
		return null;
	}

	private boolean validar() {
		boolean respuesta = false;
		try {
			respuesta = this.textNombre.getText() != "" && !this.textNombre.getText().isEmpty()
					&& !this.textCosto.getText().isEmpty() && this.textCosto.getText() != ""
					&& !this.textVenta.getText().isEmpty() && this.textVenta.getText() != ""
					&& Double.valueOf(this.textCosto.getText()) > 0 && Double.valueOf(this.textVenta.getText()) > 0
					&& Double.valueOf(this.textVenta.getText()) >= Double.valueOf(this.textCosto.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Los precios deben ingresarse de forma numerica");
			this.textCosto.setText("");
			this.textVenta.setText("");
		}
		return respuesta;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.btnEnviar.setEnabled(validar());
	}

	public void keyTyped(KeyEvent e) {
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
		// TODO Auto-generated method stub
		return null;
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
		return this.producto;
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
	public int getNroComensales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMozo(Mozo mozo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMesa(Mesa mesa) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOperario(Operario op) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProducto(Producto producto) {
		this.producto = producto;
		this.textNombre.setText(producto.getNombre());
		this.textCosto.setText(String.valueOf(producto.getpCosto()));
		this.textVenta.setText(String.valueOf(producto.getpVenta()));
		this.spinnerStock.setValue(producto.getStock());
		this.btnEnviar.setEnabled(validar());
	}

	@Override
	public boolean getActivo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCant() {
		Double aux = (double) this.spinnerStock.getValue();
		return aux.intValue();

	}

}
