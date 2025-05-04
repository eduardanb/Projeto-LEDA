package Ordenacoes;

import Ordenacoes.CountingSort.*;
import Ordenacoes.MergeSort.*;
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

    public static void Ordenacao(String[] args) {
        // Caminhos dos arquivos CSV
        String EntradaCSV = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Substitua pelo caminho do arquivo de entrada
        String SaidaCountingMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_melhorCaso.csv";
        String SaidaCountingMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_medioCaso.csv";
        String SaidaCountingPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_month_countingSort_piorCaso.csv";
        String SaidaMergeMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_melhorCaso.csv";
        String SaidaMergeMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_medioCaso.csv";
        String SaidaMergePiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_piorCaso.csv";

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

        /*  Merge Sort - Melhor Caso
        try {
            long Inicio = System.nanoTime();
            MergeMelhorCaso.mergeSortCSVDataMes(EntradaCSV, SaidaMergeMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalMergeMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergeMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Melhor Caso): " + e.getMessage());
        }

        // Merge Sort - Médio Caso
        try {
            long Inicio = System.nanoTime();
            MergeMedioCaso.mergeSortCSVDataMes(EntradaCSV, SaidaMergeMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalMergeMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergeMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Médio Caso): " + e.getMessage());
        }

        // Merge Sort - Pior Caso
        try {
            long Inicio = System.nanoTime();
            MergePiorCaso.mergeSortCSVDataMes(EntradaCSV, SaidaMergePiorCaso);
            long Fim = System.nanoTime();
            TempoTotalMergePiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaMergePiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Merge Sort (Pior Caso): " + e.getMessage());
        }*/

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
            "Quick Sort Mediana de 3 Medio Caso: " + TempoTotalQuickMedianaDe3MedioCaso + " ns\n" +
            "Selection Sort Melhor Caso: " + TempoTotalSelectionMelhorCaso + " ns\n" +
            "Selection Sort Pior Caso: " + TempoTotalSelectionPiorCaso + " ns\n" +
            "Selection Sort Medio Caso: " + TempoTotalSelectionMedioCaso + " ns\n");
    }
}
