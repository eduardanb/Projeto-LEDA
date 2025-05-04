package Ordenacoes.HeapSort;

import java.io.*;
import java.util.*;

public class HeapMelhorCaso {

    public static void heapSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false); // Coluna 2 = length, ordem decrescente
    }

    public static void heapSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 3, true); // Coluna 3 = data, ordem crescente
    }

    public static void heapSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3); // Coluna 3 = mês extraído da data, ordem crescente
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }

        int n = rows.size();
        long[] values = new long[n];

        // Captura os valores da coluna indicada
        for (int i = 0; i < n; i++) {
            try {
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido encontrado na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
            }
        }

        // Preparar o melhor caso (ordem crescente ou decrescente)
        prepareBestCase(values, rows, ascending);

        // Ordena os valores usando HeapSort
        heapSort(values, rows, ascending);

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, header, rows);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }

        int n = rows.size();
        long[] values = new long[n];

        // Extrai o mês da coluna de data
        for (int i = 0; i < n; i++) {
            try {
                String dataStr = rows.get(i)[columnIndex];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]); // Extrai o mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
            }
        }

        // Preparar o melhor caso (ordem crescente)
        prepareBestCase(values, rows, true);

        // Ordena os valores usando HeapSort
        heapSort(values, rows, true);

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, header, rows);
    }

    private static void prepareBestCase(long[] values, List<String[]> rows, boolean ascending) {
        // Ordena os valores em ordem crescente ou decrescente
        Arrays.sort(values);
        if (!ascending) {
            for (int i = 0; i < values.length / 2; i++) {
                long temp = values[i];
                values[i] = values[values.length - 1 - i];
                values[values.length - 1 - i] = temp;
            }
        }

        // Reorganiza as linhas do CSV com base nos valores ordenados
        rows.sort((row1, row2) -> {
            long value1 = Long.parseLong(row1[2]);
            long value2 = Long.parseLong(row2[2]);
            return ascending ? Long.compare(value1, value2) : Long.compare(value2, value1);
        });
    }

    private static void heapSort(long[] values, List<String[]> rows, boolean ascending) {
        int n = values.length;

        // Constrói o heap (reorganiza o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(values, rows, n, i, ascending);
        }

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual para o final
            swap(values, rows, 0, i);

            // Chama heapify no heap reduzido
            heapify(values, rows, i, 0, ascending);
        }
    }

    private static void heapify(long[] values, List<String[]> rows, int n, int i, boolean ascending) {
        int largestOrSmallest = i; // Inicializa o maior ou menor como raiz
        int left = 2 * i + 1; // Filho esquerdo
        int right = 2 * i + 2; // Filho direito

        // Verifica se o filho esquerdo é maior/menor que a raiz
        if (left < n && compare(values[left], values[largestOrSmallest], ascending)) {
            largestOrSmallest = left;
        }

        // Verifica se o filho direito é maior/menor que o maior/menor atual
        if (right < n && compare(values[right], values[largestOrSmallest], ascending)) {
            largestOrSmallest = right;
        }

        // Se o maior/menor não for a raiz
        if (largestOrSmallest != i) {
            swap(values, rows, i, largestOrSmallest);

            // Recursivamente aplica o heapify na subárvore afetada
            heapify(values, rows, n, largestOrSmallest, ascending);
        }
    }

    private static boolean compare(long a, long b, boolean ascending) {
        return ascending ? a < b : a > b;
    }

    private static void swap(long[] values, List<String[]> rows, int i, int j) {
        long tempValue = values[i];
        values[i] = values[j];
        values[j] = tempValue;

        String[] tempRow = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, tempRow);
    }

    private static void writeCSV(String outputFilePath, String[] header, List<String[]> rows) throws IOException {
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
}