package Classificação;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClassificacaoBoaeMuitoboa {

    public void classificar(String caminhoEntrada, String caminhoSaida) {
        LerArquivosCsv leitorCsv = new LerArquivosCsv(caminhoEntrada);
        Classificador classificador = new ClassificacaoDeSenhas();

        try {
            String[] registros = leitorCsv.lerCsv();

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida))) {
                escritor.write("Posicao,Senha,Tamanho,Data,Class\n");

                for (int i = 1; i < registros.length; i++) {
                    String[] registro = registros[i].split(",");

                    // Garantimos que a linha tem dados suficientes
                    if (registro.length < 4) continue;

                    String senha = registro[1];
                    String classificacao = classificador.classificar(senha);

                    // Escrevemos apenas senhas boas ou muito boas
                    if ("Boa".equals(classificacao) || "Muito Boa".equals(classificacao)) {
                        escritor.write(registro[0] + "," + senha + "," + registro[2] + "," + registro[3] + "," + classificacao + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
}
