package Ordenacoes.QuickSortMediana3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Quick3MelhorCaso {

    public static void QuickSort3CSVLength(String inputFilePath, String outputFilePath) throws IOException {
        processCSV(inputFilePath, outputFilePath, 2);
    }

    public static void QuickSort3CSVData(String inputFilePath, String outputFilePath) throws IOException {
    List<String[]> rows = new ArrayList<>();
    String[] header = null;

    // Leitura do CSV
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

    // Converter datas em dias desde 1970
    for (int i = 0; i < n; i++) {
        indices[i] = i;
        try {
            String dataStr = rows.get(i)[3]; // Coluna de data
            LocalDate data = LocalDate.parse(dataStr, formatter);
            values[i] = data.toEpochDay();
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[3]);
            values[i] = Long.MIN_VALUE; // Coloca datas inválidas no início
        }
    }

    quickSortMelhorCaso(values, indices, 0, n - 1);

    // Reorganiza as linhas do CSV
    List<String[]> sortedRows = new ArrayList<>();
    for (int index : indices) {
        sortedRows.add(rows.get(index));
    }

    // Escreve o arquivo de saída
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


    public static void QuickSort3CSVMes(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Leitura do CSV
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

        // Ordena os índices com base nos valores (mês) usando QuickSort com Mediana de 3
        quickSortMelhorCaso(values, indices, 0, n - 1);

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

        // Leitura do CSV
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

        // Prepara valores e índices
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                values[i] = Long.parseLong(rows.get(i)[columnIndex]);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = 0;
            }
        }

        // Ordena os índices com base nos valores usando QuickSort com Mediana de 3
        quickSortMelhorCaso(values, indices, 0, n - 1);

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

    /**
     * QuickSort com Mediana de 3 — ordena índices com base nos valores
     */
    private static void quickSortMelhorCaso(long[] values, Integer[] indices, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;

            // Mediana de 3 para escolher o pivô
            if (values[indices[dir]] < values[indices[meio]]) swap(indices, dir, meio);
            if (values[indices[meio]] < values[indices[esq]]) swap(indices, meio, esq);
            if (values[indices[dir]] < values[indices[meio]]) swap(indices, dir, meio);

            // Coloca o pivô na posição correta
            swap(indices, meio, dir - 1);
            long pivo = values[indices[dir - 1]];

            int i = esq;
            int j = dir - 1;

            // Particionamento
            while (true) {
                while (i < dir - 1 && values[indices[++i]] < pivo); // Incrementa i até encontrar um valor maior ou igual ao pivô
                while (j > esq && values[indices[--j]] > pivo);    // Decrementa j até encontrar um valor menor ou igual ao pivô
                if (i >= j) break;                                // Sai do loop se os índices se cruzarem
                swap(indices, i, j);                              // Troca os elementos
            }

            // Coloca o pivô na posição final
            swap(indices, i, dir - 1);

            // Chamada recursiva para as subpartições
            quickSortMelhorCaso(values, indices, esq, i - 1);
            quickSortMelhorCaso(values, indices, i + 1, dir);
        }
    }

    private static void swap(Integer[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}
