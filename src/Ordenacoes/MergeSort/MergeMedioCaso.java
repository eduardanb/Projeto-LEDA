package Ordenacoes.MergeSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MergeMedioCaso {

    public static void mergeSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2);
    }

    public static void mergeSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3);
    }

    public static void mergeSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int capacity = 10000;
        String[] rows = new String[capacity];
        long[] values = new long[capacity];
        int rowCount = 0;

        String line;
        while ((line = br.readLine()) != null) {
            if (rowCount == capacity) {
                capacity *= 2;
                rows = java.util.Arrays.copyOf(rows, capacity);
                values = java.util.Arrays.copyOf(values, capacity);
            }

            rows[rowCount] = line;
            try {
                values[rowCount] = Long.parseLong(line.split(",")[columnIndex]);
            } catch (Exception e) {
                values[rowCount] = 0;
            }
            rowCount++;
        }
        br.close();

        int[] indices = new int[rowCount];
        for (int i = 0; i < rowCount; i++) indices[i] = i;

        mergeSort(values, indices, 0, rowCount - 1);

        // INVERTE para ordem decrescente (apenas para length)
        for (int i = 0; i < rowCount / 2; i++) {
            int temp = indices[i];
            indices[i] = indices[rowCount - 1 - i];
            indices[rowCount - 1 - i] = temp;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(header);
        bw.newLine();
        for (int i = 0; i < rowCount; i++) {
            bw.write(rows[indices[i]]);
            bw.newLine();
        }
        bw.close();
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int capacity = 10000;
        String[] rows = new String[capacity];
        long[] values = new long[capacity];
        int rowCount = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String line;
        while ((line = br.readLine()) != null) {
            if (rowCount == capacity) {
                capacity *= 2;
                rows = java.util.Arrays.copyOf(rows, capacity);
                values = java.util.Arrays.copyOf(values, capacity);
            }

            rows[rowCount] = line;
            try {
                String[] campos = line.split(",");
                LocalDate date = LocalDate.parse(campos[columnIndex], formatter);
                values[rowCount] = date.toEpochDay();
            } catch (Exception e) {
                values[rowCount] = Long.MIN_VALUE;
            }
            rowCount++;
        }
        br.close();

        int[] indices = new int[rowCount];
        for (int i = 0; i < rowCount; i++) indices[i] = i;

        mergeSort(values, indices, 0, rowCount - 1);

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(header);
        bw.newLine();
        for (int i = 0; i < rowCount; i++) {
            bw.write(rows[indices[i]]);
            bw.newLine();
        }
        bw.close();
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int capacity = 10000;
        String[] rows = new String[capacity];
        long[] values = new long[capacity];
        int rowCount = 0;

        String line;
        while ((line = br.readLine()) != null) {
            if (rowCount == capacity) {
                capacity *= 2;
                rows = java.util.Arrays.copyOf(rows, capacity);
                values = java.util.Arrays.copyOf(values, capacity);
            }

            rows[rowCount] = line;
            try {
                String[] campos = line.split(",");
                String[] partes = campos[columnIndex].split("/");
                values[rowCount] = Long.parseLong(partes[1]);
            } catch (Exception e) {
                values[rowCount] = 0;
            }
            rowCount++;
        }
        br.close();

        int[] indices = new int[rowCount];
        for (int i = 0; i < rowCount; i++) indices[i] = i;

        mergeSort(values, indices, 0, rowCount - 1);

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(header);
        bw.newLine();
        for (int i = 0; i < rowCount; i++) {
            bw.write(rows[indices[i]]);
            bw.newLine();
        }
        bw.close();
    }

    private static void mergeSort(long[] values, int[] indices, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(values, indices, left, mid);
            mergeSort(values, indices, mid + 1, right);
            merge(values, indices, left, mid, right);
        }
    }

    private static void merge(long[] values, int[] indices, int left, int mid, int right) {
        int n = right - left + 1;
        int[] temp = new int[n];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (values[indices[i]] <= values[indices[j]]) {
                temp[k++] = indices[i++];
            } else {
                temp[k++] = indices[j++];
            }
        }
        while (i <= mid) temp[k++] = indices[i++];
        while (j <= right) temp[k++] = indices[j++];

        for (int m = 0; m < n; m++) {
            indices[left + m] = temp[m];
        }
    }
}
