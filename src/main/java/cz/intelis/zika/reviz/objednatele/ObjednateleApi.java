package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(objednateleService.findById(id), HttpStatus.OK);
    }

    @GetMapping({"/nazev"})
    public ResponseEntity<Objednatele> findByNazev(@RequestParam String nazev) {
        return new ResponseEntity<>(objednateleService.findByNazev(nazev), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Objednatele> delete(@PathVariable("id") Long id) {
        objednateleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Objednatele> create(@RequestBody Objednatele objednatele) {
        Objednatele newObjednatele = objednateleService.create(objednatele);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("objednatele", "/api/objednatele/" + newObjednatele.getId().toString());
        return new ResponseEntity<>(newObjednatele, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Objednatele> update(@PathVariable("id") Long id, @RequestBody Objednatele objednatele) {
        objednateleService.update(id, objednatele);
        return new ResponseEntity<>(objednateleService.findById(id), HttpStatus.OK);
    }
}
