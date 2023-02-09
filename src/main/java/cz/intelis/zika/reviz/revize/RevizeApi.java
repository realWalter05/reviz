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

    @GetMapping("/id")
    public Revize findById(@RequestParam("id") String id) {
        long revizeId = Long.parseLong(id);
        return revizeService.findById(revizeId);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        long revizeId = Long.parseLong(id);
        revizeService.delete(revizeId);
    }

    @PostMapping
    public Revize create(@RequestBody Revize revize) {
        System.out.println("Trying to send data");
        return revizeService.create(revize);
    }

    @GetMapping("/between_date")
    public List<Revize> getRevizeByDatumPredaniRevizeBetween(@RequestParam("datumPredaniRevizeOd") String datumPredaniRevizeOd,
                                                             @RequestParam("datumPredaniRevizeDo") String datumPredaniRevizeDo) {

        LocalDate datumPredaniRevizeOdDate = LocalDate.parse(datumPredaniRevizeOd);
        LocalDate datumPredaniRevizeDoDate = LocalDate.parse(datumPredaniRevizeDo);
        return revizeService.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOdDate, datumPredaniRevizeDoDate);
    }
}
