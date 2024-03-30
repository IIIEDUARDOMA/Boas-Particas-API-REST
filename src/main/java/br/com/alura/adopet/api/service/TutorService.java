package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

  @Autowired
  private TutorRepository tutorRepository;

  public void cadastrarTutor(TutorDto dto){
    boolean tutorJaCadastrado = tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

    if (tutorJaCadastrado){
      throw new ValidacaoException("Dados já cadastrados para outro tutor!");
    }

    tutorRepository.save(new Tutor(dto));
  }

  public void atualizarTutor(AtualizacaoTutorDto dto) {
    var tutor = tutorRepository.getReferenceById(dto.id());
    tutorRepository.save(tutor);
  }
}
