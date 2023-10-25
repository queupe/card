package br.mil.mar.kanban.board;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import br.mil.mar.kanban.column.Column;
import br.mil.mar.kanban.column.ColumnRecordDto;
import br.mil.mar.kanban.queue.Queue;
import br.mil.mar.kanban.queue.QueueRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_BOARD")
public class Board  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String title;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="board_id")
	private List<Column> colunms;
	
	@OneToMany(orphanRemoval=false)
	@JoinColumn(name="board_id")
	private List<Queue> queues;
	

	public Board() {
		super();
		this.colunms = new LinkedList<Column>();
		this.queues = new LinkedList<Queue>();
	}
	
	public Board(BoardRecordDto data) {
		this();
		if(!data.title().isEmpty()) {
			this.title = data.title();
		}
		if(data.columns() != null && !data.columns().isEmpty()) {
			ListIterator<ColumnRecordDto> columnListIterator = data.columns().listIterator();
			while(columnListIterator.hasNext()) {
				this.colunms.add(new Column(columnListIterator.next()));
			}
		}
		if(data.queues() != null && !data.queues().isEmpty()) {
			ListIterator<QueueRecordDto> queueListIterator = data.queues().listIterator();
			while(queueListIterator.hasNext()) {
				this.queues.add(new Queue(queueListIterator.next()));
			}
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

	public List<Column> getColunms() {
		return colunms;
	}
	
	public void setColunms(List<Column> colunms) {
		this.colunms = colunms;
	}
	
	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	

}
