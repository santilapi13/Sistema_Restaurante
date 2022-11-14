package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Promocion;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class VAdmin extends JFrame implements IVistaAdmin, MouseListener {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JButton btnOperario;
	private JButton btnMozo;
	private JButton btnMesa;
	private JButton btnPromocion;
	private JButton btnSalir;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel lblNewLabel_4;
	private JScrollPane scrollPane_3;
	private JPanel panel_12;
	private JList<Operario> listOperarios;
	private JList<Mozo> listMozos;
	private JList<Mesa> listMesas;
	private JList<PromoTemporal> listPromoTemp;
	private JButton btnModificarOp;
	private JButton btnEliminarOp;
	private JButton btnModificarMozo;
	private JButton btnEliminarMozo;
	private JButton btnModificarMesa;
	private JButton btnEliminarMesa;
	private JButton btnModificarPromoT;
	private JButton btnEliminarPromoT;
	private ActionListener actionListener;
	private JPanel panel_13;
	private JPanel panel_14;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane_4;
	private JList<PromoProducto> listPromoProd;
	private JPanel panel_15;
	private JButton btnModificarPromoP;
	private JButton btnEliminarPromoP;
	private JButton btnProducto;
	private JPanel panel_16;
	private JLabel lblNewLabel_6;
	private JScrollPane scrollPane_5;
	private JPanel panel_17;
	private JButton btnModificarProducto;
	private JButton btnEliminarProducto;
	private JList<Producto> listProductos;
	private DefaultListModel<Producto> modeloListaProducto = new DefaultListModel<Producto>();
	private DefaultListModel<PromoProducto> modeloListaPromoProducto = new DefaultListModel<PromoProducto>();
	private DefaultListModel<PromoTemporal> modeloListaPromoTemporal = new DefaultListModel<PromoTemporal>();
	private DefaultListModel<Mesa> modeloListaMesa = new DefaultListModel<Mesa>();
	private DefaultListModel<Mozo> modeloListaMozo = new DefaultListModel<Mozo>();
	private DefaultListModel<Operario> modeloListaOperario = new DefaultListModel<Operario>();
	private JPanel panel_18;
	private JScrollPane scrollPane_6;
	private JList<String> listNotificacion;
	private DefaultListModel<String> modeloListaNoti = new DefaultListModel<String>();
	private JPanel panel_19;
	private JButton btnInforme;

	public VAdmin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 471);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel, BorderLayout.WEST);
		this.panel.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EmptyBorder(0, 80, 0, 80));
		this.panel.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblNewLabel = new JLabel("ADMINISTRADOR");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_2.add(this.lblNewLabel);

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(7, 0, 0, 0));

		this.btnOperario = new JButton("AGREGAR OPERARIO");
		this.btnOperario.setActionCommand("OPERARIO");
		this.panel_3.add(this.btnOperario);

		this.btnProducto = new JButton("AGREGAR PRODUCTO");
		this.btnProducto.setActionCommand("PRODUCTO");
		this.panel_3.add(this.btnProducto);

		this.btnMozo = new JButton("AGREGAR MOZO");
		this.btnMozo.setActionCommand("MOZO");
		this.panel_3.add(this.btnMozo);

		this.panel_19 = new JPanel();
		this.panel_3.add(this.panel_19);
		this.panel_19.setLayout(new BorderLayout(0, 0));

		this.btnMesa = new JButton("AGREGAR MESA");
		this.panel_19.add(this.btnMesa);
		this.btnMesa.setActionCommand("MESA");

		this.btnPromocion = new JButton("AGREGAR PROMOCION");
		this.btnPromocion.setActionCommand("PROMOCION");
		this.panel_3.add(this.btnPromocion);

		this.btnInforme = new JButton("INFORME MOZOS");
		this.btnInforme.setActionCommand("INFORME MOZOS");
		this.panel_3.add(this.btnInforme);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setActionCommand("SALIR_ADMIN");
		this.panel_3.add(this.btnSalir);

		this.panel_18 = new JPanel();
		this.panel.add(this.panel_18);
		this.panel_18.setLayout(new BorderLayout(0, 0));

		this.scrollPane_6 = new JScrollPane();
		this.panel_18.add(this.scrollPane_6);

		this.listNotificacion = new JList<String>();
		this.listNotificacion.setModel(modeloListaNoti);
		this.scrollPane_6.setViewportView(this.listNotificacion);

		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new EmptyBorder(0, 0, 5, 0));
		this.panel_1.add(this.panel_4);
		this.panel_4.setLayout(new GridLayout(0, 3, 0, 0));

		this.panel_6 = new JPanel();
		this.panel_6.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel_4.add(this.panel_6);
		this.panel_6.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_1 = new JLabel("OPERARIOS");
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel_6.add(this.lblNewLabel_1, BorderLayout.NORTH);

		this.scrollPane = new JScrollPane();
		this.panel_6.add(this.scrollPane, BorderLayout.CENTER);

		this.listOperarios = new JList<Operario>();
		this.listOperarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listOperarios.addMouseListener(this);
		this.listOperarios.setModel(modeloListaOperario);
		this.scrollPane.setViewportView(this.listOperarios);

		this.panel_9 = new JPanel();
		this.panel_6.add(this.panel_9, BorderLayout.SOUTH);
		this.panel_9.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarOp = new JButton("MODIFICAR");
		this.btnModificarOp.setEnabled(false);
		this.btnModificarOp.addMouseListener(this);
		this.btnModificarOp.setActionCommand("MODIFICAR_OP");
		this.panel_9.add(this.btnModificarOp);

		this.btnEliminarOp = new JButton("ELIMINAR");
		this.btnEliminarOp.setEnabled(false);
		this.btnEliminarOp.addMouseListener(this);
		this.btnEliminarOp.setActionCommand("ELIMINAR_OP");
		this.panel_9.add(this.btnEliminarOp);

		this.panel_7 = new JPanel();
		this.panel_7.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel_4.add(this.panel_7);
		this.panel_7.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_2 = new JLabel("MOZOS");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_7.add(this.lblNewLabel_2, BorderLayout.NORTH);

		this.scrollPane_1 = new JScrollPane();
		this.panel_7.add(this.scrollPane_1, BorderLayout.CENTER);

		this.listMozos = new JList<Mozo>();
		this.listMozos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listMozos.addMouseListener(this);
		this.listMozos.setModel(modeloListaMozo);
		this.scrollPane_1.setViewportView(this.listMozos);

		this.panel_10 = new JPanel();
		this.panel_7.add(this.panel_10, BorderLayout.SOUTH);
		this.panel_10.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarMozo = new JButton("MODIFICAR");
		this.btnModificarMozo.setEnabled(false);
		this.btnModificarMozo.addMouseListener(this);
		this.btnModificarMozo.setActionCommand("MODIFICAR_MOZO");
		this.panel_10.add(this.btnModificarMozo);

		this.btnEliminarMozo = new JButton("ELIMINAR");
		this.btnEliminarMozo.setEnabled(false);
		this.btnEliminarMozo.addMouseListener(this);
		this.btnEliminarMozo.setActionCommand("ELIMINAR_MOZO");
		this.panel_10.add(this.btnEliminarMozo);

		this.panel_8 = new JPanel();
		this.panel_8.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel_4.add(this.panel_8);
		this.panel_8.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_3 = new JLabel("MESAS");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_8.add(this.lblNewLabel_3, BorderLayout.NORTH);

		this.scrollPane_2 = new JScrollPane();
		this.panel_8.add(this.scrollPane_2, BorderLayout.CENTER);

		this.listMesas = new JList<Mesa>();
		this.listMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listMesas.addMouseListener(this);
		this.listMesas.setModel(modeloListaMesa);
		this.scrollPane_2.setViewportView(this.listMesas);

		this.panel_11 = new JPanel();
		this.panel_8.add(this.panel_11, BorderLayout.SOUTH);
		this.panel_11.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarMesa = new JButton("MODIFICAR");
		this.btnModificarMesa.setEnabled(false);
		this.btnModificarMesa.addMouseListener(this);
		this.btnModificarMesa.setActionCommand("MODIFICAR_MESA");
		this.panel_11.add(this.btnModificarMesa);

		this.btnEliminarMesa = new JButton("ELIMINAR");
		this.btnEliminarMesa.setEnabled(false);
		this.btnEliminarMesa.addMouseListener(this);
		this.btnEliminarMesa.setActionCommand("ELIMINAR_MESA");
		this.panel_11.add(this.btnEliminarMesa);

		this.panel_13 = new JPanel();
		this.panel_1.add(this.panel_13);
		this.panel_13.setLayout(new GridLayout(0, 3, 0, 0));

		this.panel_16 = new JPanel();
		this.panel_13.add(this.panel_16);
		this.panel_16.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_6 = new JLabel("PRODUCTOS");
		this.lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel_16.add(this.lblNewLabel_6, BorderLayout.NORTH);

		this.scrollPane_5 = new JScrollPane();
		this.panel_16.add(this.scrollPane_5, BorderLayout.CENTER);

		this.listProductos = new JList<Producto>();
		this.listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listProductos.addMouseListener(this);
		this.listProductos.setModel(modeloListaProducto);
		this.scrollPane_5.setViewportView(this.listProductos);

		this.panel_17 = new JPanel();
		this.panel_16.add(this.panel_17, BorderLayout.SOUTH);
		this.panel_17.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarProducto = new JButton("MODIFICAR");
		this.btnModificarProducto.setEnabled(false);
		this.btnModificarProducto.addMouseListener(this);
		this.btnModificarProducto.setActionCommand("MODIFICAR_PRODUCTO");
		this.panel_17.add(this.btnModificarProducto);

		this.btnEliminarProducto = new JButton("ELIMINAR");
		this.btnEliminarProducto.setEnabled(false);
		this.btnEliminarProducto.addMouseListener(this);
		this.btnEliminarProducto.setActionCommand("ELIMINAR_PRODUCTO");
		this.panel_17.add(this.btnEliminarProducto);

		this.panel_5 = new JPanel();
		this.panel_13.add(this.panel_5);
		this.panel_5.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel_5.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_4 = new JLabel("PROMOCIONES TEMPORALES");
		this.lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panel_5.add(this.lblNewLabel_4, BorderLayout.NORTH);

		this.scrollPane_3 = new JScrollPane();
		this.panel_5.add(this.scrollPane_3, BorderLayout.CENTER);

		this.listPromoTemp = new JList<PromoTemporal>();
		this.listPromoTemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listPromoTemp.addMouseListener(this);
		this.listPromoTemp.setModel(modeloListaPromoTemporal);
		this.scrollPane_3.setViewportView(this.listPromoTemp);

		this.panel_12 = new JPanel();
		this.panel_5.add(this.panel_12, BorderLayout.SOUTH);
		this.panel_12.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarPromoT = new JButton("MODIFICAR");
		this.btnModificarPromoT.setEnabled(false);
		this.btnModificarPromoT.addMouseListener(this);
		this.btnModificarPromoT.setActionCommand("MODIFICAR_PROMOT");
		this.panel_12.add(this.btnModificarPromoT);

		this.btnEliminarPromoT = new JButton("ELIMINAR");
		this.btnEliminarPromoT.setEnabled(false);
		this.btnEliminarPromoT.addMouseListener(this);
		this.btnEliminarPromoT.setActionCommand("ELIMINAR_PROMOT");
		this.panel_12.add(this.btnEliminarPromoT);

		this.panel_14 = new JPanel();
		this.panel_13.add(this.panel_14);
		this.panel_14.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_5 = new JLabel("PROMOCIONES DE PRODUCTO");
		this.lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panel_14.add(this.lblNewLabel_5, BorderLayout.NORTH);

		this.scrollPane_4 = new JScrollPane();
		this.panel_14.add(this.scrollPane_4, BorderLayout.CENTER);

		this.listPromoProd = new JList<PromoProducto>();
		this.listPromoProd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listPromoProd.addMouseListener(this);
		this.listPromoProd.setModel(modeloListaPromoProducto);
		this.scrollPane_4.setViewportView(this.listPromoProd);

		this.panel_15 = new JPanel();
		this.panel_14.add(this.panel_15, BorderLayout.SOUTH);
		this.panel_15.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnModificarPromoP = new JButton("MODIFICAR");
		this.btnModificarPromoP.setEnabled(false);
		this.btnModificarPromoP.addMouseListener(this);
		this.btnModificarPromoP.setActionCommand("MODIFICAR_PROMOP");
		this.panel_15.add(this.btnModificarPromoP);

		this.btnEliminarPromoP = new JButton("ELIMINAR");
		this.btnEliminarPromoP.setEnabled(false);
		this.btnEliminarPromoP.addMouseListener(this);
		this.btnEliminarPromoP.setActionCommand("ELIMINAR_PROMOP");
		this.panel_15.add(this.btnEliminarPromoP);
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnEliminarOp.addActionListener(actionListener);
		this.btnEliminarMesa.addActionListener(actionListener);
		this.btnEliminarMozo.addActionListener(actionListener);
		this.btnEliminarPromoT.addActionListener(actionListener);
		this.btnMesa.addActionListener(actionListener);
		this.btnModificarMesa.addActionListener(actionListener);
		this.btnModificarMozo.addActionListener(actionListener);
		this.btnModificarOp.addActionListener(actionListener);
		this.btnModificarPromoT.addActionListener(actionListener);
		this.btnModificarPromoP.addActionListener(actionListener);
		this.btnModificarProducto.addActionListener(actionListener);
		this.btnMozo.addActionListener(actionListener);
		this.btnOperario.addActionListener(actionListener);
		this.btnProducto.addActionListener(actionListener);
		this.btnPromocion.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.btnInforme.addActionListener(actionListener);
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
		return (Operario) this.listOperarios.getSelectedValue();
	}

	@Override
	public Mozo getSelectedMozo() {
		return (Mozo) this.listMozos.getSelectedValue();
	}

	@Override
	public Mesa getSelectedMesa() {
		return (Mesa) this.listMesas.getSelectedValue();
	}

	@Override
	public PromoTemporal getSelectedPromoTemp() {
		return (PromoTemporal) this.listPromoTemp.getSelectedValue();
	}

	@Override
	public PromoProducto getSelectedPromoProd() {
		return (PromoProducto) this.listPromoProd.getSelectedValue();
	}

	@Override
	public Producto getSelectedProducto() {
		return (Producto) this.listProductos.getSelectedValue();
	}

	private void validar() {
		this.btnModificarMesa.setEnabled(!this.listMesas.isSelectionEmpty());
		this.btnEliminarMesa.setEnabled(!this.listMesas.isSelectionEmpty());
		this.btnModificarMozo.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnEliminarMozo.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnModificarOp.setEnabled(!this.listOperarios.isSelectionEmpty());
		this.btnEliminarOp.setEnabled(!this.listOperarios.isSelectionEmpty());
		this.btnModificarPromoP.setEnabled(!this.listPromoProd.isSelectionEmpty());
		this.btnEliminarPromoP.setEnabled(!this.listPromoProd.isSelectionEmpty());
		this.btnModificarPromoT.setEnabled(!this.listPromoTemp.isSelectionEmpty());
		this.btnEliminarPromoT.setEnabled(!this.listPromoTemp.isSelectionEmpty());
		this.btnModificarProducto.setEnabled(!this.listProductos.isSelectionEmpty());
		this.btnEliminarProducto.setEnabled(!this.listProductos.isSelectionEmpty());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		validar();
	}

	@Override
	public void actualizaListaProductos(ArrayList<Producto> productos) {
		this.modeloListaProducto.removeAllElements();
		for (Producto prodAct : productos)
			this.modeloListaProducto.addElement(prodAct);
		this.validate();
	}

	@Override
	public void actualizaListaOperarios(ArrayList<Operario> operarios) {
		this.modeloListaOperario.removeAllElements();
		for (Operario opAct : operarios)
			this.modeloListaOperario.addElement(opAct);
		this.validate();
	}

	@Override
	public void actualizaListaMesas(ArrayList<Mesa> mesas) {
		this.modeloListaMesa.removeAllElements();
		for (Mesa mesaAct : mesas)
			this.modeloListaMesa.addElement(mesaAct);
		this.validate();
	}

	@Override
	public void actualizaListaPromoTemp(ArrayList<PromoTemporal> promos) {
		this.modeloListaPromoTemporal.removeAllElements();
		for (PromoTemporal promoAct : promos)
			this.modeloListaPromoTemporal.addElement(promoAct);
		this.validate();
	}

	@Override
	public void actualizaListaPromoProd(ArrayList<PromoProducto> promos) {
		this.modeloListaPromoProducto.removeAllElements();
		for (PromoProducto promoAct : promos)
			this.modeloListaPromoProducto.addElement(promoAct);
		this.validate();

	}

	@Override
	public void actualizaListaMozos(ArrayList<Mozo> mozos) {
		this.modeloListaMozo.removeAllElements();
		for (Mozo mozoAct : mozos)
			this.modeloListaMozo.addElement(mozoAct);
		this.validate();
	}

	@Override
	public void notificar(String noti) {
		this.modeloListaNoti.addElement(noti);
		this.validate();
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getActivo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCant() {
		// TODO Auto-generated method stub
		return 0;
	}
}
