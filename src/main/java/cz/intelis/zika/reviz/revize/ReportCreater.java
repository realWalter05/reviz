package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.panely.Panely;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import cz.intelis.zika.reviz.stridace.Stridace;
import cz.intelis.zika.reviz.typy_panelu.TypyPanelu;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.pkg.OdfElement;
import org.odftoolkit.odfdom.pkg.OdfPackage;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCreater {

    public OdfDocument doc;
    private final String resultDirectory;
    private final Revize revize;
    private final List<Panely> panely;
    private final List<Stridace> stridace;
    private final Objednatele objednatele;
    private final OdfElement root;
    private final RevidovaneObjekty revidovaneObjekty;

    private Node datumVypracovaniRevize;
    private Node datumPredaniRevize;
    private Node datumUkonceniRevize;
    private Node objednatelUliceMestoZeme;
    private Node revidovanyObjekt;
    private Node novaInstalace;
    private Node distribucniSit;
    private Node pocetFazi;
    private Node predmetRevize;
    private Node popisRevidovanehoObjektu;
    private Node popisRevidovanehoObjektu1;
    private Node popisRevidovanehoObjektu2;
    private Node popisRevidovanehoObjektu3;
    private Node kWCelkem1;
    private Node stridac;
    private Node stridacVyrobniCislo;
    private Node nextStridacePlaceholder;
    private Node fvePanelyPlaceholder;
    private Node prepetovaOchrana;
    private Node typJisteni;

    public ReportCreater(OdfDocument doc, String resultDirectory, Revize revize, List<Panely> panely, List<Stridace> stridace) throws Exception {
        this.doc = doc;
        this.resultDirectory = resultDirectory;
        this.revize = revize;
        this.root = this.doc.getContentRoot();
        this.panely = panely;
        this.stridace = stridace;
        this.objednatele = this.revize.getObjednateleIdObjednatele();
        this.revidovaneObjekty = this.revize.getIdRevidovaneObjektyRevidovaneObjekty();
        setNodes();
    }

    private StringBuilder getStridaceText() {
        StringBuilder stridaceText = new StringBuilder("střídač: " + stridace.get(0).getNazev() + " " + stridace.get(0).getVyrobniCislo() + ", ");
        if (stridace.size() > 1) {
            stridaceText = new StringBuilder("střídači: ");
            for (Stridace value : stridace) {
                stridaceText.append(value.getNazev()).append(" ").append(value.getVyrobniCislo()).append(", ");
            }
        }
        return stridaceText;
    }

    private void setNodes() {
        NodeList texts = root.getElementsByTagName("text:p");
        for (int i = 0; i < texts.getLength(); i++) {
            Node style = texts.item(i).getAttributes().item(0);

            switch (style.getTextContent()) {
                case "datumVypracovaniRevize" -> datumVypracovaniRevize = texts.item(i);
                case "datumPredaniRevize" -> datumPredaniRevize = texts.item(i);
                case "datumUkonceniRevize" -> datumUkonceniRevize = texts.item(i);
                case "objednatelUliceMestoZeme" -> objednatelUliceMestoZeme = texts.item(i);
                case "revidovanyObjekt" -> revidovanyObjekt = texts.item(i);
                case "novaInstalace" -> novaInstalace = texts.item(i);
                case "distribucniSit" -> distribucniSit = texts.item(i);
                case "pocetFazi" -> pocetFazi = texts.item(i);
                case "predmetRevize" -> predmetRevize = texts.item(i);
                case "popisRevidovanehoObjektu" -> popisRevidovanehoObjektu = texts.item(i);
                case "popisRevidovanehoObjektu1" -> popisRevidovanehoObjektu1 = texts.item(i);
                case "popisRevidovanehoObjektu2" -> popisRevidovanehoObjektu2 = texts.item(i);
                case "popisRevidovanehoObjektu3" -> popisRevidovanehoObjektu3 = texts.item(i);
                case "kWCelkem1" -> kWCelkem1 = texts.item(i);
                case "stridac" -> stridac = texts.item(i);
                case "stridacVyrobniCislo" -> stridacVyrobniCislo = texts.item(i);
                case "nextStridacePlaceholder" -> nextStridacePlaceholder = texts.item(i);
                case "fvePanelyPlaceholder" -> fvePanelyPlaceholder = texts.item(i);
                case "prepetovaOchrana" -> prepetovaOchrana = texts.item(i);
                case "typJisteni" -> typJisteni = texts.item(i);
            }
        }
    }

    private StringBuilder getVyrobceText() {
        StringBuilder vyrobceText = new StringBuilder("střídače: " + stridace.get(0).getVyrobce());
        if (stridace.size() > 1) {
            vyrobceText = new StringBuilder("střídačů: ");
            for (Stridace value : stridace) {
                vyrobceText.append(value.getVyrobce()).append(", ");
            }
        }
        return vyrobceText;
    }

    private String getFormattedDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        return formatter.format(date);
    }

    private String getObjednateleUliceMestoZeme() {
        return objednatele.getNazev() + ", " + objednatele.getUlice() + " " + objednatele.getMesto() +
                " " + objednatele.getPsc() + ", " + objednatele.getZeme();
    }

    private String getRevidovaneObjektyInfo() {
        return "Technologie FVE na objektu " + (revidovaneObjekty.getJeBytovyDum() ? "bytového domu" : "rodinného domu") + ", " +
                revidovaneObjekty.getUlice() + " " + revidovaneObjekty.getMesto() + " " + revidovaneObjekty.getPsc() + " " + revidovaneObjekty.getZeme();
    }

    private String translateNovaInstalace() {
        return revize.getJeNovaInstalace() ? "Ano" : "Ne";
    }

    private String getDistribucniSit() {
        return "Distribuční síť: " + revize.getDistribucniSit();
    }

    private String getPocetFazi() {
        return "Počet fází: " + revize.getPocetFazi();
    }

    private String getPredmetRevizeText() {
        return "Předmětem této výchozí revize je elektroinstalace Technologie FVE na objektu " + (revidovaneObjekty.getJeBytovyDum() ? "BD" : "RD") + " " +
                revidovaneObjekty.getUlice() + " " + revidovaneObjekty.getMesto() + " " + revidovaneObjekty.getPsc() + " " + revidovaneObjekty.getZeme();
    }

    private String getPopisRevidovanehoObjektu() {
        return "Na " + (revidovaneObjekty.getJeBytovyDum() ? "BD" : "RD") + " jsou na samonosné konstrukci uloženy FVE panely - " + panely.size()  + " ks dle níže rozepsaného použitého materiálu a datasheetů v příloze.";
    }

    private String getPopisRevidovanehoObjektu1() {
        return "Panely jsou zapojeny do " + revize.getPocetStringu() + " stringy/ů a odjištěno předepsanými pojistkami v pojistkovém odpojovači. " +
                "Zároveň je připojena přepěťová ochrana DC technologie.";
    }

    private String getPopisRevidovanehoObjektu2() {
        StringBuilder stridaceText = this.getStridaceText();
        return "Z rozvaděče DC je napájen " + stridaceText + "který je do sítě připojen přes AC rozvaděč, kde je instalováno rozpadové " +
                "místo instalace FVE stykačem 1-0 a elektroměrem který měří veškerou vyrobenou el. energii. V tomto rozvaděči jsou instalovány " +
                "fázové přepěťové ochrany AC. 1+2 st.";
    }

    private String getPopisRevidovanehoObjektu3() {
        StringBuilder vyrobceText = this.getVyrobceText();
        return "Zároveň je doloženo vydání Prohlášení o shodě s PPDS 2021 s vybranými vlastnostmi dle regulace RfG 2016/631 od výrobce " + vyrobceText +
                ", které je v příloze revizní zprávy. Další přílohou revizní zprávy jsou datesheety použitých FVE panelů a střídače.";
    }

    private String getPrepetovaOchrana() {
        return "PŘEPĚTOVÁ OCHRANA: " + revize.getPrepetovaOchrana();
    }

    private String getJisteni() {
        return revize.getJisteni();
    }

    private void putRowsToTable(Node putThemBeforeNode, Stridace stridace) {
        // Inserting next stridace
        Node thisTableRow = putThemBeforeNode.getParentNode().getParentNode();
        Node stridaceRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);
        Node vyrobniCisloRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);

        setStridaceContentToNode(stridaceRow, vyrobniCisloRow, stridace);

        thisTableRow.getParentNode().insertBefore(stridaceRow, thisTableRow);
        thisTableRow.getParentNode().insertBefore(vyrobniCisloRow, thisTableRow);
    }

    private void putStridaceRowsToNextTable(Node putThemBeforeNode, Stridace stridace) {
        // Inserting next stridace
        Node thisTableRow = putThemBeforeNode.getParentNode().getParentNode();
        Node stridaceRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);
        Node vyrobniCisloRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);

        stridaceRow.getChildNodes().item(1).getFirstChild().setTextContent("Střídač: " + stridace.getVyrobce() + " " + stridace.getNazev());
        stridaceRow.getChildNodes().item(5).getFirstChild().setTextContent(stridace.getPocet() + "ks");

        vyrobniCisloRow.getChildNodes().item(1).getFirstChild().setTextContent("vč.: " + stridace.getVyrobniCislo());

        thisTableRow.getParentNode().insertBefore(stridaceRow, thisTableRow);
        thisTableRow.getParentNode().insertBefore(vyrobniCisloRow, thisTableRow);
    }

    private void putPanelyRowsToNextTable(Node putThemBeforeNode, Map.Entry<String, List<Panely>> panelyDict) {
        // Inserting next stridace
        Node thisTableRow = putThemBeforeNode.getParentNode().getParentNode();
        Node stridaceRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);

        Short panelyVykon = panelyDict.getValue().get(0).getIdTypyPaneluTypyPanelu().getVykon();
        String panelyPower = String.valueOf(panelyDict.getValue().size() * panelyVykon);
        stridaceRow.getChildNodes().item(1).getFirstChild().setTextContent(panelyDict.getValue().size() + " ks" + " " + panelyDict.getKey());
        stridaceRow.getChildNodes().item(5).getFirstChild().setTextContent(panelyPower + "kWp");
        stridaceRow.getChildNodes().item(8).getFirstChild().setTextContent("30");
        stridaceRow.getChildNodes().item(10).getFirstChild().setTextContent("0,4");

        thisTableRow.getParentNode().insertBefore(stridaceRow, thisTableRow);
    }

    private void setStridaceContentToNode(Node stridaceRow, Node vyrobniCisloRow, Stridace stridace) {
        // Set content
        stridaceRow.getFirstChild().getFirstChild().setTextContent("Střídač: " + stridace.getNazev());
        stridaceRow.getChildNodes().item(1).getFirstChild().setTextContent(stridace.getPocet() + " ks");
        vyrobniCisloRow.getFirstChild().getFirstChild().setTextContent("v.č.: " + stridace.getVyrobniCislo());
    }

    private void putPanelyToTable(Node putThemBeforeNode, Map.Entry<String, List<Panely>> panelyDict) {
        Node thisTableRow = putThemBeforeNode.getParentNode().getParentNode();
        Node stridaceRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);
        Node vyrobniCisloRow = putThemBeforeNode.getParentNode().getParentNode().getParentNode().getLastChild().cloneNode(true);

        Short panelyVykon = panelyDict.getValue().get(0).getIdTypyPaneluTypyPanelu().getVykon();
        String panelyPower = String.valueOf(panelyDict.getValue().size() * panelyVykon);
        stridaceRow.getFirstChild().getFirstChild().setTextContent("Vlastní " + panelyDict.getValue().size() + " ks panelů " + panelyVykon + "Wp");
        stridaceRow.getChildNodes().item(1).getFirstChild().setTextContent(panelyPower);
        stridaceRow.getChildNodes().item(4).getFirstChild().setTextContent(panelyPower);
        vyrobniCisloRow.getFirstChild().getFirstChild().setTextContent("Typ: " + panelyDict.getKey());

        thisTableRow.getParentNode().insertBefore(stridaceRow, thisTableRow);
        thisTableRow.getParentNode().insertBefore(vyrobniCisloRow, thisTableRow);
    }

    private void addFvePanelToTable(Node putThemBeforeNode, Panely panely, Integer i) {
        Node thisTableRow = putThemBeforeNode.getParentNode().getParentNode();
        Node newTableRow = putThemBeforeNode.getParentNode().getParentNode().cloneNode(true);

        newTableRow.getFirstChild().getFirstChild().setTextContent(String.valueOf(i));
        newTableRow.getChildNodes().item(1).getFirstChild().setTextContent(panely.getVyrobniCislo());

        thisTableRow.getParentNode().insertBefore(newTableRow, thisTableRow);
    }

    private Map<String, List<Panely>> getPanely() {
        Map<String, List<Panely>> panelyDict = new HashMap<String, List<Panely>>();
        for (Panely panel : panely) {
            TypyPanelu typyPanelu = panel.getIdTypyPaneluTypyPanelu();
            String typPanelu = typyPanelu.getTyp();
            if (!panelyDict.containsKey(typPanelu)) {
                List<Panely> panelyList = new ArrayList<Panely>();
                panelyList.add(panel);
                panelyDict.put(typPanelu, panelyList);
                continue;
            }
            panelyDict.get(typPanelu).add(panel);
        }
        return panelyDict;
    }


    public void createReport() throws Exception {
        // Set dates
        datumVypracovaniRevize.setTextContent(getFormattedDate(revize.getDatumVypracovani()));
        datumPredaniRevize.setTextContent(getFormattedDate(revize.getDatumPredaniRevize()));
        datumUkonceniRevize.setTextContent(getFormattedDate(revize.getDatumUkonceniRevize()));

        objednatelUliceMestoZeme.setTextContent(getObjednateleUliceMestoZeme());
        revidovanyObjekt.setTextContent(getRevidovaneObjektyInfo());
        novaInstalace.setTextContent(translateNovaInstalace());

        distribucniSit.setTextContent(getDistribucniSit());
        pocetFazi.setTextContent(getPocetFazi());
        predmetRevize.setTextContent(getPredmetRevizeText());

        // Setting popis revidovaneho objektu
        popisRevidovanehoObjektu.setTextContent(getPopisRevidovanehoObjektu());
        popisRevidovanehoObjektu1.setTextContent(getPopisRevidovanehoObjektu1());
        popisRevidovanehoObjektu2.setTextContent(getPopisRevidovanehoObjektu2());
        popisRevidovanehoObjektu3.setTextContent(getPopisRevidovanehoObjektu3());

        // TODO prepetova ochrana musi bejt string a pridat vsude kde jsou ulice collum cislo popisne (i do databaze ) xd
        prepetovaOchrana.setTextContent(getPrepetovaOchrana());
        typJisteni.setTextContent(getJisteni());

        for (int i = 0; i < panely.size(); i++) {
            addFvePanelToTable(fvePanelyPlaceholder, panely.get(i), i+1);
        }

        Integer kwCelkemvalue = 0;
        Map<String, List<Panely>> typesPanely = getPanely();
        for (Map.Entry<String, List<Panely>> panel : typesPanely.entrySet()) {
            kwCelkemvalue += panel.getValue().get(0).getIdTypyPaneluTypyPanelu().getVykon() * panel.getValue().size();
            putPanelyToTable(stridac, panel);
            putPanelyRowsToNextTable(typJisteni, panel);
        }

        kWCelkem1.setTextContent(String.valueOf(kwCelkemvalue));

        setStridaceContentToNode(stridac.getParentNode().getParentNode(), stridacVyrobniCislo.getParentNode().getParentNode(), stridace.get(0));
        stridace.remove(0);
        for (Stridace value : stridace) {
            putRowsToTable(nextStridacePlaceholder, value);
            putStridaceRowsToNextTable(typJisteni, value);
        }
    }

    public void saveReport(String revizeName) throws Exception {
        File f = new File(resultDirectory + revizeName);
        if (f.exists()) {
            Files.delete(Path.of(resultDirectory + revizeName));
        }
        doc.save(resultDirectory + revizeName);
    }

}
