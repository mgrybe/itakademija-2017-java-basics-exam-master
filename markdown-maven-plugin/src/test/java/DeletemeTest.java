import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by mariusg on 2017.01.27.
 */
@Ignore
public class DeletemeTest {

    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(Parser.EXTENSIONS, Collections.singleton(TablesExtension.create()));

    @Test
    public void test() throws Exception {
        File inputFile = new File("C:\\Users\\mariusg\\Desktop\\test.md");
        File outputFile = new File("C:\\Users\\mariusg\\Desktop\\test.pdf");

        String xhtmlSource = markdownToXhtml(inputFile);
        byte[] pdfBytes = new XhtmlToPdfConverter().convert(xhtmlSource);

        try (OutputStream os = new FileOutputStream(outputFile)) {
            os.write(pdfBytes);
        }
    }

    private String markdownToXhtml(final File inputFile) throws IOException {
        Parser parser = Parser.builder(OPTIONS).build();

        Node document = parser.parseReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));

        HtmlRenderer htmlRenderer = HtmlRenderer.builder(OPTIONS).escapeHtml(false).build();
        return htmlRenderer.render(document);
    }

}
