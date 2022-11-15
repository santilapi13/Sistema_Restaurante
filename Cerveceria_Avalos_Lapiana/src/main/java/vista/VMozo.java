package vista;

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
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;

public class VMozo extends JFrame implements IVistaLogin {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textNyA;
	private JLabel lblNewLabel_2;
	private JTextField textFecha;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerCant;
	private JButton btnAnotar;

	
	/**
	 * Create the frame.
	 */
	public VMozo() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 467);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("Agregar Mozo");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.GRAY);
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(7, 0, 0, 0));
		
		this.lblNewLabel_1 = new JLabel("Nombre y Apellido");
		lblNewLabel_1.setForeground(Color.RED);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_1);
		
		this.textNyA = new JTextField();
		this.panel.add(this.textNyA);
		this.textNyA.setColumns(10);
		
		this.lblNewLabel_2 = new JLabel("Fecha de nacimiento");
		lblNewLabel_2.setForeground(Color.RED);
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_2);
		
		this.textFecha = new JTextField();
		this.panel.add(this.textFecha);
		this.textFecha.setColumns(10);
		
		this.lblNewLabel_3 = new JLabel("Cantidad de hijos");
		lblNewLabel_3.setForeground(Color.RED);
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_3);
		
		this.spinnerCant = new JSpinner();
		this.spinnerCant.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		this.panel.add(this.spinnerCant);
		
		this.btnAnotar = new JButton("Aceptar");
		btnAnotar.setForeground(Color.RED);
		this.btnAnotar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnAnotar);
		
		this.setVisible(true);
	}


	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAnotar.addActionListener(actionListener);
		
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
		return this.textNyA.getText();
	}


	@Override
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getHijos() {
		int value = (Integer) this.spinnerCant.getValue();
		  return value;
	}


	@Override
	public String fecha() {
		return this.textFecha.getText();
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
	
	

}
