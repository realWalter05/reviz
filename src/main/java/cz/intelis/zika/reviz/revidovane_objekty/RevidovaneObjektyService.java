package cz.intelis.zika.reviz.revidovane_objekty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class RevidovaneObjektyService {
    private final RevidovaneObjektyRepository revidovaneObjektyRepository;

    public RevidovaneObjektyService(RevidovaneObjektyRepository revidovaneObjektyRepository) {
        this.revidovaneObjektyRepository = revidovaneObjektyRepository;
    }

    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyRepository.findAll();
    }


    public Optional<RevidovaneObjekty> findById(Long id) {
        return revidovaneObjektyRepository.findById(id);
    }

    public List<RevidovaneObjekty> getRevidovaneObjektiesByJeBytovyDum(Boolean jeBytovyDum) {
        return revidovaneObjektyRepository.getRevidovaneObjektiesByJeBytovyDum(jeBytovyDum);
    }

    public RevidovaneObjekty update(RevidovaneObjekty revidovaneObjekty) {
        Optional<RevidovaneObjekty> oldRevidovaneObjekty = revidovaneObjektyRepository.findById(revidovaneObjekty.getId());
        RevidovaneObjekty instance = oldRevidovaneObjekty.get();
        instance.setZeme(revidovaneObjekty.getZeme());
        instance.setPsc(revidovaneObjekty.getZeme());
        instance.setUlice(revidovaneObjekty.getUlice());
        instance.setMesto(revidovaneObjekty.getMesto());
        instance.setJeBytovyDum(revidovaneObjekty.getJeBytovyDum());
        return revidovaneObjektyRepository.save(instance);
    }

    @Transactional
    public RevidovaneObjekty create(RevidovaneObjekty revidovaneObjekty) {
        return revidovaneObjektyRepository.save(revidovaneObjekty);
    }

    public void delete(Long id) {
        revidovaneObjektyRepository.deleteById(id);
    }
}
