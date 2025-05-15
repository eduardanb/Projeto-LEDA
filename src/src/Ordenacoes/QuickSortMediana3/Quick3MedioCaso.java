package Ordenacoes.QuickSortMediana3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Quick3MedioCaso {

    public static void QuickSort3CSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2); // coluna 2 = length
    }

    public static void QuickSort3CSVData(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String headerLine = br.readLine();
        String[] header = headerLine.split(",");

        int n = countLines(inputFilePath) - 1;
        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < n; i++) {
            lines[i] = br.readLine();
            indices[i] = i;
            String[] parts = lines[i].split(",");
            try {
                LocalDate date = LocalDate.parse(parts[3], formatter);
                values[i] = date.toEpochDay();
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + parts[3]);
                values[i] = Long.MIN_VALUE;
            }
        }
        br.close();

        quickSortIndices(values, indices, 0, n - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(String.join(",", header));
            bw.newLine();
            for (int i = 0; i < n; i++) {
                bw.write(lines[indices[i]]);
                bw.newLine();
            }
        }
    }

    public static void QuickSort3CSVMes(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String headerLine = br.readLine();
        String[] header = headerLine.split(",");

        int n = countLines(inputFilePath) - 1;
        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            lines[i] = br.readLine();
            indices[i] = i;
            String[] parts = lines[i].split(",");
            try {
                String dataStr = parts[3];
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}"))
                    throw new IllegalArgumentException("Formato de data inválido");
                String[] dateParts = dataStr.split("/");
                values[i] = Long.parseLong(dateParts[1]); // mês
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + parts[3]);
                values[i] = 0;
            }
        }
        br.close();

        quickSortIndices(values, indices, 0, n - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(String.join(",", header));
            bw.newLine();
            for (int i = 0; i < n; i++) {
                bw.write(lines[indices[i]]);
                bw.newLine();
            }
        }
    }

    private static void processCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String headerLine = br.readLine();
        String[] header = headerLine.split(",");

        int n = countLines(inputFilePath) - 1;
        String[] lines = new String[n];
        long[] values = new long[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            lines[i] = br.readLine();
            indices[i] = i;
            String[] parts = lines[i].split(",");
            try {
                values[i] = Long.parseLong(parts[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + parts[columnIndex]);
                values[i] = 0;
            }
        }
        br.close();

        quickSortIndices(values, indices, 0, n - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(String.join(",", header));
            bw.newLine();
            for (int i = 0; i < n; i++) {
                bw.write(lines[indices[i]]);
                bw.newLine();
            }
        }
    }

    // QuickSort com mediana de três e tail recursion para evitar stack overflow
    private static void quickSortIndices(long[] values, int[] indices, int esq, int dir) {
        while (esq < dir) {
            int meio = (esq + dir) / 2;

            // Mediana de três
            if (values[indices[dir]] < values[indices[meio]]) swap(indices, dir, meio);
            if (values[indices[meio]] < values[indices[esq]]) swap(indices, meio, esq);
            if (values[indices[dir]] < values[indices[meio]]) swap(indices, dir, meio);

            swap(indices, meio, dir - 1);
            long pivo = values[indices[dir - 1]];

            int i = esq;
            int j = dir - 1;

            while (true) {
                while (i < dir - 1 && values[indices[++i]] < pivo);
                while (j > esq && values[indices[--j]] > pivo);
                if (i >= j) break;
                swap(indices, i, j);
            }

            swap(indices, i, dir - 1);

            // Tail recursion: recursiona na menor partição
            if ((i - 1 - esq) < (dir - i - 1)) {
                quickSortIndices(values, indices, esq, i - 1);
                esq = i + 1;
            } else {
                quickSortIndices(values, indices, i + 1, dir);
                dir = i - 1;
            }
        }
    }

    private static void swap(int[] v, int i, int j) {
        if (i != j) {
            int temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
    }

    private static int countLines(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int lines = 0;
            while (br.readLine() != null) lines++;
            return lines;
        }
    }
}
