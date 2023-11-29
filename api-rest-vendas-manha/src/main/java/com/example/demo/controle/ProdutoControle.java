package com.example.demo.controle;

import com.example.demo.controle.repository.ProdutoRepository;
import com.example.demo.model.Produto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

@PostMapping
    public Produto insert(@RequestBody Produto produto)
    {
        return repository.save(produto);
    }
}
