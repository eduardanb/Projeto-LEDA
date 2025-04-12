import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivosCsv {
    private String caminhoArquivo;

    // Inicializamos a classe com o caminho do arquivo CSV
    public LerArquivosCsv(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Lemos o arquivo CSV e retornamos todas as linhas em um array de Strings
    public String[] lerCsv() throws IOException {
        int totalLinhas = contarLinhas(); // Primeiro, contamos quantas linhas o arquivo possui
        String[] registros = new String[totalLinhas]; // Criamos um array para armazenar as linhas

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int index = 0;
            // Lemos cada linha do arquivo e a armazenamos no array
            while ((linha = leitor.readLine()) != null) {
                registros[index++] = linha;
            }
        }
        return registros; // Retornamos todas as linhas do arquivo
    }

    // Método auxiliar para contar o número de linhas no arquivo
    private int contarLinhas() throws IOException {
        int linhas = 0;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            while (leitor.readLine() != null) {
                linhas++; // Incrementamos a contagem para cada linha lida
            }
        }
        return linhas; // Retornamos o total de linhas
    }
}
