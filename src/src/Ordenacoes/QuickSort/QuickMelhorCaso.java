package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class QuickMelhorCaso {

    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSVNumeric(inputFilePath, outputFilePath, 2); // Coluna 2 = length
    }

    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3); // Coluna 3 = data
    }

    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3); // Coluna 3 = data
    }

    // Processamento genérico de coluna numérica
    private static void processCSVNumeric(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = readCSV(inputFilePath, rows);

        long[] values = new long[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            try {
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
                rows.get(i)[columnIndex] = "0";
            }
        }

        prepareBestCase(values, rows);
        quickSort(values, rows, 0, values.length - 1);
        writeCSV(outputFilePath, header, rows);
    }

    // Processamento por data completa (dd/MM/yyyy)
    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = readCSV(inputFilePath, rows);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        long[] values = new long[rows.size()];

        for (int i = 0; i < rows.size(); i++) {
            try {
                String dataStr = rows.get(i)[columnIndex];
                LocalDate data = LocalDate.parse(dataStr, formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Data inválida na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = LocalDate.of(1, 1, 1).toEpochDay();
                rows.get(i)[columnIndex] = "01/01/0001";
            }
        }

        prepareBestCase(values, rows);
        quickSort(values, rows, 0, values.length - 1);
        writeCSV(outputFilePath, header, rows);
    }

    // Processamento apenas pelo mês da data
    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = readCSV(inputFilePath, rows);

        long[] values = new long[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            try {
                String dataStr = rows.get(i)[columnIndex];
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]);
            } catch (Exception e) {
                System.err.println("Erro na data da linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 1;
                rows.get(i)[columnIndex] = "01/01/0001";
            }
        }

        prepareBestCase(values, rows);
        quickSort(values, rows, 0, values.length - 1);
        writeCSV(outputFilePath, header, rows);
    }

    // Lê CSV e retorna o cabeçalho
    private static String[] readCSV(String inputFilePath, List<String[]> rows) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            String[] header = null;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
            return header;
        }
    }

    // Prepara o melhor caso (dados já ordenados)
    private static void prepareBestCase(long[] values, List<String[]> rows) {
        Integer[] indices = new Integer[values.length];
        for (int i = 0; i < values.length; i++) indices[i] = i;

        Arrays.sort(indices, Comparator.comparingLong(i -> values[i]));

        long[] sortedValues = new long[values.length];
        List<String[]> sortedRows = new ArrayList<>(values.length);

        for (int i = 0; i < values.length; i++) {
            sortedValues[i] = values[indices[i]];
            sortedRows.add(rows.get(indices[i]));
        }

        System.arraycopy(sortedValues, 0, values, 0, values.length);
        rows.clear();
        rows.addAll(sortedRows);
    }

    // QuickSort
    private static void quickSort(long[] values, List<String[]> rows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, rows, low, high);
            quickSort(values, rows, low, pivotIndex - 1);
            quickSort(values, rows, pivotIndex + 1, high);
        }
    }

    // Partição para QuickSort
    private static int partition(long[] values, List<String[]> rows, int low, int high) {
        long pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[j] <= pivot) {
                i++;
                swap(values, rows, i, j);
            }
        }

        swap(values, rows, i + 1, high);
        return i + 1;
    }

    // Troca elementos (valores + linhas)
    private static void swap(long[] values, List<String[]> rows, int i, int j) {
        long tempVal = values[i];
        values[i] = values[j];
        values[j] = tempVal;

        String[] tempRow = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, tempRow);
    }

    // Escrita do CSV final
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
