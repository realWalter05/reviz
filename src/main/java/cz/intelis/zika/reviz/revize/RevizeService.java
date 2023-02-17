package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.panely.Panely;
import cz.intelis.zika.reviz.stridace.Stridace;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RevizeService {
    private final RevizeRepository revizeRepository;

    private final String templateDirectory;
    private final String resultDirectory;

    public RevizeService(RevizeRepository revizeRepository, @Value("${revize.template.directory}") String templateDirectory, @Value("${revize.result.directory}") String resultDirectory) {
        this.revizeRepository = revizeRepository;
        this.templateDirectory = templateDirectory;
        this.resultDirectory = resultDirectory;
    }

    public List<Revize> findAll() {
        return revizeRepository.findAll();
    }

    public Optional<Revize> findById(Long id) {
        return revizeRepository.findById(id);
    }

    public Revize update(Revize revize) {
        Optional<Revize> oldRevize = revizeRepository.findById(revize.getId());
        Revize instance = oldRevize.get();
        instance.setDatumVypracovani(revize.getDatumVypracovani());
        instance.setDatumUkonceniRevize(revize.getDatumUkonceniRevize());
        instance.setDatumPredaniRevize(revize.getDatumPredaniRevize());
        instance.setJeNovaInstalace(revize.getJeNovaInstalace());
        instance.setDistribucniSit(revize.getDistribucniSit());
        instance.setPocetFazi(revize.getPocetFazi());
        instance.setPocetStringu(revize.getPocetStringu());
        instance.setPrepetovaOchrana(revize.getPrepetovaOchrana());
        instance.setFotkaSrc(revize.getFotkaSrc());
        instance.setObjednateleIdObjednatele(revize.getObjednateleIdObjednatele());
        instance.setIdRevidovaneObjektyRevidovaneObjekty(revize.getIdRevidovaneObjektyRevidovaneObjekty());
        return revizeRepository.save(instance);
    }

    @Transactional
    public Revize create(Revize revize) {
        return revizeRepository.save(revize);
    }

    public void createReport(Revize revize, List<Panely> panely, List<Stridace> stridace) throws Exception {
        OdfDocument doc = OdfDocument.loadDocument(templateDirectory);

        ReportCreater reportCreater = new ReportCreater(doc, resultDirectory, revize, panely, stridace);
        reportCreater.createReport();
        reportCreater.saveReport(getRevizeFileName(revize));
    }

    public void delete(Long id) {
        revizeRepository.deleteById(id);
    }

    public List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo) {
        return revizeRepository.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo);
    }

    public String getRevizeFileName(Revize revize) {
        return "revize_" + revize.getObjednateleIdObjednatele().getNazev().replace(" ", "_") + "_" + revize.getId() + ".odt";
    }
}
