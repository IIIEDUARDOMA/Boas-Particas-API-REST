package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;

public record CadastrarAbrigoDto(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank String email
) {
  public CadastrarAbrigoDto(Abrigo abrigo){
    this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getTelefone());
  }
}
