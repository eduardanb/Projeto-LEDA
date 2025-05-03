package Ordenacoes.MergeSort;

import java.io.*;
import java.util.*;

public class MergePiorCaso {

    // Método para ordenar um arquivo CSV no pior caso usando Merge Sort
    public static void mergeSortCSVPiorCaso(String inputFilePath, String outputFilePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        String[] header = null; // Para armazenar os cabeçalhos

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
                lengths[i] = 0; // Define um valor padrão ou trate conforme necessário
            }
        }

        // Preparar o pior caso (alternar valores altos e baixos)
        prepareWorstCase(lengths);

        // Aplicar Merge Sort na coluna "length"
        int[] sortedIndices = mergeSortWithIndices(lengths);

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

    // Método para preparar o pior caso (alternar valores altos e baixos)
    private static void prepareWorstCase(int[] array) {
        Arrays.sort(array); // Ordena em ordem crescente
        int[] temp = new int[array.length];
        int left = 0, right = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                temp[i] = array[right--]; // Pega o maior valor disponível
            } else {
                temp[i] = array[left++]; // Pega o menor valor disponível
            }
        }
        System.arraycopy(temp, 0, array, 0, array.length); // Copia de volta para o array original
    }

    // Método auxiliar para aplicar Merge Sort e retornar os índices ordenados
    private static int[] mergeSortWithIndices(int[] array) {
        int n = array.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i; // Inicializa os índices
        }
        mergeSortHelper(array, indices, 0, n - 1);
        return indices;
    }

    // Método recursivo para dividir o array e aplicar o Merge Sort
    private static void mergeSortHelper(int[] array, int[] indices, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(array, indices, left, mid);
            mergeSortHelper(array, indices, mid + 1, right);
            merge(array, indices, left, mid, right);
        }
    }

    // Método para mesclar dois subarrays ordenados
    private static void merge(int[] array, int[] indices, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        int[] leftIndices = new int[n1];
        int[] rightIndices = new int[n2];

        // Copia os dados para os arrays temporários
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
            leftIndices[i] = indices[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = array[mid + 1 + i];
            rightIndices[i] = indices[mid + 1 + i];
        }

        // Mescla os arrays temporários de volta no array original
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] >= rightArray[j]) {
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

        // Copia os elementos restantes de leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            indices[k] = leftIndices[i];
            i++;
            k++;
        }

        // Copia os elementos restantes de rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            indices[k] = rightIndices[j];
            j++;
            k++;
        }
    }
}