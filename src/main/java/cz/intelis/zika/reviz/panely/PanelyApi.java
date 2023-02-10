package cz.intelis.zika.reviz.panely;

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
@RequestMapping("/panely")
public class PanelyApi {
    private final PanelyService panelyService;

    @GetMapping
    public List<Panely> findAll() {
        return panelyService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Panely> findById(@PathVariable Long id) {
        Optional<Panely> revize = panelyService.findById(id);
        if (revize.isPresent()) {
            return new ResponseEntity<>(revize.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        panelyService.delete(id);
    }

    @PostMapping
    @Transactional
    public Panely create(@RequestBody Panely panely) {
        return panelyService.create(panely);
    }

    @PutMapping
    public Panely update(@RequestBody Panely panely) {
        return panelyService.update(panely);
    }

    @GetMapping({"/vyrobni_cislo"})
    public Panely findByNazev(@RequestParam String nazev) {
        return panelyService.findByVyrobniCislo(nazev);
    }
}
