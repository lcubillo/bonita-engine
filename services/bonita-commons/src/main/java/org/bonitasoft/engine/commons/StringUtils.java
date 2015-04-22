/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.engine.commons;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * String manipulation utilitary class.
 *
 * @author Emmanuel Duchastenier
 */
public class StringUtils {

    protected static boolean IS_OS_UNIX = SystemUtils.IS_OS_UNIX;

    /**
     * Replaces all "\" character with {@code File.separator character}, and ensures that there is no double "/".
     *
     * @param path the path-like string to clean.
     * @return the cleaned path-like string.
     */
    public static String uniformizePathPattern(final String path) {
        return FilenameUtils.normalize(path, IS_OS_UNIX);
    }
}
