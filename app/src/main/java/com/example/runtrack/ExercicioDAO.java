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

    //TODO: IMPLEMENTAR MÉTODOS PARA INSERIR UM EXERCÍCIO, BUSCAR O ÚLTIMO EXERCÍCIO CADASTRADO, E EXCLUIR UM EXERCÍCIO.
    //PONTO EXTRA PARTE 1: IMPLEMENTAÇÃO DA BUSCA DO MAIOR EXERCÍCIO CADASTRADO
}
