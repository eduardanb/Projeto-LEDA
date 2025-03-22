import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LerArquivosCsv {
    public static void main(String[] args) {
        String cvsArquivo = "src\\ArquivosCSV\\passwords.csv";
        String novoArquivo = "src\\ArquivosCSV\\password_classifier.csv";
        BufferedReader conteudoCsv = null;
        BufferedWriter escritorCsv = null;
        String linha = "";
        String csvSeparadorCampo = ",";

        try {
            conteudoCsv = new BufferedReader(new FileReader(cvsArquivo));
            escritorCsv = new BufferedWriter(new FileWriter(novoArquivo));
            conteudoCsv.readLine();
            escritorCsv.write("Position,Password,Length,Data,Class\n");

            while ((linha = conteudoCsv.readLine()) != null) {
                String[] senha = linha.split(csvSeparadorCampo);
                String classificacao = classificarSenha(senha[1]);
                escritorCsv.write(senha[0] + csvSeparadorCampo + senha[1] + csvSeparadorCampo + senha[2] + csvSeparadorCampo + senha[3] + csvSeparadorCampo + classificacao + "\n");
                System.out.println("Posição: " + senha[0] + " | Senha: " + senha[1] + " | Tamanho: " + senha[2] + " | Data: " + senha[3] + " | Classificação: " + classificacao);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBounds: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Erro: " + e.getMessage());
        } finally {
            try {
                if (conteudoCsv != null) conteudoCsv.close();
                if (escritorCsv != null) escritorCsv.close();
            } catch (IOException e) {
                System.out.println("IO Erro ao fechar: " + e.getMessage());
            }
        }
    }

    private static String classificarSenha(String senha) {
        int length = senha.length();
        boolean temMinuscula = senha.matches(".*[a-z].*");
        boolean temMaiuscula = senha.matches(".*[A-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        boolean temEspecial = senha.matches(".*[^a-zA-Z0-9].*");

        int tipos = (temMinuscula ? 1 : 0) + (temMaiuscula ? 1 : 0) + (temNumero ? 1 : 0) + (temEspecial ? 1 : 0);

        if (length < 5 && tipos == 1) {
            return "Muito Ruim";
        } else if (length <= 5 && tipos == 1) {
            return "Ruim";
        } else if (length <= 6 && tipos == 2) {
            return "Fraca";
        } else if (length <= 7 && tipos == 3) {
            return "Boa";
        } else if (length > 8 && tipos == 4) {
            return "Muito Boa";
        } else {
            return "Sem Classificação";
        }
    }
}