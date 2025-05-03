package Ordenacoes.CountingSort;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CountingMedioCaso {

    public static void countingSortCSVlength(String inputFilePath, String outputFilePath, int max) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null; // Para armazenar os cabeçalhos
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(","); // Armazena os cabeçalhos
                    isFirstLine = false;
                    continue; // Pula a primeira linha
                }
                rows.add(line.split(","));
            }
        }
    
        // Extrair a coluna "length" e calcular o valor máximo dinamicamente
        int n = rows.size();
        int[] lengths = new int[n];
        int dynamicMax = 0; // Valor máximo dinâmico
        for (int i = 0; i < n; i++) {
            try {
                // Tenta converter o valor para inteiro
                lengths[i] = (int) Double.parseDouble(rows.get(i)[2]); // Ajuste o índice da coluna para 2
                if (lengths[i] > dynamicMax) {
                    dynamicMax = lengths[i]; // Atualiza o valor máximo
                }
            } catch (NumberFormatException e) {
                // Trata valores inválidos
                System.err.println("Valor inválido encontrado na linha " + (i + 1) + ": " + rows.get(i)[2]);
                lengths[i] = 0; // Define um valor padrão ou trate conforme necessário
            }
        }
    
        // Atualizar o valor de max com o valor máximo dinâmico
        max = dynamicMax;
    
        // Aplicar Counting Sort na coluna "length"
        int[] outputIndices = countingSort(lengths, max);
    
        // Reorganizar as linhas do CSV com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : outputIndices) {
            sortedRows.add(rows.get(index));
        }
    
        // Escrever o arquivo CSV ordenado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Escrever os cabeçalhos primeiro
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            // Escrever as linhas ordenadas
            for (String[] row : sortedRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    public static void countingSortCSVData(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null; // Para armazenar os cabeçalhos
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    header = line.split(","); // Armazena os cabeçalhos
                    isFirstLine = false;
                    continue; // Pula a primeira linha
                }
                rows.add(line.split(","));
            }
        }
    
        // Extrair a coluna "Data" e converter para timestamps
        int n = rows.size();
        long[] timestamps = new long[n];
        long maxTimestamp = Long.MIN_VALUE; // Valor máximo dinâmico
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
        for (int i = 0; i < n; i++) {
            try {
                // Converte a data para timestamp
                timestamps[i] = dateFormat.parse(rows.get(i)[3]).getTime(); // Índice 3 para a coluna "Data"
                if (timestamps[i] > maxTimestamp) {
                    maxTimestamp = timestamps[i]; // Atualiza o valor máximo
                }
            } catch (ParseException e) {
                // Trata valores inválidos
                System.err.println("Data inválida encontrada na linha " + (i + 1) + ": " + rows.get(i)[3]);
                timestamps[i] = 0; // Define um valor padrão ou trate conforme necessário
            }
        }
    
        // Aplicar Counting Sort na coluna "Data" (convertida para timestamps)
        int[] outputIndices = countingSort(timestamps, maxTimestamp);
    
        // Reorganizar as linhas do CSV com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : outputIndices) {
            sortedRows.add(rows.get(index));
        }
    
        // Escrever o arquivo CSV ordenado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Escrever os cabeçalhos primeiro
            if (header != null) {
                bw.write(String.join(",", header));
                bw.newLine();
            }
            // Escrever as linhas ordenadas
            for (String[] row : sortedRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }
    
    // Método para ordenar por mês no médio caso
    public static void countingSortCSVDataMes(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Ler o arquivo CSV
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

        // Extrair a coluna "mês" (ajuste o índice conforme necessário)
        int n = rows.size();
        int[] months = new int[n];
        for (int i = 0; i < n; i++) {
            months[i] = Integer.parseInt(rows.get(i)[1]); // Supondo que o mês está na coluna 1
        }

        // Aplicar Counting Sort no médio caso
        int max = Arrays.stream(months).max().orElse(12);
        int[] sortedIndices = countingSort(months, max);

        // Reorganizar as linhas do CSV com base na ordenação
        List<String[]> sortedRows = new ArrayList<>();
        for (int index : sortedIndices) {
            sortedRows.add(rows.get(index));
        }

        // Escrever o arquivo CSV ordenado
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

    private static int[] countingSort(int[] arr, int max) {
        int n = arr.length;
        int[] output = new int[n]; // Índices ordenados
        int[] count = new int[max + 1]; // Array de contagem
    
        // Inicializar o array de contagem
        for (int i = 0; i <= max; i++) {
            count[i] = 0;
        }
    
        // Contar ocorrências
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
    
        // Ajustar o array de contagem
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
    
        // Construir o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = i; // Armazena o índice original
            count[arr[i]]--;
        }
    
        return output;
    }

    private static int[] countingSort(long[] arr, long max) {
        int n = arr.length;
        int[] output = new int[n]; // Índices ordenados
        int[] count;

        // Verifica se o valor de max é válido
        if (max > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("O valor máximo é muito grande para o Counting Sort.");
        }

        // Inicializar o array de contagem
        count = new int[(int) (max + 1)];
        for (int i = 0; i <= max; i++) {
            count[i] = 0;
        }

        // Contar ocorrências
        for (int i = 0; i < n; i++) {
            count[(int) arr[i]]++;
        }

        // Ajustar o array de contagem
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Construir o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[(int) arr[i]] - 1] = i; // Armazena o índice original
            count[(int) arr[i]]--;
        }

        return output;
    }

    
}
