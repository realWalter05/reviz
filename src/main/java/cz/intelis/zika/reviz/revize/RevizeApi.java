package cz.intelis.zika.reviz.revize;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Controller
@AllArgsConstructor
@RequestMapping("/revize")
public class RevizeApi {
    private final RevizeService revizeService;

    @GetMapping
    public List<Revize> findAll() {
        return revizeService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Revize> findById(@PathVariable Long id) {
        return new ResponseEntity<>(revizeService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Revize> delete(@PathVariable("id") Long id) {
        revizeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Revize> create(@RequestBody Revize revize) {
        System.out.println("Trying to send data");
        Revize newRevize = revizeService.create(revize);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("revize", "/api/revize/" + newRevize.getId().toString());
        return new ResponseEntity<>(newRevize, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Revize> update(@PathVariable("id") Long id, @RequestBody Revize revize) {
        revizeService.update(id, revize);
        return new ResponseEntity<>(revizeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/between_date")
    public List<Revize> getRevizeByDatumPredaniRevizeBetween(@RequestParam("datumPredaniRevizeOd") String datumPredaniRevizeOd,
                                                             @RequestParam("datumPredaniRevizeDo") String datumPredaniRevizeDo) {
        LocalDate datumPredaniRevizeOdDate = LocalDate.parse(datumPredaniRevizeOd);
        LocalDate datumPredaniRevizeDoDate = LocalDate.parse(datumPredaniRevizeDo);
        return revizeService.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOdDate, datumPredaniRevizeDoDate);
    }
}
