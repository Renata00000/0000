package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;


//criar record, por debaixo dos panos e uma class so que mais enchuto
// especialidades sao fixo, constante entao criar um enull uma class
// criar outro record para colocar dados de enreco


// bim validachion, dependencia p fazer validar minhas informacoes  dispensando if/else,

public record DadosdeCadastroMedico(
        //@NotNull // campo nome nao pode ser nulo
        @NotBlank // campo de preenchimento obrigatorio  verifica se o nome n esta vazio e tambem verifica se esta null, entao posso tirar o nul
        String nome,
        @NotBlank
        @Email // alem de notBlack esse @email indica qe o campo precisa ser exatamente expecifico e preenchido como e mail
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "//d{4,6}")  // estou dizendo que e ujm campo de 4 a 6 digitos
        String crm,
        @NotNull // n e notblanc pois not blank e so para campos string
        Especialidade especialidade,

        @NotNull
        @Valid // dados cadastrais estao em outra reco, @valid e para dizer que esse dado nao esta nessa record mas eu quero que valide ele
        DadosEndereco endereco) {


}
