package br.com.fiap.umbl.scenarios;

import br.com.fiap.umbl.domain.EnderecoPontoColetaEntity;
import br.com.fiap.umbl.domain.PontoColetaEntity;
import br.com.fiap.umbl.repository.PontoColetaRepository;
import br.com.fiap.umbl.service.PontoColetaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static br.com.fiap.umbl.dto.cadastro.MaterialAceito.ISOPOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PontoColetaTests {

        @InjectMocks
        private PontoColetaService pontoColetaService;

        @Mock
        private PontoColetaRepository pontoColetaRepository;

    @Test
    void deveSalvarPontoColetaComTodosOsCamposCorretamente() {
        EnderecoPontoColetaEntity endereco = new EnderecoPontoColetaEntity();
        endereco.setLogradouro("Av Mineirinhos");
        endereco.setCep("12345-678");
        endereco.setNumero("104");
        endereco.setCidade("Minas Gerais");
        endereco.setBairro("Baiacu");
        endereco.setEstado("MG");

        PontoColetaEntity pontoColeta = new PontoColetaEntity();
        pontoColeta.setNome("Ponto de Coleta Fotez");
        pontoColeta.setCapacidade("15kg");
        pontoColeta.setMaterialAceito(ISOPOR);
        pontoColeta.setEmail("lasttesteddd@gmail.com");
        pontoColeta.setTelefone("(11) 98465-4322");
        pontoColeta.setEnderecoPontoColeta(endereco);

        when(pontoColetaRepository.save(any(PontoColetaEntity.class))).thenReturn(pontoColeta);

        PontoColetaEntity salvo = pontoColetaService.insertPontoColeta(pontoColeta);

        assertNotNull(salvo);
        assertEquals("Ponto de Coleta Fotez", salvo.getNome());
        assertEquals("15kg", salvo.getCapacidade());
        assertEquals(ISOPOR, salvo.getMaterialAceito());
        assertEquals("lasttesteddd@gmail.com", salvo.getEmail());
        assertEquals("(11) 98465-4322", salvo.getTelefone());

        assertNotNull(salvo.getEnderecoPontoColeta());
        assertEquals("Av Mineirinhos", salvo.getEnderecoPontoColeta().getLogradouro());
        assertEquals("12345-678", salvo.getEnderecoPontoColeta().getCep());
        assertEquals("104", salvo.getEnderecoPontoColeta().getNumero());
        assertEquals("Minas Gerais", salvo.getEnderecoPontoColeta().getCidade());
        assertEquals("Baiacu", salvo.getEnderecoPontoColeta().getBairro());
        assertEquals("MG", salvo.getEnderecoPontoColeta().getEstado());

        verify(pontoColetaRepository, times(1)).save(any(PontoColetaEntity.class));
    }

    @Test
    void deveDeletarPontoColetaQuandoIdExistente() {
        Long id = 1L;
        PontoColetaEntity ponto = new PontoColetaEntity();
        ponto.setId(id);

        when(pontoColetaRepository.findById(id)).thenReturn(Optional.of(ponto));

        pontoColetaService.deletePontoColeta(id);

        verify(pontoColetaRepository).delete(ponto);
    }



}

