package com.savia.hemofilia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "illness_model")
public class IllnesModel {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_tables", nullable = false)
    private String table;

    public IllnesModel() {
    }

    public IllnesModel(String nameTables) {
        table = nameTables;
    }

    public IllnesModel(Integer id, String nameTables) {
        this.id = id;
        table = nameTables;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("table")
    public String getNameTables() {
        return table;
    }

    public void setNameTables(String nameTables) {
        table = nameTables;
    }
}
