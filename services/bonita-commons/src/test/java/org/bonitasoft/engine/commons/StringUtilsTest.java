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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.io.File;

public class StringUtilsTest {

    @Test
    public void uniformizePathPatternShouldChangeAllSlashesToSystemDependentSeparator_On_Linux() {
        final String uniformized = StringUtils.uniformizePathPattern("C:\\toto\\my path\\my\\/file.bak.txt");
        assertThat(uniformized)
                .isEqualTo("C:/toto/my path/my/file.bak.txt");
    }

    @Test
     public void uniformizePathPatternShouldChangeAllSlashesToSystemDependentSeparator_On_Windows() {
        StringUtils.IS_OS_UNIX = false;
        final String uniformized = StringUtils.uniformizePathPattern("C:\\toto\\my path\\my\\/file.bak.txt");
        assertThat(uniformized)
                .isEqualTo("C:\\toto\\my path\\my\\file.bak.txt");
    }

    @Test
    public void uniformizePathPatternShouldLeaveNoDoubleSeparator_On_Linux() {
        final String uniformized = StringUtils.uniformizePathPattern("C:///toto//my path/////full_slashes/my file.bak.txt");
        assertThat(uniformized).isEqualTo("C://toto/my path/full_slashes/my file.bak.txt");
    }

    @Test
    public void uniformizePathPatternShouldLeaveNoDoubleSeparator_On_Windows() {
        StringUtils.IS_OS_UNIX = false;
        final String uniformized = StringUtils.uniformizePathPattern("C:///toto//my path/////full_slashes/my file.bak.txt");
        assertThat(uniformized).isEqualTo("C:\\\\toto\\my path\\full_slashes\\my file.bak.txt");
    }

    @Test
    public void uniformizePathPatternOnNetworkDriveShouldWork() {
        StringUtils.IS_OS_UNIX = false;
        final String uniformized = StringUtils.uniformizePathPattern("\\\\server\\shareddir\\bonita");
        assertThat(uniformized).isEqualTo(
                "\\\\server\\shareddir\\bonita");
    }

    @Test
    public void uniformizePathPatternShouldDoNothingOnSimpleString() {
        final String uniformized = StringUtils.uniformizePathPattern("test");
        assertThat(uniformized).isEqualTo("test");
    }

}
