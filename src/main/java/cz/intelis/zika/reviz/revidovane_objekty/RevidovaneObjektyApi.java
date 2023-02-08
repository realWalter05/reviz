package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/revidovane_objekty")
public class RevidovaneObjektyApi {
    private final RevidovaneObjektyService revidovaneObjektyService;

    @GetMapping
    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyService.findAll();
    }
}
