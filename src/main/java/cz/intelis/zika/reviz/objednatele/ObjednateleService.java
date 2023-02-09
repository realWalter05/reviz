package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public Objednatele findById(Long id) {
        return objednateleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Objednatele findByNazev(String nazev) {
        return objednateleRepository.getObjednateleByNazev(nazev);
    }

    public void update(Long id, Objednatele objednatele) {
        Objednatele oldObjednatele = objednateleRepository.findById(id).get();
        oldObjednatele.setNazev(objednatele.getNazev());
        oldObjednatele.setZeme(objednatele.getZeme());
        oldObjednatele.setPsc(objednatele.getZeme());
        oldObjednatele.setUlice(objednatele.getUlice());
        oldObjednatele.setMesto(objednatele.getMesto());
        objednateleRepository.save(oldObjednatele);
    }

    @Transactional
    public Objednatele create(Objednatele objednatele) {
        return objednateleRepository.save(objednatele);
    }

    public void delete(Long id) {
        objednateleRepository.deleteById(id);
    }
}
