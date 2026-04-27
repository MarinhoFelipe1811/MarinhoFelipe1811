package com.example.runtrack;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Exercicio.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ExercicioDAO exercicioDAO();
}
