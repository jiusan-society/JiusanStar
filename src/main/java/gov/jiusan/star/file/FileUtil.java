/*
 * Copyright 2019 Marcus Lin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.jiusan.star.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Service
public class FileUtil {

    public static List<File> getDirFiles(String dirName) {
        File dir = new File(dirName);
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
            .sorted(Comparator.comparing(File::lastModified).reversed())
            .collect(Collectors.toList());
    }

    public static List<File> getDirFiles(File dir) {
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
            .sorted(Comparator.comparing(File::lastModified).reversed())
            .collect(Collectors.toList());
    }
}
