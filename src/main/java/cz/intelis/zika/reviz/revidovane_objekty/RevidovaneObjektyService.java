package cz.intelis.zika.reviz.revidovane_objekty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RevidovaneObjektyService {
    private final RevidovaneObjektyRepository revidovaneObjektyRepository;

    public RevidovaneObjektyService(RevidovaneObjektyRepository revidovaneObjektyRepository) {
        this.revidovaneObjektyRepository = revidovaneObjektyRepository;
    }

    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyRepository.findAll();
    }


    public RevidovaneObjekty findById(Long id) {
        return revidovaneObjektyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<RevidovaneObjekty> findByJeBytovyDum(Boolean jeBytovyDum) {
        return revidovaneObjektyRepository.getRevidovaneObjektiesByJeBytovyDum(jeBytovyDum);
    }

    public void update(Long id, RevidovaneObjekty revidovaneObjekty) {
        RevidovaneObjekty oldRevidovaneObjekty = revidovaneObjektyRepository.findById(id).get();
        oldRevidovaneObjekty.setZeme(revidovaneObjekty.getZeme());
        oldRevidovaneObjekty.setPsc(revidovaneObjekty.getZeme());
        oldRevidovaneObjekty.setUlice(revidovaneObjekty.getUlice());
        oldRevidovaneObjekty.setMesto(revidovaneObjekty.getMesto());
        oldRevidovaneObjekty.setJeBytovyDum(revidovaneObjekty.getJeBytovyDum());
        revidovaneObjektyRepository.save(oldRevidovaneObjekty);
    }

    @Transactional
    public RevidovaneObjekty create(RevidovaneObjekty revidovaneObjekty) {
        return revidovaneObjektyRepository.save(revidovaneObjekty);
    }

    public void delete(Long id) {
        revidovaneObjektyRepository.deleteById(id);
    }
}
