package Ordenacoes.HeapSort;

import java.io.*;

public class HeapMedioCaso {

    public static void heapSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false);
    }

    public static void heapSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 3, true);
    }

    public static void heapSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String header = br.readLine();

        // Conta número de linhas
        int n = 0;
        while (br.readLine() != null) n++;
        br.close();

        // Carrega linhas e valores
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

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
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
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] dataParts = dataStr.split("/");
                values[i] = Long.parseLong(dataParts[1]);
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
            indices[i] = i;
        }
        br.close();

        heapSort(values, indices, true);

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
        int largestOrSmallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(values[indices[left]], values[indices[largestOrSmallest]], ascending)) {
            largestOrSmallest = left;
        }

        if (right < n && compare(values[indices[right]], values[indices[largestOrSmallest]], ascending)) {
            largestOrSmallest = right;
        }

        if (largestOrSmallest != i) {
            swap(indices, i, largestOrSmallest);
            heapify(values, indices, n, largestOrSmallest, ascending);
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
