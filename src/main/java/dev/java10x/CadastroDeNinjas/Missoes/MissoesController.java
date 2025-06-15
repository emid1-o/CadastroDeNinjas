package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")

public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/todos")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @PostMapping("/criar")
    public void criarMissao(@RequestBody MissoesModel missao){
        missoesService.criarMissao(missao);
    }

    @GetMapping("/todos/{id}")
    public MissoesModel listarMissoesPorId(@PathVariable Long id){
        return missoesService.listarMissaoId(id);
    }
}
