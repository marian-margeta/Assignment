package com.garwan.assignment.todolist;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garwan.assignment.auth.Account;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Length(max = 255)
	@NotEmpty(message = "The title is mandatory")
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	
	@Column(name = "created_by", nullable = false)
	private Integer createdById;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "completed", nullable = false)
	private Boolean completed;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", insertable = false, updatable = false)
    private Account createdBy;

	
	public Task() {
		
	}
	
	public Task(String title, Integer createdById, Boolean completed) {
		this.title = title;
		this.createdById = createdById;
		this.completed = completed;
		this.createdAt = LocalDateTime.now();
	}



	// Generated getters & setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Account getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Account createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
