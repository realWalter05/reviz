package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;


@Service
public class PanelyService {
    private final PanelyRepository panelyRepository;

    public PanelyService(PanelyRepository panelyRepository) {
        this.panelyRepository = panelyRepository;
    }

    public List<Panely> findAll() {
        return panelyRepository.findAll();
    }

    public Panely findById(Long id) {
        return panelyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Panely findByVyrobniCislo(String nazev) {
        return panelyRepository.getPanelyByVyrobniCislo(nazev);
    }

    public void update(Long id, Panely panely) {
        Panely oldPanely = panelyRepository.findById(id).get();
        oldPanely.setIdRevizeRevize(panely.getIdRevizeRevize());
        oldPanely.setVyrobniCislo(panely.getVyrobniCislo());
        oldPanely.setIdTypyPaneluTypyPanelu(panely.getIdTypyPaneluTypyPanelu());
        panelyRepository.save(oldPanely);
    }

    @Transactional
    public Panely create(Panely panely) {
        return panelyRepository.save(panely);
    }

    public void delete(Long id) {
        panelyRepository.deleteById(id);
    }

}
