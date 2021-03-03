package br.com.forum.Forun.config.validacao;


import lombok.Getter;

public @Getter
class ErroDeFormularioDTO {

    private String campo;
    private String erro;

    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
