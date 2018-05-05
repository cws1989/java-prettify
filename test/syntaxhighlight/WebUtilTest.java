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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

public class WebUtilTest {

    private static final Logger LOG = Logger.getLogger(WebUtilTest.class.getName());

    public WebUtilTest() {

    }

    protected static String getClassName() {
        return new Object() {
        }.getClass().getEnclosingClass().getName();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("***** " + getClassName() + " *****");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("******************************\r\n");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContentToHtml() {
        System.out.println("+++++ testContentToHtml +++++");

        String content = "Lorem > ispum > dolor";
        List<ParseResult> parseResults = Arrays.asList(
                new ParseResult(6, 1, Collections.singletonList("kw1")),
                new ParseResult(14, 1, Collections.singletonList("kw2"))
        );
        String result = WebUtil.contentToHtml(content, parseResults);
        assertEquals("<div class=\"prettyprint\"><pre>Lorem <span class=\"kw1\">></span> ispum <span class=\"kw2\">></span> dolor</pre></div>", result);
    }
}
