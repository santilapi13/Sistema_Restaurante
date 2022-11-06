package vista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class VComanda extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JList listProductos;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JSpinner spinnerCantidad;
	private JButton btnAgregar;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JList listPedidos;

	

	/**
	 * Create the frame.
	 */
	public VComanda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 471);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("PRODUCTOS");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);
		
		this.listProductos = new JList();
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
		this.spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		this.panel_3.add(this.spinnerCantidad);
		
		this.btnAgregar = new JButton("AGREGAR");
		this.panel_2.add(this.btnAgregar);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("COMANDA ACTUAL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		this.scrollPane2 = new JScrollPane();
		this.panel_1.add(this.scrollPane2, BorderLayout.CENTER);
		
		
		this.listPedidos = new JList();
		this.scrollPane2.setViewportView(this.listPedidos);
		
	}

}
