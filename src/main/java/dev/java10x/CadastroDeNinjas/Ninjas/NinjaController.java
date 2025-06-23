package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja =  ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja " + novoNinja.getNome() + " criado com sucesso.");
    }


    //Procurar ninja por id (READ)
    @GetMapping("/todos/{id}")
    public ResponseEntity<String> listarNinjaPorId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null){
            return ResponseEntity.ok().body("Nome:" + ninja.getNome() + " Idade:" + ninja.getIdade()
            + " Rank:" + ninja.getRank() + " Id:" + ninja.getId() + " Email:" + ninja.getEmail()
            + " Missao[ Nome da missão:" + ninja.getMissoes().getNome() + " Id da missão:" + ninja.getMissoes().getId()
            + " Dificuldade:" + ninja.getMissoes().getDificuldade() + " ]");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de Id " + id + " não encontrado.");}

    }


    //Mostrar todos os ninjas (READ)
    @GetMapping("/todos")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        return ResponseEntity.ok().body(ninjaService.listarNinjas());
    }


    //Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja){
        NinjaDTO ninjaAtualizado =  ninjaService.atualizarNinja(id, ninja);
        if (ninjaAtualizado.getId() != null){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Ninja " + ninjaAtualizado.getNome() + " alterado com sucesso.");}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de id " + id + " não encontrado.");
    }


    //Deletar ninja (DELETE)
    @DeleteMapping("/deletarId/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null){
            String nome = ninjaService.listarNinjasPorId(id).getNome();
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok().body("Ninja " + nome + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de ID:" + id + " não encontrado.");
        }

    }
}
