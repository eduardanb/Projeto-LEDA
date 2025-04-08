import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformadorDeDados {
    public void transformar(String caminhoEntrada, String caminhoSaida) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida))) {

            String linha = leitor.readLine(); // Lê o cabeçalho
            if (linha != null) {
                escritor.write(linha); // Escreve o cabeçalho no novo arquivo
                escritor.newLine();
            }

            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length >= 4) {
                    String dataOriginal = campos[3];
                    String dataFormatada = formatarData(dataOriginal);
                    campos[3] = dataFormatada;
                    escritor.write(String.join(",", campos));
                    escritor.newLine();
                }
            }

            System.out.println("Arquivo transformado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao transformar o arquivo: " + e.getMessage());
        }
    }

    private String formatarData(String dataOriginal) {
        String[] partes = dataOriginal.split(" ")[0].split("-");
        if (partes.length == 3) {
            return partes[2] + "/" + partes[1] + "/" + partes[0]; // Formato DD/MM/AAAA
        }
        return dataOriginal; // Retorna a data original se não estiver no formato esperado
    }
}