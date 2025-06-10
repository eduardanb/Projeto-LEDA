package Ordenacoes.QuickSortMediana3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Quick3PiorCaso {

    public static void QuickSort3CSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2);
    }

    public static void QuickSort3CSVData(String inputFilePath, String outputFilePath) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] campos = dataLines[i].split(",");
            try {
                String rawDate = campos[3].trim();
                LocalDate data = LocalDate.parse(rawDate, formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + (campos.length > 3 ? campos[3] : "coluna ausente"));
                values[i] = Long.MIN_VALUE;
            }
        }

        quickSortPiorCaso(values, indices, 0, n - 1);
        writeCSV(outputFilePath, header, dataLines, indices);
    }

    public static void QuickSort3CSVMes(String inputFilePath, String outputFilePath, int dataColumnIndex) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] campos = dataLines[i].split(",");
            try {
                String rawDate = campos[dataColumnIndex].trim();
                LocalDate data = LocalDate.parse(rawDate, formatter);
                values[i] = data.getMonthValue();         // extrai o mês
            } catch (Exception e) {
                System.err.println("Erro ao processar o mês na linha " + (i + 2) + ": " + (campos.length > dataColumnIndex ? campos[dataColumnIndex] : "coluna ausente"));
                values[i] = 0;                             // inválidos vão para início
            }
        }

        quickSortPiorCaso(values, indices, 0, n - 1);
        writeCSV(outputFilePath, header, dataLines, indices);
    }

    private static void quickSortPiorCaso(long[] v, int[] idx, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            ordenarMediana(v, idx, esq, meio, dir);
            int pivoIndex = dir - 1;
            int pivoNovo = particionar(v, idx, esq, dir, pivoIndex);
            quickSortPiorCaso(v, idx, esq, pivoNovo - 1);
            quickSortPiorCaso(v, idx, pivoNovo + 1, dir);
        }
    }

    private static void ordenarMediana(long[] v, int[] idx, int a, int b, int c) {
        if (v[c] < v[b]) swap(v, idx, c, b);
        if (v[b] < v[a]) swap(v, idx, b, a);
        if (v[c] < v[b]) swap(v, idx, c, b);
        swap(v, idx, b, c - 1);
    }

    private static int particionar(long[] v, int[] idx, int esq, int dir, int pivoIndex) {
        long pivo = v[pivoIndex];
        int i = esq, j = dir - 1;
        while (true) {
            while (v[++i] < pivo);
            while (v[--j] > pivo);
            if (i >= j) break;
            swap(v, idx, i, j);
        }
        swap(v, idx, i, pivoIndex);
        return i;
    }

    private static void swap(long[] v, int[] idx, int i, int j) {
        long tmp = v[i]; v[i] = v[j]; v[j] = tmp;
        int ti = idx[i]; idx[i] = idx[j]; idx[j] = ti;
    }

    private static String[] readCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line; StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) sb.append(line).append("\n");
        br.close();
        return sb.toString().split("\n");
    }

    private static void writeCSV(String outputPath, String header, String[] dataLines, int[] sortedIndices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
        bw.write(header);
        bw.newLine();
        for (int idx : sortedIndices) {
            bw.write(dataLines[idx]);
            bw.newLine();
        }
        bw.close();
    }

    // para LENGTH (coluna 2)
    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        String[] linhas = readCSV(inputFilePath);
        if (linhas.length <= 1) return;

        String header = linhas[0];
        int n = linhas.length - 1;
        long[] valores = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] colunas = linhas[i + 1].split(",");
            try {
                valores[i] = Long.parseLong(colunas[columnIndex].trim());
            } catch (NumberFormatException e) {
                valores[i] = 0;
            }
        }

        quickSortPiorCaso(valores, indices, 0, n - 1);
        // inverte para ordem decrescente
        for (int i = 0; i < n / 2; i++) {
            int tmp = indices[i];
            indices[i] = indices[n - 1 - i];
            indices[n - 1 - i] = tmp;
        }

        writeCSV(outputFilePath, header, Arrays.copyOfRange(linhas, 1, linhas.length), indices);
    }
}
