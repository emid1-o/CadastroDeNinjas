package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }

    public MissoesModel listarMissaoId(Long id){
        Optional<MissoesModel> missoesModel =  missoesRepository.findById(id);
        return missoesModel.orElse(null);
    }
}
