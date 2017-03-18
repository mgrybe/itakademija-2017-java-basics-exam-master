import com.lowagie.text.pdf.BaseFont;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mariusg on 2016.12.22.
 */
public final class XhtmlToPdfConverter {

    public byte[] convert(String xhtml) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        builder.setEntityResolver(FSEntityResolver.instance());

        Map<String, String> params = new HashMap<>();
        params.put("content", xhtml);
        Document doc = builder.parse(new InputSource(new StringReader(Templates.render("default.tmpl", params))));

        ITextRenderer renderer = new ITextRenderer();

        List<String> fonts = new LinkedList<>();
        InputStream is = getClass().getResourceAsStream("/fonts/roboto/fonts.list");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fonts.add("/fonts/roboto/" + line);
            }
        }
        // TODO: remove this duplication
        is = getClass().getResourceAsStream("/fonts/source-code-pro/fonts.list");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fonts.add("/fonts/source-code-pro/" + line);
            }
        }

        for (String fontUri : fonts) {
            renderer.getFontResolver().addFont(fontUri,
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
        }

        renderer.getSharedContext().setMedia("pdf");

        renderer.setDocument(doc, null);
        renderer.layout();

        //File outputFile = File.createTempFile("markdown", ".pdf");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        renderer.createPDF(baos, true);
        renderer.finishPDF();

        return baos.toByteArray();
    }

}
