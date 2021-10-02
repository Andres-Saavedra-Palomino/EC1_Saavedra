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
	@Column(name = "id_aut")
	private String codigo;
	@Column(name = "nom_aut")
	private String nombre;
	@Column(name = "eda_aut")
	private String edad;
}
