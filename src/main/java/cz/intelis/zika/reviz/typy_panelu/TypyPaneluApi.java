package cz.intelis.zika.reviz.typy_panelu;

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
@RequestMapping("/typy_panelu")
public class TypyPaneluApi {
    private final TypyPaneluService typyPaneluService;
    private final TypyPaneluRepository typyPaneluRepository;

    @GetMapping
    public List<TypyPanelu> findAll() {
        return typyPaneluService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TypyPanelu> findById(@PathVariable Long id) {
        return new ResponseEntity<>(typyPaneluService.findById(id), HttpStatus.OK);    }

    @GetMapping({"/typ"})
    public List<TypyPanelu> findByTyp(String typ) {
        return typyPaneluRepository.getTypyPanelusByTyp(typ);
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<TypyPanelu> update(@PathVariable("id") Long id, @RequestBody TypyPanelu typyPanelu) {
        typyPaneluService.update(id, typyPanelu);
        return new ResponseEntity<>(typyPaneluService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TypyPanelu> create(@RequestBody TypyPanelu typyPanelu) {
        TypyPanelu newTypyPanelu = typyPaneluService.create(typyPanelu);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("stridace", "/api/stridace/" + newTypyPanelu.getId().toString());
        return new ResponseEntity<>(newTypyPanelu, httpHeaders, HttpStatus.CREATED);
    }

    public void delete(Long id) {
        typyPaneluRepository.deleteById(id);
    }
}
