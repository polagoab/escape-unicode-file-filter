/*
 * Copyright 2014 Polago AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.polago.maven.shared.filtering.escapeunicode;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

/**
 * Test the {@link EscapeUnicodeReaderTest} class.
 */
public class EscapeUnicodeReaderTest {

    @Test
    public void testEmptyReader() throws IOException {
	EscapeUnicodeReader r = setUpReader("");
	assertEquals(-1, r.read());
    }

    @Test
    public void testAllASCIIReader() throws IOException {
	String expected = "ascii";
	EscapeUnicodeReader r = setUpReader(expected);
	assertEquals(expected, readAll(r));
    }

    @Test
    public void testSingleUnicodeCharReader() throws IOException {
	String source = "\u00e4";
	String expected = "\\u00e4";
	EscapeUnicodeReader r = setUpReader(source);
	assertEquals(expected, readAll(r));
    }

    @Test
    public void testUnicodeCharReader() throws IOException {
	String source = "before\u00e4after";
	String expected = "before\\u00e4after";
	EscapeUnicodeReader r = setUpReader(source);
	assertEquals(expected, readAll(r));
    }

    @Test
    public void testUnicodeCharReaderUsingBuffer() throws IOException {
	String source = "before\u00e4after";
	String expected = "before\\u00e4after";
	EscapeUnicodeReader r = setUpReader(source);
	
	char[] buf = new char[1024];
	int result = r.read(buf, 100, 100);
	assertEquals(expected.length(), result);
	assertEquals(expected, String.copyValueOf(buf, 100, result));
    }

    private String readAll(EscapeUnicodeReader r) throws IOException {
	StringBuilder result = new StringBuilder();
	int c = r.read();
	while (c != -1) {
	    result.append((char) c);
	    c = r.read();
	}
	return result.toString();
    }

    private EscapeUnicodeReader setUpReader(String s) {
	return new EscapeUnicodeReader(new StringReader(s));
    }

}
