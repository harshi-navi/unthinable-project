package com.employee.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Table(name = "department")
@Entity
public class Department implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;


    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    
}
