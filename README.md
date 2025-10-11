# Projeto da Primeira Avaliação – Teoria dos Grafos

## 
<p align="center">
  <img src="https://th.bing.com/th/id/R.5052a4e6f0705e791a5aba227d159831?rik=FwKKHWUyD3G0vA&pid=ImgRaw&r=0" alt="Project Logo or Graph Example" width="400"/>
</p>

## 
## Informações Gerais
* **Universidade:** Universidade Estadual do Maranhão (UEMA)
* **Disciplina:** Estrutura de Dados Avançada
* **Professor:** Jacob

## Equipe
* **Nome:** Camila Luiza Silva Machado
* **Nome:** Angelo Antonio Barbosa Luz Freitas

## 1. Descrição do Projeto
Este projeto, desenvolvido para a disciplina de Estrutura de Dados Avançada, aplica conceitos teóricos na resolução de um problema prático do mundo da computação. O trabalho consistiu na implementação de uma estrutura de dados para grafos a partir do zero, com operações básicas e um algoritmo de busca para solucionar um problema específico.

O tema escolhido foi o **Tema A: Analisador de Comunidades em Redes Sociais**. O objetivo é ler um grafo de amizades (não-dirigido), encontrar todos os componentes conexos e exibir o número de comunidades encontradas, listando os membros de cada uma.

## 2. Tecnologias e Estruturas
* **Linguagem de Programação:** Java (JDK 25)
* **Estrutura de Dados:** Lista de Adjacência

## 3. Funcionalidades Implementadas
O programa implementa todos os requisitos obrigatórios e desafios extras:
- [x] **Leitura de Grafo:** O sistema lê e interpreta um grafo a partir de um arquivo de texto (`.txt`), detectando automaticamente se é ponderado ou não.
- [x] **Operações Básicas Obrigatórias:**
    - Verificar Adjacência
    - Calcular Grau
    - Listar Vizinhos
    - Listar Arestas
- [x] **Algoritmo Principal (Tema A):** Implementação do algoritmo de **Componentes Conexos** para identificar "bolhas" ou grupos em uma rede.
- [x] **Visualização Gráfica:** Geração de uma imagem (`.png`) para a visualização do grafo lido.
- [x] **Desafios Extras:**
    - Algoritmo de Dijkstra
    - Algoritmo de Kosaraju
    - Algoritmo DFS (Busca em Profundidade)

## 4. Formato do Arquivo de Entrada
O programa espera um arquivo `.txt` com o seguinte formato:
* **1ª Linha:** `ND` para Grafos Não-Dirigidos ou `D` para Grafos Dirigidos.
* **Linhas Seguintes:** Pares de vértices (`v1 v2`) para grafos não ponderados, ou trios (`v1 v2 peso`) para grafos ponderados.
  Ele precisa estar localizado na mesma pasta que o arquivo executável, e precisa estar nomeado como (`graph.txt`).
  **Exemplo (`graph.txt`):**
```text
ND
A B
A C
B C
C D
```
## 5. Como rodar
Existem 3 alternativas:
### 5.1- Pela ide
Requisitos: Você vai precisar desse repositório no seu computador e de um jdk
* **Passo 1:** abrir a classe (`org.example.Main`) dentro do diretório com o codigo fonte
* **Passo 2:** trocar o valor da constante filePath para o caminho absoluto do arquivo (`graph.txt`), que deve seguir a estrutura descrita
* **Passo 3:** executar o arquivo (`Main.java`) normalmente
### 5.2- Pelo jar
Requisitos: Você vai precisar de um jre na sua máquina
* **Passo 1:** abrir o arquivo (`Grafos.jar`) no terminal (está localizado na pasta out desse diretório)
* **Passo 2:** Crie um arquivo (`graph.txt`) no mesmo nível que o arquivo jar. Siga a estrutura descrita nas **[regras de formatação](#4-formato-do-arquivo-de-entrada)**..
* **Passo 3:** execute o código abaixo no terminal:
```bash
java -jar Grafos.jar
```
### 5.3- Pelo exe
Requisitos: Desse repositório, você só precisa da pasta out (caso seu sistema seja windows).
* **Passo 1:** clice duas vezes no arquivo (`Graph.exe`)
  Caso queira diferentes grafos, mude as informações no arquivo (`graph.txt`) seguindo as diretrizes de formatação.

