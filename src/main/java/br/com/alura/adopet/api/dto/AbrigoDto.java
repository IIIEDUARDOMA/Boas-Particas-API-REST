package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbrigoDto(
        @NotNull Long id,
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank String email
) {
  public AbrigoDto (Abrigo abrigo){
    this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
  }
}
