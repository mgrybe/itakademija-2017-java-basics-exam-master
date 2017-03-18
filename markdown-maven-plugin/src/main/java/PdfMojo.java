import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by mariusg on 2017.01.12.
 */
@Mojo(name = "pdf")
public class PdfMojo extends AbstractMojo {

    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(Parser.EXTENSIONS, Collections.singleton(TablesExtension.create()));

    @Parameter
    private File inputFile;

    @Parameter
    private File outputFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            if (inputFile == null || !inputFile.exists()) {
                return;
            }

            Objects.requireNonNull(outputFile);

            String xhtmlSource = markdownToXhtml(inputFile);
            byte[] pdfBytes = new XhtmlToPdfConverter().convert(xhtmlSource);

            try (OutputStream os = new FileOutputStream(outputFile)) {
                os.write(pdfBytes);
            }
        } catch (Exception e) {
            throw new MojoFailureException("Markdown to PDF conversion failure", e);
        }
    }

    private String markdownToXhtml(final File inputFile) throws IOException {
        Parser parser = Parser.builder(OPTIONS).build();

        Node document = parser.parseReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));

        HtmlRenderer htmlRenderer = HtmlRenderer.builder(OPTIONS).escapeHtml(false).build();
        return htmlRenderer.render(document);
    }

}
