package br.com.forum.Forun.controller.form;

import br.com.forum.Forun.modelo.Topico;
import br.com.forum.Forun.repository.TopicoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TopicoAtualizacaoForm {
    @NotNull
    @NotEmpty
    @Length(min = 5, max = 50)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10, max = 500)
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setMensagem(this.mensagem);
        topico.setTitulo(this.titulo);

        return topico;
    }
}

