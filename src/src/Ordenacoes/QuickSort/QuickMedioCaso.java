package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;

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

    private static void processCSVByLong(String inputFilePath, String outputFilePath, int columnIndex,ValueExtractor extractor) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] campos = dataLines[i].split(",");
            try {
                values[i] = extractor.extract(campos[columnIndex], i + 2);
            } catch (Exception e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + e.getMessage());
                values[i] = 0;
            }
        }

        quickSortWithIndices(values, indices, 0, n - 1);

        // Inverte apenas se for length (coluna 2)
        if (columnIndex == 2) {
            for (int i = 0; i < n / 2; i++) {
                int temp = indices[i];
                indices[i] = indices[n - 1 - i];
                indices[n - 1 - i] = temp;
            }
        }

        String[] sortedDataLines = new String[n];
        for (int i = 0; i < n; i++) {
            sortedDataLines[i] = dataLines[indices[i]];
        }

        writeCSV(outputFilePath, header, sortedDataLines);
    }

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
        return Long.parseLong(parts[1]);
    }

    // Corrigido: nunca chama recursivamente se o particionamento não avançar
    private static void quickSortWithIndices(long[] valores, int[] indices, int left, int right) {
        while (left < right) {
            int pivotIndex = partition(valores, indices, left, right);
            // Recursão na menor partição para evitar stack overflow
            if (pivotIndex - left < right - pivotIndex) {
                quickSortWithIndices(valores, indices, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quickSortWithIndices(valores, indices, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
    }

    private static int partition(long[] values, int[] indices, int low, int high) {
        // Seleciona pivô aleatório e troca com o último
        int pivotRandomIndex = low + new Random().nextInt(high - low + 1);
        swap(indices, pivotRandomIndex, high);

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

    private static void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static String[] readCSV(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            return br.lines().toArray(String[]::new);
        }
    }

    private static void writeCSV(String outputFilePath, String header, String[] dataLines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(header);
            bw.newLine();
            for (String line : dataLines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}