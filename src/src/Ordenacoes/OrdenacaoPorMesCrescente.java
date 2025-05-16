package Ordenacoes;

import Ordenacoes.CountingSort.*;
import Ordenacoes.HeapSort.HeapMedioCaso;
import Ordenacoes.HeapSort.HeapMelhorCaso;
import Ordenacoes.HeapSort.HeapPiorCaso;
import Ordenacoes.InsectionSort.InsectionMedioCaso;
import Ordenacoes.InsectionSort.InsectionMelhorCaso;
import Ordenacoes.InsectionSort.InsectionPiorCaso;
import Ordenacoes.MergeSort.*;
import Ordenacoes.QuickSort.QuickMedioCaso;
import Ordenacoes.QuickSort.QuickMelhorCaso;
import Ordenacoes.QuickSort.QuickPiorCaso;
import Ordenacoes.QuickSortMediana3.Quick3MedioCaso;
import Ordenacoes.QuickSortMediana3.Quick3MelhorCaso;
import Ordenacoes.QuickSortMediana3.Quick3PiorCaso;
import Ordenacoes.SelectionSort.SelectionMedioCaso;
import Ordenacoes.SelectionSort.SelectionMelhorCaso;
import Ordenacoes.SelectionSort.SelectionPiorCaso;

import java.io.*;

public class OrdenacaoPorMesCrescente {
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
        // Caminhos dos arquivos CSV
        String EntradaCSV = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Substitua pelo caminho do arquivo de entrada
        String SaidaCountingMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_melhorCaso.csv";
        String SaidaCountingMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_medioCaso.csv";
        String SaidaCountingPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_piorCaso.csv";
        String SaidaMergeMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_mergeSort_melhorCaso.csv";
        String SaidaMergeMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_mergeSort_medioCaso.csv";
        String SaidaMergePiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_mergeSort_piorCaso.csv";

        // Counting Sort - Melhor Caso
        try {
            long Inicio = System.nanoTime();
            CountingMelhorCaso.CountingSortCSVMes(EntradaCSV, SaidaCountingMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Melhor Caso): " + e.getMessage());
        }

        // Counting Sort - Médio Caso
        try {
            long Inicio = System.nanoTime();
            CountingMedioCaso.CountingSortCSVMes(EntradaCSV, SaidaCountingMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Médio Caso): " + e.getMessage());
        }

        // Counting Sort - Pior Caso
        try {
            long Inicio = System.nanoTime();
            CountingPiorCaso.CountingSortCSVMes(EntradaCSV, SaidaCountingPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Pior Caso): " + e.getMessage());
        }

        //Merge Sort - Melhor Caso
        try {
            long Inicio = System.nanoTime();
            MergeMelhorCaso.mergeSortCSVMes(EntradaCSV, SaidaMergeMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergeMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Melhor Caso): " + e.getMessage());
        }

        // Merge Sort - Médio Caso
        try {
            long Inicio = System.nanoTime();
            MergeMedioCaso.mergeSortCSVMes(EntradaCSV, SaidaMergeMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalMergeMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergeMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Médio Caso): " + e.getMessage());
        }

        // Merge Sort - Pior Caso
        try {
            long Inicio = System.nanoTime();
            MergePiorCaso.mergeSortCSVMes(EntradaCSV, SaidaMergePiorCaso);
            long Fim = System.nanoTime();
            TempoTotalMergePiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergePiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Pior Caso): " + e.getMessage());
        }

        // Quick Sort mediana de 3 - Melhor Caso
        String SaidaQuickMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSortmedianade3_melhorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            Quick3MelhorCaso.QuickSort3CSVMes(EntradaCSV, SaidaQuickMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Melhor Caso): " + e.getMessage());
        }
        // Quick Sort mediana de 3 - Médio Caso
        String SaidaQuickMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSortmedianade3_medioCaso.csv";
        try {
            long Inicio = System.nanoTime();
            Quick3MedioCaso.QuickSort3CSVMes(EntradaCSV, SaidaQuickMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3MedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Médio Caso): " + e.getMessage());
        }
        // Quick Sort mediana de 3 - Pior Caso
        String SaidaQuickPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSortmedianade3_piorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            Quick3PiorCaso.QuickSort3CSVMes(EntradaCSV, SaidaQuickPiorCaso,2);
            long Fim = System.nanoTime();
            TempoTotalQuickMedianaDe3PiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Pior Caso): " + e.getMessage());
        }
        
         
         // Selection Sort - Melhor Caso
         String SaidaSelectionMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_selectionSort_melhorCaso.csv";
         try {
            long Inicio = System.nanoTime();
            SelectionMelhorCaso.SelectionCSVMes(EntradaCSV, SaidaSelectionMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalSelectionMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaSelectionMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Selection Sort (Melhor Caso): " + e.getMessage());
        }
        // Selection Sort - Médio Caso
        String SaidaSelectionMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_selectionSort_medioCaso.csv";
        try {
            long Inicio = System.nanoTime();
            SelectionMedioCaso.SelectionCSVMes(EntradaCSV, SaidaSelectionMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalSelectionMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaSelectionMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Selection Sort (Médio Caso): " + e.getMessage());
        }
        // Selection Sort - Pior Caso
        String SaidaSelectionPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_selectionSort_piorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            SelectionPiorCaso.SelectionCSVMes(EntradaCSV, SaidaSelectionPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalSelectionPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaSelectionPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Selection Sort (Pior Caso): " + e.getMessage());
        }
        // Quick Sort - Melhor Caso
        String SaidaQuickSortMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSort_melhorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            QuickMelhorCaso.quickSortCSVMes(EntradaCSV, SaidaQuickSortMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalQuickMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickSortMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Melhor Caso): " + e.getMessage());
        }
        // Quick Sort - Médio Caso
        String SaidaQuickSortMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSort_medioCaso.csv";
        try {
            long Inicio = System.nanoTime();
            QuickMedioCaso.quickSortCSVMes(EntradaCSV, SaidaQuickSortMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalQuickMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickSortMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Médio Caso): " + e.getMessage());
        }
        // Quick Sort - Pior Caso
        String SaidaQuickSortPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_quickSort_piorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            QuickPiorCaso.quickSortCSVMes(EntradaCSV, SaidaQuickSortPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalQuickPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaQuickSortPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Quick Sort (Pior Caso): " + e.getMessage());
        }
        // Heap Sort - Melhor Caso
        String SaidaHeapSortMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_heapSort_melhorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            HeapMelhorCaso.heapSortCSVMes(EntradaCSV, SaidaHeapSortMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalHeapSortMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaHeapSortMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Heap Sort (Melhor Caso): " + e.getMessage());
        }
        // Heap Sort - Médio Caso
        String SaidaHeapSortMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_heapSort_medioCaso.csv";
        try {
            long Inicio = System.nanoTime();
            HeapMedioCaso.heapSortCSVMes(EntradaCSV, SaidaHeapSortMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalHeapSortMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaHeapSortMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Heap Sort (Médio Caso): " + e.getMessage());
        }
        // Heap Sort - Pior Caso
        String SaidaHeapSortPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_heapSort_piorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            HeapPiorCaso.heapSortCSVMes(EntradaCSV, SaidaHeapSortPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalHeapSortPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaHeapSortPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Heap Sort (Pior Caso): " + e.getMessage());
        }
        // Insection Sort - Melhor Caso
        String SaidaInsectionSortMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_insectionSort_melhorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            InsectionMelhorCaso.insertionSortCSVMes(EntradaCSV, SaidaInsectionSortMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaInsectionSortMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Insection Sort (Melhor Caso): " + e.getMessage());
        }
        // Insection Sort - Médio Caso
        String SaidaInsectionSortMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_insectionSort_medioCaso.csv";
        try {
            long Inicio = System.nanoTime();
            InsectionMedioCaso.insertionSortCSVMes(EntradaCSV, SaidaInsectionSortMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaInsectionSortMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Insection Sort (Médio Caso): " + e.getMessage());
        }
        // Insection Sort - Pior Caso
        String SaidaInsectionSortPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_insectionSort_piorCaso.csv";
        try {
            long Inicio = System.nanoTime();
            InsectionPiorCaso.insertionSortCSVMes(EntradaCSV, SaidaInsectionSortPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalInsectionSortPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaInsectionSortPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Insection Sort (Pior Caso): " + e.getMessage());
        }
        
        
    }
    
    public static void TempoDeExecucao() {
        System.out.println("Tempo de execução de cada algoritmo quando ordenados pelao mês: \n" +
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
