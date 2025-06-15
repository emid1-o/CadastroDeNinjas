package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
        return ninjaService.criarNinja(ninja);
    }


    //Procurar ninja por id (READ)
    @GetMapping("/todos/{id}")
    public NinjaModel listarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
    }


    //Mostrar todos os ninjas (READ)
    @GetMapping("/todos")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }


    //Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarId")
    public String alterarNinja(){
        return "Alterar ninja por ID";
    }


    //Deletar ninja (DELETE)
    @DeleteMapping("/deletarId/{id}")
    public void deletarNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
    }
}
