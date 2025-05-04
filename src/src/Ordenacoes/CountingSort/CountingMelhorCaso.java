package Ordenacoes.CountingSort;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CountingMelhorCaso {

    // Método para ordenar por comprimento no melhor caso
    public static void CountingSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // Coluna 2 = comprimento
    }

    // Método para ordenar por data no melhor caso
    public static void CountingSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
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
        Integer[] indices = new Integer[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[3]; // Coluna de data
                LocalDate data = LocalDate.parse(dataStr, formatter);
                values[i] = data.toEpochDay();
                if (values[i] > max) max = values[i];
            } catch (DateTimeParseException e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + rows.get(i)[3]);
                values[i] = 0; // Valor padrão para datas inválidas
            }
        }

        indices = countingSort(values, indices, max);

        writeCSV(outputFilePath, header, rows, indices);
    }

    // Método para ordenar por mês no melhor caso
    public static void CountingSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
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
        Integer[] indices = new Integer[n];

        long max = 12; // Máximo valor para o mês (1 a 12)
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[3]; // Coluna de data
                String[] partes = dataStr.split("/");
                if (partes.length < 2) throw new IllegalArgumentException("Formato de data inválido");
                values[i] = Long.parseLong(partes[1]); // Extrai o mês
            } catch (Exception e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + rows.get(i)[3]);
                values[i] = 0; // Valor padrão para meses inválidos
            }
        }

        indices = countingSort(values, indices, max);

        writeCSV(outputFilePath, header, rows, indices);
    }

    // Processa o CSV para uma coluna específica
    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
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
        Integer[] indices = new Integer[n];

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
                if (values[i] > max) max = values[i];
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0; // Valor padrão para valores inválidos
            }
        }

        indices = countingSort(values, indices, max);

        writeCSV(outputFilePath, header, rows, indices);
    }

    // Escreve o CSV ordenado
    private static void writeCSV(String outputFilePath, String[] header, List<String[]> rows, Integer[] indices) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (int index : indices) {
                bw.write(String.join(",", rows.get(index)));
                bw.newLine();
            }
        }
    }

    // Implementação do Counting Sort
    private static Integer[] countingSort(long[] arr, Integer[] indices, long max) {
        int n = arr.length;
        Integer[] output = new Integer[n]; // Índices ordenados

        // Verifica se o valor de max é válido
        if (max > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("O valor máximo é muito grande para o Counting Sort.");
        }

        int[] count = new int[(int) (max + 1)]; // Array de contagem

        // Inicializar o array de contagem
        Arrays.fill(count, 0);

        // Contar ocorrências
        for (int i = 0; i < n; i++) {
            count[(int) arr[i]]++;
        }

        // Ajustar o array de contagem
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Construir o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[--count[(int) arr[i]]] = indices[i];
        }

        return output;
    }
}
