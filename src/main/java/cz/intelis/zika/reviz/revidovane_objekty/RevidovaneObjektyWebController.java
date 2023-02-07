package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import cz.intelis.zika.reviz.revize.RevizeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class RevidovaneObjektyWebController {

    private final RevidovaneObjektyService revidovaneObjekty;
    @RequestMapping(value = "/revidovane_objekty")
    public String updateUser(Model model) {
        List<RevidovaneObjekty> objekty = revidovaneObjekty.findAll();
        model.addAttribute("revidovane_objekty", objekty);
        return "revidovane_objekty";
    }
}
