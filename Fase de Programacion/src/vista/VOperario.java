package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.LineBorder;

import modelo.*;

import java.awt.Color;
import javax.swing.JTextArea;

public class VOperario extends JFrame implements IVistaLogin {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane2;
	private JList<Mozo> listMozos;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane_1;
	private JList<Mesa> listMesas;
	private JPanel panel_4;
	private JButton btnAsignar;
	private JButton btnPedido;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel_2;
	private JComboBox comboBoxPago;
	private JPanel panel_7;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane_2;
	private JList<Comanda> listComandas;
	private JButton btnCerrarmesa;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel lblNewLabel_4;
	private JPanel panel_10;
	private JLabel lblNewLabel_5;
	private JComboBox comboBoxEstado;
	private JButton btnAplicar;
	private JPanel panel_11;
	private JButton btnSalir;
	private JButton btnContrasena;
	private JLabel lblNewLabel_6;
	private ActionListener actionListener;
	
	private DefaultListModel<Comanda> modeloComanda = new DefaultListModel<Comanda>();
	private DefaultListModel<Venta> modeloVenta = new DefaultListModel<Venta>();
	private DefaultListModel<Mozo> modeloMozo = new DefaultListModel<Mozo>();
	private DefaultListModel<Mesa> modeloMesa = new DefaultListModel<Mesa>();
	private JPanel panel_12;
	private JList<Venta> listVentas;
	private JPanel panel_13;
	private JTextArea textArea;
	private JButton btnEstadistica;
	
	/**
	 * Create the frame.
	 */
	public VOperario(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 5, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("MOZOS");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.listMozos = new JList();
		this.scrollPane.setViewportView(this.listMozos);
		
		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3, BorderLayout.SOUTH);
		this.panel_3.setLayout(new GridLayout(3, 0, 0, 0));
		
		panel_10 = new JPanel();
		panel_3.add(panel_10);
		panel_10.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblNewLabel_5 = new JLabel("ESTADO");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_10.add(lblNewLabel_5);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVO", "FRANCO", "AUSENTE"}));
		comboBoxEstado.setEditable(true);
		panel_10.add(comboBoxEstado);
		
		btnAplicar = new JButton("APLICAR");
		panel_3.add(btnAplicar);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel_1 = new JLabel("MESAS");
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel_1.add(this.lblNewLabel_1, BorderLayout.NORTH);
		
		this.scrollPane_1 = new JScrollPane();
		this.panel_1.add(this.scrollPane_1, BorderLayout.CENTER);
		
		this.listMesas = new JList();
		this.scrollPane_1.setViewportView(this.listMesas);
		
		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4, BorderLayout.SOUTH);
		this.panel_4.setLayout(new GridLayout(4, 0, 0, 0));
		
		this.btnAsignar = new JButton("ASIGNAR");
		this.panel_4.add(this.btnAsignar);
		
		this.btnPedido = new JButton("TOMAR COMANDA");
		this.panel_4.add(this.btnPedido);
		
		this.panel_5 = new JPanel();
		this.panel_4.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.panel_6 = new JPanel();
		this.panel_5.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.lblNewLabel_2 = new JLabel("FORMA DE PAGO");
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_6.add(this.lblNewLabel_2);
		
		this.comboBoxPago = new JComboBox();
		this.comboBoxPago.setModel(new DefaultComboBoxModel(new String[] {"EFECTIVO", "TARJETA", "MERCADO PAGO", "CUENTA DNI"}));
		this.comboBoxPago.setSelectedIndex(0);
		this.comboBoxPago.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.comboBoxPago.setEditable(true);
		this.panel_6.add(this.comboBoxPago);
		
		this.btnCerrarmesa = new JButton("CERRAR MESA");
		this.panel_4.add(this.btnCerrarmesa);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.panel_7 = new JPanel();
		this.panel_2.add(this.panel_7);
		this.panel_7.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel_3 = new JLabel("COMANDAS");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_7.add(this.lblNewLabel_3, BorderLayout.NORTH);
		
		this.scrollPane_2 = new JScrollPane();
		this.panel_7.add(this.scrollPane_2, BorderLayout.CENTER);
		
		this.listComandas = new JList();
		this.scrollPane_2.setViewportView(this.listComandas);
		
		panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 5, 0, 5));
		contentPane.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_4 = new JLabel("Estadisticas Mozo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_4, BorderLayout.NORTH);
		
		panel_12 = new JPanel();
		panel_9.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		this.scrollPane3 = new JScrollPane();
		this.panel_12.add(this.scrollPane3, BorderLayout.CENTER);
		
		this.listVentas = new JList();
		this.scrollPane3.setViewportView(this.listVentas);
		
		panel_11 = new JPanel();
		contentPane.add(panel_11);
		panel_11.setLayout(new GridLayout(3, 0, 0, 0));
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_11.add(btnSalir);
		
		btnContrasena = new JButton("Cambiar Contrasena");
		btnContrasena.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(btnContrasena);
		
		lblNewLabel_6 = new JLabel(username);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblNewLabel_6);
		this.listMozos.setModel(modeloMozo);
		this.listMesas.setModel(modeloMesa);
		this.listComandas.setModel(modeloComanda);
		this.listVentas.setModel(modeloVenta);
		
		panel_13 = new JPanel();
		panel_12.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		this.scrollPane2 = new JScrollPane();
		
		panel_13.add(this.scrollPane2, BorderLayout.CENTER);
		this.scrollPane2.setViewportView(this.textArea);
		
		btnEstadistica = new JButton("Estadistica Mozo");
		panel_13.add(btnEstadistica, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}


	public void setActionListener(ActionListener actionListener) {
		this.btnAplicar.addActionListener(actionListener);
		this.btnAsignar.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.btnContrasena.addActionListener(actionListener);
		this.btnPedido.addActionListener(actionListener);
		this.btnCerrarmesa.addActionListener(actionListener);
		this.btnEstadistica.addActionListener(actionListener);
		
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
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getPasswordActual() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getNya() {
		// TODO Auto-generated method stub
		return null;
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


	public void ActualizarMozos(ArrayList<Mozo> mozos) {
		this.modeloMozo.removeAllElements();
	
		for (Mozo mozoAct : mozos)
			this.modeloMozo.addElement(mozoAct);
		this.validate();
	}

@Override
	public void ActualizarMesas(ArrayList<Mesa> mesas) {
		this.modeloMesa.removeAllElements();
	
		for (Mesa mesaAct : mesas)
			this.modeloMesa.addElement(mesaAct);
		this.validate();
	
	}

@Override
	public void ActualizarVentas(ArrayList<Venta> ventas) {
		this.modeloVenta.removeAllElements();
	
		for (Venta vAct : ventas)
			this.modeloVenta.addElement(vAct);
		this.validate();
	
		
		
	}


@Override
	public void ActualizarComandas(ArrayList<Comanda> comandas) {
		this.modeloComanda.removeAllElements();
	
		for (Comanda cAct : comandas)
			this.modeloComanda.addElement(cAct);
		this.validate();
	
		}
	
	


	@Override
	public void ActualizarProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getComensales() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double pCosto() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double pVenta() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int stock() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean getIsMesaEmpty() {
		return this.listMesas.isSelectionEmpty();
	}

	@Override
	public boolean getIsMozoEmpty() {
		return this.listMozos.isSelectionEmpty();
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
	public Operario getOperarioSeleccionado() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mesa getMesaSeleccionada() {
		return this.listMesas.getSelectedValue();
	}

	@Override
	public Mozo getMozoSeleccionado() {
		return this.listMozos.getSelectedValue();
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


	public FormaPago getFormaPago() {
		String text = (String) this.comboBoxPago.getSelectedItem();
		
		FormaPago forma = null;
		
		if (text.equalsIgnoreCase("EFECTIVO"))
			forma = FormaPago.EFECTIVO;
		else if (text.equalsIgnoreCase("TARJETA")) 
			forma = FormaPago.TARJETA;
		else if (text.equalsIgnoreCase("MP")) 
			forma = FormaPago.MP;
		else if (text.equalsIgnoreCase("CTADNI")) 
			forma = FormaPago.CTADNI;
		
		return forma;
	
	}
	
	public EstadoMozo getEstadoMozo() {
		String text = (String) this.comboBoxEstado.getSelectedItem();
		
		EstadoMozo e = null;
		
		if (text.equalsIgnoreCase("ACTIVO"))
			e = EstadoMozo.ACTIVO;
		else if (text.equalsIgnoreCase("FRANCO")) 
			e = EstadoMozo.FRANCO;
		else if (text.equalsIgnoreCase("AUSENTE")) 
			e = EstadoMozo.AUSENTE;
		
		
		return e;
	
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
	public void ActualizarPedidos(ArrayList<Pedido> pedidos) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void estadisticas(String s) {
		this.textArea.append(s);
		
	}


	

}