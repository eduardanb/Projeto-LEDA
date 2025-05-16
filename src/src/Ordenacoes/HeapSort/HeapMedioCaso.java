package Ordenacoes.HeapSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HeapMedioCaso {

    public static void heapSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, true);
    }

    public static void heapSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVData(inputFilePath, outputFilePath);
    }

    public static void heapSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVMonth(inputFilePath, outputFilePath, 3);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int n = 0;
        while (br.readLine() != null) n++;
        br.close();

        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];

        br = new BufferedReader(new FileReader(inputFilePath));
        br.readLine(); // Pula cabeçalho

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            lines[i] = line;
            String[] parts = line.split(",");
            try {
                values[i] = Long.parseLong(parts[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
            indices[i] = i;
        }
        br.close();

        heapSort(values, indices, ascending);

        writeCSV(outputFilePath, header, lines, indices);
    }

    private static void processCSVData(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int n = 0;
        while (br.readLine() != null) n++;
        br.close();

        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];

        br = new BufferedReader(new FileReader(inputFilePath));
        br.readLine(); // Pula cabeçalho

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            lines[i] = line;
            String[] parts = line.split(",");
            try {
                LocalDate data = LocalDate.parse(parts[3], formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + (parts.length > 3 ? parts[3] : "coluna ausente"));
                values[i] = 0;
            }
            indices[i] = i;
        }
        br.close();

        heapSort(values, indices, false);

        writeCSV(outputFilePath, header, lines, indices);
    }

    private static void processCSVMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        int n = 0;
        while (br.readLine() != null) n++;
        br.close();

        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];

        br = new BufferedReader(new FileReader(inputFilePath));
        br.readLine(); // Pula cabeçalho

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            lines[i] = line;
            String[] parts = line.split(",");
            try {
                String dataStr = parts[columnIndex];
                String[] dataParts = dataStr.split("/");
                if (dataParts.length < 2) throw new IllegalArgumentException("Data inválida: " + dataStr);
                values[i] = Long.parseLong(dataParts[1]);
            } catch (Exception e) {
                System.err.println("Erro ao processar o mês na linha " + (i + 2) + ": " + (parts.length > columnIndex ? parts[columnIndex] : "coluna ausente"));
                values[i] = 0;
            }
            indices[i] = i;
        }
        br.close();

        heapSort(values, indices, false);

        writeCSV(outputFilePath, header, lines, indices);
    }

    private static void heapSort(long[] values, int[] indices, boolean ascending) {
        int n = values.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(values, indices, n, i, ascending);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(indices, 0, i);
            heapify(values, indices, i, 0, ascending);
        }
    }

    private static void heapify(long[] values, int[] indices, int n, int i, boolean ascending) {
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(values[indices[left]], values[indices[target]], ascending)) {
            target = left;
        }

        if (right < n && compare(values[indices[right]], values[indices[target]], ascending)) {
            target = right;
        }

        if (target != i) {
            swap(indices, i, target);
            heapify(values, indices, n, target, ascending);
        }
    }

    private static boolean compare(long a, long b, boolean ascending) {
        return ascending ? a < b : a > b;
    }

    private static void swap(int[] indices, int i, int j) {
        int temp = indices[i];
        indices[i] = indices[j];
        indices[j] = temp;
    }

    private static void writeCSV(String outputFilePath, String header, String[] lines, int[] indices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
        bw.write(header);
        bw.newLine();
        for (int i = 0; i < indices.length; i++) {
            bw.write(lines[indices[i]]);
            bw.newLine();
        }
        bw.close();
    }
}
