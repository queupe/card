package br.mil.mar.kanban.member;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import br.mil.mar.kanban.team.Team;
import br.mil.mar.kanban.team.TeamRecordDto;
import br.mil.mar.kanban.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_MEMBER")
public class Member  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String name;
	

	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="member_id")
	private List<User> users;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="member_id")
	private List<Team> teams;
	
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
		this.users = new LinkedList<User>();
	}
	
	public Member(MemberRecordDto data) {
		if(data.name() != null && !data.name().isEmpty()) {
			this.name = data.name();
		}
		if(data.teams() != null && !data.teams().isEmpty()) {
			ListIterator<TeamRecordDto> teamListIterator = data.teams().listIterator();
			while(teamListIterator.hasNext()) {
				this.teams.add(new Team(teamListIterator.next()));
			}
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	

}
