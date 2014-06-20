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

import java.util.List;

import org.apache.maven.shared.filtering.AbstractMavenFilteringRequest;
import org.apache.maven.shared.filtering.DefaultMavenFileFilter;
import org.apache.maven.shared.filtering.MavenFileFilter;
import org.apache.maven.shared.filtering.MavenFilteringException;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.FileUtils;

/**
 * A Maven File Filter that translates all non-ASCII characters to the
 * corresponding java escape sequence.
 * <p>
 * This is a Plexus Component with a default hint so it will replace the
 * DefaultMavenFilter when added to a maven-plugin as a dependency.
 */
@Component(role = MavenFileFilter.class, hint = "default")
public class EscapeUnicodeMavenFileFilter extends DefaultMavenFileFilter {

    @Override
    public List<FileUtils.FilterWrapper> getDefaultFilterWrappers(
        final AbstractMavenFilteringRequest req)
        throws MavenFilteringException {
        List<FileUtils.FilterWrapper> result =
            super.getDefaultFilterWrappers(req);

        return result;
    }

}
