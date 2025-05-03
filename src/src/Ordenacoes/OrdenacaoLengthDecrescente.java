package Ordenacoes;
/*Ordenar o arquivo completo de senhas pelo campo length em ordem decrescente.
Deve-se gerar um arquivo para cada algoritmo de ordenação e o tipo de caso. Por exemplo, passwords_length_insertionSort_medioCaso.csv, passwords_length_insertionSort_piorCaso.csv, passwords_length_insertionSort_melhorCaso.csv */

import Classificacao.LerArquivosCsv;
import Ordenacoes.CountingSort.CountingMedioCaso;
import Ordenacoes.MergeSort.MergeMedioCaso;
import Ordenacoes.MergeSort.MergeMelhorCaso;
import Ordenacoes.MergeSort.MergePiorCaso;
import java.io.*;
import java.util.*;

public class OrdenacaoLengthDecrescente {
    String LerArquivosCsv = "src\\ArquivosCSV\\passwords_formated_data.csv";

    String caminhoSaidaCountingMedio = "src\\ArquivosCSV\\passwords_length_countingSort_medioCaso.csv";

    String caminhoSaidaMergeMedio = "src\\ArquivosCSV\\passwords_length_mergeSort_medioCaso.csv";
    String caminhoSaidaMergePior = "src\\ArquivosCSV\\passwords_length_mergeSort_piorCaso.csv";
    String caminhoSaidaMergeMelhor = "src\\ArquivosCSV\\passwords_length_mergeSort_melhorCaso.csv";

    LerArquivosCsv leitorCsv = new LerArquivosCsv(LerArquivosCsv);

    // Método para Counting Sort - Caso Médio
    public void countingSortMedioCaso() {
        String inputFilePath = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Caminho do arquivo de entrada
        String outputFilePath = caminhoSaidaCountingMedio; // Caminho do arquivo de saída
        int max = 100; // Valor máximo esperado na coluna "length"

        try {
            // Chamada para o método de ordenação Counting Sort
            CountingMedioCaso.countingSortCSV(inputFilePath, outputFilePath, max);
            System.out.println("Counting Sort - Caso Médio concluído e salvo em: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Erro ao processar o Counting Sort - Caso Médio: " + e.getMessage());
        }
    }

    // Método para Merge Sort - Caso Médio
    public void mergeSortMedioCaso() {
        try {
            List<String[]> rows = new ArrayList<>();
            String[] header = null;

            // Ler o arquivo CSV
            try (var br = new java.io.BufferedReader(new java.io.FileReader(LerArquivosCsv))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        header = line.split(","); // Armazena os cabeçalhos
                        isFirstLine = false;
                        continue; // Pula a primeira linha
                    }
                    rows.add(line.split(","));
                }
            }

            // Extrair a coluna "length" e tratar valores inválidos
            int n = rows.size();
            int[] lengths = new int[n];
            for (int i = 0; i < n; i++) {
                try {
                    lengths[i] = (int) Double.parseDouble(rows.get(i)[2]); // Ajuste o índice da coluna para 2
                } catch (NumberFormatException | NullPointerException e) {
                    System.err.println("Valor inválido encontrado na linha " + (i + 1) + ": " + rows.get(i)[2]);
                    lengths[i] = 0; // Define um valor padrão
                }
            }

            // Chamar o método de ordenação do Merge Sort
            MergeMedioCaso.mergeSortCSV(LerArquivosCsv, caminhoSaidaMergeMedio);
            System.out.println("Merge Sort - Caso Médio concluído e salvo em: " + caminhoSaidaMergeMedio);
        } catch (IOException e) {
            System.err.println("Erro ao processar o Merge Sort - Caso Médio: " + e.getMessage());
        }
    }

    // Método para Merge Sort - Melhor Caso
    public void mergeSortMelhorCaso() {
        try {
            MergeMelhorCaso.mergeSortCSVBestCase(
                LerArquivosCsv,
                caminhoSaidaMergeMelhor
            );
            System.out.println("Merge Sort - Melhor Caso concluído e salvo em: " + caminhoSaidaMergeMelhor);
        } catch (IOException e) {
            System.err.println("Erro ao processar o Merge Sort - Melhor Caso: " + e.getMessage());
        }
    }

    // Método para Merge Sort - Pior Caso
    public void mergeSortPiorCaso() {
        try {
            MergePiorCaso.mergeSortCSVPiorCaso(
                LerArquivosCsv,
                caminhoSaidaMergePior
            );
            System.out.println("Merge Sort - Pior Caso concluído e salvo em: " + caminhoSaidaMergePior);
        } catch (IOException e) {
            System.err.println("Erro ao processar o Merge Sort - Pior Caso: " + e.getMessage());
        }
    }

    // Método principal para executar as ordenações
    public static void Ordenacao(String[] args) {
        OrdenacaoLengthDecrescente ordenacao = new OrdenacaoLengthDecrescente();

        // Counting Sort - Caso Médio
        ordenacao.countingSortMedioCaso();

        // Merge Sort - Caso Médio
        ordenacao.mergeSortMedioCaso();

        // Merge Sort - Melhor Caso
        ordenacao.mergeSortMelhorCaso();

        // Merge Sort - Pior Caso
        ordenacao.mergeSortPiorCaso();
    }
}