package br.mil.mar.kanban.column;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import br.mil.mar.kanban.card.Card;
import br.mil.mar.kanban.card.CardRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_COLUMN")
public class Column  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String title;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="column_id")
	private List<Card> cards;
	
	

	public Column() {
		super();
		this.cards = new LinkedList<Card>();
	}

	public Column(ColumnRecordDto data) {
		this();
		if(data.title() != null && !data.title().isEmpty()) {
			this.title = data.title();
		}
		if(data.cards() != null && !data.cards().isEmpty()) {
			ListIterator<CardRecordDto> cardListIterator = data.cards().listIterator();
			while(cardListIterator.hasNext()) {
				this.cards.add(new Card(cardListIterator.next()));
			}
		}
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
