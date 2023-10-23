package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.ecxecao.ErroDeConversaoDeAnoEcxecao;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in); //para escanear t dar a opcao de escolher
       String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()  //como estava antes  Gson gson = new Gson(); , foi modificado para resolver os problemas de minusculas que nao rodam no jonson
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                // vai deixar o json um em baixo do outro
                .setPrettyPrinting()
                .create();

       //enquanto busca for diferente de sair ele executa, so sai quando eu dig sair
       while(! busca.equalsIgnoreCase("sair")) {
           System.out.println("diga qual filme deseja consultar ");
           busca = leitura.nextLine();

           if (busca.equalsIgnoreCase("sair")){
               break;
           }

           //. replace p concatenar o espaco em branco, de um nome tipo                 a era do gelo
           String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=9d930738";

           try {
               HttpClient client = HttpClient.newHttpClient();
               HttpRequest request = HttpRequest.newBuilder()         // a baixo requisicao
                       .uri(URI.create(endereco))
                       .build();  // a baixo resposta da reqisicaso
               HttpResponse<String> response = client
                       .send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
               String json = response.body();  //criando json pedindo resposta do body  que esta na api
               System.out.println(json);

//               // declarando o gson como qualquer outro objeto
//               Gson gson = new GsonBuilder()  //como estava antes  Gson gson = new Gson(); , foi modificado para resolver os problemas de minusculas que nao rodam no jonson
//                       .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                       .create();
               //        Titulo meutitulo = gson.fromJson(json, Titulo.class);
               TituloOmdb meutituloOmdb = gson.fromJson(json, TituloOmdb.class);
               System.out.println(meutituloOmdb);

               Titulo meuTitulo = new Titulo(meutituloOmdb);
               System.out.println("titulo ja convertido");
               System.out.println(meuTitulo);

               // Filew.. e um txt, que vai escrever um arquivo com oque eu estou imprimindo na tela
//               FileWriter escrita = new FileWriter("Filme.TXT");
//               escrita.write(meuTitulo.toString());
//               escrita.close();  // clouse p entender que o arquivo acabou de ser escrito

                titulos.add(meuTitulo);
           } catch (NumberFormatException ex) {
               System.out.println("aconteceu um erro;");
               System.out.println(ex.getMessage());
           } catch (IllegalArgumentException ex) {
               System.out.println("algo deu errado, consulte o endereco");
               System.out.println(ex.getMessage());
           } catch (ErroDeConversaoDeAnoEcxecao ex) {
               System.out.println("ops tivemos um erro");
           }
       }
        System.out.println(titulos);
       //convertendo de lista para json
       FileWriter escrita = new FileWriter("filme.jasn");
       escrita.close();
       escrita.write(gson.toJson(titulos));


            System.out.println("programa finalizado com sucesso");

    }

}
