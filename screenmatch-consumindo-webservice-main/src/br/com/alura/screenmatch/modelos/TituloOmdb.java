// essa class reco e para fazer o processo de cerialize, do qual pego o nome dos atributos das cless usadas e transformo de json para o nome do objeto ex. nome esta como to title, ano esta como ear, vou peg

package br.com.alura.screenmatch.modelos;

// enbora os atributos so rodem se estiverem com letra maiuscula, tem que estar com letra minuscula, la na class principa; vc coloca uma funcao que resolve issop
public record TituloOmdb(String title, String year, String runtime) {
}
