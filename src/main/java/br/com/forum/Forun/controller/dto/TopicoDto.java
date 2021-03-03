package br.com.forum.Forun.controller.dto;

import br.com.forum.Forun.modelo.Topico;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public @Getter
class TopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }


    public static Page<TopicoDto> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDto::new);
    }
}
