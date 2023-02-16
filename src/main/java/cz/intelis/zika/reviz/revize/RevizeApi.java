package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.panely.PanelyService;
import cz.intelis.zika.reviz.stridace.StridaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@AllArgsConstructor
@RequestMapping("/revize")
public class RevizeApi {
    private final RevizeService revizeService;
    private final PanelyService panelyService;
    private final StridaceService stridaceService;

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

    @GetMapping({"create_report"})
    public ResponseEntity<byte[]> createReport(@RequestParam Long id) throws Exception {
        Optional<Revize> revize = revizeService.findById(id);
        Revize revizeInstance = revize.get();
        revizeService.createReport(revizeInstance, panelyService.getPaneliesByIdRevizeRevize(revizeInstance), stridaceService.getStridacesByIdRevizeRevize(revizeInstance));

        byte[] contents = this.getClass().getClassLoader().getResourceAsStream("result/result.odt").readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        String filename = "output.odt";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        Files.delete(Path.of("C:/Users/vacla/IdeaProjects/reviz/src/main/resources/result/result.odt"));
        return response;
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
