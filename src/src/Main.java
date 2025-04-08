import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoEntrada = "src\\ArquivosCSV\\passwords.csv";
        String caminhoSaida = "src\\ArquivosCSV\\password_classifier.csv";
        String caminhoTransformado = "src\\ArquivosCSV\\passwords_formated_data.csv";

        LerArquivosCsv leitorCsv = new LerArquivosCsv(caminhoEntrada);
        Classificador classificador = new ClassificacaoDeSenhas();

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
                String classificacao = classificador.classificar(senha);
                escritor.write(registro[0] + "," + senha + "," + registro[2] + "," + registro[3] + "," + classificacao + "\n");
            }

            escritor.close();

            // Transformar o arquivo para o novo formato
            TransformadorDeDados transformador = new TransformadorDeDados();
            transformador.transformar(caminhoSaida, caminhoTransformado);

            // Exibir o conteúdo do novo arquivo CSV no console
            exibirArquivo(caminhoTransformado);

        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    private static void exibirArquivo(String caminhoArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            System.out.println("\nConteúdo do arquivo gerado (" + caminhoArquivo + "):");
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}