package Ordenacoes;
/*Ordenar o arquivo completo de senhas pelo campo length em ordem decrescente.
Deve-se gerar um arquivo para cada algoritmo de ordenação e o tipo de caso. Por exemplo, passwords_length_insertionSort_medioCaso.csv, passwords_length_insertionSort_piorCaso.csv, passwords_length_insertionSort_melhorCaso.csv */

import Classificacao.LerArquivosCsv;

public class OrdenacaoLength {
    String LerArquivosCsv = "src\\ArquivosCSV\\passwords_formated_data.csv";
    
    String caminhoSaidaInsectionMedio = "src\\ArquivosCSV\\passwords_length_insertionSort_medioCaso.csv";
    String caminhoSaidaInsectionPior = "src\\ArquivosCSV\\passwords_length_insertionSort_piorCaso.csv";
    String caminhoSaidaInsectionMelhor = "src\\ArquivosCSV\\passwords_length_insertionSort_melhorCaso.csv";



    LerArquivosCsv leitorCsv = new LerArquivosCsv(LerArquivosCsv);
    
    


}
