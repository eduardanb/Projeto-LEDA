package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class QuickPiorCaso {

    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 2, QuickPiorCaso::parseLongFromColumn);
    }

    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickPiorCaso::parseEpochDayFromDate);
    }

    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickPiorCaso::parseMonthFromDate);
    }

    // Interface funcional para extrair valores
    @FunctionalInterface
    interface ValueExtractor {
        long extract(String data, int rowIndex) throws Exception;
    }

    // Método genérico para processar CSV com uma função extratora
    private static void processCSVByLong(String inputFilePath, String outputFilePath, int columnIndex,
                                         ValueExtractor extractor) throws IOException {

        List<String[]> rows = new ArrayList<>();
        String[] header = readCSV(inputFilePath, rows);

        long[] values = new long[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            try {
                values[i] = extractor.extract(rows.get(i)[columnIndex], i + 2); // +2 por conta do cabeçalho
            } catch (Exception e) {
                System.err.println("Erro ao processar linha " + (i + 2) + ": " + e.getMessage());
                values[i] = 0;
                rows.get(i)[columnIndex] = "0";
            }
        }

        prepareWorstCase(values, rows);
        quickSort(values, rows, 0, values.length - 1);
        writeCSV(outputFilePath, header, rows);
    }

    // Preparação do pior caso (valores em ordem crescente para pivot final)
    private static void prepareWorstCase(long[] values, List<String[]> rows) {
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

    // Funções extratoras
    private static long parseLongFromColumn(String value, int rowIndex) {
        return Long.parseLong(value);
    }

    private static long parseEpochDayFromDate(String dateStr, int rowIndex) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date.toEpochDay();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + dateStr);
        }
    }

    private static long parseMonthFromDate(String dateStr, int rowIndex) {
        try {
            String[] partes = dateStr.split("/");
            return Long.parseLong(partes[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao extrair mês da data: " + dateStr);
        }
    }

    // QuickSort clássico com valores e linhas acopladas
    private static void quickSort(long[] values, List<String[]> rows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, rows, low, high);
            quickSort(values, rows, low, pivotIndex - 1);
            quickSort(values, rows, pivotIndex + 1, high);
        }
    }

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

    private static void swap(long[] values, List<String[]> rows, int i, int j) {
        long tempVal = values[i];
        values[i] = values[j];
        values[j] = tempVal;

        String[] tempRow = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, tempRow);
    }

    // Leitura do CSV
    private static String[] readCSV(String inputFilePath, List<String[]> rows) throws IOException {
        String[] header = null;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirst = true;

            while ((line = br.readLine()) != null) {
                if (isFirst) {
                    header = line.split(",");
                    isFirst = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }
        return header;
    }

    // Escrita do CSV
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
