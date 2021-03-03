package br.com.forum.Forun.controller.dto;

import br.com.forum.Forun.modelo.StatusTopico;
import br.com.forum.Forun.modelo.Topico;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicoDtoDetalhes {
    private Long id;
    private String titulo;
    private String mensagem;
    private String nomeAutor;
    private StatusTopico status;
    private LocalDateTime dataCriacao;
    private List<RespostaDto> respostas;

    public TopicoDtoDetalhes(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }


    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }
}
