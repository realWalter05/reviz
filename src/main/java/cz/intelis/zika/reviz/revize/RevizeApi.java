package cz.intelis.zika.reviz.revize;

import lombok.AllArgsConstructor;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Optional<Revize> revize = revizeService.findById(id);
        if (revize.isPresent())
            return new ResponseEntity<>(revize.get(), HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }

    @GetMapping({"create_report/{id}"})
    public ResponseEntity<Revize> createReport(@PathVariable Long id) throws Exception {
        Optional<Revize> revize = revizeService.findById(id);
        if (revize.isPresent()) {
            revizeService.createReport(revize.get());
            return new ResponseEntity<>(revize.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        revizeService.delete(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Revize> create(@RequestBody Revize revize) {
        return new ResponseEntity<>(revizeService.create(revize), HttpStatus.CREATED);
    }

    @PutMapping
    public Revize update(@RequestBody Revize revize) {
        return revizeService.update(revize);
    }

    @GetMapping("/between_date")
    public List<Revize> getRevizeByDatumPredaniRevizeBetween(@RequestParam("datumPredaniRevizeOd") String datumPredaniRevizeOd,
                                                             @RequestParam("datumPredaniRevizeDo") String datumPredaniRevizeDo) {
        LocalDate datumPredaniRevizeOdDate = LocalDate.parse(datumPredaniRevizeOd);
        LocalDate datumPredaniRevizeDoDate = LocalDate.parse(datumPredaniRevizeDo);
        return revizeService.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOdDate, datumPredaniRevizeDoDate);
    }
}
