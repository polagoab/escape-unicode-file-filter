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

import java.io.IOException;
import java.io.Reader;

/**
 * A Reader that translates all non-ASCII characters to the corresponding java
 * escape sequence.
 */
public class EscapeUnicodeReader extends Reader {

    private static final String UNICODE_PLACEHOLDER = "u0000";

    private final Reader reader;

    private StringBuilder unicodeBuffer;

    /**
     * Public Constructor.
     *
     * @param reader the Reader to wrap
     */
    public EscapeUnicodeReader(Reader reader) {
        this.reader = reader;
        unicodeBuffer = new StringBuilder();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            int ch = readChar();
            if (ch == -1 && i > 0) {
                return i;
            } else if (ch == -1) {
                return -1;
            } else {
                cbuf[off + i] = (char) ch;
            }
        }

        return len;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    /**
     * Read a single char from the wrapped Reader and handle any non-ascii
     * chars.
     *
     * @return a simgle character or -1 if EOF
     * @throws IOException indicating IO Error
     */
    private int readChar() throws IOException {
        int ch = -1;
        if (unicodeBuffer.length() == 0) {
            ch = reader.read();
            if (ch != -1) {
                char c = (char) ch;
                if (c >= '\u0080') {
                    unicodeBuffer = escapeUnicode(c);
                    ch = '\\';
                }
            }
        } else {
            ch = unicodeBuffer.charAt(0);
            unicodeBuffer.deleteCharAt(0);
        }

        return ch;
    }

    /**
     * Escape a single Unicode character to a Java escape sequence.
     *
     * @param ch the characater to process
     * @return a StringBuilder containing the escape sequence
     */
    private StringBuilder escapeUnicode(char ch) {

        StringBuilder result = new StringBuilder(UNICODE_PLACEHOLDER);

        String s = Integer.toHexString(ch);

        for (int i = 0; i < s.length(); i++) {
            result.setCharAt(result.length() - s.length() + i, s.charAt(i));
        }

        return result;
    }

}
