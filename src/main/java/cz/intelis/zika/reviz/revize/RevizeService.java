package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.panely.Panely;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import cz.intelis.zika.reviz.stridace.Stridace;
import cz.intelis.zika.reviz.typy_panelu.TypyPanelu;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.pkg.OdfElement;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.time.LocalDate;
import java.util.List;
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

    public void createReport(Revize revize, List<Panely> panely, List<Stridace> stridace) throws Exception {
        OdfDocument doc = OdfDocument.loadDocument("./src/main/resources/templates/revize.odt");
        OdfElement root = doc.getContentRoot();
        NodeList texts = root.getElementsByTagName("text:p");

        System.out.println(root);

        Objednatele objednatele = revize.getObjednateleIdObjednatele();
        RevidovaneObjekty revidovaneObjekty = revize.getIdRevidovaneObjektyRevidovaneObjekty();
        String typBudovy = (revidovaneObjekty.getJeBytovyDum() ? "BD" : "RD");
        // TODO do tabulek když bude víc střídačů nebo víc typů panelů
        TypyPanelu typyPanelu = panely.get(0).getIdTypyPaneluTypyPanelu();

        StringBuilder stridaceText = new StringBuilder("střídač: " + stridace.get(0).getNazev() + " " + stridace.get(0).getVyrobniCislo() + ", ");
        StringBuilder vyrobceText = new StringBuilder("střídače: " + stridace.get(0).getVyrobce());
        if (stridace.size() > 1) {
            stridaceText = new StringBuilder("střídači: ");
            vyrobceText = new StringBuilder("střídačů: ");
            for (Stridace value : stridace) {
                stridaceText.append(value.getNazev()).append(" ").append(value.getVyrobniCislo()).append(", ");
                vyrobceText.append(value.getVyrobce()).append(" ").append(", ");
            }
        }

        // TODO Porešit xpath
        XPath xpath = XPathFactory.newInstance().newXPath();
        OdfFileDom dom = doc.getContentDom();
        NodeList xmlDoc = (NodeList) xpath.compile("/").evaluate(dom, XPathConstants.NODE);
        xmlDoc.item(0);
        System.out.println(xmlDoc);

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
                    String objednatelUliceMestoZeme = objednatele.getNazev() + ", " + objednatele.getUlice() + " " + objednatele.getMesto() +
                            " " + objednatele.getPsc() + ", " + objednatele.getZeme();
                    texts.item(i).setTextContent(objednatelUliceMestoZeme);
                    break;
                case "revidovanyObjekt":
                    String revidovaneObjektyInfo = "Technologie FVE na objektu " + (revidovaneObjekty.getJeBytovyDum() ? "bytového domu" : "rodinného domu") + ", " +
                            revidovaneObjekty.getUlice() + " " + revidovaneObjekty.getMesto() + " " + revidovaneObjekty.getPsc() + " " + revidovaneObjekty.getZeme();
                    texts.item(i).setTextContent(revidovaneObjektyInfo);
                    break;
                case "novaInstalace":
                    texts.item(i).setTextContent(revize.getJeNovaInstalace() ? "Ano" : "Ne");
                    break;
                case "distribucniSit":
                    texts.item(i).setTextContent("Distribuční síť: " + revize.getDistribucniSit());
                    break;
                case "pocetFazi":
                    texts.item(i).setTextContent("Počet fází: " + revize.getPocetFazi());
                    break;
                case "predmetRevize":
                    String predmetRevize = "Předmětem této výchozí revize je elektroinstalace Technologie FVE na objektu " + typBudovy + " " +
                            revidovaneObjekty.getUlice() + " " + revidovaneObjekty.getMesto() + " " + revidovaneObjekty.getPsc() + " " + revidovaneObjekty.getZeme();
                    texts.item(i).setTextContent(predmetRevize);
                    break;
                case "popisRevidovanehoObjektu":
                    String popisRevidovanehoObjektu = "Na " + typBudovy + " jsou na samonosné konstrukci uloženy FVE panely - " + panely.size()  + " ks dle níže rozepsaného použitého materiálu a datasheetů v příloze.";
                    texts.item(i).setTextContent(popisRevidovanehoObjektu);
                    break;
                case "popisRevidovanehoObjektu1":
                    String popisRevidovanehoObjektu1 = "Panely jsou zapojeny do " + revize.getPocetStringu() + " stringy/ů a odjištěno předepsanými pojistkami v pojistkovém odpojovači. Zároveň je připojena přepěťová ochrana DC technologie.";
                    texts.item(i).setTextContent(popisRevidovanehoObjektu1);
                    break;
                case "popisRevidovanehoObjektu2":
                    String popisRevidovanehoObjektu2 = "Z rozvaděče DC je napájen " + stridaceText + "který je do sítě připojen přes AC rozvaděč, kde je instalováno rozpadové místo instalace FVE stykačem 1-0 a elektroměrem který měří veškerou vyrobenou el. energii. V tomto rozvaděči jsou instalovány fázové přepěťové ochrany AC. 1+2 st.";
                    texts.item(i).setTextContent(popisRevidovanehoObjektu2);
                    break;
                case "popisRevidovanehoObjektu3":
                    String popisRevidovanehoObjektu3 = "Zároveň je doloženo vydání Prohlášení o shodě s PPDS 2021 s vybranými vlastnostmi dle regulace RfG 2016/631 od výrobce " + vyrobceText + ", které je v příloze revizní zprávy. Další přílohou revizní zprávy jsou datesheety použitých FVE panelů a střídače.";
                    texts.item(i).setTextContent(popisRevidovanehoObjektu3);
                    break;
                case "pocetVykonPanelu":
                    String pocetVykonPanelu = "Vlastní " + panely.size() + " ks panelů " + typyPanelu.getVykon() + " Wp";
                    texts.item(i).setTextContent(pocetVykonPanelu);
                    break;
                case "typPanelu":
                    String typyPaneluText = "Typ: " + typyPanelu.getTyp();
                    texts.item(i).setTextContent(typyPaneluText);
                    break;                    
                case "kWCelkem", "kWCelkem1":
                    String kWCelkem = String.valueOf(panely.size() * typyPanelu.getVykon().intValue());
                    texts.item(i).setTextContent(kWCelkem);
                    break;
                case "stridac":
                    String stridac = stridace.get(0).getNazev();
                    texts.item(i).setTextContent(stridac);
                    break;
                case "stridacVyrobniCislo":
                    String stridacVyrobniCislo = stridace.get(0).getVyrobniCislo();
                    texts.item(i).setTextContent(stridacVyrobniCislo);
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
