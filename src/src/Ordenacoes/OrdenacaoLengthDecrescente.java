package Ordenacoes;

import Ordenacoes.CountingSort.*;
import Ordenacoes.HeapSort.*;
import Ordenacoes.InsectionSort.*;
import Ordenacoes.MergeSort.*;
import Ordenacoes.QuickSort.*;
import Ordenacoes.QuickSortMediana3.*;
import Ordenacoes.SelectionSort.*;
import java.io.*;

public class OrdenacaoLengthDecrescente {
    static long TempoTotalCountingMedioCaso = 0;
    static long TempoTotalCountingPiorCaso = 0;
    static long TempoTotalCountingMelhorCaso = 0;
    static long TempoTotalMergeMelhorCaso = 0;
    static long TempoTotalMergePiorCaso = 0;
    static long TempoTotalMergeMedioCaso = 0;
    static long TempoTotalQuickMedianaDe3MelhorCaso = 0;
    static long TempoTotalQuickMedianaDe3PiorCaso = 0;
    static long TempoTotalQuickMedianaDe3MedioCaso = 0;
    static long TempoTotalSelectionMelhorCaso = 0;
    static long TempoTotalSelectionPiorCaso = 0;
    static long TempoTotalSelectionMedioCaso = 0;
    static long TempoTotalQuickMelhorCaso = 0;
    static long TempoTotalQuickPiorCaso = 0;
    static long TempoTotalQuickMedioCaso = 0;
    static long TempoTotalHeapSortMelhorCaso = 0;
    static long TempoTotalHeapSortPiorCaso = 0;
    static long TempoTotalHeapSortMedioCaso = 0;
    static long TempoTotalInsectionSortMelhorCaso = 0;
    static long TempoTotalInsectionSortPiorCaso = 0;
    static long TempoTotalInsectionSortMedioCaso = 0;


    public static void Ordenacao(String[] args) {
        
        String EntradaCSV = "src\\ArquivosCSV\\passwords_formated_data.csv"; 
        String SaidaMedioCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_length_countingSort_medioCaso.csv"; 
        String SaidaPiorCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_length_countingSort_piorCaso.csv"; 
        String SaidaMelhorCasoCounting = "src\\ArquivosCSVOrdenados\\passwords_length_countingSort_melhorCaso.csv"; 

        try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingMedioCaso.CountingSortCSVLength(EntradaCSV, SaidaMedioCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingPiorCaso.CountingSortCSVLength(EntradaCSV, SaidaPiorCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Counting Sort
            long Inicio = System.nanoTime();
            CountingMelhorCaso.CountingSortCSVLength(EntradaCSV, SaidaMelhorCasoCounting);
            long Fim = System.nanoTime();
            TempoTotalCountingMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoCounting);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        String SaidaMedioCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_length_mergeSort_medioCaso.csv";
        String SaidaPiorCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_length_mergeSort_piorCaso.csv";
        String SaidaMelhorCasoMerge = "src\\ArquivosCSVOrdenados\\passwords_length_mergeSort_melhorCaso.csv";

        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergeMedioCaso.mergeSortCSVLength(EntradaCSV, SaidaMedioCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoMerge);
            
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergePiorCaso.mergeSortCSVLength(EntradaCSV, SaidaPiorCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoMerge);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Merge Sort
            long Inicio = System.nanoTime();
            MergeMelhorCaso.mergeSortCSVLength(EntradaCSV, SaidaMelhorCasoMerge);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoMerge);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        String SaidaMedioCasoQuickMedianaDe3 = "src\\ArquivosCSVOrdenados\\passwords_length_quickSortMedianaDe3_medioCaso.csv";
        String SaidaPiorCasoQuickMedianaDe3 = "src\\ArquivosCSVOrdenados\\passwords_length_quickSortMedianaDe3_piorCaso.csv";
        String SaidaMelhorCasoQuickMedianaDe3 = "src\\ArquivosCSVOrdenados\\passwords_length_quickSortMedianaDe3_melhorCaso.csv";

        try {
            // Chamada para o método de ordenação Quick Sort mediana de 3
            long Inicio = System.nanoTime();
            Quick3MedioCaso.QuickSort3CSVLength(EntradaCSV, SaidaMedioCasoQuickMedianaDe3);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoQuickMedianaDe3);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Quick Sort mediana de 3
            long Inicio = System.nanoTime();
            Quick3PiorCaso.QuickSort3CSVLength(EntradaCSV, SaidaPiorCasoQuickMedianaDe3);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3PiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoQuickMedianaDe3);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        try {
            // Chamada para o método de ordenação Quick Sort mediana de 3
            long Inicio = System.nanoTime();
            Quick3MelhorCaso.QuickSort3CSVLength(EntradaCSV, SaidaMelhorCasoQuickMedianaDe3);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoQuickMedianaDe3);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        System.out.println("Iniciando ordenação por SelectionSort...");
         ///Selection Sort
         String SaidaMedioCasoSelection = "src\\ArquivosCSVOrdenados\\passwords_length_selectionSort_medioCaso.csv";
         try {
            // Chamada para o método de ordenação Selection Sort
            long Inicio = System.nanoTime();
            SelectionMedioCaso.SelectionCSVLength(EntradaCSV, SaidaMedioCasoSelection);
            long Fim = System.nanoTime();
            TempoTotalSelectionMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoSelection);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaPiorCasoSelection = "src\\ArquivosCSVOrdenados\\passwords_length_selectionSort_piorCaso.csv";
        try {
            // Chamada para o método de ordenação Selection Sort
            long Inicio = System.nanoTime();
            SelectionPiorCaso.SelectionCSVLength(EntradaCSV, SaidaPiorCasoSelection);
            long Fim = System.nanoTime();
            TempoTotalSelectionPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoSelection);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaMelhorCasoSelection = "src\\ArquivosCSVOrdenados\\passwords_length_selectionSort_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Selection Sort
            long Inicio = System.nanoTime();
            SelectionMelhorCaso.SelectionCSVLength(EntradaCSV, SaidaMelhorCasoSelection);
            long Fim = System.nanoTime();
            TempoTotalSelectionMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoSelection);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        //quick sort
        String SaidaMedioCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_length_quickSort_medioCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            QuickMedioCaso.quickSortCSVLength(EntradaCSV, SaidaMedioCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaPiorCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_length_quickSort_piorCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            QuickPiorCaso.quickSortCSVLength(EntradaCSV, SaidaPiorCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaMelhorCasoQuick = "src\\ArquivosCSVOrdenados\\passwords_length_quickSort_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Quick Sort
            long Inicio = System.nanoTime();
            QuickMelhorCaso.quickSortCSVLength(EntradaCSV, SaidaMelhorCasoQuick);
            long Fim = System.nanoTime();
            TempoTotalQuickMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoQuick);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        //Heap Sort
        String SaidaMedioCasoHeapSort = "src\\ArquivosCSVOrdenados\\passwords_length_heapSort_medioCaso.csv";
        try {
            // Chamada para o método de ordenação Heap Sort
            long Inicio = System.nanoTime();
            HeapMedioCaso.heapSortCSVLength(EntradaCSV, SaidaMedioCasoHeapSort);
            long Fim = System.nanoTime();
            TempoTotalHeapSortMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoHeapSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaPiorCasoHeapSort = "src\\ArquivosCSVOrdenados\\passwords_length_heapSort_piorCaso.csv";
        try {
            // Chamada para o método de ordenação Heap Sort
            long Inicio = System.nanoTime();
            HeapPiorCaso.heapSortCSVLength(EntradaCSV, SaidaPiorCasoHeapSort);
            long Fim = System.nanoTime();
            TempoTotalHeapSortPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoHeapSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaMelhorCasoHeapSort = "src\\ArquivosCSVOrdenados\\passwords_length_heapSort_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Heap Sort
            long Inicio = System.nanoTime();
            HeapMelhorCaso.heapSortCSVLength(EntradaCSV, SaidaMelhorCasoHeapSort);
            long Fim = System.nanoTime();
            TempoTotalHeapSortMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoHeapSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        //Insection Sort
        String SaidaMedioCasoInsectionSort = "src\\ArquivosCSVOrdenados\\passwords_length_insectionSort_medioCaso.csv";
        try {
            // Chamada para o método de ordenação Insection Sort
            long Inicio = System.nanoTime();
            InsectionMedioCaso.insertionSortCSVLength(EntradaCSV, SaidaMedioCasoInsectionSort);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMedioCasoInsectionSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaPiorCasoInsectionSort = "src\\ArquivosCSVOrdenados\\passwords_length_insectionSort_piorCaso.csv";
        try {
            // Chamada para o método de ordenação Insection Sort
            long Inicio = System.nanoTime();
            InsectionPiorCaso.insertionSortCSVLength(EntradaCSV, SaidaPiorCasoInsectionSort);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaPiorCasoInsectionSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        String SaidaMelhorCasoInsectionSort = "src\\ArquivosCSVOrdenados\\passwords_length_insectionSort_melhorCaso.csv";
        try {
            // Chamada para o método de ordenação Insection Sort
            long Inicio = System.nanoTime();
            InsectionMelhorCaso.insertionSortCSVLength(EntradaCSV, SaidaMelhorCasoInsectionSort);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMelhorCasoInsectionSort);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        
    }

    public static void TempoDeExecucao() {
        System.out.println("Tempo de execução de cada algoritmo quando ordenados pela coluna Length: \n" +
            "Counting Sort Medio Caso: " + TempoTotalCountingMedioCaso + " ns\n" +
            "Counting Sort Pior Caso: " + TempoTotalCountingPiorCaso + " ns\n" +
            "Counting Sort Melhor Caso: " + TempoTotalCountingMelhorCaso + " ns\n" +
            "Merge Sort Melhor Caso: " + TempoTotalMergeMelhorCaso + " ns\n" +
            "Merge Sort Pior Caso: " + TempoTotalMergePiorCaso + " ns\n" +
            "Merge Sort Medio Caso: " + TempoTotalMergeMedioCaso + " ns\n"+
            "Quick Sort Melhor Caso: " + TempoTotalQuickMelhorCaso + " ns\n" +
            "Quick Sort Pior Caso: " + TempoTotalQuickPiorCaso + " ns\n" +
            "Quick Sort Medio Caso: " + TempoTotalQuickMedioCaso + " ns\n" +
            "Quick Sort Mediana de 3 Melhor Caso: " + TempoTotalQuickMedianaDe3MelhorCaso + " ns\n" +
            "Quick Sort Mediana de 3 Pior Caso: " + TempoTotalQuickMedianaDe3PiorCaso + " ns\n" +
            "Quick Sort Mediana de 3 Medio Caso: " + TempoTotalQuickMedianaDe3MedioCaso + " ns\n" +
            "Selection Sort Melhor Caso: " + TempoTotalSelectionMelhorCaso + " ns\n" +
            "Selection Sort Pior Caso: " + TempoTotalSelectionPiorCaso + " ns\n" +
            "Selection Sort Medio Caso: " + TempoTotalSelectionMedioCaso + " ns\n" +
            "Heap Sort Melhor Caso: " + TempoTotalHeapSortMelhorCaso + " ns\n" +
            "Heap Sort Pior Caso: " + TempoTotalHeapSortPiorCaso + " ns\n" +
            "Heap Sort Medio Caso: " + TempoTotalHeapSortMedioCaso + " ns\n" +
            "Insection Sort Melhor Caso: " + TempoTotalInsectionSortMelhorCaso + " ns\n" +
            "Insection Sort Pior Caso: " + TempoTotalInsectionSortPiorCaso + " ns\n" +
            "Insection Sort Medio Caso: " + TempoTotalInsectionSortMedioCaso + " ns\n" +
            "----------------------------------------\n");
    }
}