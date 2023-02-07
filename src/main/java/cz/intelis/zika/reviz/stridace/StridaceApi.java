package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stridace")
@AllArgsConstructor
public class StridaceApi {
    private final StridaceService stridaceService;

    @GetMapping
    public List<Stridace> findAll() {
        return stridaceService.findAll();
    }
}
