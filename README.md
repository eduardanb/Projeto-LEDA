# T2-Projeto Passwords

## **Descrição do Projeto**
O **T2-Projeto Passwords** tem como objetivo processar um grande conjunto de senhas contidas no arquivo `passwords.csv`, aplicando regras específicas para **classificação**, **transformação** e **filtragem**. O resultado é a geração de arquivos organizados e prontos para análises futuras, como ordenações e estudos de desempenho de algoritmos.

O projeto está dividido em duas partes:
1. **Parte 1**: Classificação, transformação e filtragem das senhas.
2. **Parte 2**: Ordenação das senhas utilizando diferentes algoritmos e cenários.

---

## **Parte 1: Classificação e Transformação**

### **1. Classificação das Senhas**
As senhas do arquivo `passwords.csv` são classificadas de acordo com as seguintes regras:

- **Muito Ruim**: Tamanho da senha menor que 5 e contendo apenas **um tipo de caractere** (letras minúsculas/maiúsculas, números ou caracteres especiais).
- **Ruim**: Tamanho da senha menor ou igual a 5 e contendo apenas **um tipo de caractere**.
- **Fraca**: Tamanho da senha menor ou igual a 6 e contendo **dois tipos de caracteres**.
- **Boa**: Tamanho da senha menor ou igual a 7 e contendo **todos os tipos de caracteres**.
- **Muito Boa**: Tamanho da senha maior que 8 e contendo **todos os tipos de caracteres**.
- **Sem Classificação**: Senhas que não se enquadram em nenhuma das categorias acima.

#### **Resultado**
- Um novo arquivo chamado `password_classifier.csv` é gerado, contendo as senhas classificadas com uma nova coluna chamada `Classificacao`.

---

### **2. Transformação**
Após a classificação, o arquivo `password_classifier.csv` passa por uma transformação no formato da data. A data original, no formato `AAAA-MM-DD`, é convertida para o formato **`DD/MM/AAAA`**.

#### **Resultado**
- Um novo arquivo chamado `passwords_formated_data.csv` é gerado, contendo as senhas com a data transformada.

---

### **3. Filtragem**
As senhas classificadas como **"Boa"** ou **"Muito Boa"** são filtradas do arquivo `passwords_formated_data.csv` e salvas em um novo arquivo.

#### **Resultado**
- Um novo arquivo chamado `passwords_classifier.csv` é gerado, contendo apenas as senhas filtradas.

---

## **Arquivos Gerados**
Durante a execução da Parte 1, os seguintes arquivos são gerados:

1. **`password_classifier.csv`**:
   - Contém todas as senhas classificadas com base nas regras descritas.
   - Estrutura:
     ```
     Posicao,Senha,Tamanho,Data,Classificacao
     ```

2. **`passwords_formated_data.csv`**:
   - Contém as senhas com a data transformada para o formato `DD/MM/AAAA`.
   - Estrutura:
     ```
     Posicao,Senha,Tamanho,Data,Classificacao
     ```

3. **`passwords_classifier.csv`**:
   - Contém apenas as senhas classificadas como **"Boa"** ou **"Muito Boa"**.
   - Estrutura:
     ```
     Posicao,Senha,Tamanho,Data,Classificacao
     ```

---

## **Execução do Código**

### **Passo a Passo**
1. **Classificação**:
   - O arquivo `passwords.csv` é lido e processado.
   - As senhas são classificadas e salvas no arquivo `password_classifier.csv`.

2. **Transformação**:
   - O arquivo `password_classifier.csv` é lido.
   - A data é transformada para o formato `DD/MM/AAAA`.
   - O resultado é salvo no arquivo `passwords_formated_data.csv`.

3. **Filtragem**:
   - O arquivo `passwords_formated_data.csv` é lido.
   - Apenas as senhas classificadas como **"Boa"** ou **"Muito Boa"** são filtradas.
   - O resultado é salvo no arquivo `passwords_classifier.csv`.

### **Como Executar**
1. Compile o código:
   ```bash
   javac src/src/*.java
   ```
2. Execute o programa:
   ```bash
   java -cp src/src Main
   ```

---

## **Estrutura do Projeto**
```plaintext
Projeto-LEDA/
├── src/
│   ├── Main.java                     # Classe principal
│   ├── LerArquivosCsv.java           # Classe para leitura do arquivo CSV
│   ├── ClassificacaoDeSenhas.java    # Classe para classificação das senhas
│   ├── TransformadorDeDados.java     # Classe para transformação e filtragem dos dados
│   ├── ClassificacaoBoaeMuitoboa.java # Classe para filtrar senhas "Boa" e "Muito Boa"
├── ArquivosCSV/
│   ├── passwords.csv                 # Arquivo de entrada com as senhas
│   ├── password_classifier.csv       # Arquivo com as senhas classificadas
│   ├── passwords_formated_data.csv   # Arquivo com a data transformada
│   ├── passwords_classifier.csv      # Arquivo com senhas "Boa" e "Muito Boa"
```

---

## **Exemplo de Entrada e Saída**

### **Entrada (`passwords.csv`)**
```csv
Posicao,Senha,Tamanho,Data
1,abc123,6,2023-04-10 12:00:00
2,Password@123,12,2023-04-10 12:00:00
3,12345,5,2023-04-10 12:00:00
4,StrongPass1!,11,2023-04-10 12:00:00
```

### **Saída 1 (`password_classifier.csv`)**
```csv
Posicao,Senha,Tamanho,Data,Classificacao
1,abc123,6,2023-04-10 12:00:00,Fraca
2,Password@123,12,2023-04-10 12:00:00,Muito Boa
3,12345,5,2023-04-10 12:00:00,Ruim
4,StrongPass1!,11,2023-04-10 12:00:00,Boa
```

### **Saída 2 (`passwords_formated_data.csv`)**
```csv
Posicao,Senha,Tamanho,Data,Classificacao
1,abc123,6,10/04/2023,Fraca
2,Password@123,12,10/04/2023,Muito Boa
3,12345,5,10/04/2023,Ruim
4,StrongPass1!,11,10/04/2023,Boa
```

### **Saída 3 (`passwords_classifier.csv`)**
```csv
Posicao,Senha,Tamanho,Data,Classificacao
2,Password@123,12,10/04/2023,Muito Boa
4,StrongPass1!,11,10/04/2023,Boa
```

---

## **Próximos Passos**
A **Parte 2** do projeto será focada em **ordenações**. O arquivo `passwords_formated_data.csv` será utilizado como entrada para:
1. Ordenar as senhas pelo campo `Tamanho` em ordem decrescente.
2. Ordenar as senhas pelo **mês** da coluna `Data` em ordem crescente.
3. Ordenar as senhas pela coluna `Data` em ordem crescente.

Cada ordenação será realizada utilizando diferentes algoritmos de ordenação e analisada em três casos: **melhor**, **médio** e **pior**.

---

Colaboradores: [Maria Eduarda da Nóbrega, João Victor da Silva Almeida Guimarães e Adrielly Carla Ferreira de Melo]