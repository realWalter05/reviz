package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ObjednateleService {
    private final ObjednateleRepository objednateleRepository;

    public ObjednateleService(ObjednateleRepository objednateleRepository) {
        this.objednateleRepository = objednateleRepository;
    }

    public List<Objednatele> findAll() {
        return objednateleRepository.findAll();
    }

    public Optional<Objednatele> findById(Long id) {
        return objednateleRepository.findById(id);
    }

    public List<Objednatele> findByNazev(String nazev) {
        return objednateleRepository.getObjednatelesByNazev(nazev);
    }

    public Objednatele update(Objednatele objednatele) {
        Optional<Objednatele> oldObjednatele = objednateleRepository.findById(objednatele.getId());
        Objednatele instance = oldObjednatele.get();
        instance.setNazev(objednatele.getNazev());
        instance.setZeme(objednatele.getZeme());
        instance.setPsc(objednatele.getZeme());
        instance.setUlice(objednatele.getUlice());
        instance.setMesto(objednatele.getMesto());
        return objednateleRepository.save(instance);
    }

    @Transactional
    public Objednatele create(Objednatele objednatele) {
        return objednateleRepository.save(objednatele);
    }

    public void delete(Long id) {
        objednateleRepository.deleteById(id);
    }
}
