package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import cz.intelis.zika.reviz.typy_panelu.TypyPanelu;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/revidovane_objekty")
public class RevidovaneObjektyApi {
    private final RevidovaneObjektyService revidovaneObjektyService;
    private final RevidovaneObjektyRepository revidovaneObjektyRepository;

    @GetMapping
    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyService.findAll();
    }

    public ResponseEntity<RevidovaneObjekty> findById(Long id) {
        Optional<RevidovaneObjekty> revidovaneObjekty = revidovaneObjektyService.findById(id);
        if (revidovaneObjekty.isPresent()) {
            return new ResponseEntity<>(revidovaneObjekty.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();    }

    public List<RevidovaneObjekty> findByJeBytovyDum(Boolean jeBytovyDum) {
        return revidovaneObjektyRepository.getRevidovaneObjektiesByJeBytovyDum(jeBytovyDum);
    }

    @PutMapping
    public RevidovaneObjekty update(@RequestBody RevidovaneObjekty revidovaneObjekty) {
        return revidovaneObjektyService.update(revidovaneObjekty);
    }

    @PostMapping
    @Transactional
    public RevidovaneObjekty create(@RequestBody RevidovaneObjekty revidovaneObjekty) {
        return revidovaneObjektyService.create(revidovaneObjekty);
    }

    public void delete(Long id) {
        revidovaneObjektyService.delete(id);
    }
}
