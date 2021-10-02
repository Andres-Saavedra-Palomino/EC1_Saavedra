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
	@Column(name = "id_lib")
	private String codigo;
	@Column(name = "tit_lib")
	private String titulo;
	@Column(name = "fcp_lib")
	private String fechaPublicacion;
	@Column(name = "edi_lib")
	private String editorial;
	@Column(name = "gen_lib")
	private String genero;
	@Column(name = "id_aut")
	private String idAutor;
}
