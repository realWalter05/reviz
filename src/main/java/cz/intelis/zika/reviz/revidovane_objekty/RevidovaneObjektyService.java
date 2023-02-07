package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RevidovaneObjektyService {
    private final RevidovaneObjektyRepository revidovaneObjektyService;

    public RevidovaneObjektyService(RevidovaneObjektyRepository revidovaneObjektyService) {
        this.revidovaneObjektyService = revidovaneObjektyService;
    }

    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyService.findAll();
    }
}
