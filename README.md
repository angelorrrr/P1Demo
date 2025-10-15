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
* **Passo 1:** clice duas vezes no arquivo (`Graph.exe`), localizado na pasta (`out`) do projeto.

  Caso queira diferentes grafos, mude as informações no arquivo (`graph.txt`) seguindo as diretrizes de formatação.
* **Passo 2:** interaja com o menu, requerindo as funcionalidades que desejar.
## 6- Técnicas de Projeto
O projeto foi construído com a finalidade de ter portabilidade para quaisquer implementações de grafos sem impactar na
performance dos algoritmos. Para alcançar tal objetivo, recorremos a paradigmas de projeto e de construção de objetos que 
facilitassem essas implementações.
### 6.1- Programação Orientada a Objetos
Na classe grafo, recorremos ao conceito de tipo genérico, amplamente utilizado pela tecnologia Java, para permitir que a estrutura
de dados porte qualquer tipo de vértice ou aresta.
Também na classe grafo, definimos uma interface que definisse as funções comuns a todas as implementações e, intermediária à linha de
herança, implementamos uma classe abstrata que definisse alguns métodos com antecedência.
Além disso, criamos uma enumeração interna a esta classe (em questão, GraphType), que facilitou o processo de leitura do arquivo de texto. Para isso, criamos
um nome descritivo do tipo como o valor das enumerações e um atributo String, que seria o símbolo no cabeçalho do arquivo de leitura. Dentro da enumeração, 
criamos um conversor de String para GraphType, caso exista uma correspondência entre o valor de entrada e os atributos das constantes.
### 6.2- Singletons, Factories
Utilizamos alguns métodos de fábrica. Entre eles, um dos mais importantes foi o constructGraph(), que constrói um grafo do mesmo tipo de sua implementação concreta.
Isso permitiu que nós implementássemos o método do grafo transposto logo na classe abstrata, reduzindo as responsabilidades nas implementações.
Como Singletons, tivemos duas classes: (`Menu.java`) e (`Context.java`). Optamos por isso pela lógica de não poderem existir dois ou mais menus ou comportadores
de estado de grafos, e também para permitir o fornecimento direto de algumas informações entre as classes de comandos sem a necessidade de parametrização.
### 6.3- Padrão Command-Based
Para a construção do menu, achamos interessante trazer um padrão de projeto amplamente usado na robótica competitiva (mais especificamente, na FIRST Robotics
Competition). Esse recurso tem pouco aproveitamento para o futuro (só foi útil para o Menu). Mas podem haver outras aplicações desse padrão em algoritmos ou
ações quaisquer na posteridade.
### 6.4- Programação Funcional na DFS
A fim de não precisar escrever 700 dfs, criamos um único método estático na clsse (`DFS.java`) que recebe consummers de vértices para executar ações em in-ordem ou
pos-ordem. Isso foi muito útil na classe (`Kosaraju.java`), pois o algoritmo, em uma de suas versões, tinha ações em in-ordem e pos-ordem. Com o auxílio da programação 
funcional, pudemos melhorar a legibilidade desse trecho.
### 6.5- Gerenciamento de Dependências
Nas versões iniciais, utilizávamos o gerenciador da ide em uso (em questão, Intellij) para as dependências. Quando tivemos a necessidade de iplementar a classe (`GraphView.java`),
tivemos que trocar para o Maven para importar algumas dependências importantes (em questão, graphstream). Isso certamente terá utilidade para a posteridade.
### 6.6- Exceptions personalizadas
Essa parte é útil para o menu, mas pode ter aplicações no contexto dos algoritmos e dos grafos. EM geral, exceções tornam o sistema muito transparente por conta das stacktraces acusadas.Para remediar, optamos por criar exceções próprias que exibissem erros específicos no contexto do programa. Isso com certeza pode ser útil nos algoritmos.
