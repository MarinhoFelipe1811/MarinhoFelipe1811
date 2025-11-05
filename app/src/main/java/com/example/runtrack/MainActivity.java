package com.example.runtrack;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText descricao;
    private EditText distancia;
    private Button salvar, excluir;
    private TextView informacoes;
    private ListView listViewExercicios;
    private List<Exercicio> listaExercicios = new ArrayList();
    private ArrayAdapter<Exercicio> adapter;
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //INICIALIZAÇÃO DE VARIÁVEIS
        descricao = findViewById(R.id.editTextDescricao);
        excluir = findViewById(R.id.buttonExcluir);
        distancia = findViewById(R.id.editTextDistancia);
        salvar = findViewById(R.id.buttonSalvar);
        informacoes = findViewById(R.id.textViewInformacoes);
        listViewExercicios = findViewById(R.id.listViewExercicios);

        //INICIALIZANDO O ADAPTER QUE EXIBIRÁ A LISTA DE EXERCÍCIOS (listaExercicios) NO LISTVIEW (listViewExercicios)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaExercicios);
        listViewExercicios.setAdapter(adapter);

        //CRIAÇÃO DO BANCO DE DADOS NA VARIÁVEL "db"
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "exercicios.db").build();

        //INICIA O APP EXIBINDO AS INFORMAÇÕES DE EXERCÍCIOS CADASTRADOS, CASO EXISTAM
        buscarExercicios();

        //BOTÃO QUE CHAMA O CADASTRO DE EXERCÍCIOS
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarExercicio();
            }
        });

        //BOTÃO QUE EXCLUI O ÚLTIMO EXERCÍCIO CADASTRADO
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirExercicio();
            }
        });
    }

    private void excluirExercicio() {
        //TODO: BUSCAR O ÚLTIMO EXERCÍCIO NO BANCO DE DADOS, EXCLUÍ-LO, E DEPOIS BUSCAR TODOS OS EXERCÍCIOS NOVAMENTE, PARA ATUALIZAR A TELA
        //OBS: LEMBRAR QUE O ACESSO AO BANCO DE DADOS PRECISA SER FEITO EM UMA NOVA THREAD
    }

    private void salvarExercicio() {
        //TODO: CAPTURAR AS INFORMAÇÕES DO EXERCÍCIO, INSERIR NO BANCO DE DADOS, E DEPOIS BUSCAR TODOS OS EXERCÍCIOS NOVAMENTE, PARA ATUALIZAR A TELA
        //OBS: LEMBRAR QUE O ACESSO AO BANCO DE DADOS PRECISA SER FEITO EM UMA NOVA THREAD
    }

    private void buscarExercicios() {
        //TODO: BUSCAR TODOS OS EXERCÍCIOS NO BANCO DE DADOS, E EXIBÍ-LOS NO LISTVIEW
        //OBS: LEMBRAR QUE O ACESSO AO BANCO DE DADOS PRECISA SER FEITO EM UMA NOVA THREAD
        //OBS: LEMBRAR QUE A LISTA DE EXERCÍCIOS DEVE SER ATUALIZADA A CADA INSERÇÃO/REMOÇÃO
        //PONTO EXTRA PARTE 2: EXIBIÇÃO/ATUALIZAÇÃO, NO TEXTVIEW, DA DISTÂNCIA TOTAL PERCORRIDA E DO MAIOR EXERCÍCIO CADASTRADO.
    }
}