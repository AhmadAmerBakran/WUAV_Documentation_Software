package easv_2nd_term_exam.util;

import java.io.*;
import java.nio.file.*;

public final class FileUtility {

    private FileUtility() {} // Private constructor to prevent instantiation

    public static File findUniqueOutputFile(String packagePath, String fileName) {
        int counter = 1;
        String baseFilename = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));
        File outputFile = new File(packagePath + baseFilename + extension);

        while (outputFile.exists()) {
            outputFile = new File(packagePath + baseFilename + "_" + counter + extension);
            counter++;
        }
        return outputFile;
    }

    public static void copySelectedFile(File selectedFile, File targetFile) throws IOException {
        Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void deleteFilesFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }
        }
    }
}
