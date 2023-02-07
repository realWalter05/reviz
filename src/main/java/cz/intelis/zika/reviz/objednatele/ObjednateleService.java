package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObjednateleService {
    private final ObjednateleRepository objednateleRepository;

    public ObjednateleService(ObjednateleRepository objednateleRepository) {
        this.objednateleRepository = objednateleRepository;
    }

    public List<Objednatele> findAll() {
        return objednateleRepository.findAll();
    }
}
