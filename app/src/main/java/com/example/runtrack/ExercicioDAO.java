package com.example.runtrack;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ExercicioDAO {
    @Query("SELECT * FROM exercicios")
    List<Exercicio> buscaTodosExercicios();

    @Insert
    void inserir(Exercicio exercicio);

    @Query("SELECT * FROM exercicios ORDER BY id DESC LIMIT 1")
    Exercicio buscarUltimoExercicio();

    @Delete
    void excluir(Exercicio exercicio);

    @Query("SELECT * FROM exercicios ORDER BY distancia DESC LIMIT 1")
    Exercicio buscarMaiorExercicio();

    @Query("SELECT SUM(distancia) FROM exercicios")
    Double buscarDistanciaTotal();
}
