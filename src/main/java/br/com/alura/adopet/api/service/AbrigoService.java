package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

  @Autowired
  private AbrigoRepository abrigoRepository;
  @Autowired
  private PetRepository petRepository;

  public List<AbrigoDto> listar(){
      return abrigoRepository.findAll()
              .stream()
              .map(AbrigoDto :: new)
              .toList();
  }

  public void cadastrarAbrigo(CadastrarAbrigoDto dto) {
   boolean abrigoJaCadastrado = abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(),dto.telefone(),dto.email());

    if (abrigoJaCadastrado) {
      throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
    }
    abrigoRepository.save(new Abrigo(dto));
  }

  public List<PetDto> listarPet(String idOuNome) {
    Abrigo abrigo = carregarAbrigo(idOuNome);
    return petRepository.findByAbrigo(abrigo)
            .stream()
            .map(PetDto::new)
            .toList();

  }

  public Abrigo carregarAbrigo(String idOuNome) {
    Optional<Abrigo> optional;
    try {
      Long id = Long.parseLong(idOuNome);
      optional = abrigoRepository.findById(id);
    } catch (NumberFormatException exception) {
      optional = Optional.ofNullable(abrigoRepository.findByNome(idOuNome));
    }

    return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
  }

}
