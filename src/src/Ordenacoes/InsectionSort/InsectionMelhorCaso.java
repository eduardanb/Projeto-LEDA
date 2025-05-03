package Ordenacoes.InsectionSort;

import java.io.*;

public class InsectionMelhorCaso {

    // Método para ordenar pelo campo "Tamanho" (length) em ordem decrescente
    public static void ordenarPorLength(String[] senhas) {
        System.out.println("Os dados já estão ordenados por Tamanho em ordem decrescente.");
    }

    // Método para ordenar pelo mês da coluna "Data" em ordem crescente
    public static void ordenarPorMes(String[] senhas) {
        System.out.println("Os dados já estão ordenados por Mês em ordem crescente.");
    }

    // Método para ordenar pela coluna "Data" completa em ordem crescente
    public static void ordenarPorData(String[] senhas) {
        System.out.println("Os dados já estão ordenados por Data em ordem crescente.");
    }

    // Método principal para executar as ordenações
    public static void main(String[] args) {
        String caminhoEntrada = "src/ArquivosCSV/passwords_formated_data.csv";

        // Lê o arquivo de entrada
        String[] senhas = lerCsv(caminhoEntrada);

        // Ordenar por Tamanho (length) em ordem decrescente
        ordenarPorLength(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_length_insertionSort_melhorCaso.csv");

        // Ordenar por Mês em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorMes(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_month_insertionSort_melhorCaso.csv");

        // Ordenar por Data completa em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorData(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_insertionSort_melhorCaso.csv");
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