package Ordenacoes.CountingSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CountingPiorCaso {

    public static void CountingSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2);
    }

    public static void CountingSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            String[] campos = dataLines[i].split(",");
            try {
                String dataStr = campos[3];
                LocalDate data = LocalDate.parse(dataStr, formatter);
                values[i] = data.toEpochDay();
                if (values[i] > max) max = values[i];
            } catch (DateTimeParseException e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + campos[3]);
                values[i] = 0;
            }
        }

        int[] sortedIndices = countingSort(values, max);
        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    public static void CountingSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        long max = 12;

        for (int i = 0; i < n; i++) {
            String[] campos = dataLines[i].split(",");
            try {
                String dataStr = campos[3];
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]);
            } catch (Exception e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + campos[3]);
                values[i] = 0;
            }
        }

        int[] sortedIndices = countingSort(values, max);
        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        long max = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            String[] campos = dataLines[i].split(",");
            try {
                values[i] = Long.parseLong(campos[columnIndex]);
                if (values[i] > max) max = values[i];
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + campos[columnIndex]);
                values[i] = 0;
            }
        }

        int[] sortedIndices = countingSort(values, max);
        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    private static String[] readCSV(String inputFilePath) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
    int totalLinhas = 0;

    // Primeira passada: contar as linhas
    while (br.readLine() != null) {
        totalLinhas++;
    }
    br.close();

    String[] lines = new String[totalLinhas];

    // Segunda passada: preencher o array
    br = new BufferedReader(new FileReader(inputFilePath));
    String line;
    int rowCount = 0;
    while ((line = br.readLine()) != null) {
        lines[rowCount++] = line;
    }
    br.close();

    return lines;
    }


    private static void writeCSV(String outputFilePath, String header, String[] lines, int[] indices) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(header);
                bw.newLine();
            }
            for (int index : indices) {
                bw.write(lines[index]);
                bw.newLine();
            }
        }
    }

    private static int[] countingSort(long[] arr, long max) {
        int n = arr.length;
        int[] output = new int[n];

        if (max > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("O valor máximo é muito grande para o Counting Sort.");
        }

        int[] count = new int[(int) (max + 1)];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(int) arr[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(int) arr[i]] - 1] = i;
            count[(int) arr[i]]--;
        }

        return output;
    }
}
