package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Autor;
import model.Libro;

import javax.swing.JScrollPane;

public class APP_SAAVEDRA_CONSULTA extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private String columnas[] = { "Codigo", "Titulo", "Numero", "Editorial", "Genero", "Autor" };
	private ArrayList<Libro> libros;
	private ArrayList<Autor> autores;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APP_SAAVEDRA_CONSULTA frame = new APP_SAAVEDRA_CONSULTA();
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
	public APP_SAAVEDRA_CONSULTA() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarRegistrar();

			}
		});
		btnNuevo.setBounds(10, 52, 89, 23);
		getContentPane().add(btnNuevo);

		JLabel lblNewLabel = new JLabel("Listado de Libros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 564, 23);
		getContentPane().add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 564, 301);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setAutoscrolls(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		setVisible(true);
		setTitle("Consulta");
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		listaAutores();
		listaLibros();

	}

	private void listaAutores() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		String sql = "select a from Autor a";
		TypedQuery<Autor> query = em.createQuery(sql, Autor.class);

		try {
			autores = (ArrayList<Autor>) query.getResultList();
			System.out.println("\nCargo Autores\n");
		} catch (NoResultException ex) {
			System.out.println("\nNo Cargo Autores\n");
		} finally {
			em.close();
		}
	}

	private void listaLibros() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		String sql = "select l from Libro l";
		TypedQuery<Libro> query = em.createQuery(sql, Libro.class);

		try {
			libros = (ArrayList<Libro>) query.getResultList();

			model = new DefaultTableModel();
			for (int i = 0; i < columnas.length; i++)
				model.addColumn(columnas[i]);
			for (Libro l : libros)
				model.addRow(new Object[] { l.getCodigo(), l.getTitulo(), l.getNumero(), l.getEditorial(), l.getGenero(),
						autor(l.getIdAutor()) });
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(280);
			table.getColumnModel().getColumn(2).setPreferredWidth(60);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
		} catch (NoResultException ex) {
			System.out.println("txtResultado no cargo data");
		} finally {
			em.close();
		}
	}

	private String autor(int idAutor) {
		return autores.get(idAutor-1).getNombre();
	}

	void cargarRegistrar() {
		APP_SAAVEDRA_REGISTRO registro = new APP_SAAVEDRA_REGISTRO();
		registro.setLocationRelativeTo(this);
		registro.setVisible(true);
		this.dispose();
	}
}
