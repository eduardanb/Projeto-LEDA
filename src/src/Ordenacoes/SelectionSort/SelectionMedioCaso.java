package Ordenacoes.SelectionSort;

import java.io.*;

public class SelectionMedioCaso {

    // Método para ordenar pelo campo "Tamanho" (length) em ordem decrescente
    public static void ordenarPorLength(String[][] senhas) {
        int n = senhas.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (Integer.parseInt(senhas[j][2]) > Integer.parseInt(senhas[maxIndex][2])) {
                    maxIndex = j;
                }
            }
            // Troca as posições
            String[] temp = senhas[maxIndex];
            senhas[maxIndex] = senhas[i];
            senhas[i] = temp;
        }
    }

    // Método para ordenar pelo mês da coluna "Data" em ordem crescente
    public static void ordenarPorMes(String[][] senhas) {
        int n = senhas.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                int mesAtual = Integer.parseInt(senhas[j][3].split("/")[1]); // Obtém o mês
                int mesMin = Integer.parseInt(senhas[minIndex][3].split("/")[1]); // Obtém o mês
                if (mesAtual < mesMin) {
                    minIndex = j;
                }
            }
            // Troca as posições
            String[] temp = senhas[minIndex];
            senhas[minIndex] = senhas[i];
            senhas[i] = temp;
        }
    }

    // Método para ordenar pela coluna "Data" completa em ordem crescente
    public static void ordenarPorData(String[][] senhas) {
        int n = senhas.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                String[] dataAtual = senhas[j][3].split("/");
                String[] dataMin = senhas[minIndex][3].split("/");

                // Compara ano, mês e dia
                if (Integer.parseInt(dataAtual[2]) < Integer.parseInt(dataMin[2]) || // Ano
                    (Integer.parseInt(dataAtual[2]) == Integer.parseInt(dataMin[2]) &&
                     Integer.parseInt(dataAtual[1]) < Integer.parseInt(dataMin[1])) || // Mês
                    (Integer.parseInt(dataAtual[2]) == Integer.parseInt(dataMin[2]) &&
                     Integer.parseInt(dataAtual[1]) == Integer.parseInt(dataMin[1]) &&
                     Integer.parseInt(dataAtual[0]) < Integer.parseInt(dataMin[0]))) { // Dia
                    minIndex = j;
                }
            }
            // Troca as posições
            String[] temp = senhas[minIndex];
            senhas[minIndex] = senhas[i];
            senhas[i] = temp;
        }
    }

    // Método principal para executar as ordenações
    public static void main(String[] args) {
        String caminhoEntrada = "src/ArquivosCSV/passwords_formated_data.csv";

        // Lê o arquivo de entrada
        String[][] senhas = lerCsv(caminhoEntrada);

        // Ordenar por Tamanho (length) em ordem decrescente
        ordenarPorLength(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_length_selectionSort_medioCaso.csv");

        // Ordenar por Mês em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorMes(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_month_selectionSort_medioCaso.csv");

        // Ordenar por Data completa em ordem crescente
        senhas = lerCsv(caminhoEntrada); // Recarrega o arquivo original
        ordenarPorData(senhas);
        escreverCsv(senhas, "src/ArquivosCSV/passwords_data_selectionSort_medioCaso.csv");
    }

    // Método para ler o arquivo CSV
    public static String[][] lerCsv(String caminhoArquivo) {
        String[][] linhas = null;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int totalLinhas = (int) leitor.lines().count(); // Conta o número de linhas
            leitor.close(); // Fecha e reabre para reiniciar a leitura
            BufferedReader leitor2 = new BufferedReader(new FileReader(caminhoArquivo));
            linhas = new String[totalLinhas][];
            int index = 0;
            while ((linha = leitor2.readLine()) != null) {
                linhas[index++] = linha.split(",");
            }
            leitor2.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return linhas;
    }

    // Método para escrever o arquivo CSV
    public static void escreverCsv(String[][] linhas, String caminhoArquivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String[] linha : linhas) {
                escritor.write(String.join(",", linha));
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}