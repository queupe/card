package br.mil.mar.kanban.team;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import br.mil.mar.kanban.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_TEAM")
public class Team  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String name;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="team_id")
	private List<User> users;
	
	
	
	public Team() {
		super();
		// TODO Auto-generated constructor stub
		this.users = new LinkedList<User>();
	}
	
	public Team(TeamRecordDto data) {
		this();
		if(!data.name().isEmpty()) {
			this.name = data.name();
		}
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
