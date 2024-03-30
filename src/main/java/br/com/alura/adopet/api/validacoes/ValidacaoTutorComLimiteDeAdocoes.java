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
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao{

  @Autowired
  private AdocaoRepository adocaoRepository;

  public void validar(SolicitacaoAdocaoDto dto){
      boolean tutorComStatusAprovado = adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(),StatusAdocao.APROVADO);
      int contador = 0;
      if (tutorComStatusAprovado) {
        contador = contador + 1;
      }
      if (contador == 5) {
        throw new ValidationException("Tutor chegou ao limite máximo de 5 adoções!");
      }
  }
}
