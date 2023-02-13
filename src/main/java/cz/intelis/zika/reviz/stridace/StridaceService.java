package cz.intelis.zika.reviz.stridace;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StridaceService {
    private final StridaceRepository stridaceRepository;

    public StridaceService(StridaceRepository revizeRepository) {
        this.stridaceRepository = revizeRepository;
    }

    public List<Stridace> findAll() {
        return stridaceRepository.findAll();
    }

    public Optional<Stridace> findById(Long id) {
        return stridaceRepository.findById(id);
    }

    public List<Stridace> findByVyrobce(String vyrobce) {
        return stridaceRepository.getStridacesByVyrobce(vyrobce);
    }

    public List<Stridace> findByVyrobniCislo(String vyrobniCislo) {
        return stridaceRepository.getStridacesByVyrobniCislo(vyrobniCislo);
    }

    public Stridace update(Stridace stridace) {
        Optional<Stridace> oldStridace = stridaceRepository.findById(stridace.getId());
        Stridace instance = oldStridace.get();
        instance.setNazev(stridace.getNazev());
        instance.setPocet(stridace.getPocet());
        instance.setVyrobce(stridace.getVyrobce());
        instance.setVyrobniCislo(stridace.getVyrobniCislo());
        instance.setIdRevizeRevize(stridace.getIdRevizeRevize());
        return stridaceRepository.save(instance);
    }

    @Transactional
    public Stridace create(Stridace stridace) {
        return stridaceRepository.save(stridace);
    }

    public void delete(Long id) {
        stridaceRepository.deleteById(id);
    }
}
