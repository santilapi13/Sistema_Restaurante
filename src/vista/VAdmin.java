package vista;

import java.awt.EventQueue;
import modelo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JSpinner;

public class VAdmin extends JFrame implements IVistaLogin {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JButton btnOperario;
	private JButton btnMozo;
	private JButton btnPromocion;
	private JButton btnAgregarProducto;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel_4;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_9;
	private JScrollPane scrollPane_8;
	private JScrollPane scrollPane_7;
	private JList<Mozo> listMozos;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnContrasena;
	private JButton btnSalir;
	private JLabel lblNewLabel_1;
	private JList<Operario> listOperarios;
	private JList<Mesa> listMesas;
	private JLabel lblNewLabel_2;
	private JList<Promocion> listPromociones;
	private JLabel lblNewLabel_3;
	private JList<Producto> listProductos;
	private JLabel lblNewLabel_5;
	private JPanel panel_11;
	private JButton btnAgregarMesa;
	private JLabel lblNewLabel_6;
	private JSpinner spinnerComensales;
	private ActionListener actionListener;
	
	private DefaultListModel<Operario> modeloOperario = new DefaultListModel<Operario>();
	private DefaultListModel<Mozo> modeloMozo = new DefaultListModel<Mozo>();
	private DefaultListModel<Mesa> modeloMesa = new DefaultListModel<Mesa>();
	private DefaultListModel<Producto> modeloProd = new DefaultListModel<Producto>();
	private DefaultListModel<Promocion> modeloPromo = new DefaultListModel<Promocion>();
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 471);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel, BorderLayout.WEST);
		this.panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.lblNewLabel = new JLabel("ADMINISTRADOR");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_2.add(this.lblNewLabel);
		
		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(5, 0, 0, 0));
		
		this.btnOperario = new JButton("AGREGAR OPERARIO");
		
		this.panel_3.add(this.btnOperario);
		
		this.btnMozo = new JButton("AGREGAR MOZO");
		
		this.panel_3.add(this.btnMozo);
		
		this.btnPromocion = new JButton("AGREGAR PROMOCION");
	
		this.panel_3.add(this.btnPromocion);
		
		this.btnAgregarProducto = new JButton("AGREGAR PRODUCTO");
		this.panel_3.add(this.btnAgregarProducto);
		
		panel_11 = new JPanel();
		panel_3.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		btnAgregarMesa = new JButton("Agregar Mesa");
		panel_11.add(btnAgregarMesa);
		
		lblNewLabel_6 = new JLabel("Comensales");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblNewLabel_6, BorderLayout.NORTH);
		
		spinnerComensales = new JSpinner();
		panel_11.add(spinnerComensales, BorderLayout.EAST);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new EmptyBorder(0, 0, 5, 0));
		this.panel_1.add(this.panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("Operarios");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		listOperarios = new JList();
		
		this.scrollPane_4 = new JScrollPane();
		this.panel_4.add(this.scrollPane_4, BorderLayout.CENTER);
		
		this.scrollPane_4.setViewportView(this.listOperarios);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel_1.add(this.panel_5);
		this.panel_5.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel_4 = new JLabel("Mozos");
		lblNewLabel_4.setForeground(Color.RED);
		this.lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.panel_5.add(this.lblNewLabel_4, BorderLayout.NORTH);
		
		this.scrollPane_3 = new JScrollPane();
		this.panel_5.add(this.scrollPane_3, BorderLayout.CENTER);
		
		this.listMozos = new JList();
		this.scrollPane_3.setViewportView(this.listMozos);
		
		panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		listMesas = new JList();
		
		lblNewLabel_2 = new JLabel("Mesas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7.add(lblNewLabel_2, BorderLayout.NORTH);
		
		this.scrollPane_7 = new JScrollPane();
		this.panel_7.add(this.scrollPane_7, BorderLayout.CENTER);
		
		this.scrollPane_7.setViewportView(this.listMesas);
		
		
		panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		listPromociones = new JList();
		
		
		lblNewLabel_3 = new JLabel("Promociones");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_8.add(lblNewLabel_3, BorderLayout.NORTH);
		
		this.scrollPane_8 = new JScrollPane();
		this.panel_8.add(this.scrollPane_8, BorderLayout.CENTER);
		
		this.scrollPane_8.setViewportView(this.listPromociones);
		
		
		panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		listProductos = new JList();
		
		lblNewLabel_5 = new JLabel("Productos");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_9.add(lblNewLabel_5, BorderLayout.NORTH);
		
		this.scrollPane_9 = new JScrollPane();
		this.panel_9.add(this.scrollPane_9, BorderLayout.CENTER);
		
		this.scrollPane_9.setViewportView(this.listProductos);
		
		
		panel_6 = new JPanel();
		contentPane.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new GridLayout(4, 1, 0, 0));
		
		btnEliminar = new JButton("ELIMINAR");
		panel_10.add(btnEliminar);
		
		btnModificar = new JButton("MODIFICAR");
		panel_10.add(btnModificar);
		
		btnContrasena = new JButton("CONTRASENA");
		panel_10.add(btnContrasena);
		
		btnSalir = new JButton("SALIR");
		panel_10.add(btnSalir);
		
		
		this.listOperarios.setModel(modeloOperario);
		this.listMozos.setModel(modeloMozo);
		this.listMesas.setModel(modeloMesa);
		this.listProductos.setModel(modeloProd);
		this.listPromociones.setModel(modeloPromo);
		
		this.setVisible(true);
		
		
	}

	public void setActionListener(ActionListener actionListener) {
		this.btnAgregarMesa.addActionListener(actionListener);
		this.btnAgregarProducto.addActionListener(actionListener);
		this.btnContrasena.addActionListener(actionListener);
		this.btnEliminar.addActionListener(actionListener);
		this.btnModificar.addActionListener(actionListener);
		this.btnMozo.addActionListener(actionListener);
		this.btnOperario.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.btnPromocion.addActionListener(actionListener);
		
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

	
	public String getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ActualizarListaOperarios(ArrayList<Operario> operarios) {
			this.modeloOperario.removeAllElements();
			
		
		for (Operario operarioAct : operarios)
			this.modeloOperario.addElement(operarioAct);
		this.validate();
		
		
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
		public void ActualizarPromociones(ArrayList<Promocion> promociones) {
				this.modeloPromo.removeAllElements();
			
			for (Promocion promoAct : promociones)
				this.modeloPromo.addElement(promoAct);
			this.validate();
			
		}

		@Override
		public void ActualizarProductos(ArrayList<Producto> productos) {
				this.modeloProd.removeAllElements();
			
			for (Producto prodAct : productos)
				this.modeloProd.addElement(prodAct);
			this.validate();
			
		}

		@Override
		public int getComensales() {
			return this.spinnerComensales.getComponentCount();
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
			return this.listProductos.isSelectionEmpty();
		}

		@Override
		public Producto getProdSeleccionado() {
			return this.listProductos.getSelectedValue();
		}

	@Override
	public boolean getIsOperarioEmpty() {
		return this.listOperarios.isSelectionEmpty();
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
	public boolean getIsPromocionEmpty() {
		return this.listPromociones.isSelectionEmpty();
	}

	@Override
	public Operario getOperarioSeleccionado() {
		return this.listOperarios.getSelectedValue();
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
	public Promocion getPromocionSeleccionada() {
		return this.listPromociones.getSelectedValue();
	}

	@Override
	public boolean getEstadoOperario() {
		return false;
	}


}
