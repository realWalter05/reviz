package cz.intelis.zika.reviz.panely;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(panelyService.findById(id), HttpStatus.OK);
    }

    @GetMapping({"/vyrobni_cislo"})
    public ResponseEntity<Panely> findByNazev(@RequestParam String nazev) {
        return new ResponseEntity<>(panelyService.findByVyrobniCislo(nazev), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Panely> delete(@PathVariable("id") Long id) {
        panelyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Panely> create(@RequestBody Panely panely) {
        Panely newPanely = panelyService.create(panely);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("panely", "/api/panely/" + newPanely.getId().toString());
        return new ResponseEntity<>(newPanely, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Panely> update(@PathVariable("id") Long id, @RequestBody Panely panely) {
        panelyService.update(id, panely);
        return new ResponseEntity<>(panelyService.findById(id), HttpStatus.OK);
    }
}
