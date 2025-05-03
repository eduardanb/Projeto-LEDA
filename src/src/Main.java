import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Classificacao.ClassificacaoBoaeMuitoboa;
import Classificacao.ClassificacaoDeSenhas;
import Classificacao.Classificador;
import Classificacao.LerArquivosCsv;
import Classificacao.TransformadorDeDados;
import Ordenacoes.OrdenacaoLengthDecrescente;

public class Main {
    public static void main(String[] args) {
        // Definimos os caminhos dos arquivos que vamos usar
        String caminhoEntrada = "src\\ArquivosCSV\\passwords.csv";
        String caminhoSaida = "src\\ArquivosCSV\\password_classifier.csv";
        String caminhoTransformado = "src\\ArquivosCSV\\passwords_formated_data.csv";
        String caminhoBoaeMuitoBoa = "src\\ArquivosCSV\\passwords_classifier.csv";
        
        // Criamos os objetos para leitura e classificação das senhas
        LerArquivosCsv leitorCsv = new LerArquivosCsv(caminhoEntrada);
        Classificador classificador = new ClassificacaoDeSenhas();

        try {
            // Lemos o conteúdo do arquivo CSV original
            String[] registros = leitorCsv.lerCsv();
            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida));
            
            // Escrevemos o cabeçalho do novo arquivo CSV
            escritor.write("Posicao,Senha,Tamanho,Data,Class\n");

            // Iteramos sobre cada linha do CSV (exceto o cabeçalho)
            for (int i = 1; i < registros.length; i++) {
                String linha = registros[i];
                String[] registro = linha.split(",");
                if (registro.length < 4) {
                    // Ignoramos linhas inválidas
                    System.out.println("Linha inválida: " + linha);
                    continue;
                }

                // Classificamos a senha e escrevemos no novo arquivo
                String senha = registro[1];
                String classificacao = classificador.classificar(senha);
                escritor.write(registro[0] + "," + senha + "," + registro[2] + "," + registro[3] + "," + classificacao + "\n");
            }

            escritor.close();

            // Transformamos o arquivo para outro formato com base nas regras definidas
            ClassificacaoBoaeMuitoboa classificacaoBoaeMuitoboa = new ClassificacaoBoaeMuitoboa();
            TransformadorDeDados transformador = new TransformadorDeDados();
            transformador.transformar(caminhoSaida, caminhoTransformado);

            // Classificamos novamente de acordo com uma nova lógica (boa ou muito boa)
            classificacaoBoaeMuitoboa.classificar(caminhoSaida, caminhoBoaeMuitoBoa);

            // Exibimos no console o conteúdo do arquivo transformado
            exibirArquivo(caminhoBoaeMuitoBoa);

            OrdenacaoLengthDecrescente.Ordenacao(args);

        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    // Função auxiliar para exibir um arquivo linha por linha no console
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
