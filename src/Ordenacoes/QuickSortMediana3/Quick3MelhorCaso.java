package Ordenacoes.QuickSortMediana3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Quick3MelhorCaso {

    public static void QuickSort3CSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2);
    }

    public static void QuickSort3CSVData(String inputFilePath, String outputFilePath) throws IOException {
        String[] linhas = lerCSV(inputFilePath);
        if (linhas.length <= 1) return;

        String header = linhas[0];
        int n = linhas.length - 1;
        long[] valores = new long[n];
        int[] indices = new int[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] colunas = linhas[i + 1].split(",");
            try {
                LocalDate data = LocalDate.parse(colunas[3], formatter);
                valores[i] = data.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + colunas[3]);
                valores[i] = Long.MIN_VALUE;
            }
        }

        quickSortMelhorCaso(valores, indices, 0, n - 1);
        escreverCSV(outputFilePath, header, linhas, indices);
    }

    public static void QuickSort3CSVMes(String inputFilePath, String outputFilePath) throws IOException {
        String[] linhas = lerCSV(inputFilePath);
        if (linhas.length <= 1) return;

        String header = linhas[0];
        int n = linhas.length - 1;
        long[] valores = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] colunas = linhas[i + 1].split(",");
            try {
                String dataStr = colunas[3];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                valores[i] = Long.parseLong(partes[1]);
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + colunas[3]);
                valores[i] = 0;
            }
        }

        quickSortMelhorCaso(valores, indices, 0, n - 1);
        escreverCSV(outputFilePath, header, linhas, indices);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        String[] linhas = lerCSV(inputFilePath);
        if (linhas.length <= 1) return;

        String header = linhas[0];
        int n = linhas.length - 1;
        long[] valores = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] colunas = linhas[i + 1].split(",");
            try {
                valores[i] = Long.parseLong(colunas[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + colunas[columnIndex]);
                valores[i] = 0;
            }
        }

        quickSortMelhorCaso(valores, indices, 0, n - 1);

        // INVERTE para ordem decrescente (apenas para length)
        for (int i = 0; i < n / 2; i++) {
            int temp = indices[i];
            indices[i] = indices[n - 1 - i];
            indices[n - 1 - i] = temp;
        }

        escreverCSV(outputFilePath, header, linhas, indices);
    }

    private static String[] lerCSV(String caminho) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        StringBuilder conteudo = new StringBuilder();
        while ((linha = br.readLine()) != null) {
            conteudo.append(linha).append("\n");
        }
        br.close();
        return conteudo.toString().split("\n");
    }

    private static void escreverCSV(String caminho, String header, String[] linhas, int[] indices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        bw.write(header);
        bw.newLine();
        for (int index : indices) {
            bw.write(linhas[index + 1]);
            bw.newLine();
        }
        bw.close();
    }

    private static void quickSortMelhorCaso(long[] valores, int[] indices, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            if (valores[indices[dir]] < valores[indices[meio]]) swap(indices, dir, meio);
            if (valores[indices[meio]] < valores[indices[esq]]) swap(indices, meio, esq);
            if (valores[indices[dir]] < valores[indices[meio]]) swap(indices, dir, meio);

            swap(indices, meio, dir - 1);
            long pivo = valores[indices[dir - 1]];

            int i = esq, j = dir - 1;
            while (true) {
                while (i < dir - 1 && valores[indices[++i]] < pivo);
                while (j > esq && valores[indices[--j]] > pivo);
                if (i >= j) break;
                swap(indices, i, j);
            }

            swap(indices, i, dir - 1);
            quickSortMelhorCaso(valores, indices, esq, i - 1);
            quickSortMelhorCaso(valores, indices, i + 1, dir);
        }
    }

    private static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}
