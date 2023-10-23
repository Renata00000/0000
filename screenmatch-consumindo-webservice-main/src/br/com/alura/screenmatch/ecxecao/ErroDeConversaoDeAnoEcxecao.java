package br.com.alura.screenmatch.ecxecao;
//criando minha ecxessao
public class ErroDeConversaoDeAnoEcxecao extends RuntimeException {

    private String mensagem;


    public ErroDeConversaoDeAnoEcxecao(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
