package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VLogin extends JFrame implements KeyListener, IVistaLogin {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textUser;
	private JLabel lblNewLabel_2;
	private JButton btnIngresar;
	private JPasswordField textPass;
	private ActionListener actionListener;

	public VLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(6, 1, 0, 0));

		this.lblNewLabel = new JLabel("LOGIN");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.panel.add(this.lblNewLabel);

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

		this.btnIngresar = new JButton("INGRESAR");
		this.btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.panel.add(this.btnIngresar);
		this.btnIngresar.setEnabled(false);
		this.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		this.btnIngresar.setEnabled(verifica());
	}

	public void keyTyped(KeyEvent e) {
	}

	@SuppressWarnings("deprecation")
	public boolean verifica() {
		return this.textUser.getText().length() > 0 && this.textUser.getText()!=""
				&& this.textPass.getText().length() >= 6 && this.textPass.getText()!="";
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnIngresar.addActionListener(actionListener);
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

}
