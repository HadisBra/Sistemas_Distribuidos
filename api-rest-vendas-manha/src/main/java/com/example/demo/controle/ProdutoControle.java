package com.example.demo.controle;

import com.example.demo.controle.repository.ProdutoRepository;
import com.example.demo.model.Produto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.event.IIOReadProgressListener;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoControle {
    //@Autowired
    private final ProdutoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity <Produto> findById (@Valid @PathVariable long id)
    {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // fucionda sem a mensagem de codigo de status

    }

    @GetMapping
    public ResponseEntity<List <Produto>> findAll()
    {
        return ResponseEntity.ok(repository.findAll());
    }


    @PostMapping
    public ResponseEntity< Produto> insert(@Valid @RequestBody Produto produto)
    {
        final var newProduto=repository.save(produto);
        final var location = URI.create("/produto" + newProduto.getId());

        return ResponseEntity.created(location).body(newProduto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable long id, @RequestBody Produto produto)
    {
        final var msg="O id informado não concide com id do objeto passado";
        if(id != produto.getId())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, msg);
        return ResponseEntity.ok(repository.save(produto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity delete (@PathVariable long id)
    {
        repository.findAndDelete(id);
        return  ResponseEntity.noContent().build();

    }


}
