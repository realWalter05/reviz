package cz.intelis.zika.reviz.revize;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RevizeService {
    private final RevizeRepository revizeRepository;

    public RevizeService(RevizeRepository revizeRepository) {
        this.revizeRepository = revizeRepository;
    }

    public List<Revize> findAll() {
        return revizeRepository.findAll();
    }

    public Revize findById(Long id) {
        return revizeRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    public List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo) {
        return revizeRepository.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo);
    }

    public void update(Long id, Revize revize) {
        Revize oldRevize = revizeRepository.findById(id).get();
        oldRevize.setDatumVypracovani(revize.getDatumVypracovani());
        oldRevize.setDatumUkonceniRevize(revize.getDatumUkonceniRevize());
        oldRevize.setDatumPredaniRevize(revize.getDatumPredaniRevize());
        oldRevize.setJeNovaInstalace(revize.getJeNovaInstalace());
        oldRevize.setDistribucniSit(revize.getDistribucniSit());
        oldRevize.setPocetFazi(revize.getPocetFazi());
        oldRevize.setPocetStringu(revize.getPocetStringu());
        oldRevize.setPrepetovaOchrana(revize.getPrepetovaOchrana());
        oldRevize.setFotkaSrc(revize.getFotkaSrc());
        oldRevize.setObjednateleIdObjednatele(revize.getObjednateleIdObjednatele());
        oldRevize.setIdRevidovaneObjektyRevidovaneObjekty(revize.getIdRevidovaneObjektyRevidovaneObjekty());
        revizeRepository.save(oldRevize);
    }

    @Transactional
    public Revize create(Revize revize) {
        return revizeRepository.save(revize);
    }

    public void delete(Long id) {
        revizeRepository.deleteById(id);
    }

}
