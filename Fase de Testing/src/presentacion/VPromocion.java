package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Promocion;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class VPromocion extends JFrame implements IVistaPromo, KeyListener, MouseListener {

	private Promocion promo = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JPanel panel_2;
	private JCheckBox checkBoxDomingo;
	private JCheckBox checkBoxLunes;
	private JCheckBox checkBoxMartes;
	private JCheckBox checkBoxMiercoles;
	private JCheckBox checkBoxJueves;
	private JCheckBox checkBoxViernes;
	private JCheckBox checkBoxSabado;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private JRadioButton rdbtnTemporal;
	private JRadioButton rdbtnProducto;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JButton btnEnviar;
	private JLabel lblNewLabel_4;
	private JTextField textNombre;
	private JLabel lblNewLabel_5;
	private JTextField textPorcentaje;
	private JComboBox<String> comboBoxPago;
	private JLabel lblNewLabel_6;
	private JCheckBox checkBoxAcumulable;
	private JLabel lblNewLabel_7;
	private JComboBox<Producto> comboBoxProductos;
	private JPanel panel_9;
	private JRadioButton rdbtn2x1;
	private JRadioButton rdbtnCantidad;
	private JLabel lblNewLabel_8;
	private JSpinner spinnerCantMinima;
	private JLabel lblNewLabel_9;
	private JTextField textPrecio;
	private ActionListener actionListener;
	private DefaultComboBoxModel<Producto> modeloListaProducto = new DefaultComboBoxModel<Producto>();
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JButton btnSalir;
	private JCheckBox checkBoxActiva;

	@SuppressWarnings("unchecked")
	public VPromocion(ArrayList<Producto> productos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 477);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("NUEVA PROMOCION");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new BorderLayout(0, 0));

		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		this.panel.add(this.panel_1, BorderLayout.NORTH);
		this.panel_1.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_1 = new JLabel("Dias de promocion");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.lblNewLabel_1, BorderLayout.NORTH);

		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2, BorderLayout.CENTER);
		this.panel_2.setLayout(new GridLayout(0, 7, 0, 0));

		this.checkBoxDomingo = new JCheckBox("Domingo");
		this.checkBoxDomingo.addMouseListener(this);
		this.checkBoxDomingo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxDomingo);

		this.checkBoxLunes = new JCheckBox("Lunes");
		this.checkBoxLunes.addMouseListener(this);
		this.checkBoxLunes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxLunes);

		this.checkBoxMartes = new JCheckBox("Martes");
		this.checkBoxMartes.addMouseListener(this);
		this.checkBoxMartes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxMartes);

		this.checkBoxMiercoles = new JCheckBox("Miercoles");
		this.checkBoxMiercoles.addMouseListener(this);
		this.checkBoxMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxMiercoles);

		this.checkBoxJueves = new JCheckBox("Jueves");
		this.checkBoxJueves.addMouseListener(this);
		this.checkBoxJueves.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxJueves);

		this.checkBoxViernes = new JCheckBox("Viernes");
		this.checkBoxViernes.addMouseListener(this);
		this.checkBoxViernes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxViernes);

		this.checkBoxSabado = new JCheckBox("Sabado");
		this.checkBoxSabado.addMouseListener(this);
		this.checkBoxSabado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxSabado);

		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panel.add(this.panel_3, BorderLayout.CENTER);
		this.panel_3.setLayout(new BorderLayout(0, 0));

		this.panel_4 = new JPanel();
		this.panel_3.add(this.panel_4, BorderLayout.NORTH);
		this.panel_4.setLayout(new GridLayout(0, 3, 0, 0));

		this.lblNewLabel_2 = new JLabel("Tipo de promocion");
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_4.add(this.lblNewLabel_2);

		this.rdbtnTemporal = new JRadioButton("Temporal");
		this.rdbtnTemporal.addMouseListener(this);
		this.rdbtnTemporal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_4.add(this.rdbtnTemporal);

		this.rdbtnProducto = new JRadioButton("De producto");
		this.rdbtnProducto.addMouseListener(this);
		this.rdbtnProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_4.add(this.rdbtnProducto);

		ButtonGroup tipoPromo = new ButtonGroup();
		tipoPromo.add(this.rdbtnProducto);
		tipoPromo.add(this.rdbtnTemporal);

		this.panel_5 = new JPanel();
		this.panel_3.add(this.panel_5, BorderLayout.CENTER);
		this.panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel_6 = new JPanel();
		this.panel_6.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.panel_5.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(7, 0, 0, 0));

		this.lblNewLabel_4 = new JLabel("Nombre de la promocion");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.lblNewLabel_4);

		this.textNombre = new JTextField();
		this.textNombre.setEnabled(false);
		this.textNombre.addKeyListener(this);
		this.panel_6.add(this.textNombre);
		this.textNombre.setColumns(10);

		this.lblNewLabel_5 = new JLabel("Forma de pago");
		this.lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.lblNewLabel_5);

		this.comboBoxPago = new JComboBox<String>();
		this.comboBoxPago.setEnabled(false);
		this.comboBoxPago.addKeyListener(this);
		this.comboBoxPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-", "EFECTIVO", "TARJETA", "MERCADO PAGO", "CUENTA DNI" }));
		this.comboBoxPago.setSelectedIndex(0);
		this.panel_6.add(this.comboBoxPago);

		this.lblNewLabel_6 = new JLabel("Porcentaje");
		this.lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.lblNewLabel_6);

		this.textPorcentaje = new JTextField();
		this.textPorcentaje.setEnabled(false);
		this.textPorcentaje.addKeyListener(this);
		this.panel_6.add(this.textPorcentaje);
		this.textPorcentaje.setColumns(10);

		this.checkBoxAcumulable = new JCheckBox("Acumulable");
		this.checkBoxAcumulable.setEnabled(false);
		this.checkBoxAcumulable.setHorizontalAlignment(SwingConstants.CENTER);
		this.checkBoxAcumulable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.checkBoxAcumulable);

		this.panel_7 = new JPanel();
		this.panel_7.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.panel_5.add(this.panel_7);
		this.panel_7.setLayout(new GridLayout(7, 0, 0, 0));

		this.lblNewLabel_7 = new JLabel("Producto en promocion");
		this.lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_7.add(this.lblNewLabel_7);

		this.comboBoxProductos = new JComboBox<Producto>();
		this.comboBoxProductos.setModel(modeloListaProducto);
		this.comboBoxProductos.setEnabled(false);
		this.comboBoxProductos.addKeyListener(this);
		this.panel_7.add(this.comboBoxProductos);

		this.panel_9 = new JPanel();
		this.panel_7.add(this.panel_9);
		this.panel_9.setLayout(new GridLayout(0, 2, 0, 0));

		this.rdbtn2x1 = new JRadioButton("2x1");
		this.rdbtn2x1.addMouseListener(this);
		this.rdbtn2x1.setEnabled(false);
		this.rdbtn2x1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_9.add(this.rdbtn2x1);

		this.rdbtnCantidad = new JRadioButton("Por cantidad");
		this.rdbtnCantidad.addMouseListener(this);
		this.rdbtnCantidad.setEnabled(false);
		this.rdbtnCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_9.add(this.rdbtnCantidad);

		this.panel_8 = new JPanel();
		this.panel_8.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.panel_3.add(this.panel_8, BorderLayout.SOUTH);
		this.panel_8.setLayout(new GridLayout(0, 3, 0, 0));

		this.checkBoxActiva = new JCheckBox("ACTIVA");
		this.checkBoxActiva.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.checkBoxActiva.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_8.add(this.checkBoxActiva);

		this.btnEnviar = new JButton("ENVIAR");
		this.btnEnviar.setEnabled(false);
		this.panel_8.add(this.btnEnviar);

		this.lblNewLabel_10 = new JLabel("");
		this.panel_8.add(this.lblNewLabel_10);

		this.lblNewLabel_11 = new JLabel("");
		this.panel_8.add(this.lblNewLabel_11);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.panel_8.add(this.btnSalir);

		ButtonGroup tipoProducto = new ButtonGroup();
		tipoProducto.add(rdbtn2x1);
		tipoProducto.add(rdbtnCantidad);

		this.lblNewLabel_8 = new JLabel("Cantidad minima");
		this.lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_7.add(this.lblNewLabel_8);

		this.spinnerCantMinima = new JSpinner();
		this.spinnerCantMinima.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		this.spinnerCantMinima.setEnabled(false);
		this.spinnerCantMinima.addKeyListener(this);
		this.panel_7.add(this.spinnerCantMinima);

		this.lblNewLabel_9 = new JLabel("Precio unitario");
		this.lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_7.add(this.lblNewLabel_9);

		this.textPrecio = new JTextField();
		this.textPrecio.setEnabled(false);
		this.textPrecio.addKeyListener(this);
		this.panel_7.add(this.textPrecio);
		this.textPrecio.setColumns(10);
		this.setVisible(true);
		this.cargaProductos(productos);

	}

	private boolean verifica() {
		boolean resp = false;
		resp = (this.checkBoxDomingo.isSelected() || this.checkBoxLunes.isSelected() || this.checkBoxMartes.isSelected()
				|| this.checkBoxMiercoles.isSelected() || this.checkBoxJueves.isSelected()
				|| this.checkBoxViernes.isSelected() || this.checkBoxSabado.isSelected())
				&& ((this.rdbtnTemporal.isSelected() && !this.textNombre.getText().isEmpty()
						&& this.textNombre.getText() != "" && this.comboBoxPago.getSelectedIndex() != 0
						&& this.textPorcentaje.getText() != "" && !this.textPorcentaje.getText().isEmpty())
						|| (this.rdbtnProducto.isSelected() && this.comboBoxProductos.getSelectedIndex() != -1
								&& (this.rdbtn2x1.isSelected() || (this.rdbtnCantidad.isSelected()
										&& this.textPrecio.getText() != "" && !this.textPrecio.getText().isEmpty()))));
		return resp;
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnEnviar.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void cerrarse() {
		this.dispose();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.btnEnviar.setEnabled(verifica());
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	private void activar() {
		if (this.rdbtnTemporal.isSelected()) {
			this.textNombre.setEnabled(true);
			this.textPorcentaje.setEnabled(true);
			this.comboBoxPago.setEnabled(true);
			this.checkBoxAcumulable.setEnabled(true);

			this.rdbtn2x1.setEnabled(false);
			this.rdbtnCantidad.setEnabled(false);
			this.comboBoxProductos.setEnabled(false);
		} else if (this.rdbtnProducto.isSelected()) {
			this.textNombre.setEnabled(false);
			this.textPorcentaje.setEnabled(false);
			this.comboBoxPago.setEnabled(false);
			this.checkBoxAcumulable.setEnabled(false);

			this.rdbtn2x1.setEnabled(true);
			this.rdbtnCantidad.setEnabled(true);
			this.comboBoxProductos.setEnabled(true);
		}
		if (this.rdbtnCantidad.isSelected()) {
			this.textPrecio.setEnabled(true);
			this.spinnerCantMinima.setEnabled(true);
		} else if (this.rdbtn2x1.isSelected()) {
			this.textPrecio.setEnabled(false);
			this.spinnerCantMinima.setEnabled(false);
		}
		this.btnEnviar.setEnabled(verifica());
	}

	public void mouseReleased(MouseEvent e) {
		activar();
	}

	@Override
	public String getTipoPromo() {
		return (this.rdbtnProducto.isSelected()) ? "PRODUCTO" : "TEMPORAL";
	}

	@Override
	public boolean getDosPorUno() {
		return this.rdbtn2x1.isSelected();
	}

	@Override
	public String getNombre() {
		return this.textNombre.getText();
	}

	@Override
	public String getFormaPago() {
		return (String) this.comboBoxPago.getSelectedItem();
	}

	@Override
	public int getPorcentaje() {
		int porcentaje = 1;
		try {
			porcentaje = Integer.valueOf(this.textPorcentaje.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El porcentaje debe ser numerico");
			this.textPorcentaje.setText("");
		}
		return porcentaje;
	}

	@Override
	public boolean getAcumulable() {
		return this.checkBoxAcumulable.isSelected();
	}

	@Override
	public Producto getProducto() {
		return (Producto) this.comboBoxProductos.getSelectedItem();
	}

	@Override
	public int getCant() {
		return (int) this.spinnerCantMinima.getValue();
		
	}

	@Override
	public double getPrecio() {
		double precio = 1;
		try {
			precio = Double.valueOf(this.textPrecio.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser numerico");
			this.textPrecio.setText("");
		}
		return precio;
	}

	@Override
	public ArrayList<String> getDias() {
		ArrayList<String> dias = new ArrayList<String>();
		if (this.checkBoxDomingo.isSelected())
			dias.add("DOMINGO");
		if (this.checkBoxLunes.isSelected())
			dias.add("LUNES");
		if (this.checkBoxMartes.isSelected())
			dias.add("MARTES");
		if (this.checkBoxMiercoles.isSelected())
			dias.add("MIERCOLES");
		if (this.checkBoxJueves.isSelected())
			dias.add("JUEVES");
		if (this.checkBoxViernes.isSelected())
			dias.add("VIERNES");
		if (this.checkBoxSabado.isSelected())
			dias.add("SABADO");
		return dias;
	}

	public void cargaProductos(ArrayList<Producto> productos) {
		this.modeloListaProducto.removeAllElements();
		for (Producto p : productos)
			this.modeloListaProducto.addElement(p);
		this.validate();
	}

	@Override
	public void setPromoTemp(PromoTemporal promo) {
		this.promo = promo;
		if (promo.getDiasDePromo().contains("DOMINGO"))
			this.checkBoxDomingo.setSelected(true);
		if (promo.getDiasDePromo().contains("LUNES"))
			this.checkBoxLunes.setSelected(true);
		if (promo.getDiasDePromo().contains("MARTES"))
			this.checkBoxMartes.setSelected(true);
		if (promo.getDiasDePromo().contains("MIERCOLES"))
			this.checkBoxMiercoles.setSelected(true);
		if (promo.getDiasDePromo().contains("JUEVES"))
			this.checkBoxJueves.setSelected(true);
		if (promo.getDiasDePromo().contains("VIERNES"))
			this.checkBoxViernes.setSelected(true);
		if (promo.getDiasDePromo().contains("SABADO"))
			this.checkBoxSabado.setSelected(true);

		this.rdbtnTemporal.setSelected(true);
		this.textNombre.setText(promo.getNombre());
		this.textPorcentaje.setText(String.valueOf(promo.getPorcentajeDesc()));
		this.checkBoxAcumulable.setSelected(promo.isEsAcumulable());
		this.comboBoxPago.setSelectedItem(promo.getFormaPago());
		this.checkBoxActiva.setSelected(promo.isActiva());
		activar();
	}

	@Override
	public void setPromoProd(PromoProducto promo) {
		this.promo = promo;
		this.comboBoxProductos.setEnabled(false);
		if (promo.getDiasDePromo().contains("DOMINGO"))
			this.checkBoxDomingo.setSelected(true);
		if (promo.getDiasDePromo().contains("LUNES"))
			this.checkBoxLunes.setSelected(true);
		if (promo.getDiasDePromo().contains("MARTES"))
			this.checkBoxMartes.setSelected(true);
		if (promo.getDiasDePromo().contains("MIERCOLES"))
			this.checkBoxMiercoles.setSelected(true);
		if (promo.getDiasDePromo().contains("JUEVES"))
			this.checkBoxJueves.setSelected(true);
		if (promo.getDiasDePromo().contains("VIERNES"))
			this.checkBoxViernes.setSelected(true);
		if (promo.getDiasDePromo().contains("SABADO"))
			this.checkBoxSabado.setSelected(true);

		this.rdbtnProducto.setSelected(true);
		this.comboBoxProductos.setSelectedItem(promo.getProducto());
		this.textPrecio.setText(String.valueOf(promo.getDtoPorCantidad_PrecioUnit()));
		this.spinnerCantMinima.setValue(promo.getDtoPorCantidad_CantMinima());
		this.checkBoxActiva.setSelected(promo.isActiva());
		this.rdbtn2x1.setSelected(promo.aplicaDosPorUno);
		this.rdbtnCantidad.setSelected(!promo.aplicaDosPorUno);
		activar();
	}

	@Override
	public Promocion getPromocion() {
		return this.promo;
	}

	@Override
	public boolean getActivo() {
		return this.checkBoxActiva.isSelected();
	}
}
