package br.marinha.casnav.kanban.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.marinha.casnav.kanban.papel.Papel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String login;
	private String name;
	private String abrev;
	private String fullName;
	private String email;
	private Rank rank;
	

	@ManyToMany(mappedBy = "users")
	@JsonBackReference
	private List<Papel> papeis;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
		this.papeis = new ArrayList<Papel>();
	}
	
	public User(UserRecordDto data) {
		this();
		if(!data.login().isEmpty()) {
			this.login = data.login();
		}
		if(!data.abrev().isEmpty()) {
			this.abrev = data.abrev();
		}
		if(!data.name().isEmpty()) {
			this.name = data.name();
		}
		if(!data.fullName().isEmpty()) {
			this.fullName = data.fullName();
		}
		if(!data.email().isEmpty()) {
			this.email = data.email();
		}
		if(data.rank()!= null) {
			this.rank = data.rank();
		}

		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
