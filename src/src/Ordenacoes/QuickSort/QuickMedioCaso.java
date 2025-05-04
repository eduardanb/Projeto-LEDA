package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuickMedioCaso {

    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 2, QuickMedioCaso::parseLongFromColumn);
    }

    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickMedioCaso::parseEpochDayFromDate);
    }

    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickMedioCaso::parseMonthFromDate);
    }

    // Método genérico
    private static void processCSVByLong(String inputFilePath, String outputFilePath, int columnIndex,
                                         ValueExtractor extractor) throws IOException {
        CSVContent csv = readCSV(inputFilePath);
        List<String[]> rows = csv.rows;
        String[] header = csv.header;

        int n = rows.size();
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                values[i] = extractor.extract(rows.get(i)[columnIndex], i + 2); // +2 por causa do header
            } catch (Exception e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + e.getMessage());
                values[i] = 0;
            }
        }

        quickSortWithIndices(values, indices, 0, n - 1);

        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        writeCSV(outputFilePath, header, sortedRows);
    }

    // Interface funcional
    @FunctionalInterface
    interface ValueExtractor {
        long extract(String data, int rowIndex) throws Exception;
    }

    private static long parseLongFromColumn(String value, int rowIndex) throws NumberFormatException {
        return Long.parseLong(value);
    }

    private static long parseEpochDayFromDate(String dateStr, int rowIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return date.toEpochDay();
    }

    private static long parseMonthFromDate(String dateStr, int rowIndex) {
        if (!dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Formato de data inválido");
        }
        String[] parts = dateStr.split("/");
        return Long.parseLong(parts[1]); // mês
    }

    private static void quickSortWithIndices(long[] values, Integer[] indices, int low, int high) {
        if (low >= high || low < 0 || high >= indices.length) {
            return;
        }

        int pivotIndex = partition(values, indices, low, high);

        quickSortWithIndices(values, indices, low, pivotIndex - 1);
        quickSortWithIndices(values, indices, pivotIndex + 1, high);
    }

    private static int partition(long[] values, Integer[] indices, int low, int high) {
        long pivot = values[indices[high]];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (values[indices[j]] <= pivot) {
                i++;
                swap(indices, i, j);
            }
        }
        swap(indices, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Leitura do CSV
    private static CSVContent readCSV(String inputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                rows.add(parts);
            }
        }

        return new CSVContent(header, rows);
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

    // Classe auxiliar
    private static class CSVContent {
        String[] header;
        List<String[]> rows;

        CSVContent(String[] header, List<String[]> rows) {
            this.header = header;
            this.rows = rows;
        }
    }
}
