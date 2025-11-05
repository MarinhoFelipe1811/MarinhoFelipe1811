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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Exercicio ultimoExercicio = db.exercicioDAO().buscarUltimoExercicio();
                
                if (ultimoExercicio != null) {
                    db.exercicioDAO().excluir(ultimoExercicio);
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Exercício excluído com sucesso", Toast.LENGTH_SHORT).show();
                            buscarExercicios();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Nenhum exercício para excluir", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void salvarExercicio() {
        String desc = descricao.getText().toString();
        String distStr = distancia.getText().toString();

        if (desc.isEmpty() || distStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double dist = Double.parseDouble(distStr);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Exercicio exercicio = new Exercicio();
                exercicio.setDescricao(desc);
                exercicio.setDistancia(dist);
                db.exercicioDAO().inserir(exercicio);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        descricao.setText("");
                        distancia.setText("");
                        Toast.makeText(MainActivity.this, "Exercício cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        buscarExercicios();
                    }
                });
            }
        }).start();
    }

    private void buscarExercicios() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Exercicio> exercicios = db.exercicioDAO().buscaTodosExercicios();
                Double distanciaTotal = db.exercicioDAO().buscarDistanciaTotal();
                Exercicio maiorExercicio = db.exercicioDAO().buscarMaiorExercicio();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaExercicios.clear();
                        listaExercicios.addAll(exercicios);
                        adapter.notifyDataSetChanged();

                        String info = "Total de exercícios: " + exercicios.size();
                        if (distanciaTotal != null) {
                            info += "\nDistância total: " + distanciaTotal + "m";
                        }
                        if (maiorExercicio != null) {
                            info += "\nMaior exercício: " + maiorExercicio.getDistancia() + "m";
                        }
                        informacoes.setText(info);
                    }
                });
            }
        }).start();
    }
}