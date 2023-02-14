package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import org.apache.jena.base.Sys;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.pkg.NamespaceName;
import org.odftoolkit.odfdom.pkg.OdfElement;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.odftoolkit.odfdom.pkg.OdfNamespace;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.odftoolkit.odfdom.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RevizeService {
    private final RevizeRepository revizeRepository;

    public RevizeService(RevizeRepository revizeRepository) {
        this.revizeRepository = revizeRepository;
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

    public void createReport(Revize revize) throws Exception {
        OdfDocument doc = OdfDocument.loadDocument("./src/main/resources/templates/revize.odt");
        OdfElement root = doc.getContentRoot();
        NodeList texts = root.getElementsByTagName("text:p");

        System.out.println(root);

        for (int i = 0; i < texts.getLength(); i++) {
            Node style = texts.item(i).getAttributes().item(0);

            switch (style.getTextContent()) {
                case "datumVypracovaniRevize":
                    texts.item(i).setTextContent(revize.getDatumVypracovani().toString());
                    break;
                case "datumPredaniRevize":
                    texts.item(i).setTextContent(revize.getDatumPredaniRevize().toString());
                    break;
                case "datumUkonceniRevize":
                    texts.item(i).setTextContent(revize.getDatumUkonceniRevize().toString());
                    break;
                case "objednatelUliceMestoZeme":
                    Objednatele objednatele = revize.getObjednateleIdObjednatele();
                    String objednatelUliceMestoZeme = objednatele.getNazev() + ", " + objednatele.getUlice() + " " + objednatele.getMesto() + ", " + objednatele.getZeme();
                    texts.item(i).setTextContent(objednatelUliceMestoZeme);
                    break;
            }
        }

        doc.save("./src/main/resources/result/result.odt");
    }

    public void delete(Long id) {
        revizeRepository.deleteById(id);
    }

    public List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo) {
        return revizeRepository.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo);
    }

}
