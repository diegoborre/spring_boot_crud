package br.com.forum.Forun.controller;

import br.com.forum.Forun.controller.dto.TopicoDto;
import br.com.forum.Forun.controller.dto.TopicoDtoDetalhes;
import br.com.forum.Forun.controller.form.TopicoAtualizacaoForm;
import br.com.forum.Forun.controller.form.TopicoForm;
import br.com.forum.Forun.modelo.Topico;
import br.com.forum.Forun.repository.CursoRepository;
import br.com.forum.Forun.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomecurso,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        if (nomecurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomecurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).
                body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDtoDetalhes> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent())
            return ResponseEntity.ok(new TopicoDtoDetalhes(topico.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoForm form) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent())
            return ResponseEntity.ok
                    (new TopicoDto(form.atualizar(id, topicoRepository)));
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

}
