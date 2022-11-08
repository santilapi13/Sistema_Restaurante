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
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JRadioButton;

public class VRegOp extends JFrame implements IVistaLogin {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textUser;
	private JLabel lblNewLabel_2;
	private JButton btnRegistrar;
	private JLabel lblNewLabel_3;
	private JTextField textNyA;
	private JPasswordField textPass;
	private JRadioButton rdbtnActivo;

	

	/**
	 * Create the frame.
	 */
	public VRegOp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(9, 1, 0, 0));
		
		this.lblNewLabel = new JLabel("Agregar Operario");
		lblNewLabel.setForeground(Color.BLUE);
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.panel.add(this.lblNewLabel);
		
		this.lblNewLabel_3 = new JLabel("Nombre y Apellido");
		lblNewLabel_3.setForeground(Color.BLUE);
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_3);
		
		this.textNyA = new JTextField();
		this.textNyA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textNyA);
		this.textNyA.setColumns(10);
		
		this.lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.BLUE);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_1);
		
		this.textUser = new JTextField();
		this.textUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textUser);
		this.textUser.setColumns(10);
		
		this.lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.BLUE);
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_2);
		
		this.textPass = new JPasswordField();
		this.textPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textPass);
		
		rdbtnActivo = new JRadioButton("Activo");
		rdbtnActivo.setSelected(true);
		panel.add(rdbtnActivo);
		
		this.btnRegistrar = new JButton("ACEPTAR");
		this.btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel.add(this.btnRegistrar);
		
		this.setVisible(true);
	}



	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnRegistrar.addActionListener(actionListener);
		this.rdbtnActivo.addActionListener(actionListener);
		
	}



	@Override
	public void cerrarse() {
		this.dispose();
		
	}



	@Override
	public String getUsername() {
		return this.textUser.getText();
	}



	@Override
	public String getPassword() {
		return this.textPass.getText();
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
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getNya() {
		return this.textNyA.getText();
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
	public boolean getEstadoOperario() {
		return this.rdbtnActivo.isSelected();
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