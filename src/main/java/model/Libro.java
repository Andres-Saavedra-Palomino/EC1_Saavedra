package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_libros")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Libro {
	@Id
	@Column(name = "id_lib", unique = true, nullable = false, length = 5)
	private String codigo;
	@Column(name = "tit_lib", length = 250, nullable = false)
	private String titulo;
	@Column(name = "num_lib", nullable = false)
	private int numero;
	@Column(name = "edi_lib", length = 100, nullable = false)
	private String editorial;
	@Column(name = "gen_lib", length = 100, nullable = false)
	private String genero;
	@Column(name = "id_aut", nullable = false)
	private int idAutor;
}
