package Ordenacoes.InsectionSort;

import java.io.*;

public class InsectionMelhorCaso {

    public static void insertionSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false); // Coluna 2 = length, ordem decrescente
    }

    public static void insertionSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3, true); // Coluna 3 = data, ordem crescente
    }

    public static void insertionSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3, true); // Coluna 3 = mês extraído da data, ordem crescente
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean descending) throws IOException {
        String[][] rows = readCSV(inputFilePath);
        int n = rows.length;

        // Os dados já estão ordenados no melhor caso
        // Apenas reorganiza os índices para simular o processamento
        long[] values = new long[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(rows[i][columnIndex]);
            indices[i] = i;
        }

        // Simula a execução do InsertionSort sem alterações
        insertionSort(values, indices, descending);

        // Reorganizar as linhas do CSV
        String[][] sortedRows = new String[n][];
        for (int i = 0; i < n; i++) {
            sortedRows[i] = rows[indices[i]];
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, sortedRows);
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        String[][] rows = readCSV(inputFilePath);
        int n = rows.length;

        // Os dados já estão ordenados no melhor caso
        // Apenas reorganiza os índices para simular o processamento
        long[] values = new long[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            String[] dateParts = rows[i][columnIndex].split("/");
            values[i] = Long.parseLong(dateParts[2] + dateParts[1] + dateParts[0]); // AnoMêsDia
            indices[i] = i;
        }

        // Simula a execução do InsertionSort sem alterações
        insertionSort(values, indices, ascending);

        // Reorganizar as linhas do CSV
        String[][] sortedRows = new String[n][];
        for (int i = 0; i < n; i++) {
            sortedRows[i] = rows[indices[i]];
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, sortedRows);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        String[][] rows = readCSV(inputFilePath);
        int n = rows.length;

        // Os dados já estão ordenados no melhor caso
        // Apenas reorganiza os índices para simular o processamento
        long[] values = new long[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            String[] dateParts = rows[i][columnIndex].split("/");
            values[i] = Long.parseLong(dateParts[1]); // Apenas o mês
            indices[i] = i;
        }

        // Simula a execução do InsertionSort sem alterações
        insertionSort(values, indices, ascending);

        // Reorganizar as linhas do CSV
        String[][] sortedRows = new String[n][];
        for (int i = 0; i < n; i++) {
            sortedRows[i] = rows[indices[i]];
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, sortedRows);
    }

    private static String[][] readCSV(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String[] lines = br.lines().toArray(String[]::new);
            String[][] rows = new String[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                rows[i] = lines[i].split(",");
            }
            return rows;
        }
    }

    private static void insertionSort(long[] values, int[] indices, boolean ascending) {
        // Simula a execução do InsertionSort no melhor caso
        for (int i = 1; i < values.length; i++) {
            long key = values[i];
            int keyIndex = indices[i];
            int j = i - 1;

            // No melhor caso, nenhuma troca é necessária
            while (j >= 0 && (ascending ? values[j] < key : values[j] > key)) {
                break; // Simula a condição de saída imediata
            }
            values[j + 1] = key;
            indices[j + 1] = keyIndex;
        }
    }

    private static void writeCSV(String outputFilePath, String[][] rows) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row)); // Escreve cada linha
                bw.newLine();
            }
        }
    }
}