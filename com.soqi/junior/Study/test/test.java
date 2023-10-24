package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String html = "<html><body>  <div align=\"left\"> <font face=\"Arial\"><span style=\"font-size:8pt\">erfreererrr</span></font> </div>   </body></html>";

        Pattern pattern = Pattern.compile("<span style=\"font-size:8pt\">(.*?)</span>");
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String extractedText = matcher.group(1);
            System.out.println(extractedText);
        }
    }
}
