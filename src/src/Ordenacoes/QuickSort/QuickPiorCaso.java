package Ordenacoes.QuickSort;

import java.io.*;
import java.util.*;

public class QuickPiorCaso {

    // Método para ordenar pelo campo "Tamanho" (length) em ordem decrescente
    public static void ordenarPorLength(String[] senhas) {
        prepararPiorCaso(senhas, 2, false); // Prepara o pior caso para o campo "length" (coluna 2)
        quickSort(senhas, 0, senhas.length - 1, 2, false);
    }

    // Método para ordenar pelo mês da coluna "Data" em ordem crescente
    public static void ordenarPorMes(String[] senhas) {
        prepararPiorCaso(senhas, 3, true); // Prepara o pior caso para o campo "mês" (coluna 3)
        quickSort(senhas, 0, senhas.length - 1, 3, true);
    }

    // Método para ordenar pela coluna "Data" completa em ordem crescente
    public static void ordenarPorData(String[] senhas) {
        prepararPiorCaso(senhas, 3, true); // Prepara o pior caso para o campo "data" completa (coluna 3)
        quickSort(senhas, 0, senhas.length - 1, 3, true);
    }

    // Método para preparar o pior caso
    private static void prepararPiorCaso(String[] senhas, int coluna, boolean crescente) {
        Arrays.sort(senhas, (a, b) -> {
            String valorA = a.split(",")[coluna];
            String valorB = b.split(",")[coluna];
            if (coluna == 3 && !crescente) { // Para "data", ordena por ano, mês e dia
                String[] dataA = valorA.split("/");
                String[] dataB = valorB.split("/");
                int comparacao = Integer.compare(Integer.parseInt(dataB[2]), Integer.parseInt(dataA[2])); // Ano
                if (comparacao == 0) {
                    comparacao = Integer.compare(Integer.parseInt(dataB[1]), Integer.parseInt(dataA[1])); // Mês
                }
                if (comparacao == 0) {
                    comparacao = Integer.compare(Integer.parseInt(dataB[0]), Integer.parseInt(dataA[0])); // Dia
                }
                return comparacao;
            }
            return crescente ? valorB.compareTo(valorA) : valorA.compareTo(valorB);
        });
    }

    // Método recursivo para aplicar QuickSort
    private static void quickSort(String[] senhas, int low, int high, int coluna, boolean crescente) {
        if (low < high) {
            int pivotIndex = partition(senhas, low, high, coluna, crescente);
            quickSort(senhas, low, pivotIndex - 1, coluna, crescente);
            quickSort(senhas, pivotIndex + 1, high, coluna, crescente);
        }
    }

    // Método para particionar o array e retornar o índice do pivô
    private static int partition(String[] senhas, int low, int high, int coluna, boolean crescente) {
        String pivot = senhas[high].split(",")[coluna];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            String valorAtual = senhas[j].split(",")[coluna];
            boolean condicao = crescente ? valorAtual.compareTo(pivot) <= 0 : valorAtual.compareTo(pivot) >= 0;
            if (condicao) {
                i++;
                swap(senhas, i, j);
            }
        }

        swap(senhas, i + 1, high);
        return i + 1;
    }

    // Método para trocar elementos no array
    private static void swap(String[] senhas, int i, int j) {
        String temp = senhas[i];
        senhas[i] = senhas[j];
        senhas[j] = temp;
    }

    // Método principal para executar as ordenações
    public static void main(String[] args) {
        String caminhoEntrada = "src/ArquivosCSV/passwords_formated_data.csv";

        // Lê o arquivo de entrada
        String[] senhas = lerCsv(caminhoEntrada);

        // Ordenar por Tamanho (length) em ordem decrescente
        ordenarPorLength(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_length_quickSort_piorCaso.csv");

        // Ordenar por Mês em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorMes(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_month_quickSort_piorCaso.csv");

        // Ordenar por Data completa em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorData(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_quickSort_piorCaso.csv");
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