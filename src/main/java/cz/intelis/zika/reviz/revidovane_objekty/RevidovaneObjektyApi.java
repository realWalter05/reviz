package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import cz.intelis.zika.reviz.typy_panelu.TypyPanelu;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/revidovane_objekty")
public class RevidovaneObjektyApi {
    private final RevidovaneObjektyService revidovaneObjektyService;
    private final RevidovaneObjektyRepository revidovaneObjektyRepository;

    @GetMapping
    public List<RevidovaneObjekty> findAll() {
        return revidovaneObjektyService.findAll();
    }

    public RevidovaneObjekty findById(Long id) {
        return revidovaneObjektyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<RevidovaneObjekty> findByJeBytovyDum(Boolean jeBytovyDum) {
        return revidovaneObjektyRepository.getRevidovaneObjektiesByJeBytovyDum(jeBytovyDum);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable  Long id, RevidovaneObjekty revidovaneObjekty) {
        RevidovaneObjekty oldRevidovaneObjekty = revidovaneObjektyRepository.findById(id).get();
        oldRevidovaneObjekty.setJeBytovyDum(revidovaneObjekty.getJeBytovyDum());
        oldRevidovaneObjekty.setZeme(revidovaneObjekty.getZeme());
        oldRevidovaneObjekty.setPsc(revidovaneObjekty.getPsc());
        oldRevidovaneObjekty.setMesto(revidovaneObjekty.getMesto());
        oldRevidovaneObjekty.setUlice(revidovaneObjekty.getUlice());
        revidovaneObjektyRepository.save(oldRevidovaneObjekty);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RevidovaneObjekty> create(@RequestBody RevidovaneObjekty revidovaneObjekty) {
        System.out.println("Trying to send data");
        RevidovaneObjekty newRevidovaneObjekty = revidovaneObjektyService.create(revidovaneObjekty);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("revize", "/api/revize/" + newRevidovaneObjekty.getId().toString());
        return new ResponseEntity<>(newRevidovaneObjekty, httpHeaders, HttpStatus.CREATED);
    }

    public void delete(Long id) {
        revidovaneObjektyService.delete(id);
    }
}
