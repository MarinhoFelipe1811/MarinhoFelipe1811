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

    //TODO: SOBRESCREVER O MÉTODO "toString" PARA EXIBIR AS INFORMAÇÕES TEXTUAIS DE CADA EXERCÍCIO CADASTRADO
    @Override
    public String toString() {
        return "";
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
