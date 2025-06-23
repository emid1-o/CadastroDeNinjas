package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<MissoesDTO> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao){
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Missao " + novaMissao.getNome() + " criada.");
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<String> listarMissoesPorId(@PathVariable Long id){
        MissoesDTO missao = missoesService.listarMissaoId(id);
        if (missao != null){
            return ResponseEntity.ok().body("Nome da missão: " + missao.getNome() + " Dificuldade da missão: "
            + missao.getDificuldade() + " Id da missão: " + missao.getId() + " Ninja dessa missao: " +
                    missoesService.listarNomesDosNinjas(id));
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao de id " + id + " não encontrada.");
    }
}
