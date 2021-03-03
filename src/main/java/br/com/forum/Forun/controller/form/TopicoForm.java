package br.com.forum.Forun.controller.form;

import br.com.forum.Forun.modelo.Curso;
import br.com.forum.Forun.modelo.Topico;
import br.com.forum.Forun.repository.CursoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public @Data

class TopicoForm {
    @NotNull
    @NotEmpty
    @Length(min = 5, max = 50)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10, max = 500)
    private String mensagem;
    @NotNull
    @NotEmpty
    @Length(min = 3, max = 30)
    private String nomeCurso;

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(this.nomeCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
