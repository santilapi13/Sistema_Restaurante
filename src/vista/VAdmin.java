package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.*;

@SuppressWarnings("serial")
public class VAdmin extends JFrame implements IVistaLogin  {

	private JPanel principal;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JButton btnOperarios;
	private JButton btnMesas;
	private JButton btnComandas;
	private JButton btnVentas;
	private ActionListener actionListener;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblUsername;
	private JButton btnSalir;
	//private DefaultListModel<NoAdmin> modeloListaNoAdmin = new DefaultListModel<NoAdmin>();
	private JScrollPane scrollPane;
	//private JList<NoAdmin> listNoAdmin;
	private JScrollPane scrollPane_1;
	//private JList<Ticket> listTickets;
	//private DefaultListModel<Ticket> modeloListaTickets = new DefaultListModel<Ticket>();
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel_3;
	private JScrollPane scrollPane_2;
	private JList<String> listNoti;
	private DefaultListModel<String> modeloListaNoti = new DefaultListModel<String>();
	private JLabel lblNewLabel_4;
	private JButton btnPromociones;

	/**
	 * Create the frame.
	 */
	public VAdmin(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 440);

		this.principal = new JPanel();
		this.principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.principal);
		this.principal.setLayout(new BorderLayout(0, 0));
		this.contentPane = new JPanel();
		// this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.principal.add(this.contentPane, BorderLayout.NORTH);

		this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.X_AXIS));

		this.scrollPane_1 = new JScrollPane();
		this.contentPane.add(this.scrollPane_1);

		//this.listTickets = new JList<Ticket>();
		//this.scrollPane_1.setViewportView(this.listTickets);
		//this.listTickets.setModel(modeloListaTickets);

		this.lblNewLabel_2 = new JLabel("SOLICITUDES");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane_1.setColumnHeaderView(this.lblNewLabel_2);

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("ADMINISTRADOR");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panel.add(this.lblNewLabel, BorderLayout.NORTH);

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(10, 0, 0, 0));

		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(2, 0, 0, 0));

		this.lblNewLabel_1 = new JLabel("USUARIO:");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_2.add(this.lblNewLabel_1);

		this.lblUsername = new JLabel(username);
		this.lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel_2.add(this.lblUsername);

		this.btnOperarios = new JButton("OPERARIOS");
		this.btnOperarios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.btnOperarios);

		this.btnMesas = new JButton("MESAS");
		this.btnMesas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.btnMesas);

		this.btnComandas = new JButton("COMANDAS");
		this.btnComandas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.btnComandas);

		this.btnVentas = new JButton("VENTAS");
		this.btnVentas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.btnVentas);
		
		btnPromociones = new JButton("PROMOCIONES");
		btnPromociones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnPromociones);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel_1.add(this.btnSalir);

		this.scrollPane = new JScrollPane();
		this.contentPane.add(this.scrollPane);

		//this.listNoAdmin = new JList<NoAdmin>();
		//this.scrollPane.setViewportView(this.listNoAdmin);
		//this.listNoAdmin.setModel(modeloListaNoAdmin);

		this.lblNewLabel_3 = new JLabel("EMPLEADOS/ EMPLEADORES");
		this.lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.scrollPane.setColumnHeaderView(this.lblNewLabel_3);

		this.panel_3 = new JPanel();
		this.principal.add(this.panel_3, BorderLayout.CENTER);
		this.panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		this.scrollPane_2 = new JScrollPane();
		this.panel_3.add(this.scrollPane_2);

		this.listNoti = new JList<String>();
		this.listNoti.setModel(modeloListaNoti);
		this.scrollPane_2.setViewportView(this.listNoti);

		this.lblNewLabel_4 = new JLabel("NOTIFICACIONES");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.scrollPane_2.setColumnHeaderView(this.lblNewLabel_4);
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnOperarios.addActionListener(actionListener);
		this.btnMesas.addActionListener(actionListener);
		this.btnComandas.addActionListener(actionListener);
		this.btnSalir.addActionListener(actionListener);
		this.btnVentas.addActionListener(actionListener);
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
	public String getFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRubro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return null;
	}



	
	/*public void actualizarListaEmpleados(ArrayList<Empleado> empleados) {
		this.modeloListaNoAdmin.removeAllElements();
		for (Empleado empleadoAct : empleados)
			this.modeloListaNoAdmin.addElement(empleadoAct);
		this.validate();
	}

	@Override
	public void actualizarListaEmpleadores(ArrayList<Empleador> empleadores) {
		this.modeloListaNoAdmin.removeAllElements();
		for (Empleador empleadorAct : empleadores)
			this.modeloListaNoAdmin.addElement(empleadorAct);
		this.validate();
	}

	@Override
	public void informar(String s) {
		this.modeloListaNoti.addElement(s);
		this.validate();
	}

	@Override
	public void actualizarSoliEmpleo(ArrayList<Empleado> empleados) {
		this.modeloListaTickets.removeAllElements();
		for (Empleado empleadoAct : empleados)
			this.modeloListaTickets.addElement(empleadoAct.getTicket());
		this.validate();
	}

	@Override
	public void actualizarSoliEmpleados(ArrayList<Empleador> empleadores) {
		this.modeloListaTickets.removeAllElements();
		for (Empleador empleadorAct : empleadores)
			for (Ticket ticketAct : empleadorAct.getTickets())
				this.modeloListaTickets.addElement(ticketAct);
		this.validate();
	}*/
	
	

}
