package Ordenacoes.QuickSort;

import java.io.*;
import java.util.*;

public class QuickMelhorCaso {

    // Método para ordenar um arquivo CSV no melhor caso usando QuickSort
    public static void quickSortCSVBestCase(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Ler o arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
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

        // Extrair a coluna especificada e convertê-la para um array
        int n = rows.size();
        String[] values = new String[n];
        for (int i = 0; i < n; i++) {
            values[i] = rows.get(i)[columnIndex]; // Obtém o valor da coluna especificada
        }

        // Preparar o melhor caso (organizar os dados para que o pivô seja sempre o elemento central)
        prepareBestCase(values, rows, columnIndex);

        // Aplicar QuickSort na coluna especificada
        quickSort(values, rows, 0, n - 1);

        // Escrever o arquivo CSV ordenado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    // Método para preparar o melhor caso
    private static void prepareBestCase(String[] values, List<String[]> rows, int columnIndex) {
        // Ordena os valores em ordem crescente
        Arrays.sort(values);

        // Reorganiza as linhas do CSV com base nos valores ordenados
        rows.sort(Comparator.comparing(row -> row[columnIndex]));
    }

    // Método recursivo para aplicar QuickSort
    private static void quickSort(String[] values, List<String[]> rows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, rows, low, high);
            quickSort(values, rows, low, pivotIndex - 1);
            quickSort(values, rows, pivotIndex + 1, high);
        }
    }

    // Método para particionar o array e retornar o índice do pivô
    private static int partition(String[] values, List<String[]> rows, int low, int high) {
        String pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[j].compareTo(pivot) <= 0) { // Ordenação crescente
                i++;
                swap(values, rows, i, j);
            }
        }

        swap(values, rows, i + 1, high);
        return i + 1;
    }

    // Método para trocar elementos no array e na lista de linhas
    private static void swap(String[] values, List<String[]> rows, int i, int j) {
        String tempValue = values[i];
        values[i] = values[j];
        values[j] = tempValue;

        String[] tempRow = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, tempRow);
    }

    // Método principal para executar as ordenações
    public static void main(String[] args) {
        String inputFilePath = "src/ArquivosCSV/passwords_formated_data.csv";
        String outputFilePath = "src/ArquivosCSV/passwords_length_quickSort_melhorCaso.csv";

        try {
            // Ordenar pelo campo "length" (coluna 2)
            quickSortCSVBestCase(inputFilePath, outputFilePath, 2);
        } catch (IOException e) {
            System.err.println("Erro ao processar o QuickSort: " + e.getMessage());
        }
    }
}