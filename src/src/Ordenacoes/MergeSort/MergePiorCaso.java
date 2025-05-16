package Ordenacoes.MergeSort;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MergePiorCaso {

    public static void mergeSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2, true); // true = decrescente
    }

    public static void mergeSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithDate(inputFilePath, outputFilePath, 3, false); // false = crescente
    }

    public static void mergeSortCSVMes(String inputFilePath, String outputFilePath) throws IOException {
        processCSVWithMonth(inputFilePath, outputFilePath, 3, false); // false = crescente
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex, boolean decrescente) throws IOException {
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

        prepareWorstCase(values, indices);
        int[] sortedIndices = mergeSortWithIndices(values, indices, decrescente);

        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    private static void processCSVWithDate(String inputFilePath, String outputFilePath, int columnIndex, boolean decrescente) throws IOException {
        String[] lines = readCSV(inputFilePath);
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int n = dataLines.length;
        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
            String[] campos = dataLines[i].split(",");
            try {
                LocalDate data = LocalDate.parse(campos[columnIndex], formatter);
                values[i] = data.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + campos[columnIndex]);
                values[i] = Long.MIN_VALUE;
            }
        }

        prepareWorstCase(values, indices);
        int[] sortedIndices = mergeSortWithIndices(values, indices, decrescente);

        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    private static void processCSVWithMonth(String inputFilePath, String outputFilePath, int columnIndex, boolean decrescente) throws IOException {
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
                if (!campos[columnIndex].matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = campos[columnIndex].split("/");
                values[i] = Long.parseLong(partes[1]); // Mês
            } catch (Exception e) {
                System.err.println("Erro ao processar o mês na linha " + (i + 2) + ": " + campos[columnIndex]);
                values[i] = 0;
            }
        }

        prepareWorstCase(values, indices);
        int[] sortedIndices = mergeSortWithIndices(values, indices, decrescente);

        writeCSV(outputFilePath, header, dataLines, sortedIndices);
    }

    private static String[] readCSV(String inputFilePath) throws IOException {
        int totalLinhas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            while (br.readLine() != null) {
                totalLinhas++;
            }
        }

        String[] lines = new String[totalLinhas];
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {
                lines[rowCount++] = line;
            }
        }

        return lines;
    }

    private static void prepareWorstCase(long[] array, int[] indices) {
        int n = array.length;
        long[] sortedArray = Arrays.copyOf(array, n);
        int[] originalIndices = Arrays.copyOf(indices, n);

        // Ordena os dois arrays com base em valores
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    long tempVal = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = tempVal;

                    int tempIdx = originalIndices[i];
                    originalIndices[i] = originalIndices[j];
                    originalIndices[j] = tempIdx;
                }
            }
        }

        long[] tempArray = new long[n];
        int[] tempIndices = new int[n];
        int left = 0, right = n - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                tempArray[i] = sortedArray[right];
                tempIndices[i] = originalIndices[right];
                right--;
            } else {
                tempArray[i] = sortedArray[left];
                tempIndices[i] = originalIndices[left];
                left++;
            }
        }

        System.arraycopy(tempArray, 0, array, 0, n);
        System.arraycopy(tempIndices, 0, indices, 0, n);
    }

    private static int[] mergeSortWithIndices(long[] array, int[] indices, boolean decrescente) {
        mergeSortHelper(array, indices, 0, array.length - 1, decrescente);
        return indices;
    }

    private static void mergeSortHelper(long[] array, int[] indices, int left, int right, boolean decrescente) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(array, indices, left, mid, decrescente);
            mergeSortHelper(array, indices, mid + 1, right, decrescente);
            merge(array, indices, left, mid, right, decrescente);
        }
    }

    private static void merge(long[] array, int[] indices, int left, int mid, int right, boolean decrescente) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        long[] leftArray = new long[n1];
        long[] rightArray = new long[n2];
        int[] leftIndices = new int[n1];
        int[] rightIndices = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
            leftIndices[i] = indices[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = array[mid + 1 + i];
            rightIndices[i] = indices[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if ((decrescente && leftArray[i] >= rightArray[j]) || (!decrescente && leftArray[i] <= rightArray[j])) {
                array[k] = leftArray[i];
                indices[k] = leftIndices[i];
                i++;
            } else {
                array[k] = rightArray[j];
                indices[k] = rightIndices[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            indices[k] = leftIndices[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            indices[k] = rightIndices[j];
            j++;
            k++;
        }
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
}
