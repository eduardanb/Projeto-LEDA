import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivosCsv {
    private String caminhoArquivo;

    public LerArquivosCsv(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public List<String[]> lerCsv() throws IOException {
        List<String[]> registros = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(",");
            registros.add(campos);
        }
        leitor.close();
        return registros;
    }
}