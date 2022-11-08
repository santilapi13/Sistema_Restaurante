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


public class VPromoProd extends JFrame implements IVistaLogin {
	
	private JPanel contentPane;
	private JTextField textCantMinima;
	private JTextField textPUnitario;
	private JRadioButton rdbtnLunes;
	private JRadioButton rdbtnMartes;
	private JRadioButton rdbtnMiercoles;
	private JRadioButton rdbtnJueves;
	private JRadioButton rdbtnViernes;
	private JRadioButton rdbtnSabado;
	private JRadioButton rdbtnDomingo;
	private JRadioButton rdbtn2x1;
	private JRadioButton rdbtnCantidad;
	private JButton btnENVIAR;
	private ActionListener actionListener;
	
	private JScrollPane scrollPane;
	private JList<Producto> listProductos;
	private DefaultListModel<Producto> modeloProd = new DefaultListModel<Producto>();
	
	public VPromoProd() {
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
		
		JLabel lblNewLabel_1 = new JLabel("PROMOCION DE PRODUCTO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 8, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Dias Activa:");
		panel_2.add(lblNewLabel_2);
		
		this.rdbtnLunes = new JRadioButton("Lunes");
		panel_2.add(rdbtnLunes);
		
		this.rdbtnMartes = new JRadioButton("Martes");
		panel_2.add(rdbtnMartes);
		
		this.rdbtnMiercoles = new JRadioButton("Miercoles");
		panel_2.add(rdbtnMiercoles);
		
		this.rdbtnJueves = new JRadioButton("Jueves");
		panel_2.add(rdbtnJueves);
		
		this.rdbtnViernes = new JRadioButton("Viernes");
		panel_2.add(rdbtnViernes);
		
		this.rdbtnSabado = new JRadioButton("Sabado");
		panel_2.add(rdbtnSabado);
		
		this.rdbtnDomingo = new JRadioButton("Domingo");
		panel_2.add(rdbtnDomingo);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Elija Producto");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_8);
		
		
		
		listProductos = new JList();
		scrollPane = new JScrollPane();
		panel_5.add(this.scrollPane, BorderLayout.CENTER);
		this.scrollPane.setViewportView(this.listProductos);
		
		this.rdbtn2x1 = new JRadioButton("2x1");
		panel_5.add(rdbtn2x1);
		
		this.rdbtnCantidad = new JRadioButton("Cantidad");
		panel_5.add(rdbtnCantidad);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("Cantidad minima");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel_9);
		
		textCantMinima = new JTextField();
		panel_7.add(textCantMinima);
		textCantMinima.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Precio Unitario");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel_10);
		
		textPUnitario = new JTextField();
		panel_7.add(textPUnitario);
		textPUnitario.setColumns(10);
		
		this.btnENVIAR = new JButton("ACEPTAR");
		btnENVIAR.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnENVIAR.setForeground(Color.BLACK);
		panel.add(btnENVIAR, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("GESTOR PROMOCIONES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		this.listProductos.setModel(modeloProd);
		
		this.setVisible(true);
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
      this.btnENVIAR.addActionListener(actionListener);
      this.rdbtn2x1.addActionListener(actionListener);
      this.rdbtnCantidad.addActionListener(actionListener);
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
		return null;
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
	public void ActualizarProductos(ArrayList<Producto> productos) {
		this.modeloProd.removeAllElements();
	
	  for (Producto prodAct : productos)
		   this.modeloProd.addElement(prodAct);
	   this.validate();
	
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
		return this.rdbtn2x1.isSelected();
	}

	@Override
	public boolean isCantidad() {
		return this.rdbtnCantidad.isSelected();
	}

	@Override
	public int getCantMinima() {
		String text = this.textCantMinima.getText();
		return Integer.parseInt(text);
	}

	@Override
	public double getpUnitario() {
		return Double.parseDouble(this.textPUnitario.getText());
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
