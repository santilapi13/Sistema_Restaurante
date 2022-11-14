package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Venta;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class VOperario extends JFrame implements IVistaOperario, MouseListener {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JList<Mozo> listMozos;
	private JButton btnActivo;
	private JButton btnFranco;
	private JButton btnAusente;
	private JButton btnEstadisticas;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane_1;
	private JList<Mesa> listMesas;
	private JPanel panel_4;
	private JButton btnAsignar;
	private JButton btnPedido;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel_2;
	private JButton btnCerrar;
	private JComboBox<String> comboBoxPago;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JList<Comanda> listComandas;
	private JList<Venta> listVentas;
	private JButton btnSalir;
	private ActionListener actionListener;
	private DefaultListModel<Comanda> modeloListaComanda = new DefaultListModel<Comanda>();
	private DefaultListModel<Mesa> modeloListaMesa = new DefaultListModel<Mesa>();
	private DefaultListModel<Mozo> modeloListaMozo = new DefaultListModel<Mozo>();
	private DefaultListModel<Venta> modeloListaVenta = new DefaultListModel<Venta>();
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JScrollPane scrollPane_4;
	private JList<String> listNotificacion;
	private DefaultListModel<String> modeloListaNoti = new DefaultListModel<String>();
	private JButton btnLiberarMesas;
	
	public VOperario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 3, 0, 0));

		this.panel_9 = new JPanel();
		this.contentPane.add(this.panel_9);
		this.panel_9.setLayout(new GridLayout(3, 1, 0, 0));

		this.panel = new JPanel();
		this.panel_9.add(this.panel);
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("MOZOS");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);

		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);

		this.listMozos = new JList<Mozo>();
		this.listMozos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listMozos.addMouseListener(this);
		this.listMozos.setModel(modeloListaMozo);
		this.scrollPane.setViewportView(this.listMozos);

		this.panel_10 = new JPanel();
		this.panel_9.add(this.panel_10);
		this.panel_10.setLayout(new GridLayout(4, 0, 0, 0));

		this.btnActivo = new JButton("MARCAR ACTIVO");
		this.panel_10.add(this.btnActivo);
		this.btnActivo.setEnabled(false);
		this.btnActivo.setActionCommand("ACTIVO");

		this.btnFranco = new JButton("MARCAR FRANCO");
		this.panel_10.add(this.btnFranco);
		this.btnFranco.setEnabled(false);
		this.btnFranco.setActionCommand("FRANCO");

		this.btnAusente = new JButton("MARCAR AUSENTE");
		this.panel_10.add(this.btnAusente);
		this.btnAusente.setEnabled(false);
		this.btnAusente.setActionCommand("AUSENTE");

		this.btnEstadisticas = new JButton("OBTENER ESTADISTICAS");
		this.panel_10.add(this.btnEstadisticas);
		this.btnEstadisticas.setEnabled(false);
		this.btnEstadisticas.setActionCommand("ESTADISTICAS");

		this.panel_11 = new JPanel();
		this.panel_9.add(this.panel_11);
		this.panel_11.setLayout(new BorderLayout(0, 0));

		this.scrollPane_4 = new JScrollPane();
		this.panel_11.add(this.scrollPane_4);

		this.listNotificacion = new JList<String>();
		this.listNotificacion.setModel(modeloListaNoti);
		this.scrollPane_4.setViewportView(this.listNotificacion);

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

		this.listMesas = new JList<Mesa>();
		this.listMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listMesas.addMouseListener(this);
		this.listMesas.setModel(modeloListaMesa);
		this.scrollPane_1.setViewportView(this.listMesas);

		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4, BorderLayout.SOUTH);
		this.panel_4.setLayout(new GridLayout(5, 0, 0, 0));

		this.btnAsignar = new JButton("ASIGNAR");
		this.btnAsignar.setEnabled(false);
		this.panel_4.add(this.btnAsignar);

		this.btnPedido = new JButton("TOMAR PEDIDO");
		this.btnPedido.setEnabled(false);
		this.btnPedido.setActionCommand("PEDIDO");
		this.panel_4.add(this.btnPedido);

		this.panel_5 = new JPanel();
		this.panel_4.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel_6 = new JPanel();
		this.panel_5.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(2, 0, 0, 0));

		this.lblNewLabel_2 = new JLabel("FORMA DE PAGO");
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_6.add(this.lblNewLabel_2);

		this.comboBoxPago = new JComboBox<String>();
		this.comboBoxPago.addMouseListener(this);
		this.comboBoxPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-", "EFECTIVO", "TARJETA", "MERCADO PAGO", "CUENTA DNI" }));
		this.comboBoxPago.setSelectedIndex(0);
		this.comboBoxPago.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.comboBoxPago.setEditable(true);
		this.panel_6.add(this.comboBoxPago);

		this.btnCerrar = new JButton("CERRAR MESA");
		this.btnCerrar.setEnabled(false);
		this.btnCerrar.setActionCommand("CERRAR");
		this.panel_5.add(this.btnCerrar);

		this.btnLiberarMesas = new JButton("DESASIGNAR MESAS DESOCUPADAS");
		this.btnLiberarMesas.setActionCommand("DESASIGNAR");
		this.panel_4.add(this.btnLiberarMesas);


		this.btnSalir = new JButton("SALIR");
		this.panel_4.add(this.btnSalir);

		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_7 = new JPanel();
		this.panel_2.add(this.panel_7);
		this.panel_7.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_3 = new JLabel("COMANDAS");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_7.add(this.lblNewLabel_3, BorderLayout.NORTH);

		this.scrollPane_2 = new JScrollPane();
		this.panel_7.add(this.scrollPane_2, BorderLayout.CENTER);

		this.listComandas = new JList<Comanda>();
		this.listComandas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listComandas.setModel(modeloListaComanda);
		this.scrollPane_2.setViewportView(this.listComandas);

		this.panel_8 = new JPanel();
		this.panel_2.add(this.panel_8);
		this.panel_8.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_4 = new JLabel("VENTAS");
		this.lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel_8.add(this.lblNewLabel_4, BorderLayout.NORTH);

		this.scrollPane_3 = new JScrollPane();
		this.panel_8.add(this.scrollPane_3, BorderLayout.CENTER);

		this.listVentas = new JList<Venta>();
		this.listVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listVentas.setModel(modeloListaVenta);
		this.scrollPane_3.setViewportView(this.listVentas);
		this.setVisible(true);
	}

	private void validar() {
		this.btnActivo.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnFranco.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnAusente.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnEstadisticas.setEnabled(!this.listMozos.isSelectionEmpty());
		this.btnCerrar.setEnabled(!this.listMesas.isSelectionEmpty() && this.comboBoxPago.getSelectedIndex() != 0);
		this.btnPedido.setEnabled(!this.listMesas.isSelectionEmpty());
		this.btnAsignar.setEnabled(!this.listMozos.isSelectionEmpty() && !this.listMesas.isSelectionEmpty());
	}

	@Override
	public void cerrarse() {
		this.dispose();
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnActivo.addActionListener(actionListener);
		this.btnAusente.addActionListener(actionListener);
		this.btnFranco.addActionListener(actionListener);
		this.btnPedido.addActionListener(actionListener);
		this.btnAsignar.addActionListener(actionListener);
		this.btnLiberarMesas.addActionListener(actionListener);
		this.btnEstadisticas.addActionListener(actionListener);
		this.btnCerrar.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void actualizaListaMesas(ArrayList<Mesa> mesas) {
		this.modeloListaMesa.removeAllElements();
		for (Mesa mesaAct : mesas)
			this.modeloListaMesa.addElement(mesaAct);
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
	public void actualizaListaVenta(ArrayList<Venta> ventas) {
		this.modeloListaVenta.removeAllElements();
		for (Venta ventaAct : ventas)
			this.modeloListaVenta.addElement(ventaAct);
		this.validate();
	}

	@Override
	public void actualizaListaComanda(ArrayList<Comanda> comandas) {
		this.modeloListaComanda.removeAllElements();
		for (Comanda comandaAct : comandas)
			this.modeloListaComanda.addElement(comandaAct);
		this.validate();
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
	public String getSelectedPago() {
		return (String) this.comboBoxPago.getSelectedItem();
	}

	@Override
	public void notificar(String noti) {
		this.modeloListaNoti.addElement(noti);
		this.validate();
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
	public void actualizaListaProducto(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Pedido> getListaPedidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido getPedido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregaPedido(Pedido pedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borraPedido(Pedido pedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public Producto getProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCant() {
		// TODO Auto-generated method stub
		return 0;
	}
}
