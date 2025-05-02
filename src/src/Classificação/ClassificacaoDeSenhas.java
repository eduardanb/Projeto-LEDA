package Classificação;
public class ClassificacaoDeSenhas implements Classificador {

    @Override
    public String classificar(String senha) {
        // Verificamos se a senha está vazia ou nula
        if (senha == null || senha.isEmpty()) {
            return "Sem Classificacao";
        }

        int tamanho = senha.length();

        // Verificamos se a senha possui cada tipo de caractere, utilizando o regex
        boolean temMinuscula = senha.matches(".*[a-z].*");
        boolean temMaiuscula = senha.matches(".*[A-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        boolean temEspecial = senha.matches(".*[^a-zA-Z0-9].*");

        // Contamos quantos tipos diferentes de caracteres a senha possui
        int tipos = (temMinuscula ? 1 : 0) + (temMaiuscula ? 1 : 0) + (temNumero ? 1 : 0) + (temEspecial ? 1 : 0);

        // Classificamos com base no tamanho e na diversidade de caracteres
        if (tamanho < 5 && tipos == 1) {
            return "Muito Ruim";
        } else if (tamanho <= 5 && tipos == 1) {
            return "Ruim";
        } else if (tamanho <= 6 && tipos == 2) {
            return "Fraca";
        } else if (tamanho <= 7 && tipos == 4) {
            return "Boa";
        } else if (tamanho > 8 && tipos == 4) {
            return "Muito Boa";
        } else {
            return "Sem Classificacao";
        }
    }
}
