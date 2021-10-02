package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_autores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Autor {
	@Id
	@Column(name = "id_aut", unique = true, nullable = false)
	private int codigo;
	@Column(name = "nom_aut", length = 100, nullable = false)
	private String nombre;
	@Column(name = "eda_aut", nullable = false)
	private String edad;
}
