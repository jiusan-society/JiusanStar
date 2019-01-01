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

package gov.jiusan.star.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
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

    public static void saveToFile(MultipartFile file, String dirPath) {
        File f = new File(dirPath + file.getOriginalFilename());
        saveToFile(file, f);
    }

    public static void saveToFile(MultipartFile file) {
        File f = new File(file.getOriginalFilename());
        saveToFile(file, f);
    }

    private static void saveToFile(MultipartFile file, File f) {
        try (FileOutputStream fos = new FileOutputStream(f); BufferedInputStream bis = new BufferedInputStream(file.getInputStream())) {
            byte[] span = new byte[256];
            for (int len = 0; len != -1; len = bis.read(span)) {
                fos.write(span, 0, len);
            }
        } catch (IOException e) {
            // TODO 待加入 logger 机制
        }
    }
}
