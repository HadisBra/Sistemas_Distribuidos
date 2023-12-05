package com.example.demo.controle;

import com.example.demo.controle.repository.ProdutoRepository;
import com.example.demo.model.Produto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoControle {
    //@Autowired
    private final ProdutoRepository repository;


    @GetMapping
    public List<Produto> findAll() {
        return repository.findAll();
    }



    @GetMapping("/{id}")
    public Produto findById (@PathVariable long id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping ("/{id}")
    public void delete (@PathVariable long id)
    {
        repository.findAndDelete(id);
       }


    @PostMapping
    public Produto insert(@RequestBody Produto produto)
    {
        return repository.save(produto);
    }
    @PutMapping("{id}")
    public Produto update(@PathVariable long id, @RequestBody Produto produto)
    {
       final var msg="O id informado não concide com id do objeto passado";
        if(id != produto.getId())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, msg);
        return repository.save(produto);
    }
}
