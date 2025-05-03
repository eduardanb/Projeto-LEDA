package Ordenacoes;

import Ordenacoes.CountingSort.*;
import Ordenacoes.MergeSort.*;
import java.io.*;

public class OrdenacaoPorMesCrescente {
    static long TempoTotalCountingMelhorCaso = 0;
    static long TempoTotalCountingMedioCaso = 0;
    static long TempoTotalCountingPiorCaso = 0;
    static long TempoTotalMergeMelhorCaso = 0;
    static long TempoTotalMergeMedioCaso = 0;
    static long TempoTotalMergePiorCaso = 0;

    public static void Ordenacao(String[] args) {
        // Caminhos dos arquivos CSV
        String EntradaCSV = "src\\ArquivosCSV\\passwords_formated_data.csv"; // Substitua pelo caminho do arquivo de entrada
        String SaidaCountingMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_mesCrescente_melhorCaso.csv";
        String SaidaCountingMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_mesCrescente_medioCaso.csv";
        String SaidaCountingPiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_countingSort_mesCrescente_piorCaso.csv";
        String SaidaMergeMelhorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_melhorCaso.csv";
        String SaidaMergeMedioCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_medioCaso.csv";
        String SaidaMergePiorCaso = "src\\ArquivosCSVOrdenados\\passwords_data_mergeSort_mesCrescente_piorCaso.csv";

        // Counting Sort - Melhor Caso
        try {
            long Inicio = System.nanoTime();
            CountingMelhorCaso.countingSortCSVDataMes(EntradaCSV, SaidaCountingMelhorCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingMelhorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingMelhorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Melhor Caso): " + e.getMessage());
        }

        // Counting Sort - Médio Caso
        try {
            long Inicio = System.nanoTime();
            CountingMedioCaso.countingSortCSVData(EntradaCSV, SaidaCountingMedioCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingMedioCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingMedioCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Médio Caso): " + e.getMessage());
        }

        // Counting Sort - Pior Caso
        try {
            long Inicio = System.nanoTime();
            CountingPiorCaso.countingSortCSVData(EntradaCSV, SaidaCountingPiorCaso);
            long Fim = System.nanoTime();
            TempoTotalCountingPiorCaso = Fim - Inicio;
            System.out.println("Arquivo ordenado com sucesso e salvo em: " + SaidaCountingPiorCaso);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo com Counting Sort (Pior Caso): " + e.getMessage());
        }

        // Merge Sort - Melhor Caso
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
        }
    }

    public static void TempoDeExecucao() {
        System.out.println("Tempo de execução de cada algoritmo quando ordenados pela coluna mês: \n" +
            "Counting Sort Melhor Caso: " + TempoTotalCountingMelhorCaso + " ns\n" +
            "Counting Sort Médio Caso: " + TempoTotalCountingMedioCaso + " ns\n" +
            "Counting Sort Pior Caso: " + TempoTotalCountingPiorCaso + " ns\n" +
            "Merge Sort Melhor Caso: " + TempoTotalMergeMelhorCaso + " ns\n" +
            "Merge Sort Médio Caso: " + TempoTotalMergeMedioCaso + " ns\n" +
            "Merge Sort Pior Caso: " + TempoTotalMergePiorCaso + " ns\n");
    }
}
