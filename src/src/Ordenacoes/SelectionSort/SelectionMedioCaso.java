package Ordenacoes.SelectionSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SelectionMedioCaso {

    public static void SelectionCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // coluna 2 = length
    }

    public static void SelectionCSVData(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (isFirstLine) {
                    header = parts;
                    isFirstLine = false;
                    continue;
                }
                rows.add(parts);
            }
        }

        int n = rows.size();
    long[] values = new long[n];
    Integer[] indices = new Integer[n];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Converter a data para número de dias desde 1970
    for (int i = 0; i < n; i++) {
        indices[i] = i;
        try {
            String dataStr = rows.get(i)[3]; // Coluna de data (índice 3)
            LocalDate data = LocalDate.parse(dataStr, formatter);
            values[i] = data.toEpochDay();
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[3]);
            values[i] = Long.MIN_VALUE;
        }
    }

        selectionSort(values, indices);

        writeCSV(outputFilePath, header, rows, indices);
    }

    public static void SelectionCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (isFirstLine) {
                    header = parts;
                    isFirstLine = false;
                    continue;
                }
                rows.add(parts);
            }
        }

        int n = rows.size();
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                if (rows.get(i).length <= 3) throw new IllegalArgumentException("Coluna de data ausente");
                String dataStr = rows.get(i)[3];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}"))
                    throw new IllegalArgumentException("Formato de data inválido");
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]); // Mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " +
                        (rows.get(i).length > 3 ? rows.get(i)[3] : "DADO AUSENTE"));
                values[i] = 0;
            }
        }

        selectionSort(values, indices);
        writeCSV(outputFilePath, header, rows, indices);
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (isFirstLine) {
                    header = parts;
                    isFirstLine = false;
                    continue;
                }
                rows.add(parts);
            }
        }

        int n = rows.size();
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                if (rows.get(i).length <= columnIndex)
                    throw new IllegalArgumentException("Coluna ausente");
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
            } catch (IllegalArgumentException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " +
                        (rows.get(i).length > columnIndex ? rows.get(i)[columnIndex] : "DADO AUSENTE"));
                values[i] = 0;
            }
        }

        selectionSort(values, indices);
        writeCSV(outputFilePath, header, rows, indices);
    }

    private static void writeCSV(String outputFilePath, String[] header, List<String[]> rows, Integer[] indices) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (int index : indices) {
                bw.write(String.join(",", rows.get(index)));
                bw.newLine();
            }
        }
    }

    private static void selectionSort(long[] values, Integer[] indices) {
        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (values[indices[j]] < values[indices[minIdx]]) {
                    minIdx = j;
                }
            }
            int temp = indices[minIdx];
            indices[minIdx] = indices[i];
            indices[i] = temp;
        }
    }
}
