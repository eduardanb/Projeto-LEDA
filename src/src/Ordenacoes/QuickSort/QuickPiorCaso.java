package Ordenacoes.QuickSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class QuickPiorCaso {

    // Interface funcional para extrair valores de uma coluna
    @FunctionalInterface
    interface ValueExtractor {
        long extract(String data, int rowIndex) throws Exception;
    }

    // Ordena por tamanho (coluna 2)
    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 2, QuickPiorCaso::parseLongFromColumn, true); // decrescente
    }

    // Ordena por data (coluna 3)
    public static void quickSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickPiorCaso::parseEpochDayFromDate, false); // crescente
    }

    // Ordena por mês (coluna 3)
    public static void quickSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVByLong(inputFilePath, outputFilePath, 3, QuickPiorCaso::parseMonthFromDate, false); // crescente
    }

    // Processa o CSV, extrai valores e executa o QuickSort no pior caso
    private static void processCSVByLong(String inputFilePath, String outputFilePath, int columnIndex,
                                         ValueExtractor extractor, boolean decrescente) throws IOException {

        String[] lines = readCSV(inputFilePath);
        if (lines.length == 0) throw new IOException("Arquivo vazio: " + inputFilePath);

        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);

        int n = dataLines.length;
        long[] values = new long[n];
        String[] cleanedLines = new String[n];

        for (int i = 0; i < n; i++) {
            String[] campos = dataLines[i].split(",", -1);
            cleanedLines[i] = dataLines[i]; // Mantém original

            try {
                if (columnIndex < campos.length) {
                    values[i] = extractor.extract(campos[columnIndex], i + 2);
                } else {
                    throw new IllegalArgumentException("Coluna inexistente");
                }
            } catch (Exception e) {
                System.err.println("Erro na linha " + (i + 2) + ": " + e.getMessage());
                values[i] = 0;
            }
        }

        // Gera o pior caso para o QuickSort (array já ordenado crescente ou decrescente)
        prepareWorstCase(values, cleanedLines, decrescente);

        // Executa o QuickSort
        quickSort(values, cleanedLines, 0, n - 1, decrescente);

        // Escreve o resultado no arquivo de saída
        writeCSV(outputFilePath, header, cleanedLines);
    }

    // Gera o pior caso para o QuickSort: array já ordenado crescente ou decrescente
    private static void prepareWorstCase(long[] values, String[] lines, boolean decrescente) {
        Integer[] indices = new Integer[values.length];
        for (int i = 0; i < values.length; i++) indices[i] = i;

        // Ordena os índices para criar o pior caso
        Arrays.sort(indices, (a, b) -> decrescente ? Long.compare(values[b], values[a]) : Long.compare(values[a], values[b]));

        // Cria arrays temporários ordenados
        long[] sortedValues = new long[values.length];
        String[] sortedLines = new String[lines.length];

        for (int i = 0; i < values.length; i++) {
            sortedValues[i] = values[indices[i]];
            sortedLines[i] = lines[indices[i]];
        }

        // Substitui os arrays originais pelos ordenados
        System.arraycopy(sortedValues, 0, values, 0, values.length);
        System.arraycopy(sortedLines, 0, lines, 0, lines.length);
    }

    // Lê todas as linhas do CSV em um array de Strings
    private static String[] readCSV(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            return br.lines().toArray(String[]::new);
        }
    }

    // Extrai um valor long de uma coluna
    private static long parseLongFromColumn(String value, int rowIndex) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor numérico inválido: '" + value + "'");
        }
    }

    // Extrai o epochDay de uma data no formato dd/MM/yyyy
    private static long parseEpochDayFromDate(String dateStr, int rowIndex) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr.trim(), formatter).toEpochDay();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: '" + dateStr + "'");
        }
    }

    // Extrai o mês de uma data no formato dd/MM/yyyy
    private static long parseMonthFromDate(String dateStr, int rowIndex) {
        try {
            String[] partes = dateStr.trim().split("/");
            if (partes.length < 2 || partes[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Formato de data inválido");
            }
            return Long.parseLong(partes[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao extrair mês: '" + dateStr + "'");
        }
    }

    // QuickSort padrão (pior caso: array já ordenado)
    private static void quickSort(long[] values, String[] lines, int low, int high, boolean decrescente) {
        while (low < high) {
            int pivotIndex = partition(values, lines, low, high, decrescente);
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(values, lines, low, pivotIndex - 1, decrescente);
                low = pivotIndex + 1;
            } else {
                quickSort(values, lines, pivotIndex + 1, high, decrescente);
                high = pivotIndex - 1;
            }
        }
    }

    // Particionamento do QuickSort
    private static int partition(long[] values, String[] lines, int low, int high, boolean decrescente) {
        long pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((decrescente && values[j] >= pivot) || (!decrescente && values[j] <= pivot)) {
                i++;
                swap(values, lines, i, j);
            }
        }
        swap(values, lines, i + 1, high);
        return i + 1;
    }

    // Troca valores e linhas
    private static void swap(long[] values, String[] lines, int i, int j) {
        long tempVal = values[i];
        values[i] = values[j];
        values[j] = tempVal;

        String tempLine = lines[i];
        lines[i] = lines[j];
        lines[j] = tempLine;
    }

    // Escreve o resultado no arquivo de saída
    private static void writeCSV(String outputFilePath, String header, String[] lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(header);
            bw.newLine();
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}