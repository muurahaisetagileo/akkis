package fi.agileo.akkis.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TASK")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TASK_ID")
	private long taskId;	
	
	@Column(name="TASK_TYPE")
	private String taskType;	// assignedTask/todoTask
	
	@OneToOne(optional=false ,fetch=FetchType.EAGER)
	@JoinColumn(name="ASSIGNER")
	private User assigner;
	
	@OneToOne(optional=false, fetch=FetchType.EAGER)	// TODO
	@JoinColumn(name="EXECUTOR")
	private User executor;
	
	@Column(name="DESCRIPTION")	// TODO
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIME")	// TODO)
	private Date updateTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DUE_TIME")	// TODO
	private Date dueTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name="REMIND_TIME")	// TODO
	private Date remindBefore;
	
	@Column(name="STATE")	// TODO
	private String state;
	
	@Column(name="EXECUTOR_COMMENT")
	private String executorComment;
	
//	@Column(name="CUSTOMER")
//	private ContactPerson customer;
	
	//@Column(name="DELIVERY")
	//private Delivery delivery;
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public User getAssigner() {
		return assigner;
	}
	
	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}
	
	public User getExecutor() {
		return executor;
	}
	
	public void setExecutor(User executor) {
		this.executor = executor;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getDueTime() {
		return dueTime;
	}
	
	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}
	
	public Date getRemindBefore() {
		return remindBefore;
	}
	
	public void setRemindBefore(Date remindBefore) {
		this.remindBefore = remindBefore;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getExecutorComment() {
		return executorComment;
	}
	
	public void setExecutorComment(String executorComment) {
		this.executorComment = executorComment;
	}
	
//	public User getCustomer() {
//		return customer;
//	}
//	
//	public void setCustomer(ContactPerson customer) {
//		this.customer = customer;
//	}
	
}
