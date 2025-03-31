public class ClassificacaoDeSenhas {
    public static String classificar(String senha) {
        int tamanho = senha.length();
        boolean temMinuscula = senha.matches(".[a-z].");
        boolean temMaiuscula = senha.matches(".[A-Z].");
        boolean temNumero = senha.matches(".[0-9].");
        boolean temEspecial = senha.matches(".[^a-zA-Z0-9].");

        int tipos = (temMinuscula ? 1 : 0) + (temMaiuscula ? 1 : 0) + (temNumero ? 1 : 0) + (temEspecial ? 1 : 0);

        if (tamanho < 5 && tipos == 1) {
            return "Muito Ruim";
        } else if (tamanho <= 5 && tipos == 1) {
            return "Ruim";
        } else if (tamanho <= 6 && tipos == 2) {
            return "Fraca";
        } else if (tamanho <= 7 && tipos == 3) {
            return "Boa";
        } else if (tamanho > 8 && tipos == 4) {
            return "Muito Boa";
        } else {
            return "Sem Classificação";
        }
    }
}
