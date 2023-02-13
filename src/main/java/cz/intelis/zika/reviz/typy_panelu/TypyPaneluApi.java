package cz.intelis.zika.reviz.typy_panelu;

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
@RequestMapping("/typy_panelu")
public class TypyPaneluApi {
    private final TypyPaneluService typyPaneluService;

    @GetMapping
    public List<TypyPanelu> findAll() {
        return typyPaneluService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TypyPanelu> findById(@PathVariable Long id) {
        Optional<TypyPanelu> typyPanelu = typyPaneluService.findById(id);
        if (typyPanelu.isPresent())
            return new ResponseEntity<>(typyPanelu.get(), HttpStatus.CREATED);
        return ResponseEntity.notFound().build();
    }

    @GetMapping({"/typ"})
    public List<TypyPanelu> findByTyp(String typ) {
        return typyPaneluService.getTypyPanelusByTyp(typ);
    }


    @PutMapping
    public TypyPanelu update(@RequestBody TypyPanelu typyPanelu) {
        return typyPaneluService.update(typyPanelu);
    }

    @PostMapping
    @Transactional
    public TypyPanelu create(@RequestBody TypyPanelu typyPanelu) {
        return typyPaneluService.create(typyPanelu);
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable Long id) {
        typyPaneluService.delete(id);
    }
}
