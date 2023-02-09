package cz.intelis.zika.reviz.typy_panelu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TypyPaneluService {
    private final TypyPaneluRepository typyPaneluRepository;

    public TypyPaneluService(TypyPaneluRepository typyPaneluRepository) {
        this.typyPaneluRepository = typyPaneluRepository;
    }

    public List<TypyPanelu> findAll() {
        return typyPaneluRepository.findAll();
    }
    public TypyPanelu findById(Long id) {
        return typyPaneluRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<TypyPanelu> findByNazev(String typ) {
        return typyPaneluRepository.getTypyPanelusByTyp(typ);
    }

    public void update(Long id, TypyPanelu typyPanelu) {
        TypyPanelu oldTypyPanelu = typyPaneluRepository.findById(id).get();
        oldTypyPanelu.setTyp(typyPanelu.getTyp());
        oldTypyPanelu.setVykon(typyPanelu.getVykon());
        typyPaneluRepository.save(oldTypyPanelu);
    }

    @Transactional
    public TypyPanelu create(TypyPanelu typyPanelu) {
        return typyPaneluRepository.save(typyPanelu);
    }

    public void delete(Long id) {
        typyPaneluRepository.deleteById(id);
    }
}
