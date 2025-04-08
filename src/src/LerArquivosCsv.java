import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivosCsv {
    private String caminhoArquivo;

    public LerArquivosCsv(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String[] lerCsv() throws IOException {
        int totalLinhas = contarLinhas();
        String[] registros = new String[totalLinhas];

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int index = 0;
            while ((linha = leitor.readLine()) != null) {
                registros[index++] = linha;
            }
        }
        return registros;
    }

    private int contarLinhas() throws IOException {
        int linhas = 0;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            while (leitor.readLine() != null) {
                linhas++;
            }
        }
        return linhas;
    }
}