package br.marinha.casnav.kanban.papel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.marinha.casnav.kanban.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PAPEL")
public class Papel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String codigo;
	private String titulo;
	private String abrev;
	private UUID superior;
	
	@ManyToMany
	@JoinTable(
	  name = "TB_PAPEL_USER", 
	  joinColumns = @JoinColumn(name = "papel_id", referencedColumnName="id"), 
	  inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"))
	@JsonManagedReference
	private List<User> users;
	
	
	public Papel() {
		super();
		this.superior = null;
	}
	
	public Papel(PapelRecordDto data) {
		if(data.codigo() != null && !data.codigo().isEmpty()) {
			this.codigo = data.codigo();
		}
		if(data.titulo() != null && !data.titulo().isEmpty()) {
			this.titulo = data.titulo();
		}
		if(data.abrev() != null && !data.abrev().isEmpty()) {
			this.abrev = data.abrev();	
		}
		if(data.superior() != null) {
			this.superior = data.superior();
		}
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public UUID getSuperior() {
		return superior;
	}
	public void setSuperior(UUID superior) {
		this.superior = superior;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
