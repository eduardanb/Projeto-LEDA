package Ordenacoes.SelectionSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SelectionPiorCaso {

    public static void SelectionCSVLength(String inputPath, String outputPath) throws IOException {
        processCSV(inputPath, outputPath, 2);
    }

    public static void SelectionCSVData(String inputPath, String outputPath) throws IOException {
        CSVData csv = lerCSV(inputPath);

        long[] valores = new long[csv.totalRows - 1];
        int[] indices = new int[csv.totalRows - 1];
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 1; i < csv.totalRows; i++) {
            indices[i - 1] = i - 1;
            try {
                String data = csv.linear[csv.rowStart[i] + 3];
                LocalDate d = LocalDate.parse(data, formatador);
                valores[i - 1] = d.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro na data linha " + (i + 1) + ": " + csv.linear[csv.rowStart[i] + 3]);
                valores[i - 1] = Long.MIN_VALUE;
            }
        }

        selectionSortPiorCaso(valores, indices);
        escreverCSV(outputPath, csv.linear, csv.rowStart, indices, csv.totalRows);
    }

    public static void SelectionCSVMes(String inputPath, String outputPath) throws IOException {
        CSVData csv = lerCSV(inputPath);

        long[] valores = new long[csv.totalRows - 1];
        int[] indices = new int[csv.totalRows - 1];

        for (int i = 1; i < csv.totalRows; i++) {
            indices[i - 1] = i - 1;
            try {
                String data = csv.linear[csv.rowStart[i] + 3];
                String[] partes = data.split("/");
                valores[i - 1] = Long.parseLong(partes[1]); // mês
            } catch (Exception e) {
                System.err.println("Erro na data linha " + (i + 1) + ": " + csv.linear[csv.rowStart[i] + 3]);
                valores[i - 1] = 0;
            }
        }

        selectionSortPiorCaso(valores, indices);
        escreverCSV(outputPath, csv.linear, csv.rowStart, indices, csv.totalRows);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        CSVData csv = lerCSV(inputFilePath);

        long[] valores = new long[csv.totalRows - 1];
        int[] indices = new int[csv.totalRows - 1];

        for (int i = 1; i < csv.totalRows; i++) {
            indices[i - 1] = i - 1;
            try {
                valores[i - 1] = Long.parseLong(csv.linear[csv.rowStart[i] + columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 1) + ": " + csv.linear[csv.rowStart[i] + columnIndex]);
                valores[i - 1] = 0;
            }
        }

        selectionSortPiorCaso(valores, indices);
        escreverCSV(outputFilePath, csv.linear, csv.rowStart, indices, csv.totalRows);
    }

    // Estrutura auxiliar para armazenar os dados do CSV de forma dinâmica
    private static class CSVData {
        String[] linear;
        int[] rowStart;
        int totalRows;

        CSVData(String[] linear, int[] rowStart, int totalRows) {
            this.linear = linear;
            this.rowStart = rowStart;
            this.totalRows = totalRows;
        }
    }

    // Método dinâmico para ler o CSV em arrays unidimensionais
    private static CSVData lerCSV(String caminho) throws IOException {
        int linearCap = 10000;
        int rowCap = 1000;
        String[] linear = new String[linearCap];
        int[] rowStart = new int[rowCap];
        int pos = 0;
        int linhaIndex = 0;

        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linhaIndex == rowStart.length) {
                int[] novoRowStart = new int[rowStart.length * 2];
                System.arraycopy(rowStart, 0, novoRowStart, 0, rowStart.length);
                rowStart = novoRowStart;
            }
            rowStart[linhaIndex] = pos;
            String[] campos = linha.split(",");
            if (pos + campos.length > linear.length) {
                String[] novoLinear = new String[linear.length * 2];
                System.arraycopy(linear, 0, novoLinear, 0, linear.length);
                linear = novoLinear;
            }
            for (String campo : campos) {
                linear[pos++] = campo;
            }
            linhaIndex++;
        }
        br.close();

        // Reduz os arrays ao tamanho real
        String[] linearFinal = new String[pos];
        System.arraycopy(linear, 0, linearFinal, 0, pos);
        int[] rowStartFinal = new int[linhaIndex];
        System.arraycopy(rowStart, 0, rowStartFinal, 0, linhaIndex);

        return new CSVData(linearFinal, rowStartFinal, linhaIndex);
    }

    private static void escreverCSV(String caminho, String[] linear, int[] rowStart, int[] indices, int totalRows) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

        // Cabeçalho
        for (int i = rowStart[0]; i < rowStart[1]; i++) {
            bw.write(linear[i]);
            if (i < rowStart[1] - 1) bw.write(",");
        }
        bw.newLine();

        // Dados
        for (int idx : indices) {
            int start = rowStart[idx + 1];
            int end = (idx + 2 < totalRows) ? rowStart[idx + 2] : linear.length;
            for (int i = start; i < end && linear[i] != null; i++) {
                bw.write(linear[i]);
                if (i + 1 < end && linear[i + 1] != null) bw.write(",");
            }
            bw.newLine();
        }

        bw.close();
    }

    private static void selectionSortPiorCaso(long[] valores, int[] indices) {
        int n = indices.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (valores[indices[j]] < valores[indices[menor]]) {
                    menor = j;
                }
            }
            int tmp = indices[menor];
            indices[menor] = indices[i];
            indices[i] = tmp;
        }
    }
}
