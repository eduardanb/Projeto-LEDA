# T2-Projeto Passwords

## Descrição do Projeto
O projeto tem como objetivo a classificação, transformação e ordenação de um conjunto de senhas contidas no dataset `passwords.csv`, que possui mais de 600 mil senhas. O projeto será desenvolvido em etapas, seguindo regras específicas para cada uma das tarefas.

## Dataset
O arquivo principal a ser utilizado é `passwords.csv`, que pode ser baixado através do link fornecido. Todas as operações serão baseadas nesse dataset.

## Classificação das Senhas
As senhas devem ser classificadas conforme os seguintes critérios:

- **Muito Ruim**: Senhas com menos de 5 caracteres e contendo apenas um tipo de caractere (letras maiúsculas/minúsculas, números ou caracteres especiais).
- **Ruim**: Senhas com até 5 caracteres e contendo apenas um tipo de caractere.
- **Fraca**: Senhas com até 6 caracteres e contendo dois tipos de caracteres.
- **Boa**: Senhas com até 7 caracteres e contendo todos os tipos de caracteres.
- **Muito Boa**: Senhas com mais de 8 caracteres e contendo todos os tipos de caracteres.
- **Sem Classificação**: Senhas que não se enquadram nas categorias acima.

O resultado da classificação deverá ser salvo em um novo arquivo chamado `password_classifier.csv`, incluindo uma nova coluna denominada `class`.

## Transformações
A partir do arquivo `password_classifier.csv`, devem ser realizadas as seguintes transformações:

- Converter o formato da data para `DD/MM/AAAA`.
- Filtrar apenas as senhas classificadas como **Boa** ou **Muito Boa**.
- Salvar o resultado no arquivo `passwords_formated_data.csv`.

## Ordenações
Com base no arquivo `passwords_formated_data.csv`, serão realizadas três ordenações diferentes utilizando diversos algoritmos de ordenação.

### 1. Ordenar pelo tamanho da senha (`length`) em ordem decrescente.
Para cada algoritmo de ordenação, serão gerados arquivos considerando os três casos (melhor, médio e pior):

- `passwords_length_<algoritmo>_melhorCaso.csv`
- `passwords_length_<algoritmo>_medioCaso.csv`
- `passwords_length_<algoritmo>_piorCaso.csv`

### 2. Ordenar pelo mês da data de criação da senha, de forma crescente.
- `passwords_data_month_<algoritmo>_melhorCaso.csv`
- `passwords_data_month_<algoritmo>_medioCaso.csv`
- `passwords_data_month_<algoritmo>_piorCaso.csv`

### 3. Ordenar pelo campo `data`, de forma crescente.
- `passwords_data_<algoritmo>_melhorCaso.csv`
- `passwords_data_<algoritmo>_medioCaso.csv`
- `passwords_data_<algoritmo>_piorCaso.csv`

## Observação
Esta é apenas a parte 1 do projeto, que foca na classificação, transformação e ordenação dos dados. O projeto está em construção e pode receber futuras melhorias e novas funcionalidades.

---

**Autor:** [Maria Eduarda da Nóbrega, João Victor da Silva Almeida Guimarães e Adrielly Carla]  
**Data:** [18/03/2025]  
**Versão:** 1.0
