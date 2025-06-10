package Ordenacoes.InsectionSort;

import java.io.*;
import java.util.Arrays;

public class InsectionPiorCaso {

    public static void insertionSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false); // Coluna 2 = length, ordem decrescente
    }

    public static void insertionSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3, true); // Coluna 3 = data, ordem crescente
    }

    public static void insertionSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3, true); // Coluna 3 = mês, ordem crescente
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean descending) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);
        int n = dataLines.length;

        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = dataLines[i].split(",");
            try {
                values[i] = Long.parseLong(parts[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
            indices[i] = i;
        }

        prepareWorstCase(values, indices, descending);
        insertionSort(values, indices, descending);

        writeCSV(outputFilePath, header, dataLines, indices);
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);
        int n = dataLines.length;

        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = dataLines[i].split(",");
            try {
                String[] dateParts = parts[columnIndex].split("/");
                values[i] = Long.parseLong(dateParts[2] + dateParts[1] + dateParts[0]); // AnoMêsDia
            } catch (Exception e) {
                System.err.println("Erro ao processar data na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
            indices[i] = i;
        }

        prepareWorstCase(values, indices, !ascending);
        insertionSort(values, indices, ascending);

        writeCSV(outputFilePath, header, dataLines, indices);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);
        int n = dataLines.length;

        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = dataLines[i].split(",");
            try {
                String[] dateParts = parts[columnIndex].split("/");
                values[i] = Long.parseLong(dateParts[1]); // Mês
            } catch (Exception e) {
                System.err.println("Erro ao processar mês na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
            indices[i] = i;
        }

        prepareWorstCase(values, indices, !ascending);
        insertionSort(values, indices, ascending);

        writeCSV(outputFilePath, header, dataLines, indices);
    }

    private static String[] readCSV(String inputFilePath) throws IOException {
        // Primeira passada: contar linhas
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        }

        // Segunda passada: ler conteúdo
        String[] lines = new String[lineCount];
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            for (int i = 0; i < lineCount; i++) {
                lines[i] = br.readLine();
            }
        }

        return lines;
    }

    private static void prepareWorstCase(long[] values, int[] indices, boolean ascending) {
        // Ordena os valores na ordem oposta ao desejado para criar o pior caso
        insertionSort(values, indices, ascending);
        reverse(values);
        reverse(indices);
    }

    private static void reverse(long[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void reverse(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void insertionSort(long[] values, int[] indices, boolean ascending) {
        for (int i = 1; i < values.length; i++) {
            long key = values[i];
            int keyIdx = indices[i];
            int j = i - 1;
            
            while (j >= 0 && (ascending ? values[j] > key : values[j] < key)) {
                values[j + 1] = values[j];
                indices[j + 1] = indices[j];
                j--;
            }
            values[j + 1] = key;
            indices[j + 1] = keyIdx;
        }
    }

    private static void writeCSV(String outputFilePath, String header, String[] dataLines, int[] indices) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(header);
                bw.newLine();
            }
            for (int index : indices) {
                bw.write(dataLines[index]);
                bw.newLine();
            }
        }
    }
}