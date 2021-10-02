package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Autor;
import model.Libro;

import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class APP_SAAVEDRA_REGISTRO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtTitulo;
	private JTextField txtEditorial;
	private JTextField txtGenero;
	private JTextField txtNumero;
	private JButton btnListado;
	private ArrayList<Autor> autores;
	private JComboBox<String> cboAutor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APP_SAAVEDRA_REGISTRO frame = new APP_SAAVEDRA_REGISTRO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public APP_SAAVEDRA_REGISTRO() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 400, 525);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"Codigo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtCodigo.setBounds(33, 65, 150, 42);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"Titulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtTitulo.setBounds(33, 118, 324, 42);
		getContentPane().add(txtTitulo);

		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		txtEditorial.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"Editorial", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtEditorial.setBounds(33, 224, 324, 42);
		getContentPane().add(txtEditorial);

		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"G\u00E9nero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtGenero.setBounds(33, 277, 161, 42);
		getContentPane().add(txtGenero);

		cboAutor = new JComboBox<String>();
		cboAutor.setBackground(Color.WHITE);
		cboAutor.setBorder(new TitledBorder(null, "Autor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cboAutor.setBounds(33, 330, 230, 42);
		getContentPane().add(cboAutor);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						"N\u00FAmero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtNumero.setBounds(33, 171, 161, 42);
		getContentPane().add(txtNumero);

		JLabel lblNewLabel = new JLabel("Registro de Libros");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 367, 28);
		getContentPane().add(lblNewLabel);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrar.setBounds(33, 400, 324, 34);
		getContentPane().add(btnRegistrar);

		btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListado();
			}
		});
		btnListado.setBounds(196, 445, 161, 30);
		getContentPane().add(btnListado);
		listarAutores();
	}

	private void listarAutores() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		String sql = "select a from Autor a";
		TypedQuery<Autor> query = em.createQuery(sql, Autor.class);

		try {
			autores = (ArrayList<Autor>) query.getResultList();
			
			for (Autor a : autores)
				cboAutor.addItem(a.getCodigo()+" - "+a.getNombre());
		} catch (NoResultException ex) {
			System.out.println("...");
		} finally {
			em.close();
		}
	}

	void registrar() {
		if (!esValido())
			return;

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Libro l = null;

		try {
			l = new Libro();
			l.setCodigo(txtCodigo.getText().trim());
			l.setTitulo(txtTitulo.getText().trim());
			l.setNumero(Integer.parseInt(txtNumero.getText().trim()));
			l.setEditorial(txtEditorial.getText().trim());
			l.setGenero(txtGenero.getText().trim());
			l.setIdAutor(cboAutor.getSelectedIndex()+1);
			System.out.println(l.toString());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Complete los campos correctamente.");
			return;
		}
		try {
			em.getTransaction().begin();
			em.persist(l);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Registro Existoso!");
			limpiar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Registro Fallido!?");
		} finally {
			em.close();
		}
	}



	void cargarListado() {
		APP_SAAVEDRA_CONSULTA consulta = new APP_SAAVEDRA_CONSULTA();
		consulta.setLocationRelativeTo(this);
		consulta.setVisible(true);
		this.dispose();
	}

	boolean esValido() {
		String mensaje = "";
		if (!Pattern.matches("[a-zA-z0-9]{5}", txtCodigo.getText().trim()))
			mensaje += "El Código debe contener 5 caracteres(solo letras y numeros)\n";
		if (!Pattern.matches("[A-Za-z0-9 ]{2,250}", txtTitulo.getText().trim()))
			mensaje += "Solo ingrese letras y/o numeros en el campo Titulo; Hasta 250 caracteres\n";
		if (!Pattern.matches("[1-9]([0-9]){0,4}", txtNumero.getText().trim()))
			mensaje += "Solo Ingrese digitos(0-9) en el campo Numero; Hasta 4 digitos\n";
		if (!Pattern.matches("[A-Za-z ]{2,100}", txtEditorial.getText().trim()))
			mensaje += "Solo ingrese letras en el campo Editorial; Hasta 250 caracteres\n";
		if (!Pattern.matches("[A-Za-z ]{2,100}", txtGenero.getText().trim()))
			mensaje += "Solo ingrese letras en el campo Genero; Hasta 250 caracteres\n";
		if (!(mensaje.length() == 0)) {
			JOptionPane.showMessageDialog(this, mensaje);
			return false;
		}
		return true;
	}
	private void limpiar() {
		txtCodigo.setText("");
		txtTitulo.setText("");
		txtNumero.setText("");
		txtEditorial.setText("");
		txtGenero.setText("");
		cboAutor.setSelectedIndex(0);
	}
}
