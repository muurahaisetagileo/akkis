package fi.agileo.akkis.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NOTE")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NOTE_ID")
	private long noteId;		
	
	@OneToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name = "AUTHOR")
	private User author;
	
	@Column(name = "PUBLICITY")
	private String publicity; //private, public, group
	
	@Column(name = "NOTETEXT")
	private String noteText;
	
//	@Column(name = "ATTACHMENT")
//	@Lob
//	private byte[] attachment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIME")	
	private Date updateTime;

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getPublicity() {
		return publicity;
	}

	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
