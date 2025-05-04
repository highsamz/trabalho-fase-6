package br.com.fiap.umbl.dto.listagem;

import br.com.fiap.umbl.domain.PontoColetaEntity;
import br.com.fiap.umbl.dto.cadastro.MaterialAceito;

public record ListagemPontoColeta (

        Long id,
        String nome,
        MaterialAceito materialAceito,
        String email,
        String telefone
){
    public ListagemPontoColeta(PontoColetaEntity pontoColeta){
        this(pontoColeta.getId(), pontoColeta.getNome(), pontoColeta.getMaterialAceito(), pontoColeta.getEmail(), pontoColeta.getTelefone());
    }
}
