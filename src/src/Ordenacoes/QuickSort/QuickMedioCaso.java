package Ordenacoes.QuickSort;

import java.io.*;
import java.util.*;

public class QuickMedioCaso {

    // Método para ordenar um arquivo CSV pelo campo "length" em ordem decrescente
    public static void quickSortCSVLength(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null;

        // Ler o arquivo CSV
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

        // Extrair a coluna "length" e convertê-la para um array
        int n = rows.size();
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            try {
                lengths[i] = (int) Double.parseDouble(rows.get(i)[2]); // Ajuste o índice da coluna para 2
            } catch (NumberFormatException e) {
                System.err.println("Valor inválido encontrado na linha " + (i + 1) + ": " + rows.get(i)[2]);
                lengths[i] = 0; // Define um valor padrão
            }
        }

        // Aplicar QuickSort na coluna "length"
        int[] sortedIndices = quickSortWithIndices(lengths);

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

    // Método auxiliar para aplicar QuickSort e retornar os índices ordenados
    private static int[] quickSortWithIndices(int[] array) {
        int n = array.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i; // Inicializa os índices
        }
        quickSortHelper(array, indices, 0, n - 1);
        return indices;
    }

    // Método recursivo para dividir o array e aplicar o QuickSort
    private static void quickSortHelper(int[] array, int[] indices, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, indices, low, high);
            quickSortHelper(array, indices, low, pivotIndex - 1);
            quickSortHelper(array, indices, pivotIndex + 1, high);
        }
    }

    // Método para particionar o array e retornar o índice do pivô
    private static int partition(int[] array, int[] indices, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] >= pivot) { // Ordenação decrescente
                i++;
                swap(array, i, j);
                swap(indices, i, j);
            }
        }

        swap(array, i + 1, high);
        swap(indices, i + 1, high);

        return i + 1;
    }

    // Método para trocar elementos no array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Método para ler o arquivo CSV
    public static String[] lerCsv(String caminhoArquivo) {
        String[] linhas = null;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int totalLinhas = (int) leitor.lines().count(); // Conta o número de linhas
            leitor.close(); // Fecha e reabre para reiniciar a leitura
            BufferedReader leitor2 = new BufferedReader(new FileReader(caminhoArquivo));
            linhas = new String[totalLinhas];
            int index = 0;
            while ((linha = leitor2.readLine()) != null) {
                linhas[index++] = linha;
            }
            leitor2.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return linhas;
    }

    // Método para escrever o arquivo CSV
    public static void escreverCsv(String[] linhas, String caminhoArquivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                escritor.write(linha);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}