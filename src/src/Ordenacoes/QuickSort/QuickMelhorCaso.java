package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class QuickMelhorCaso {

    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // Coluna 2 = length
    }

    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3); // Coluna 3 = data no formato dd/MM/yyyy
    }

    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3); // Coluna 3 = data no formato dd/MM/yyyy
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }

        int n = rows.size();
        String[] values = new String[n];
        for (int i = 0; i < n; i++) {
            values[i] = rows.get(i)[columnIndex];
        }

        // Preparar o melhor caso
        prepareBestCase(values, rows, columnIndex);

        // Aplicar QuickSort
        quickSort(values, rows, 0, n - 1);

        // Escrever o CSV ordenado
        writeCSV(outputFilePath, header, rows);
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }

        int n = rows.size();
        String[] values = new String[n];
        for (int i = 0; i < n; i++) {
            try {
                String dataStr = rows.get(i)[columnIndex];
                LocalDate data = LocalDate.parse(dataStr, formatter);
                values[i] = String.valueOf(data.toEpochDay());
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = "0";
            }
        }

        // Preparar o melhor caso
        prepareBestCase(values, rows, columnIndex);

        // Aplicar QuickSort
        quickSort(values, rows, 0, n - 1);

        // Escrever o CSV ordenado
        writeCSV(outputFilePath, header, rows);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(",");
                    isFirstLine = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        }

        int n = rows.size();
        String[] values = new String[n];
        for (int i = 0; i < n; i++) {
            try {
                String dataStr = rows.get(i)[columnIndex];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                values[i] = partes[1]; // Extrai o mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = "0";
            }
        }

        // Preparar o melhor caso
        prepareBestCase(values, rows, columnIndex);

        // Aplicar QuickSort
        quickSort(values, rows, 0, n - 1);

        // Escrever o CSV ordenado
        writeCSV(outputFilePath, header, rows);
    }

    private static void prepareBestCase(String[] values, List<String[]> rows, int columnIndex) {
        // Ordena os valores em ordem crescente
        Arrays.sort(values);

        // Reorganiza as linhas do CSV com base nos valores ordenados
        rows.sort(Comparator.comparing(row -> row[columnIndex]));
    }

    private static void quickSort(String[] values, List<String[]> rows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, rows, low, high);
            quickSort(values, rows, low, pivotIndex - 1);
            quickSort(values, rows, pivotIndex + 1, high);
        }
    }

    private static int partition(String[] values, List<String[]> rows, int low, int high) {
        String pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[j].compareTo(pivot) <= 0) { // Ordenação crescente
                i++;
                swap(values, rows, i, j);
            }
        }

        swap(values, rows, i + 1, high);
        return i + 1;
    }

    private static void swap(String[] values, List<String[]> rows, int i, int j) {
        String tempValue = values[i];
        values[i] = values[j];
        values[j] = tempValue;

        String[] tempRow = rows.get(i);
        rows.set(i, rows.get(j));
        rows.set(j, tempRow);
    }

    private static void writeCSV(String outputFilePath, String[] header, List<String[]> rows) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "src/ArquivosCSV/passwords_formated_data.csv";
        String outputFilePath = "src/ArquivosCSV/passwords_length_quickSort_melhorCaso.csv";

        try {
            // Ordenar pelo campo "length" (coluna 2)
            quickSortCSVLength(inputFilePath, outputFilePath);
        } catch (IOException e) {
            System.err.println("Erro ao processar o QuickSort: " + e.getMessage());
        }
    }
}