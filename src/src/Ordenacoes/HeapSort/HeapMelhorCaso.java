package Ordenacoes.HeapSort;

import java.io.*;
import java.util.*;

public class HeapMelhorCaso {

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
                    values[index] = Long.parseLong(line.split(",")[columnIndex]);
                } catch (Exception e) {
                    values[index] = 0;
                }
                index++;
            }
        }

        prepareBestCase(values, rows, ascending);
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
                    String data = line.split(",")[columnIndex];
                    String[] partes = data.split("/");
                    values[index] = Long.parseLong(partes[1]);
                } catch (Exception e) {
                    values[index] = 0;
                }
                index++;
            }
        }

        prepareBestCase(values, rows, true);
        heapSort(values, rows, true);
        writeCSV(outputFilePath, header, rows);
    }

    private static void prepareBestCase(long[] values, String[] rows, boolean ascending) {
        Integer[] indices = new Integer[values.length];
        for (int i = 0; i < values.length; i++) indices[i] = i;

        Arrays.sort(indices, (i, j) -> ascending ?
                Long.compare(values[i], values[j]) :
                Long.compare(values[j], values[i]));

        long[] sortedValues = new long[values.length];
        String[] sortedRows = new String[rows.length];

        for (int i = 0; i < values.length; i++) {
            sortedValues[i] = values[indices[i]];
            sortedRows[i] = rows[indices[i]];
        }

        System.arraycopy(sortedValues, 0, values, 0, values.length);
        System.arraycopy(sortedRows, 0, rows, 0, rows.length);
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
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(values[left], values[target], ascending)) {
            target = left;
        }

        if (right < n && compare(values[right], values[target], ascending)) {
            target = right;
        }

        if (target != i) {
            swap(values, rows, i, target);
            heapify(values, rows, n, target, ascending);
        }
    }

    private static boolean compare(long a, long b, boolean ascending) {
        return ascending ? a < b : a > b;
    }

    private static void swap(long[] values, String[] rows, int i, int j) {
        long tempValue = values[i];
        values[i] = values[j];
        values[j] = tempValue;

        String tempRow = rows[i];
        rows[i] = rows[j];
        rows[j] = tempRow;
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
