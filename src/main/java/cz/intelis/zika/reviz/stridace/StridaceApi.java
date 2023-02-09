package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.stridace.Stridace;
import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(stridaceService.findById(id), HttpStatus.OK);
    }

    @GetMapping({"/vyrobce"})
    public ResponseEntity<List<Stridace>> findByVyrobce(@RequestParam String vyrobce) {
        return new ResponseEntity<>(stridaceService.findByVyrobce(vyrobce), HttpStatus.OK);
    }

    @GetMapping({"/vyrobni_cislo"})
    public ResponseEntity<List<Stridace>> findByVyrobniCislo(@RequestParam String vyrobniCislo) {
        return new ResponseEntity<>(stridaceService.findByVyrobniCislo(vyrobniCislo), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Stridace> delete(@PathVariable("id") Long id) {
        stridaceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Stridace> create(@RequestBody Stridace stridace) {
        Stridace newStridace = stridaceService.create(stridace);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("stridace", "/api/stridace/" + newStridace.getId().toString());
        return new ResponseEntity<>(newStridace, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Stridace> update(@PathVariable("id") Long id, @RequestBody Stridace stridace) {
        stridaceService.update(id, stridace);
        return new ResponseEntity<>(stridaceService.findById(id), HttpStatus.OK);
    }
}
