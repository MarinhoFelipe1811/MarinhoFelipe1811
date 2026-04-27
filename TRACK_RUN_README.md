# Track Run - Aplicativo de Gerenciamento de Exercícios

## Descrição
Track Run é um aplicativo Android para gerenciar atividades de corrida e caminhada com persistência de dados local utilizando Room Database.

## Funcionalidades

### 1. Cadastro de Exercícios
- Campo para descrição da atividade (ex: "Corrida matinal", "Caminhada no parque")
- Campo para distância percorrida em metros
- Validação de campos obrigatórios
- Validação de entrada numérica válida
- Mensagens de feedback ao usuário

### 2. Listagem de Exercícios
- Exibe todos os exercícios cadastrados em uma ListView
- Formato de exibição: "Descrição - Distância em metros"
- Atualização automática após cadastro ou exclusão
- Dados formatados com 2 casas decimais

### 3. Exclusão de Exercícios
- Botão "Remover Último Exercício"
- Remove o último exercício cadastrado (por ID)
- Atualiza automaticamente a lista após exclusão
- Mensagem de aviso se não houver exercícios para excluir

### 4. Estatísticas (Funcionalidade Extra)
Display de informações em tempo real:
- **Total de exercícios cadastrados**
- **Distância total percorrida** (soma de todos os exercícios)
- **Maior exercício** (exercício com maior distância)

As estatísticas são atualizadas automaticamente:
- Ao abrir o aplicativo
- Após cadastrar um novo exercício
- Após excluir um exercício

## Arquitetura

### Camada de Dados (Room Database)

#### Exercicio.java
Entidade Room que representa um exercício:
```java
@Entity(tableName = "exercicios")
public class Exercicio {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;
    private double distancia;
}
```

#### ExercicioDAO.java
Interface DAO com as operações de banco de dados:
- `buscaTodosExercicios()` - Lista todos os exercícios
- `inserir(Exercicio)` - Insere um novo exercício
- `buscarUltimoExercicio()` - Busca o último exercício por ID
- `excluir(Exercicio)` - Exclui um exercício
- `buscarMaiorExercicio()` - Busca o exercício com maior distância
- `buscarDistanciaTotal()` - Calcula a soma de todas as distâncias

#### MyDatabase.java
Classe abstrata que estende RoomDatabase e fornece acesso ao DAO

### Camada de Apresentação

#### MainActivity.java
Activity principal com três métodos principais:

1. **salvarExercicio()**
   - Valida campos de entrada
   - Converte distância para double com tratamento de exceções
   - Insere exercício no banco em thread separada
   - Atualiza UI na thread principal
   - Limpa os campos após salvar

2. **excluirExercicio()**
   - Busca o último exercício em thread separada
   - Exclui do banco de dados
   - Atualiza UI na thread principal
   - Exibe feedback apropriado

3. **buscarExercicios()**
   - Busca todos os exercícios em thread separada
   - Busca estatísticas (total e maior)
   - Atualiza ListView e TextView na thread principal
   - Formata números com 2 casas decimais

## Boas Práticas Implementadas

### 1. Threading Adequado
- Todas as operações de banco de dados executam em threads secundárias
- Atualizações de UI via `runOnUiThread()`
- Evita ANR (Application Not Responding)

### 2. Validação de Entrada
- Verifica campos vazios
- Tratamento de NumberFormatException
- Mensagens claras de erro

### 3. Experiência do Usuário
- Feedback imediato via Toast
- Campos limpos após cadastro
- Formatação de números para melhor legibilidade
- Atualização automática de dados

### 4. Código Limpo
- Separação de responsabilidades
- Uso correto de generics (ArrayList<>)
- Formatação consistente
- Comentários em português

## Tecnologias Utilizadas
- **Linguagem**: Java
- **Banco de Dados**: Room Database (SQLite)
- **UI**: Android XML Layouts
- **Arquitetura**: DAO Pattern
- **Threading**: Threads Java + runOnUiThread

## Como Usar

1. **Cadastrar um exercício:**
   - Preencha o campo "Descrição do Exercício"
   - Preencha o campo "Distância em Metros"
   - Clique no botão "Cadastrar Exercício"
   - O exercício aparecerá na lista e as estatísticas serão atualizadas

2. **Visualizar exercícios:**
   - Todos os exercícios cadastrados aparecem na lista
   - Formato: "Descrição - Distância"
   - As estatísticas são exibidas no topo da lista

3. **Excluir o último exercício:**
   - Clique no botão "Remover Último Exercício"
   - O último exercício cadastrado será removido
   - A lista e estatísticas serão atualizadas

## Requisitos Atendidos

✅ Cadastro de exercícios com distância em metros  
✅ Exclusão do último exercício cadastrado  
✅ Exibição de exercícios ao abrir o aplicativo  
✅ Atualização ao cadastrar novo exercício  
✅ Atualização ao excluir exercício  
✅ **EXTRA**: Exibição de distância total percorrida  
✅ **EXTRA**: Exibição do maior exercício cadastrado  
✅ **EXTRA**: Atualização automática das estatísticas  

## Segurança
- Código analisado com CodeQL
- Nenhuma vulnerabilidade encontrada
- Validação adequada de entradas
- Tratamento de exceções

## Desenvolvido para
Prova 2 - Programação para Dispositivos Móveis  
Centro Universitário de Brasília (CEUB)  
Baseado no projeto inicial do Prof. Kristian Pacheco
