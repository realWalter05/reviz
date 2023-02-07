package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjektyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ObjednateleWebController {

    private final ObjednateleService objednateleService;
    @RequestMapping(value = "/objednatele")
    public String updateUser(Model model) {
        List<Objednatele> objednatele = objednateleService.findAll();
        model.addAttribute("objednatele", objednatele);
        return "objednatele";
    }
}
