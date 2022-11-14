package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

@SuppressWarnings("serial")
public class VMozo extends JFrame implements KeyListener, IVistaAdmin {

	private Mozo mozo = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textNyA;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerCant;
	private JButton btnEnviar;
	private JFormattedTextField textFecha;
	private ActionListener actionListener;
	private JButton btnSalir;

	public VMozo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 467);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel = new JLabel("NUEVO MOZO");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(8, 0, 0, 0));

		this.lblNewLabel_1 = new JLabel("Nombre y Apellido");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_1);

		this.textNyA = new JTextField();
		this.textNyA.addKeyListener(this);
		this.panel.add(this.textNyA);
		this.textNyA.setColumns(10);

		this.lblNewLabel_2 = new JLabel("Fecha de nacimiento");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_2);

		this.textFecha = new JFormattedTextField(crearFormato("##/##/####"));
		this.textFecha.addKeyListener(this);
		this.panel.add(this.textFecha);

		this.lblNewLabel_3 = new JLabel("Cantidad de hijos");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.panel.add(this.lblNewLabel_3);

		this.spinnerCant = new JSpinner();

		this.spinnerCant.addKeyListener(this);
		this.spinnerCant
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		this.panel.add(this.spinnerCant);

		this.btnEnviar = new JButton("REGISTRAR");
		this.btnEnviar.setActionCommand("REGISTRAR_MOZO");
		this.btnEnviar.setEnabled(false);
		this.btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnEnviar);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.panel.add(this.btnSalir);
		this.setVisible(true);
	}

	private MaskFormatter crearFormato(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("Formato invalido: " + exc.getMessage());
		}
		return formatter;
	}

	public boolean verifica() {
		return this.textNyA.getText().length() > 0 && this.textNyA.getText() != ""
				&& this.textFecha.getText().charAt(9) != ' ';
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.btnEnviar.setEnabled(verifica());
	}

	public void keyTyped(KeyEvent e) {
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

	@Override
	public String getNombre() {
		return this.textNyA.getText();
	}

	@Override
	public Date getFecha() {

		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
		Date fecha = null;
		try {
			fecha = formatoDelTexto.parse(this.textFecha.getText());
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "Fecha mal ingresada");
			this.textFecha.setText("");
		}
		return fecha;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
		this.textNyA.setText(mozo.getNya());
		this.textFecha.setText("00/00/0000");
		this.textFecha.setEnabled(false);
		this.spinnerCant.setValue(mozo.getCantHijos());
		this.btnEnviar.setEnabled(verifica());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mozo getSelectedMozo() {
		return this.mozo;
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
		return (Integer) this.spinnerCant.getValue();
	}

}
