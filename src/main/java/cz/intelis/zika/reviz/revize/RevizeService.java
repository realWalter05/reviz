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

    public Optional<Revize> findById(Long id) {
        return revizeRepository.findById(id);
    }

    public Revize update(Revize revize) {
        Optional<Revize> oldRevize = revizeRepository.findById(revize.getId());
        Revize instance = oldRevize.get();
        instance.setDatumVypracovani(revize.getDatumVypracovani());
        instance.setDatumUkonceniRevize(revize.getDatumUkonceniRevize());
        instance.setDatumPredaniRevize(revize.getDatumPredaniRevize());
        instance.setJeNovaInstalace(revize.getJeNovaInstalace());
        instance.setDistribucniSit(revize.getDistribucniSit());
        instance.setPocetFazi(revize.getPocetFazi());
        instance.setPocetStringu(revize.getPocetStringu());
        instance.setPrepetovaOchrana(revize.getPrepetovaOchrana());
        instance.setFotkaSrc(revize.getFotkaSrc());
        instance.setObjednateleIdObjednatele(revize.getObjednateleIdObjednatele());
        instance.setIdRevidovaneObjektyRevidovaneObjekty(revize.getIdRevidovaneObjektyRevidovaneObjekty());
        return revizeRepository.save(instance);
    }

    @Transactional
    public Revize create(Revize revize) {
        return revizeRepository.save(revize);
    }

    public void delete(Long id) {
        revizeRepository.deleteById(id);
    }

    public List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo) {
        return revizeRepository.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo);
    }

}
