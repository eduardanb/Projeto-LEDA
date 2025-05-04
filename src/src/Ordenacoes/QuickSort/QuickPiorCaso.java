package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class QuickPiorCaso {

    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, false); // Coluna 2 = length, ordem decrescente
    }

    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3, true); // Coluna 3 = data, ordem crescente
    }

    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3, true); // Coluna 3 = mês extraído da data, ordem crescente
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
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

        // Preparar o pior caso
        prepareWorstCase(values);

        // Ordena os índices com base nos valores
        quickSortWithIndices(values, indices, 0, n - 1);

        // Reorganiza as linhas com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, header, sortedRows);
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
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
        long[] values = new long[n];
        Integer[] indices = new Integer[n];

        // Converter a data para número de dias desde 1970
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[columnIndex];
                LocalDate data = LocalDate.parse(dataStr, formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = Long.MIN_VALUE;
            }
        }

        // Preparar o pior caso
        prepareWorstCase(values);

        // Ordena os índices com base nos valores
        quickSortWithIndices(values, indices, 0, n - 1);

        // Reorganiza as linhas com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, header, sortedRows);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex, boolean ascending) throws IOException {
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

        // Extrair o mês da coluna de data
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[columnIndex];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                values[i] = Long.parseLong(partes[1]); // Extrai o mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
            }
        }

        // Preparar o pior caso
        prepareWorstCase(values);

        // Ordena os índices com base nos valores
        quickSortWithIndices(values, indices, 0, n - 1);

        // Reorganiza as linhas com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : indices) {
            sortedRows.add(rows.get(index));
        }

        // Escreve o CSV ordenado
        writeCSV(outputFilePath, header, sortedRows);
    }

    private static void prepareWorstCase(long[] values) {
        Arrays.sort(values); // Ordena em ordem crescente
        int n = values.length;
        long[] temp = new long[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                temp[i] = values[right--]; // Pega o maior valor disponível
            } else {
                temp[i] = values[left++]; // Pega o menor valor disponível
            }
        }
        System.arraycopy(temp, 0, values, 0, n); // Copia de volta para o array original
    }

    private static void quickSortWithIndices(long[] values, Integer[] indices, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, indices, low, high);
            quickSortWithIndices(values, indices, low, pivotIndex - 1);
            quickSortWithIndices(values, indices, pivotIndex + 1, high);
        }
    }

    private static int partition(long[] values, Integer[] indices, int low, int high) {
        long pivot = values[indices[high]];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[indices[j]] <= pivot) { // Ordenação crescente
                i++;
                swap(indices, i, j);
            }
        }

        swap(indices, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
}