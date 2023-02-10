package cz.intelis.zika.reviz.panely;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PanelyService {
    private final PanelyRepository panelyRepository;

    public PanelyService(PanelyRepository panelyRepository) {
        this.panelyRepository = panelyRepository;
    }

    public List<Panely> findAll() {
        return panelyRepository.findAll();
    }

    public Optional<Panely> findById(Long id) {
        return panelyRepository.findById(id);
    }

    public Panely update(Panely panely) {
        Optional<Panely> oldPanely = panelyRepository.findById(panely.getId());
        Panely instance = oldPanely.get();
        instance.setIdRevizeRevize(panely.getIdRevizeRevize());
        instance.setVyrobniCislo(panely.getVyrobniCislo());
        instance.setIdTypyPaneluTypyPanelu(panely.getIdTypyPaneluTypyPanelu());
        return panelyRepository.save(instance);
    }

    @Transactional
    public Panely create(Panely panely) {
        return panelyRepository.save(panely);
    }

    public void delete(Long id) {
        panelyRepository.deleteById(id);
    }

    public Panely findByVyrobniCislo(String nazev) {
        return panelyRepository.getPanelyByVyrobniCislo(nazev);
    }

}
