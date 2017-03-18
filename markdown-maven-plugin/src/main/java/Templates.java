import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by mariusg on 2016.12.22.
 */
public final class Templates {

    private Templates() {
    }


    public static String render(String template, Map<String, String> params) throws Exception {
        InputStream is = Templates.class.getResourceAsStream("/templates/" + template);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String templateContent = sb.toString();
        for (Map.Entry<String, String> paramEntry : params.entrySet()) {
            final String paramKey = paramEntry.getKey();
            final String value = paramEntry.getValue();

            templateContent = templateContent.replace("{{" + paramKey + "}}", value);
        }

        return templateContent;
    }
}
