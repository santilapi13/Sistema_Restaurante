package vista;

import modelo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import javax.swing.JList;


public class VPromoTemp extends JFrame implements IVistaLogin {
	
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textHoraInicio;
	private JTextField textHoraFin;
	private JTextField textDescuento;
	private JRadioButton rdbtnLunes;
	private JRadioButton rdbtnMartes;
	private JRadioButton rdbtnMiercoles;
	private JRadioButton rdbtnJueves;
	private JRadioButton rdbtnViernes;
	private JRadioButton rdbtnSabado;
	private JRadioButton rdbtnDomingo;
	private JRadioButton rdbtnAcumulable;
	private JComboBox comboBoxPago;
	private JButton btnEnviar; 
	
	private ActionListener actionListener;
	
	public VPromoTemp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 477);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("PROMOCION TEMPORAL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 8, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Dias Activa:");
		panel_2.add(lblNewLabel_2);
		
		rdbtnLunes = new JRadioButton("Lunes");
		panel_2.add(rdbtnLunes);
		
		rdbtnMartes = new JRadioButton("Martes");
		panel_2.add(rdbtnMartes);
		
		rdbtnMiercoles = new JRadioButton("Miercoles");
		panel_2.add(rdbtnMiercoles);
		
		rdbtnJueves = new JRadioButton("Jueves");
		panel_2.add(rdbtnJueves);
		
		rdbtnViernes = new JRadioButton("Viernes");
		panel_2.add(rdbtnViernes);
		
		rdbtnSabado = new JRadioButton("Sabado");
		panel_2.add(rdbtnSabado);
		
		rdbtnDomingo = new JRadioButton("Domingo");
		panel_2.add(rdbtnDomingo);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		panel_3.setLayout(new GridLayout(8, 0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(lblNewLabel_3);
		
		textNombre = new JTextField();
		panel_3.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Forma de Pago");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_4);
		
		comboBoxPago = new JComboBox();
		comboBoxPago.setModel(new DefaultComboBoxModel(new String[] {" EFECTIVO", "TARJETA", "MP", "CTADNI"}));
		comboBoxPago.setSelectedIndex(0);
		comboBoxPago.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPago.setEditable(true);
		panel_3.add(comboBoxPago);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Hora Inicio");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_5);
		
		textHoraInicio = new JTextField();
		panel_6.add(textHoraInicio);
		textHoraInicio.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Hora Fin");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_6);
		
		textHoraFin = new JTextField();
		panel_6.add(textHoraFin);
		textHoraFin.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Porcentaje Descuento");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_7);
		
		textDescuento = new JTextField();
		panel_3.add(textDescuento);
		textDescuento.setColumns(10);
		
		rdbtnAcumulable = new JRadioButton("Es acumulable");
		rdbtnAcumulable.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(rdbtnAcumulable);
		
		btnEnviar = new JButton("ACEPTAR");
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEnviar.setForeground(Color.BLACK);
		panel.add(btnEnviar, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("GESTOR PROMOCIONES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		this.setVisible(true);
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
	      this.btnEnviar.addActionListener(actionListener);
	      this.rdbtnDomingo.addActionListener(actionListener);
	      this.rdbtnLunes.addActionListener(actionListener);
	      this.rdbtnMartes.addActionListener(actionListener);
	      this.rdbtnMiercoles.addActionListener(actionListener);
	      this.rdbtnJueves.addActionListener(actionListener);
	      this.rdbtnViernes.addActionListener(actionListener);
	      this.rdbtnSabado.addActionListener(actionListener);
	      
	      this.actionListener = actionListener;
		}

	@Override
	public void cerrarse() {
      this.dispose();
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
	public String getTipo() {
		return null;
	}

	@Override
	public String getPasswordActual() {
		return null;
	}

	@Override
	public String getNya() {
		return this.textNombre.getText();
	}

	@Override
	public int getHijos() {
		return 0;
	}

	@Override
	public String fecha() {
		return null;
	}

	@Override
	public int getComensales() {
		return 0;
	}

	@Override
	public double pCosto() {
		return 0;
	}

	@Override
	public double pVenta() {
		return 0;
	}

	@Override
	public int stock() {
		return 0;
	}

	@Override
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {

	}

	@Override
	public void ActualizarMozos(ArrayList<Mozo> mozos) {

	}

	@Override
	public void ActualizarMesas(ArrayList<Mesa> mesas) {

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
		ArrayList<DayOfWeek> diasDePromo = new ArrayList<DayOfWeek>();
		
		if (this.rdbtnLunes.isSelected()) {
		  diasDePromo.add(DayOfWeek.MONDAY);	
		}
		if (this.rdbtnMartes.isSelected()) {
			  diasDePromo.add(DayOfWeek.TUESDAY);	
		}
		if (this.rdbtnMiercoles.isSelected()) {
			  diasDePromo.add(DayOfWeek.WEDNESDAY);	
		}
		if (this.rdbtnJueves.isSelected()) {
			  diasDePromo.add(DayOfWeek.THURSDAY);	
		}
		if (this.rdbtnViernes.isSelected()) {
			  diasDePromo.add(DayOfWeek.FRIDAY);	
		}
		if (this.rdbtnSabado.isSelected()) {
			  diasDePromo.add(DayOfWeek.SATURDAY);	
		}
		if (this.rdbtnDomingo.isSelected()) {
			  diasDePromo.add(DayOfWeek.SUNDAY);	
		}
		
		
		return diasDePromo;
	}

	@Override
	public FormaPago getFormaPago() {
		String text = (String) this.comboBoxPago.getSelectedItem();
		
		FormaPago forma = FormaPago.EFECTIVO;
		
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

	@Override
	public int getHoraInicio() {
		String text = this.textHoraInicio.getText();
		return Integer.parseInt(text);
	}

	@Override
	public int getHoraFin() {
		String text = this.textHoraFin.getText();
		return Integer.parseInt(text);
	}

	@Override
	public double getPorcentaje() {
		return Double.parseDouble(this.textDescuento.getText());
	}

	@Override
	public boolean isAcumulable() {
		return this.rdbtnAcumulable.isSelected();
	}

	@Override
	public void deseleccionarTodo() {

	}

	@Override
	public void ActualizarProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		
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
}

