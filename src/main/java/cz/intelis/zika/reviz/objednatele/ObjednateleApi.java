package cz.intelis.zika.reviz.objednatele;

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
@AllArgsConstructor
@RequestMapping("/objednatele")
public class ObjednateleApi {
    private final ObjednateleService objednateleService;

    @GetMapping
    public List<Objednatele> findAll() {
        return objednateleService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Objednatele> findById(@PathVariable Long id) {
        Optional<Objednatele> objednatele = objednateleService.findById(id);
        if (objednatele.isPresent()) {
            return new ResponseEntity<>(objednatele.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        objednateleService.delete(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Objednatele> create(@RequestBody Objednatele objednatele) {
        return new ResponseEntity<>(objednateleService.create(objednatele), HttpStatus.CREATED);
    }
    @PutMapping({"/{id}"})
    public Objednatele update(@RequestBody Objednatele objednatele) {
        return objednateleService.update(objednatele);
    }

    @GetMapping({"/nazev"})
    public List<Objednatele> findByNazev(@RequestParam String nazev) {
        return objednateleService.findByNazev(nazev);
    }
}
