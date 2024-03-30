package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao{

  @Autowired
  private AdocaoRepository adocaoRepository;

  public void validar(SolicitacaoAdocaoDto dto){
    boolean tutorComStatusAguardandoAvaliacao = adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(),StatusAdocao.AGUARDANDO_AVALIACAO);

      if (tutorComStatusAguardandoAvaliacao) {
        throw new ValidationException("Tutor já possui outra adoção aguardando avaliação!");
      }
  }
}
