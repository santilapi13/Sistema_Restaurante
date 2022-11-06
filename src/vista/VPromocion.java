package vista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class VPromocion extends JFrame {

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
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textNombre;
	private JLabel lblNewLabel_5;
	private JTextField textPorcentaje;
	private JComboBox comboBoxPago;
	private JLabel lblNewLabel_6;
	private JCheckBox checkBoxAcumulable;
	private JLabel lblNewLabel_7;
	private JComboBox comboBoxProductos;
	private JPanel panel_9;
	private JRadioButton rdbtn2x1;
	private JRadioButton rdbtnCantidad;
	private JLabel lblNewLabel_8;
	private JSpinner spinnerCantMinima;
	private JLabel lblNewLabel_9;
	private JTextField textPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VPromocion frame = new VPromocion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VPromocion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 477);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("PROMOCION");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
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
		this.checkBoxDomingo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxDomingo);
		
		this.checkBoxLunes = new JCheckBox("Lunes");
		this.checkBoxLunes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxLunes);
		
		this.checkBoxMartes = new JCheckBox("Martes");
		this.checkBoxMartes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxMartes);
		
		this.checkBoxMiercoles = new JCheckBox("Miercoles");
		this.checkBoxMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxMiercoles);
		
		this.checkBoxJueves = new JCheckBox("Jueves");
		this.checkBoxJueves.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxJueves);
		
		this.checkBoxViernes = new JCheckBox("Viernes");
		this.checkBoxViernes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.checkBoxViernes);
		
		this.checkBoxSabado = new JCheckBox("Sabado");
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
		this.rdbtnTemporal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_4.add(this.rdbtnTemporal);
		
		this.rdbtnProducto = new JRadioButton("De producto");
		this.rdbtnProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_4.add(this.rdbtnProducto);
		
		ButtonGroup tipoPromo= new ButtonGroup();
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
		this.panel_6.add(this.textNombre);
		this.textNombre.setColumns(10);
		
		this.lblNewLabel_5 = new JLabel("Forma de pago");
		this.lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.lblNewLabel_5);
		
		this.comboBoxPago = new JComboBox();
		this.comboBoxPago.setModel(new DefaultComboBoxModel(new String[] {"-", "EFECTIVO", "TARJETA", "MERCADO PAGO", "CUENTA DNI"}));
		this.panel_6.add(this.comboBoxPago);
		
		this.lblNewLabel_6 = new JLabel("Porcentaje");
		this.lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_6.add(this.lblNewLabel_6);
		
		this.textPorcentaje = new JTextField();
		this.panel_6.add(this.textPorcentaje);
		this.textPorcentaje.setColumns(10);
		
		this.checkBoxAcumulable = new JCheckBox("Acumulable");
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
		
		this.comboBoxProductos = new JComboBox();
		this.panel_7.add(this.comboBoxProductos);
		
		this.panel_9 = new JPanel();
		this.panel_7.add(this.panel_9);
		this.panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.rdbtn2x1 = new JRadioButton("2x1");
		this.rdbtn2x1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_9.add(this.rdbtn2x1);
		
		this.rdbtnCantidad = new JRadioButton("Por cantidad");
		this.rdbtnCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_9.add(this.rdbtnCantidad);
		
		this.panel_8 = new JPanel();
		this.panel_8.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.panel_3.add(this.panel_8, BorderLayout.SOUTH);
		this.panel_8.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.lblNewLabel_3 = new JLabel("");
		this.panel_8.add(this.lblNewLabel_3);
		
		this.btnEnviar = new JButton("ENVIAR");
		this.panel_8.add(this.btnEnviar);
		
		ButtonGroup tipoProducto= new ButtonGroup();
		tipoProducto.add(rdbtn2x1);
		tipoProducto.add(rdbtnCantidad);
		
		this.lblNewLabel_8 = new JLabel("Cantidad minima");
		this.lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_7.add(this.lblNewLabel_8);
		
		this.spinnerCantMinima = new JSpinner();
		this.panel_7.add(this.spinnerCantMinima);
		
		this.lblNewLabel_9 = new JLabel("Precio unitario");
		this.lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_7.add(this.lblNewLabel_9);
		
		this.textPrecio = new JTextField();
		this.panel_7.add(this.textPrecio);
		this.textPrecio.setColumns(10);
	}

	
	
	
}