package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class VRegistroOp extends JFrame implements KeyListener, IVistaAdmin {

	private Operario op = null;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textUser;
	private JLabel lblNewLabel_2;
	private JButton btnRegistrar;
	private JLabel lblNewLabel_3;
	private JTextField textNyA;
	private JLabel lblNewLabel_4;
	private JPasswordField textPass;
	private JPasswordField TextPass2;
	private ActionListener actionListener;
	private JButton btnNewButton;
	private JCheckBox checkBoxActivo;

	public VRegistroOp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(11, 1, 0, 0));

		this.lblNewLabel = new JLabel("NUEVO OPERARIO");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.panel.add(this.lblNewLabel);

		this.lblNewLabel_3 = new JLabel("Nombre y Apellido");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_3);

		this.textNyA = new JTextField();
		this.textNyA.addKeyListener(this);
		this.textNyA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textNyA);
		this.textNyA.setColumns(10);

		this.lblNewLabel_1 = new JLabel("Username");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_1);

		this.textUser = new JTextField();
		this.textUser.addKeyListener(this);
		this.textUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textUser);
		this.textUser.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Password");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_2);

		this.textPass = new JPasswordField();
		this.textPass.addKeyListener(this);
		this.textPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.textPass);

		this.lblNewLabel_4 = new JLabel("Confirmar password");
		this.lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.lblNewLabel_4);

		this.TextPass2 = new JPasswordField();
		this.TextPass2.addKeyListener(this);
		this.TextPass2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.TextPass2);

		this.checkBoxActivo = new JCheckBox("ACTIVO");
		this.checkBoxActivo.setHorizontalAlignment(SwingConstants.CENTER);
		this.checkBoxActivo.setSelected(true);
		this.checkBoxActivo.setEnabled(false);
		this.panel.add(this.checkBoxActivo);

		this.btnRegistrar = new JButton("REGISTRAR");
		this.btnRegistrar.setActionCommand("REGISTRAR_OPERARIO");
		this.btnRegistrar.setEnabled(false);
		this.btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel.add(this.btnRegistrar);

		this.btnNewButton = new JButton("SALIR");
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.contentPane.add(this.btnNewButton, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public boolean verifica() {
		return this.textUser.getText().length() > 0 && this.textUser.getText() != ""
				&& this.textPass.getText().length() >= 6 && this.textPass.getText().length() <= 12
				&& this.textPass.getText() != "" && !this.textNyA.getText().isEmpty() && this.textNyA.getText() != ""
				&& this.TextPass2.getText().equals(this.textPass.getText());
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.btnRegistrar.setEnabled(verifica());
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnRegistrar.addActionListener(actionListener);
		this.btnNewButton.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void cerrarse() {
		this.dispose();
	}

	@Override
	public String getUsername() {
		return this.textUser.getText();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getPassword() {
		return this.textPass.getText();
	}

	@Override
	public Date getFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.textNyA.getText();
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
		return this.op;
	}

	@Override
	public Mozo getSelectedMozo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mesa getSelectedMesa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromoTemporal getSelectedPromoTemp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromoProducto getSelectedPromoProd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto getSelectedProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizaListaProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaOperarios(ArrayList<Operario> operarios) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMesas(ArrayList<Mesa> mesas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaPromoTemp(ArrayList<PromoTemporal> promos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaPromoProd(ArrayList<PromoProducto> promos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaListaMozos(ArrayList<Mozo> mozos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notificar(String noti) {
		// TODO Auto-generated method stub

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
		this.op = op;
		this.textNyA.setText(op.getNya());
		this.textPass.setText(op.getPassword());
		this.TextPass2.setText(op.getPassword());
		this.textUser.setText(op.getUsername());
		this.checkBoxActivo.setSelected(op.isActivo());
		this.btnRegistrar.setEnabled(verifica());
		this.checkBoxActivo.setEnabled(true);
	}

	@Override
	public void setProducto(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getActivo() {
		return this.checkBoxActivo.isSelected();
	}

	@Override
	public int getCant() {
		// TODO Auto-generated method stub
		return 0;
	}
}
