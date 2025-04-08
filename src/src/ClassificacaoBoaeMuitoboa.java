import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClassificacaoBoaeMuitoboa {
    public void classificar(String caminhoEntrada, String caminhoSaida) {
        LerArquivosCsv leitorCsv = new LerArquivosCsv(caminhoEntrada);
        Classificador classificador = new ClassificacaoDeSenhas();

        try {
            String[] registros = leitorCsv.lerCsv(); // Método ajustado para retornar um array
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida))) {
                escritor.write("Posicao,Senha,Tamanho,Data,Classificacao\n");

                for (int i = 1; i < registros.length; i++) {
                    String[] registro = registros[i].split(",");

                    if (registro.length < 4) {
                        System.err.println("Linha inválida: " + String.join(",", registro));
                        continue;
                    }

                    String senha = registro[1];
                    String classificacao = classificador.classificar(senha);

                    if ("Boa".equals(classificacao) || "Muito Boa".equals(classificacao)) {
                        escritor.write(registro[0] + "," + senha + "," + registro[2] + "," + registro[3] + "," + classificacao + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
