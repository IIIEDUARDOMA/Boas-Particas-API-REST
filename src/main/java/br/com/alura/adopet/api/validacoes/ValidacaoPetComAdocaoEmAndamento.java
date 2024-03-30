package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

  @Autowired
  private AdocaoRepository adocaoRepository;

  public void validar(SolicitacaoAdocaoDto dto){
    boolean petComStatusAguardandoAvaliacao = adocaoRepository.existsByPetIdAndStatus(dto.idTutor(),StatusAdocao.AGUARDANDO_AVALIACAO);

      if (petComStatusAguardandoAvaliacao) {
        throw new ValidationException("Pet já possui outra adoção aguardando avaliação!");
      }
  }
}
