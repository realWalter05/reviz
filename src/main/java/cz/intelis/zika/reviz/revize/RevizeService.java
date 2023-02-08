package cz.intelis.zika.reviz.revize;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class RevizeService {
    private final RevizeRepository revizeRepository;

    public RevizeService(RevizeRepository revizeRepository) {
        this.revizeRepository = revizeRepository;
    }

    public List<Revize> findAll() {
        return revizeRepository.findAll();
    }

    public List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo) {
        return revizeRepository.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo);
    }

    @Transactional
    public Revize create(Revize revize) {
        return revizeRepository.save(revize);
    }
}
