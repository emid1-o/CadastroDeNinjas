package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/todos")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletarId/{id}")
    public String deletarNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
        return "redirect:/ninjas/ui/todos";

    }


    @GetMapping("/todos/{id}")
    public String listarNinjaPorId(@PathVariable Long id, Model model){
        NinjaDTO ninjas = ninjaService.listarNinjasPorId(id);
        if (ninjas != null){
                model.addAttribute("ninjas", ninjas);
                return "detalhesNinja";
        } else {
            model.addAttribute("Mensagem", "Ninja não encontrado");
            return "listarNinjas";
            }

    }
}
