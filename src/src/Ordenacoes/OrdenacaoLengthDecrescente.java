package Ordenacoes;
/*Ordenar o arquivo completo de senhas pelo campo length em ordem decrescente.
Deve-se gerar um arquivo para cada algoritmo de ordenação e o tipo de caso. Por exemplo, passwords_length_insertionSort_medioCaso.csv, passwords_length_insertionSort_piorCaso.csv, passwords_length_insertionSort_melhorCaso.csv */

import Classificacao.LerArquivosCsv;
import Ordenacoes.CountingSort.CountingMedioCaso;

import java.io.IOException;

public class OrdenacaoLengthDecrescente {
    String LerArquivosCsv = "src\\ArquivosCSV\\passwords_formated_data.csv";
    
    String caminhoSaidaInsectionMedio = "src\\ArquivosCSV\\passwords_length_insertionSort_medioCaso.csv";
    String caminhoSaidaInsectionPior = "src\\ArquivosCSV\\passwords_length_insertionSort_piorCaso.csv";
    String caminhoSaidaInsectionMelhor = "src\\ArquivosCSV\\passwords_length_insertionSort_melhorCaso.csv";



    LerArquivosCsv leitorCsv = new LerArquivosCsv(LerArquivosCsv);
    
    public static void Ordenacao(String[] args) {
        // Exemplo de uso do Counting Sort para ordenar um arquivo CSV pela coluna "length"
        String inputFilePath = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Substitua pelo caminho do arquivo de entrada
        String outputFilePath = "src\\ArquivosCSV\\passwords_length_countingSort_medioCaso.csv"; // Substitua pelo caminho do arquivo de saída
        int max = 100; // Substitua pelo valor máximo esperado na coluna "length"

        try {
            // Chamada para o método de ordenação Counting Sort
            CountingMedioCaso.countingSortCSV(inputFilePath, outputFilePath, max);
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
}
