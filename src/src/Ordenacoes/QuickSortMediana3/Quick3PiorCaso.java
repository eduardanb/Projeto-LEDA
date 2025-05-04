package Ordenacoes.QuickSortMediana3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Quick3PiorCaso {

    public static void QuickSort3CSVLength(String inputFilePath, String outputFilePath) throws IOException {
        sortCSV(inputFilePath, outputFilePath, 2);
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
    Pair[] values = new Pair[n];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (int i = 0; i < n; i++) {
        try {
            String dataStr = rows.get(i)[3]; // Coluna de data
            LocalDate data = LocalDate.parse(dataStr, formatter);
            values[i] = new Pair(data.toEpochDay(), i); // Usa dias desde 1970-01-01
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[3]);
            values[i] = new Pair(Long.MIN_VALUE, i); // Coloca datas inválidas no início
        }
    }

    quickSortPiorCaso(values, 0, n - 1);

    // Reorganiza as linhas do CSV
    List<String[]> sortedRows = new ArrayList<>();
    for (Pair pair : values) {
        sortedRows.add(rows.get(pair.index));
    }

    // Escreve no arquivo de saída
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


    public static void QuickSort3CSVMes(String inputFilePath, String outputFilePath, int dataColumnIndex) throws IOException {
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
        Pair[] values = new Pair[n];
        int[] indices = new int[n];
        // Extrair o mês da coluna de data e associar com o índice original
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            try {
                String dataStr = rows.get(i)[3]; // Coluna de data (índice 3)
                if (!dataStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data inválido");
                }
                String[] partes = dataStr.split("/");
                values[i] = new Pair(Long.parseLong(partes[1]), i); // CORRIGIDO AQUI
            } catch (Exception e) {
                System.err.println("Erro ao processar a data na linha " + (i + 2) + ": " + rows.get(i)[3]);
                values[i] = new Pair(1, i); // janeiro como padrão
            }
        }
    
        // Ordenar os valores pelo mês usando QuickSort Pior Caso
        quickSortPiorCaso(values, 0, n - 1);
    
        // Reorganizar as linhas do CSV conforme a nova ordem
        List<String[]> sortedRows = new ArrayList<>();
        for (Pair pair : values) {
            sortedRows.add(rows.get(pair.index));
        }
    
        // Escrever no arquivo de saída
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
    

    private static void sortCSV(String inputFilePath, String outputFilePath, int columnIndex) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

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
        Pair[] values = new Pair[n];

        for (int i = 0; i < n; i++) {
            try {
                long value = Long.parseLong(rows.get(i)[columnIndex]);
                values[i] = new Pair(value, i);
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido na linha " + (i + 2) + ": " + rows.get(i)[columnIndex]);
                values[i] = new Pair(0, i);
            }
        }

        quickSortPiorCaso(values, 0, n - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            for (Pair p : values) {
                bw.write(String.join(",", rows.get(p.index)));
                bw.newLine();
            }
        }
    }

    public static void quickSortPiorCaso(Pair[] v, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            ordenarMediana(v, esq, meio, dir);
            int pivoIndex = dir - 1;
            int pivoNovo = particionar(v, esq, dir, pivoIndex);
            quickSortPiorCaso(v, esq, pivoNovo - 1);
            quickSortPiorCaso(v, pivoNovo + 1, dir);
        }
    }
    
    private static void ordenarMediana(Pair[] v, int a, int b, int c) {
        if (v[c].value < v[b].value) swap(v, c, b);
        if (v[b].value < v[a].value) swap(v, b, a);
        if (v[c].value < v[b].value) swap(v, c, b);
        swap(v, b, c - 1);
    }
    
    private static int particionar(Pair[] v, int esq, int dir, int pivoIndex) {
        Pair pivo = v[pivoIndex];
        int i = esq;
        int j = dir - 1;
        while (true) {
            while (v[++i].value < pivo.value);
            while (v[--j].value > pivo.value);
            if (i >= j) break;
            swap(v, i, j);
        }
        swap(v, pivoIndex, i);
        return i;
    }
    
    private static void swap(Pair[] v, int i, int j) {
        Pair temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
    

    private static class Pair {
        long value;
        int index;

        Pair(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
