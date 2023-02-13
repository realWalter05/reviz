package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.panely.Panely;
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

    @GetMapping
    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<RevidovaneObjekty> findById(@PathVariable Long id) {
        Optional<RevidovaneObjekty> revidovaneObjekty = revidovaneObjektyService.findById(id);
        if (revidovaneObjekty.isPresent()) {
            return new ResponseEntity<>(revidovaneObjekty.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();    }

    @GetMapping({"/bytovy_dum"})
    public List<RevidovaneObjekty> findByJeBytovyDum(@RequestParam Boolean jeBytovyDum) {
        return revidovaneObjektyService.getRevidovaneObjektiesByJeBytovyDum(jeBytovyDum);
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

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        revidovaneObjektyService.delete(id);
    }
}
