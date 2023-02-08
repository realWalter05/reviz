package cz.intelis.zika.reviz.revize;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/between_date")
    public List<Revize> getRevizeByDatumPredaniRevizeBetween(@RequestParam("datumPredaniRevizeOd") String datumPredaniRevizeOd,
                                                             @RequestParam("datumPredaniRevizeDo") String datumPredaniRevizeDo) {
        System.out.println(datumPredaniRevizeOd);
        System.out.println(datumPredaniRevizeDo);
        LocalDate datumPredaniRevizeOdDate = LocalDate.parse(datumPredaniRevizeOd);
        LocalDate datumPredaniRevizeDoDate = LocalDate.parse(datumPredaniRevizeDo);
        return revizeService.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOdDate, datumPredaniRevizeDoDate);
    }

    @PostMapping
    public Revize create(@RequestBody Revize revize) {
        return revizeService.create(revize);
    }
}
