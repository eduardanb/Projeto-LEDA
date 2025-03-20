import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivosCsv {
    public static void main(String[] args) {
        String cvsArquivo = "C:\\Users\\eduar\\Documents\\UEPB - 4º Período\\LEDA\\Projeto-LEDA\\passwords.csv";
        BufferedReader conteudoCsv = null;
        String linha = "";
        String csvSeparadorCampo = ",";

        try {
            conteudoCsv = new BufferedReader(new FileReader(cvsArquivo));
            while ((linha = conteudoCsv.readLine()) != null) {
                String[] senha = linha.split(csvSeparadorCampo);
                System.out.println("[Position = " + senha[0] 
                        + " , Password = " + senha[1]
                        + " , Length = " + senha[2]
                        + " , Data = " + senha[3] + "]");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: \n" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBounds: \n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Erro: \n" + e.getMessage());
        } finally {
            if (conteudoCsv != null) {
                try {
                    conteudoCsv.close();
                } catch (IOException e) {
                    System.out.println("IO Erro: \n" + e.getMessage());
                }
            }
        }
    }
}

