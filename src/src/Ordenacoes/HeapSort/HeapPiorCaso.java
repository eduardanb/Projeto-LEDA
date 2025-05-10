package Ordenacoes.HeapSort;

import java.io.*;
import java.util.Arrays;

public class HeapPiorCaso {

    public static void heapSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false);
    }

    public static void heapSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 3, true);
    }

    public static void heapSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3);
    }

    private static int countLines(String inputFilePath) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            br.readLine(); // pula o cabeçalho
            while (br.readLine() != null) count++;
        }
        return count;
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        int rowCount = countLines(inputFilePath);
        String[] rows = new String[rowCount];
        long[] values = new long[rowCount];
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            int index = 0;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows[index] = line;
                try {
                    String[] parts = line.split(",");
                    values[index] = Long.parseLong(parts[columnIndex]);
                } catch (Exception e) {
                    values[index] = 0;
                }
                index++;
            }
        }

        prepareWorstCase(values, rows, ascending);
        heapSort(values, rows, ascending);
        writeCSV(outputFilePath, header, rows);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        int rowCount = countLines(inputFilePath);
        String[] rows = new String[rowCount];
        long[] values = new long[rowCount];
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            int index = 0;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows[index] = line;
                try {
                    String[] parts = line.split(",");
                    String data = parts[columnIndex];
                    String[] dataParts = data.split("/");
                    values[index] = Long.parseLong(dataParts[1]);
                } catch (Exception e) {
                    values[index] = 0;
                }
                index++;
            }
        }

        prepareWorstCase(values, rows, true);
        heapSort(values, rows, true);
        writeCSV(outputFilePath, header, rows);
    }

    private static void prepareWorstCase(long[] values, String[] rows, boolean ascending) {
        Arrays.sort(values);
        if (ascending) {
            for (int i = 0; i < values.length / 2; i++) {
                long temp = values[i];
                values[i] = values[values.length - 1 - i];
                values[values.length - 1 - i] = temp;
            }
        }

        // Neste ponto já estamos fazendo HeapSort com os valores ordenados,
        // a reorganização das linhas será tratada pelo heapSort
    }

    private static void heapSort(long[] values, String[] rows, boolean ascending) {
        int n = values.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(values, rows, n, i, ascending);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(values, rows, 0, i);
            heapify(values, rows, i, 0, ascending);
        }
    }

    private static void heapify(long[] values, String[] rows, int n, int i, boolean ascending) {
        int largestOrSmallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(values[left], values[largestOrSmallest], ascending)) {
            largestOrSmallest = left;
        }

        if (right < n && compare(values[right], values[largestOrSmallest], ascending)) {
            largestOrSmallest = right;
        }

        if (largestOrSmallest != i) {
            swap(values, rows, i, largestOrSmallest);
            heapify(values, rows, n, largestOrSmallest, ascending);
        }
    }

    private static boolean compare(long a, long b, boolean ascending) {
        return ascending ? a < b : a > b;
    }

    private static void swap(long[] values, String[] rows, int i, int j) {
        long tmpVal = values[i];
        values[i] = values[j];
        values[j] = tmpVal;

        String tmpRow = rows[i];
        rows[i] = rows[j];
        rows[j] = tmpRow;
    }

    private static void writeCSV(String outputFilePath, String[] header, String[] rows) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String row : rows) {
                bw.write(row);
                bw.newLine();
            }
        }
    }
}
