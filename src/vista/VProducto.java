package vista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Comanda;
import modelo.EstadoMozo;
import modelo.FormaPago;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Promocion;
import modelo.Venta;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class VProducto extends JFrame implements IVistaLogin, KeyListener{

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textNombre;
	private JLabel lblNewLabel_2;
	private JTextField textCosto;
	private JLabel lblNewLabel_3;
	private JButton btnAPLICAR;
	private JTextField textVenta;
	private JLabel lblNewLabel_4;
	private JTextField textFieldStock;
	private ActionListener actionListener;
	/**
	 * Create the frame.
	 */
	public VProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 467);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("PRODUCTO");
		lblNewLabel.setForeground(Color.DARK_GRAY);
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
		this.panel.add(this.textNombre);
		this.textNombre.setColumns(10);
		
		this.lblNewLabel_2 = new JLabel("Precio de costo");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_2);
		
		this.textCosto = new JTextField();
		this.panel.add(this.textCosto);
		this.textCosto.setColumns(10);
		
		this.lblNewLabel_3 = new JLabel("Precio de venta");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_3);
		
		this.textVenta = new JTextField();
		this.panel.add(this.textVenta);
		this.textVenta.setColumns(10);
		
		this.lblNewLabel_4 = new JLabel("Stock");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_4);
		
		textFieldStock = new JTextField();
		panel.add(textFieldStock);
		textFieldStock.setColumns(10);
		
		this.btnAPLICAR = new JButton("ACEPTAR");
		
		this.btnAPLICAR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnAPLICAR);
		
		this.btnAPLICAR.setEnabled(false);
		this.setVisible(true);
		this.textNombre.addKeyListener(this);
		this.textVenta.addKeyListener(this);
		this.textCosto.addKeyListener(this);
		this.textFieldStock.addKeyListener(this);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAPLICAR.addActionListener(actionListener);
		this.actionListener = actionListener;
		
	}

	@Override
	public void cerrarse() {
		this.dispose();
		
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
	public String getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNya() {
		return this.textNombre.getText();
	}

	@Override
	public int getHijos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String fecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getComensales() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double pCosto() {
		if (this.textCosto.getText().equals(""))
			return -1;
		else
			return Double.parseDouble(this.textCosto.getText());
	}

	@Override
	public double pVenta() {
		if (this.textCosto.getText().equals(""))
			return -1;
		else
			return Double.parseDouble(this.textVenta.getText());
	}

	@Override
	public int stock() {
		String text = this.textFieldStock.getText();
		if (text.equals(""))
			return -1;
		else
			return Integer.parseInt(text);
	}

	@Override
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActualizarMozos(ArrayList<Mozo> mozos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActualizarMesas(ArrayList<Mesa> mesas) {
		// TODO Auto-generated method stub
		
	}

	
	

	@Override
	public void ActualizarProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsProductoEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto getProdSeleccionado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getIsOperarioEmpty() {
		return false;
	}

	@Override
	public boolean getIsMesaEmpty() {
		return false;
	}

	@Override
	public boolean getIsMozoEmpty() {
		return false;
	}

	


	@Override
	public Operario getOperarioSeleccionado() {
		return null;
	}

	@Override
	public Mesa getMesaSeleccionada() {
		return null;
	}

	@Override
	public Mozo getMozoSeleccionado() {
		return null;
	}

	
	

	@Override
	public boolean getIsPromocionProdEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIsPromocionTempEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PromoProducto getPromocionProdSeleccionada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromoTemporal getPromocionTempSeleccionada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getEstadoOperario() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ActualizarPromociones(ArrayList<PromoProducto> promocionesProd,
			ArrayList<PromoTemporal> promocionesTem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean is2x1() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCantidad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCantMinima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getpUnitario() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DayOfWeek> getDias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaPago getFormaPago() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHoraInicio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHoraFin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPorcentaje() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAcumulable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deseleccionarTodo() {

	}

	@Override
	public void ActualizarVentas(ArrayList<Venta> ventas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActualizarComandas(ArrayList<Comanda> comandas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EstadoMozo getEstadoMozo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ActualizarPedidos(ArrayList<Pedido> pedidos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void estadisticas(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.btnAPLICAR.setEnabled(validar());
		
	}
	
	private boolean validar() {
		boolean resp=false;
		
		resp = this.textNombre.getText() != null && !this.textNombre.getText().isEmpty() 
			   && this.textCosto.getText() != null && !this.textCosto.getText().isEmpty()
		       && this.textVenta.getText() != null && !this.textVenta.getText().isEmpty()
		       && this.textFieldStock.getText() != null && !this.textFieldStock.getText().isEmpty();
		      
		
		return resp;
	}


}