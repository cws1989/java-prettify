/**
 * *****************************************************************************
 * Copyright 2013 Jeremie Bresson
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * ****************************************************************************
 */
package syntaxhighlight;

import java.awt.Color;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class WebUtil {

    private static final Logger LOG = Logger.getLogger(WebUtil.class.getName());
    private static final String MAIN_CLASS = "prettyprint";

    /**
     * Decorate/convert the content to HTML with {@link ParseResult}.
     *
     * @param content the original source code
     * @param parseResults the {@link Parser} parsed results
     * @return the HTML in string
     */
    public static String contentToHtml(String content, List<ParseResult> parseResults) {
        StringBuilder returnResult = new StringBuilder();

        returnResult.append("<div class=\"" + MAIN_CLASS + "\">");
        returnResult.append("<pre>");

        int endIndex = 0;
        for (ParseResult pr : parseResults) {
            int startIndex = pr.getOffset();
            if (startIndex > endIndex) {
                returnResult.append(content.substring(endIndex, startIndex));
            }
            endIndex = startIndex + pr.getLength();

            returnResult.append("<span class=\"" + pr.getStyleKeysString() + "\">");
            returnResult.append(content.substring(startIndex, endIndex));
            returnResult.append("</span>");
        }
        if (content.length() > endIndex) {
            returnResult.append(content.substring(endIndex, content.length()));
        }

        returnResult.append("</pre>");
        returnResult.append("</div>");

        return returnResult.toString();
    }

    /**
     * Convert the {@link Theme} to CSS.
     *
     * @param theme the theme
     * @return the CSS in string
     */
    public static String themeToCss(Theme theme) {
        StringBuilder returnResult = new StringBuilder();

        returnResult.append("." + MAIN_CLASS + "  {");
        returnResult.append("font-family: monospace; ");
        appendCssColor(returnResult, "background-color", theme.getBackground());
        returnResult.append("}\n");

        for (Entry<String, Style> entry : theme.getStyles().entrySet()) {
            returnResult.append("." + MAIN_CLASS + " ." + entry.getKey() + "  {");

            Style style = entry.getValue();
            appendCssColor(returnResult, "background-color", style.getBackground());
            appendCssColor(returnResult, "color", style.getColor());
            appendCssText(returnResult, "font-weight: bold", style.isBold());
            appendCssText(returnResult, "font-style: italic", style.isItalic());
            appendCssText(returnResult, "text-decoration: underline", style.isUnderline());

            returnResult.append("}\n");
        }

        return returnResult.toString();
    }

    private static void appendCssColor(StringBuilder sb, String cssKey, Color color) {
        if (color != null) {
            sb.append(cssKey + ": " + encodeColorToHexString(color) + "; ");
        }
    }

    private static String encodeColorToHexString(Color color) {
        String hexString = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hexString.length() < 6) {
            hexString = "000000".substring(0, 6 - hexString.length()) + hexString;
        }
        return '#' + hexString;
    }

    private static void appendCssText(StringBuilder sb, String cssProp, boolean isSet) {
        if (isSet) {
            sb.append(cssProp + "; ");
        }
    }
}
