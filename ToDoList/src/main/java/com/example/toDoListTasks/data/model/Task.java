package com.example.toDoListTasks.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int id;
	
	@NotNull
	private String difficulty;
	
	private String length;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
//	@JsonIgnore
	@ManyToOne(targetEntity = Urgency.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_urgency_id")
	private Urgency urgency;
	
	
	public Task() {
		
	}


	public Task(@NotNull String difficulty, String length, @NotNull String name, @NotNull String description) {
		super();
		this.difficulty = difficulty;
		this.length = length;
		this.name = name;
		this.description = description;
	}


	public Task(int id, @NotNull String difficulty, String length, @NotNull String name, @NotNull String description) {
		super();
		this.id = id;
		this.difficulty = difficulty;
		this.length = length;
		this.name = name;
		this.description = description;
	}


	public Task(int id, @NotNull String difficulty, String length, @NotNull String name, @NotNull String description,
			Urgency urgency) {
		super();
		this.id = id;
		this.difficulty = difficulty;
		this.length = length;
		this.name = name;
		this.description = description;
		this.urgency = urgency;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	

	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Urgency getUrgency() {
		return urgency;
	}


	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((urgency == null) ? 0 : urgency.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (getDescription() == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (getDifficulty() == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (getLength() == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (getName() == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (getUrgency() == null) {
			if (other.urgency != null)
				return false;
		} else if (!urgency.equals(other.urgency))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", difficulty=" + difficulty + ", length=" + length + ", name=" + name
				+ ", description=" + description + ", urgency=" + urgency + "]";
	}
	
	
}
