package com.example.runtrack;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercicios")
public class Exercicio {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "descricao")
    private String descricao;
    @ColumnInfo(name = "distancia")
    private double distancia;

    @Override
    public String toString() {
        return descricao + " - " + String.format("%.2f", distancia) + "m";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
