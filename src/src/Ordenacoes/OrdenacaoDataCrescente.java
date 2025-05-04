package Ordenacoes;

import Ordenacoes.CountingSort.*;
import Ordenacoes.HeapSort.*;
import Ordenacoes.InsectionSort.*;
import Ordenacoes.MergeSort.*;
import Ordenacoes.QuickSort.*;
import Ordenacoes.QuickSortMediana3.*;
import Ordenacoes.SelectionSort.*;
import java.io.*;
import java.util.*;

public class OrdenacaoDataCrescente {
    static long TempoTotalCountingMedioCaso = 0;
    static long TempoTotalCountingPiorCaso = 0;
    static long TempoTotalCountingMelhorCaso = 0;
    static long TempoTotalMergeMelhorCaso = 0;
    static long TempoTotalMergePiorCaso = 0;
    static long TempoTotalMergeMedioCaso = 0;
    static long TempoTotalQuickMedianaDe3MelhorCaso = 0;
    static long TempoTotalQuickMedianaDe3PiorCaso = 0;
    static long TempoTotalQuickMedianaDe3MedioCaso = 0;
    public static void Ordenacao(String[] args) {
        // Exemplo de uso do Counting Sort para ordenar um arquivo CSV pela coluna "length"
        String EntradaCSV = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Substitua pelo caminho do arquivo de entrada
        String SaidaMedioCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_medioCaso.csv"; 
        String SaidaPiorCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_piorCaso.csv"; 
        String SaidaMelhorCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_melhorCaso.csv"; 
        int max = 100; // Substitua pelo valor máximo esperado na coluna "length"

        /*try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingMedioCaso.countingSortCSVData(EntradaCSV, SaidaMedioCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingPiorCaso.countingSortCSVData(EntradaCSV, SaidaPiorCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingMelhorCaso.countingSortCSVData(EntradaCSV, SaidaMelhorCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        // Exemplo de uso do Merge Sort para ordenar um arquivo CSV pela coluna "length"
        String SaidaMedioCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_medioCaso.csv";
        String SaidaPiorCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_piorCaso.csv";
        String SaidaMelhorCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergeMedioCaso.mergeSortCSV(EntradaCSV, SaidaMedioCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoMerge);
            
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergePiorCaso.mergeSortCSVPiorCaso(EntradaCSV, SaidaPiorCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoMerge);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergeMelhorCaso.mergeSortCSVBestCase(EntradaCSV, SaidaMelhorCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoMerge);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }*/

        // Quick Sort mediana 3
        String SaidaMedioCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_data_quickSortmedianade3_medioCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            Quick3MedioCaso.QuickSort3CSVData(EntradaCSV, SaidaMedioCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaPiorCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_data_quickSortmedianade3_piorCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            Quick3PiorCaso.QuickSort3CSVData(EntradaCSV, SaidaPiorCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3PiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaMelhorCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_data_quickSortmedianade3_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            Quick3MelhorCaso.QuickSort3CSVData(EntradaCSV, SaidaMelhorCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

    }

    public static void TempoDeExecucao() {
        System.out.println("Tempo de execução de cada algoritmo quando ordenados pela coluna data: \n" +
            "Counting Sort Medio Caso: " + TempoTotalCountingMedioCaso + " ns\n" +
            "Counting Sort Pior Caso: " + TempoTotalCountingPiorCaso + " ns\n" +
            "Counting Sort Melhor Caso: " + TempoTotalCountingMelhorCaso + " ns\n" +
            "Merge Sort Melhor Caso: " + TempoTotalMergeMelhorCaso + " ns\n" +
            "Merge Sort Pior Caso: " + TempoTotalMergePiorCaso + " ns\n" +
            "Merge Sort Medio Caso: " + TempoTotalMergeMedioCaso + " ns\n"+
            "Quick Sort Mediana de 3 Melhor Caso: " + TempoTotalQuickMedianaDe3MelhorCaso + " ns\n" +
            "Quick Sort Mediana de 3 Pior Caso: " + TempoTotalQuickMedianaDe3PiorCaso + " ns\n" +
            "Quick Sort Mediana de 3 Medio Caso: " + TempoTotalQuickMedianaDe3MedioCaso + " ns\n");
    }
}
