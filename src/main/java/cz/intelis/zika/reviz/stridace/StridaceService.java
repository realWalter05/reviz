package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;

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
}
