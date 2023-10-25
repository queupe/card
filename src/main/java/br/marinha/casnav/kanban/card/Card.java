package br.marinha.casnav.kanban.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.marinha.casnav.kanban.label.Label;
import br.marinha.casnav.kanban.member.Member;
import br.marinha.casnav.kanban.papel.Papel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CARD")
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String title;
	private String coverColor;
	
	@ManyToOne
    @JoinColumn(name="owner_id", nullable=true)
	private Papel owner;
	
	@ManyToMany
	@JoinTable(
		name = "TB_CARD_LABEL", 
		joinColumns = @JoinColumn(name = "card_id", referencedColumnName="id"), 
		inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName="id"))
	@JsonManagedReference
	private Set<Label> labels;
	
	@Column(name = "description", length = 25000)
	private String description;
	
	
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	public Card() {
		super();
		this.labels = new HashSet<Label>();
	}
	
	public Card(CardRecordDto data) {
		this();
		if(data.title() != null && !data.title().isEmpty()) {
			this.title = data.title();
		}
		if(data.coverColor() != null && !data.coverColor().isEmpty()) {
			this.coverColor = data.coverColor();
		}

		if(data.description() != null && !data.description().isEmpty()) {
			this.description = data.description();
		}
		if(data.owner() != null) {
			this.owner = new Papel(data.owner());
		}		
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoverColor() {
		return coverColor;
	}
	public void setCoverColor(String coverColor) {
		this.coverColor = coverColor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Label> getLabels() {
		return labels;
	}
	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}
	public Papel getOwner() {
		return owner;
	}
	public void setOwner(Papel owner) {
		this.owner = owner;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}


}
