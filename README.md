# Projeto da Primeira Avaliação – Teoria dos Grafos

## 
<p align="center">
  <img src="https://i.imgur.com/your-image-url.png" alt="Project Logo or Graph Example" width="400"/>
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

**Exemplo (`grafo_exemplo.txt`):**
```text
ND
A B
A C
B C
C D
