package vista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.*;
import java.awt.GridBagLayout;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VComanda extends JFrame implements IVistaLogin{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JList<Producto> listProductos;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JSpinner spinnerCantidad;
	private JButton btnAgregar;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JList<Pedido> listPedidos;
	private ActionListener actionListener;

	private DefaultListModel<Producto> modeloProd = new DefaultListModel<Producto>();
	private DefaultListModel<Pedido> modeloPedido = new DefaultListModel<Pedido>();
	private JButton btnsalir;
	

	/**
	 * Create the frame.
	 */
	public VComanda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 471);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("PRODUCTOS");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.listProductos = new JList();
		this.scrollPane.setViewportView(this.listProductos);
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2, BorderLayout.SOUTH);
		this.panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel_3 = new JPanel();
		this.panel_2.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.lblNewLabel_2 = new JLabel("CANTIDAD");
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_3.add(this.lblNewLabel_2);
		
		this.spinnerCantidad = new JSpinner();
		this.spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		this.panel_3.add(this.spinnerCantidad);
		
		this.btnAgregar = new JButton("AGREGAR");
		this.panel_2.add(this.btnAgregar);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("PEDIDOS DE COMANDA ACTUAL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		this.scrollPane2 = new JScrollPane();
		this.panel_1.add(this.scrollPane2, BorderLayout.CENTER);
		
		
		this.listPedidos = new JList();
		this.scrollPane2.setViewportView(this.listPedidos);
		
		this.listProductos.setModel(modeloProd);
		this.listPedidos.setModel(modeloPedido);
		
		btnsalir = new JButton("SALIR");
		panel_1.add(btnsalir, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}



	@Override
	public void setActionListener(ActionListener actionListener) {
		
		this.btnAgregar.addActionListener(actionListener);
		this.btnsalir.addActionListener(actionListener);
		
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
	public void ActualizarPromociones(ArrayList<PromoProducto> promocionesProd,
			ArrayList<PromoTemporal> promocionesTem) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ActualizarProductos(ArrayList<Producto> productos) {
		this.modeloProd.removeAllElements();
	
	for (Producto prodAct : productos)
		this.modeloProd.addElement(prodAct);
	this.validate();
	
	}
	
	public void ActualizarPedidos(ArrayList<Pedido> pedidos) {
		this.modeloPedido.removeAllElements();
		
		for (Pedido pAct : pedidos)
			this.modeloPedido.addElement(pAct);
		this.validate();
		
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
	public boolean getIsProductoEmpty() {
		return this.listProductos.isSelectionEmpty();
	}

	@Override
	public Producto getProdSeleccionado() {
		return this.listProductos.getSelectedValue();
	}



	@Override
	public boolean getIsOperarioEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean getIsMesaEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean getIsMozoEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Mozo getMozoSeleccionado() {
		// TODO Auto-generated method stub
		return null;
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
		int value = (Integer) this.spinnerCantidad.getValue();
		  return value;
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
	public EstadoMozo getEstadoMozo() {
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
	public void estadisticas(String s) {
		// TODO Auto-generated method stub
		
	}


}
