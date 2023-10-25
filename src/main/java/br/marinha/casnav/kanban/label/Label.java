package br.marinha.casnav.kanban.label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.marinha.casnav.kanban.card.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_LABEL")
public class Label  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String icon;
	private String color;
	private String description;
	
	@ManyToMany(mappedBy="labels")
	@JsonBackReference
	private Set<Card> cards;
	
	
	public Label() {
		super();
		this.cards = new HashSet<Card>();
	}
	
	public Label(LabelRecordDto data) {
		this();
		if(data.icon() != null &&  !data.icon().isEmpty()) {
			this.icon = data.icon();
		}
		if(data.color() != null && !data.color().isEmpty()) {
			this.color = data.color();
		}
		if(data.description() != null && !data.description().isEmpty()) {
			this.description = data.description();
		}
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	

}
