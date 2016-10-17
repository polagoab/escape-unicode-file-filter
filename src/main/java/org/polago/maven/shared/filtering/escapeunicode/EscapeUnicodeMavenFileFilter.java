/*
 * Copyright 2014-2016 Polago AB.
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.maven.shared.filtering.DefaultMavenFileFilter;
import org.apache.maven.shared.filtering.MavenFileFilter;
import org.apache.maven.shared.filtering.MavenFilteringException;
import org.apache.maven.shared.utils.io.FileUtils;
import org.codehaus.plexus.component.annotations.Component;

/**
 * A Maven File Filter that translates all non-ASCII characters in
 * properties-files to the corresponding java escape sequence.
 * <p>
 * This is a Plexus Component with a default hint so it will replace the
 * DefaultMavenFilter when added to a maven-plugin as a dependency.
 */
@Component(role = MavenFileFilter.class, hint = "default")
public class EscapeUnicodeMavenFileFilter extends DefaultMavenFileFilter {

    private static final String SYSTEM_PROPERTY =
        EscapeUnicodeMavenFileFilter.class.getName() + ".pattern";

    // Properties files are by definition ISO-8859-1
    private static final String PROPERTIES_ENCODING = "ISO-8859-1";

    private Pattern escapePattern = Pattern.compile(".*\\.properties$");

    /**
     * Public Constructor.
     */
    EscapeUnicodeMavenFileFilter() {
        super();
        String pattern = System.getProperty(SYSTEM_PROPERTY);
        if (pattern != null) {
            escapePattern = Pattern.compile(pattern);
        }
    }

    @Override
    public void copyFile(File from, File to, boolean filtering,
        List<FileUtils.FilterWrapper> filterWrappers, String encoding,
        boolean overwrite) throws MavenFilteringException {

        getLogger().debug("Using Escape Pattern: " + escapePattern.pattern());

        List<FileUtils.FilterWrapper> w = filterWrappers;
        if (filtering && isPropertiesFile(from)) {
            getLogger().debug("Adding EscapeUnicodeFilterWrapper for file: "
                + from.getName());
            encoding = PROPERTIES_ENCODING;
            w = new ArrayList<FileUtils.FilterWrapper>();
            w.addAll(filterWrappers);
            w.add(new EscapeUnicodeFilterWrapper());
        }
        super.copyFile(from, to, filtering, w, encoding, overwrite);
    }

    /**
     * Determine if the given File is a properties file.
     *
     * @param file the File to examine
     * @return true if the FILE
     */
    private boolean isPropertiesFile(File file) {
        return escapePattern.matcher(file.getName()).matches();
    }
}
