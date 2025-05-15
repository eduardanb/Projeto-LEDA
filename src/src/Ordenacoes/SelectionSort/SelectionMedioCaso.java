package Ordenacoes.SelectionSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class SelectionMedioCaso {

    public static void SelectionCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // coluna 2 = length
    }

    public static void SelectionCSVData(String inputFilePath, String outputFilePath) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String[] campos = dataLines[i].split(",");
                LocalDate data = LocalDate.parse(campos[3], formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + dataLines[i]);
                values[i] = Long.MIN_VALUE;
            }
        }

        selectionSort(values, indices);
        writeCSV(outputFilePath, header, dataLines, indices);
    }

    public static void SelectionCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String[] campos = dataLines[i].split(",");
                if (campos.length <= 3) throw new IllegalArgumentException("Coluna de data ausente");
                String dataStr = campos[3];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}"))
                    throw new IllegalArgumentException("Formato de data inválido");
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]); // mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + dataLines[i]);
                values[i] = 0;
            }
        }

        selectionSort(values, indices);
        writeCSV(outputFilePath, header, dataLines, indices);
    }

    // Ordenação por seleção (Selection Sort)
    private static void selectionSort(long[] values, int[] indices) {
        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (values[indices[j]] < values[indices[minIdx]]) {
                    minIdx = j;
                }
            }
            int temp = indices[i];
            indices[i] = indices[minIdx];
            indices[minIdx] = temp;
        }
    }

    // Reutilização de funções auxiliares salvas anteriormente:
    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
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
                values[i] = Long.parseLong(campos[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + campos[columnIndex]);
                values[i] = 0;
            }
        }

        selectionSort(values, indices);
        writeCSV(outputFilePath, header, dataLines, indices);
    }

    private static String[] readCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString().split("\n");
    }

    private static void writeCSV(String outputPath, String header, String[] dataLines, int[] sortedIndices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
        bw.write(header);
        bw.newLine();
        for (int i : sortedIndices) {
            bw.write(dataLines[i]);
            bw.newLine();
        }
        bw.close();
    }
}
