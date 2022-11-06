package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;



public class VPromo extends JFrame {
	
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textHoraInicio;
	private JTextField textHoraFin;
	private JTextField textDescuento;
	private JTextField textCantMinima;
	private JTextField textPUnitario;
	
	public VPromo() {
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
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Promocion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboBoxTipoPromo = new JComboBox();
		comboBoxTipoPromo.setModel(new DefaultComboBoxModel(new String[] {"Temporal", "Producto"}));
		panel_1.add(comboBoxTipoPromo);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 8, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Dias Activa:");
		panel_2.add(lblNewLabel_2);
		
		JRadioButton rdbtnLunes = new JRadioButton("Lunes");
		panel_2.add(rdbtnLunes);
		
		JRadioButton rdbtnMartes = new JRadioButton("Martes");
		panel_2.add(rdbtnMartes);
		
		JRadioButton rdbtnMiercoles = new JRadioButton("Miercoles");
		panel_2.add(rdbtnMiercoles);
		
		JRadioButton rdbtnJueves = new JRadioButton("Jueves");
		panel_2.add(rdbtnJueves);
		
		JRadioButton rdbtnViernes = new JRadioButton("Viernes");
		panel_2.add(rdbtnViernes);
		
		JRadioButton rdbtnSabado = new JRadioButton("Sabado");
		panel_2.add(rdbtnSabado);
		
		JRadioButton rdbtnDomingo = new JRadioButton("Domingo");
		panel_2.add(rdbtnDomingo);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
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
		
		JComboBox comboBoxPago = new JComboBox();
		comboBoxPago.setModel(new DefaultComboBoxModel(new String[] {"EFECTIVO", "TARJETA", "MERCADO PAGO", "CUENTA DNI"}));
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
		
		JRadioButton rdbtnAcumulable = new JRadioButton("Es acumulable");
		rdbtnAcumulable.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(rdbtnAcumulable);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Elija Producto");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_8);
		
		JComboBox comboBoxProductos = new JComboBox();
		panel_5.add(comboBoxProductos);
		
		JRadioButton rdbtn2x1 = new JRadioButton("2x1");
		panel_5.add(rdbtn2x1);
		
		JRadioButton rdbtnCantidad = new JRadioButton("Cantidad");
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
		
		JButton btnNewButton = new JButton("ENVIAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(Color.BLACK);
		panel.add(btnNewButton, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("GESTOR PROMOCIONES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
	}

}
