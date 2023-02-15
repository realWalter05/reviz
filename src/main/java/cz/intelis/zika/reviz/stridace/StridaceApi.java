package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stridace")
@AllArgsConstructor
public class StridaceApi {
    private final StridaceService stridaceService;

    @GetMapping
    public List<Stridace> findAll() {
        return stridaceService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Stridace> findById(@PathVariable Long id) {
        Optional<Stridace> stridace = stridaceService.findById(id);
        if (stridace.isPresent())
            return new ResponseEntity<>(stridace.get(), HttpStatus.CREATED);
        return ResponseEntity.notFound().build();
    }

    @GetMapping({"/vyrobce"})
    public List<Stridace> findByVyrobce(@RequestParam String vyrobce) {
        return stridaceService.findByVyrobce(vyrobce);
    }

    @GetMapping({"/vyrobni_cislo"})
    public List<Stridace> findByVyrobniCislo(@RequestParam String vyrobniCislo) {
        return stridaceService.findByVyrobniCislo(vyrobniCislo);
    }

    @GetMapping({"/stridace_by_revize"})
    public List<Stridace> findByVyrobniCislo(@RequestBody Revize revize) {
        return stridaceService.getStridacesByIdRevizeRevize(revize);
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        stridaceService.delete(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Stridace> create(@RequestBody Stridace stridace) {
        return new ResponseEntity<>(stridaceService.create(stridace), HttpStatus.CREATED);
    }
    @PutMapping
    public Stridace update(@RequestBody Stridace stridace) {
        return stridaceService.update(stridace);
    }
}
