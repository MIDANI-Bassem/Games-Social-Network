package org.gameloom.connect.game.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String fileUrl) {
        if(StringUtils.isEmpty(fileUrl)) {
            return null;
        }
        try{
            Path path = Paths.get(fileUrl);
            return Files.readAllBytes(path);
        }catch (IOException e) {
            log.warn("Error reading file from location: " + fileUrl, e);
        }
        return null;
    }
}
