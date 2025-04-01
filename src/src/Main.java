import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoEntrada = "src\\ArquivosCSV\\passwords.csv";
        String caminhoSaida = "src\\ArquivosCSV\\password_classifier.csv";

        LerArquivosCsv leitorCsv = new LerArquivosCsv(caminhoEntrada);
        try {
            List<String[]> registros = leitorCsv.lerCsv();
            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida));
            escritor.write("Posicao,Senha,Tamanho,Data,Classificacao\n");

            for (int i = 1; i < registros.size(); i++) {
                String[] registro = registros.get(i);
                if (registro.length < 4) {
                    System.out.println("Linha inválida: " + String.join(",", registro));
                    continue;
                }
                String senha = registro[1];
                String classificacao = ClassificacaoDeSenhas.classificar(senha);
                escritor.write(registro[0] + "," + senha + "," + registro[2] + "," + registro[3] + "," + classificacao + "\n");
                System.out.println("Posicao: " + registro[0] + " | Senha: " + senha + " | Tamanho: " + registro[2] + " | Data: " + registro[3] + " | Classificacao: " + classificacao);
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
}