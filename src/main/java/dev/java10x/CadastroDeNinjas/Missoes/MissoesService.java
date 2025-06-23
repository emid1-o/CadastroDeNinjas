package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    private MissoesMapper missoesMapper;



    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missao = new MissoesMapper().map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    public MissoesDTO listarMissaoId(Long id){
        Optional<MissoesModel> missoesPorId =  missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    public List<String> listarNomesDosNinjas(Long idMissao) {
        MissoesDTO missao = listarMissaoId(idMissao); // busca a missão
        List<NinjaModel> ninjas = missao.getNinjas();   // pega os ninjas

        if (ninjas == null || ninjas.isEmpty()) {
            return List.of(); // retorna lista vazia se não tiver ninjas
        }

        return ninjas.stream()
                .map(NinjaModel::getNome)
                .collect(Collectors.toList());
    }



}
