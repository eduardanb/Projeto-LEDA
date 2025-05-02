package Classificação;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformadorDeDados {
    public void transformar(String caminhoEntrada, String caminhoSaida) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida))) {

            // Lemos o cabeçalho do arquivo e o copiamos para o novo arquivo
            String linha = leitor.readLine();
            if (linha != null) {
                escritor.write(linha);
                escritor.newLine();
            }

            // Lemos cada linha do arquivo original
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length >= 4) {
                    // Formatamos a data para o padrão DD/MM/AAAA
                    String dataOriginal = campos[3];
                    String dataFormatada = formatarData(dataOriginal);
                    campos[3] = dataFormatada;

                    // Escrevemos a nova linha no arquivo de saída
                    escritor.write(String.join(",", campos));
                    escritor.newLine();
                }
            }

            // Informamos que a transformação foi concluída com sucesso
            System.out.println("Arquivo transformado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            // Tratamos possíveis erros de leitura ou escrita
            System.out.println("Erro ao transformar o arquivo: " + e.getMessage());
        }
    }

    // Método auxiliar para formatar a data no padrão DD/MM/AAAA
    private String formatarData(String dataOriginal) {
        String[] partes = dataOriginal.split(" ")[0].split("-");
        if (partes.length == 3) {
            return partes[2] + "/" + partes[1] + "/" + partes[0];
        }
        // Se o formato não for o esperado, retornamos a data original
        return dataOriginal;
    }
}
