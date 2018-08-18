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
public class FileService {

    public List<File> getDirFiles(String dirName) {
        File dir = new File(dirName);
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
            .sorted(Comparator.comparing(File::lastModified).reversed())
            .collect(Collectors.toList());
    }

    public List<File> getDirFiles(File dir) {
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
            .sorted(Comparator.comparing(File::lastModified).reversed())
            .collect(Collectors.toList());
    }
}
