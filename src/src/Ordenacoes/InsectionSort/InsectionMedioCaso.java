package Ordenacoes.InsectionSort;

import java.io.*;

public class InsectionMedioCaso {

    // Método para ordenar pelo campo "Tamanho" (length) em ordem decrescente
    public static void ordenarPorLength(String[] senhas) {
        int n = senhas.length;
        for (int i = 1; i < n; i++) {
            String chave = senhas[i];
            int tamanhoChave = Integer.parseInt(chave.split(",")[2]); // Obtém o tamanho da senha
            int j = i - 1;

            // Move os elementos maiores que a chave para uma posição à frente
            while (j >= 0 && Integer.parseInt(senhas[j].split(",")[2]) < tamanhoChave) {
                senhas[j + 1] = senhas[j];
                j--;
            }
            senhas[j + 1] = chave;
        }
    }

    // Método para ordenar pelo mês da coluna "Data" em ordem crescente
    public static void ordenarPorMes(String[] senhas) {
        int n = senhas.length;
        for (int i = 1; i < n; i++) {
            String chave = senhas[i];
            int mesChave = Integer.parseInt(chave.split(",")[3].split("/")[1]); // Obtém o mês
            int j = i - 1;

            // Move os elementos maiores que a chave para uma posição à frente
            while (j >= 0 && Integer.parseInt(senhas[j].split(",")[3].split("/")[1]) > mesChave) {
                senhas[j + 1] = senhas[j];
                j--;
            }
            senhas[j + 1] = chave;
        }
    }

    // Método para ordenar pela coluna "Data" completa em ordem crescente
    public static void ordenarPorData(String[] senhas) {
        int n = senhas.length;
        for (int i = 1; i < n; i++) {
            String chave = senhas[i];
            String[] dataChave = chave.split(",")[3].split("/"); // Obtém a data
            int j = i - 1;

            // Move os elementos maiores que a chave para uma posição à frente
            while (j >= 0) {
                String[] dataAtual = senhas[j].split(",")[3].split("/");
                boolean maior = Integer.parseInt(dataAtual[2]) > Integer.parseInt(dataChave[2]) || // Ano
                                (Integer.parseInt(dataAtual[2]) == Integer.parseInt(dataChave[2]) &&
                                 Integer.parseInt(dataAtual[1]) > Integer.parseInt(dataChave[1])) || // Mês
                                (Integer.parseInt(dataAtual[2]) == Integer.parseInt(dataChave[2]) &&
                                 Integer.parseInt(dataAtual[1]) == Integer.parseInt(dataChave[1]) &&
                                 Integer.parseInt(dataAtual[0]) > Integer.parseInt(dataChave[0])); // Dia
                if (!maior) break;

                senhas[j + 1] = senhas[j];
                j--;
            }
            senhas[j + 1] = chave;
        }
    }

    // Método principal para executar as ordenações
    public static void main(String[] args) {
        String caminhoEntrada = "src/ArquivosCSV/passwords_formated_data.csv";

        // Lê o arquivo de entrada
        String[] senhas = lerCsv(caminhoEntrada);

        // Ordenar por Tamanho (length) em ordem decrescente
        ordenarPorLength(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_length_insertionSort_medioCaso.csv");

        // Ordenar por Mês em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorMes(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_month_insertionSort_medioCaso.csv");

        // Ordenar por Data completa em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorData(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_insertionSort_medioCaso.csv");
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