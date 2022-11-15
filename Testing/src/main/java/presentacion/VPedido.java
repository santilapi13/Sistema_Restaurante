package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Pedido;
import modelo.Producto;
import modelo.Venta;

public class VPedido extends JFrame implements IVistaOperario, MouseListener {

	private Mesa mesa;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JList<Producto> listProductos;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JSpinner spinnerCantidad;
	private JButton btnAgregar;
	private JScrollPane scrollPane_1;
	private JList<Pedido> listPedido;
	private JPanel panel_4;
	private JButton btnEliminar;
	private JButton btnEnviar;
	private DefaultListModel<Producto> modeloListaProducto = new DefaultListModel<Producto>();
	private DefaultListModel<Pedido> modeloListaPedidos = new DefaultListModel<Pedido>();
	private ActionListener actionListener;
	private JPanel panel_5;
	private JPanel panel_6;
	private JScrollPane scrollPane_2;
	private JList<String> listNotificacion;
	private DefaultListModel<String> modeloListaNotificacion = new DefaultListModel<String>();

	public VPedido(Mesa mesa) {
		this.mesa = mesa;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 471);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel_5 = new JPanel();
		this.contentPane.add(this.panel_5);
		this.panel_5.setLayout(new BorderLayout(0, 0));

		this.panel = new JPanel();
		this.panel_5.add(this.panel);
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.panel.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("PRODUCTOS");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);

		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);

		this.listProductos = new JList<Producto>();
		this.listProductos.addMouseListener(this);
		this.listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listProductos.setModel(modeloListaProducto);
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
		this.spinnerCantidad
				.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		this.panel_3.add(this.spinnerCantidad);

		this.btnAgregar = new JButton("AGREGAR");
		this.btnAgregar.setActionCommand("AGREGAR_PEDIDO");
		this.btnAgregar.setEnabled(false);
		this.panel_2.add(this.btnAgregar);

		this.panel_6 = new JPanel();
		this.panel_5.add(this.panel_6, BorderLayout.SOUTH);
		this.panel_6.setLayout(new BorderLayout(0, 0));

		this.scrollPane_2 = new JScrollPane();
		this.panel_6.add(this.scrollPane_2, BorderLayout.CENTER);

		this.listNotificacion = new JList<String>();
		this.listNotificacion.setModel(modeloListaNotificacion);
		this.scrollPane_2.setViewportView(this.listNotificacion);

		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_1 = new JLabel("PEDIDO");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_1.add(this.lblNewLabel_1, BorderLayout.NORTH);

		this.scrollPane_1 = new JScrollPane();
		this.panel_1.add(this.scrollPane_1, BorderLayout.CENTER);

		this.listPedido = new JList<Pedido>();
		this.listPedido.addMouseListener(this);
		this.listPedido.setModel(modeloListaPedidos);
		this.scrollPane_1.setViewportView(this.listPedido);

		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4, BorderLayout.SOUTH);
		this.panel_4.setLayout(new GridLayout(2, 1, 0, 0));

		this.btnEliminar = new JButton("ELIMINAR");
		this.btnEliminar.setActionCommand("ELIMINAR_PEDIDO");
		this.btnEliminar.setEnabled(false);
		this.panel_4.add(this.btnEliminar);

		this.btnEnviar = new JButton("ENVIAR");
		this.btnEnviar.setActionCommand("ENVIAR_PEDIDO");
		this.btnEnviar.setEnabled(false);
		this.panel_4.add(this.btnEnviar);
		this.setVisible(true);
	}

	@Override
	public void cerrarse() {
		this.dispose();
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAgregar.addActionListener(actionListener);
		this.btnEliminar.addActionListener(actionListener);
		this.btnEnviar.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void agregaPedido(Pedido pedido) {
		this.modeloListaPedidos.addElement(pedido);
		this.validate();
	}

	@Override
	public void borraPedido(Pedido pedido) {
		this.modeloListaPedidos.removeElement(pedido);
		this.validate();
	}

	@Override
	public Pedido getPedido() {
		return (Pedido) this.listPedido.getSelectedValue();
	}

	@Override
	public void actualizaListaProducto(ArrayList<Producto> productos) {
		this.modeloListaProducto.removeAllElements();
		for (Producto prodAct : productos)
			this.modeloListaProducto.addElement(prodAct);
		this.validate();
	}

	@Override
	public ArrayList<Pedido> getListaPedidos() {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		for (int i = 0; i < this.modeloListaPedidos.size(); i++)
			pedidos.add(this.modeloListaPedidos.get(i));
		return pedidos;
	}

	@Override
	public Producto getProducto() {
		return (Producto) this.listProductos.getSelectedValue();
	}

	@Override
	public double getCant() {
		return (Double) this.spinnerCantidad.getValue();
	}

	@Override
	public Mesa getSelectedMesa() {
		return this.mesa;
	}

	@Override
	public void notificar(String noti) {
		this.modeloListaNotificacion.addElement(noti);
		this.validate();
	}

	public void validar() {
		this.btnAgregar.setEnabled(!this.listProductos.isSelectionEmpty());
		this.btnEliminar.setEnabled(this.modeloListaPedidos.size() > 0 && !this.listPedido.isSelectionEmpty());
		this.btnEnviar.setEnabled(this.modeloListaPedidos.size() > 0);
	}

	public void mouseReleased(MouseEvent e) {
		validar();
	}

	@Override
	public Mozo getSelectedMozo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectedPago() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizaListaVenta(ArrayList<Venta> ventas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaComanda(ArrayList<Comanda> comandas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMesas(ArrayList<Mesa> mesas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMozos(ArrayList<Mozo> mozos) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

}
