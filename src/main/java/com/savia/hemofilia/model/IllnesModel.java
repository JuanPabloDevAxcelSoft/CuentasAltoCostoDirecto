package com.savia.hemofilia.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "illness_model")
public class IllnesModel {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name_tables",nullable = false)
    private String NameTables;

    public IllnesModel() {
    }

    public IllnesModel(String nameTables) {
        NameTables = nameTables;
    }

    public IllnesModel(Integer id, String nameTables) {
        this.id = id;
        NameTables = nameTables;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTables() {
        return NameTables;
    }

    public void setNameTables(String nameTables) {
        NameTables = nameTables;
    }
}
