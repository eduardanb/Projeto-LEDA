package Ordenacoes.SelectionSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SelectionMelhorCaso {

    public static void SelectionCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // coluna 2 = length
    }

    public static void SelectionCSVData(String inputFilePath, String outputFilePath) throws IOException {
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

        // Ordenar com SelectionSort
        selectionSort(values, indices);

        // Reorganizar linhas
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escrever o CSV de saída
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String[] row : sortedRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    public static void SelectionCSVMes(String inputFilePath, String outputFilePath) throws IOException {
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
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        // Extrair o mês da coluna de data e associar com os índices
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[3]; // Coluna de data (índice 3)
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]); // Extrai o mês (parte [1] da data dd/MM/yyyy)
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[3]);
                values[i] = 0; // Valor padrão em caso de erro
            }
        }

        // Ordena os índices com base nos valores (mês) usando SelectionSort
        selectionSort(values, indices);

        // Reorganiza as linhas conforme os índices ordenados
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escreve o CSV ordenado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String[] row : sortedRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
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
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        // Inicializa índices e captura os valores da coluna indicada
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido encontrado na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
            }
        }

        // Ordena os índices com base nos valores
        selectionSort(values, indices);

        // Reorganiza as linhas com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escreve o CSV ordenado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (String[] row : sortedRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    // Selection Sort otimizado para o melhor caso
    private static void selectionSort(long[] values, Integer[] indices) {
        int n = indices.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (values[indices[j]] < values[indices[minIndex]]) {
                    minIndex = j;
                    swapped = true;
                }
            }
            
            // Se não houve trocas, o array já está ordenado
            if (!swapped) {
                break;
            }
            
            // Troca os índices
            int temp = indices[minIndex];
            indices[minIndex] = indices[i];
            indices[i] = temp;
        }
    }
}