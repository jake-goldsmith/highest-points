package highest.points.io;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FileFinder {
    private File file;

    public List<File> findFilesRecursively(String suffix) {
        if (file.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .map(file -> new FileFinder(file).findFilesRecursively(suffix))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } else if (file.getName().endsWith(suffix)) {
            return Collections.singletonList(file);
        } else {
            return Collections.emptyList();
        }
    }
}
