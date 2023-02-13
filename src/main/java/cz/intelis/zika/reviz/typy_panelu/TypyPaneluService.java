package cz.intelis.zika.reviz.typy_panelu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TypyPaneluService {
    private final TypyPaneluRepository typyPaneluRepository;

    public TypyPaneluService(TypyPaneluRepository typyPaneluRepository) {
        this.typyPaneluRepository = typyPaneluRepository;
    }

    public List<TypyPanelu> findAll() {
        return typyPaneluRepository.findAll();
    }
    public Optional<TypyPanelu> findById(Long id) {
        return typyPaneluRepository.findById(id);
    }

    public List<TypyPanelu> getTypyPanelusByTyp(String typ) {
        return typyPaneluRepository.getTypyPanelusByTyp(typ);
    }

    public TypyPanelu update(TypyPanelu typyPanelu) {
        Optional<TypyPanelu> oldTypyPanelu = typyPaneluRepository.findById(typyPanelu.getId());
        TypyPanelu instance = oldTypyPanelu.get();
        instance.setTyp(typyPanelu.getTyp());
        instance.setVykon(typyPanelu.getVykon());
        return typyPaneluRepository.save(instance);
    }

    @Transactional
    public TypyPanelu create(TypyPanelu typyPanelu) {
        return typyPaneluRepository.save(typyPanelu);
    }

    public void delete(Long id) {
        typyPaneluRepository.deleteById(id);
    }
}
