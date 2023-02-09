package cz.intelis.zika.reviz.stridace;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StridaceService {
    private final StridaceRepository stridaceRepository;

    public StridaceService(StridaceRepository revizeRepository) {
        this.stridaceRepository = revizeRepository;
    }

    public List<Stridace> findAll() {
        return stridaceRepository.findAll();
    }

    public Stridace findById(Long id) {
        return stridaceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Stridace> findByVyrobce(String vyrobce) {
        return stridaceRepository.getStridacesByVyrobce(vyrobce);
    }

    public List<Stridace> findByVyrobniCislo(String vyrobniCislo) {
        return stridaceRepository.getStridacesByVyrobniCislo(vyrobniCislo);
    }

    public void update(Long id, Stridace stridace) {
        Stridace oldStridace = stridaceRepository.findById(id).get();
        oldStridace.setNazev(stridace.getNazev());
        oldStridace.setPocet(stridace.getPocet());
        oldStridace.setVyrobce(stridace.getVyrobce());
        oldStridace.setVyrobniCislo(stridace.getVyrobniCislo());
        oldStridace.setIdRevizeRevize(stridace.getIdRevizeRevize());
        stridaceRepository.save(oldStridace);
    }

    @Transactional
    public Stridace create(Stridace stridace) {
        return stridaceRepository.save(stridace);
    }

    public void delete(Long id) {
        stridaceRepository.deleteById(id);
    }
}
