package br.com.fiap.umbl.dto.atualizacao;

import io.swagger.v3.oas.annotations.media.Schema;

public record AtualizarEnderecoPontoColeta(

        @Schema()
        String logradouro,
        @Schema()
        String cep,
        @Schema()
        String numero,
        @Schema()
        String cidade,
        @Schema()
        String bairro,
        @Schema()
        String estado
) {
}
