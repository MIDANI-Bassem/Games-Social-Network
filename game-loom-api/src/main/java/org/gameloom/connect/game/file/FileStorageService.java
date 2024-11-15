package org.gameloom.connect.game.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.security.file.upload.photos-output-path}")
    private String fileUploadPath;

    public String saveFile(
            @NonNull MultipartFile file,
            @NonNull Integer userId) {

        final String fileUploadSubPath = "users" + separator + userId;

        return uploadFile(file, fileUploadSubPath);

    }

    private String uploadFile(
            @NonNull MultipartFile file,
            @NonNull String fileUploadSubPath
    ) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Unable to create folder");
                return null;
            }
        }
            final String fileExtension = getFileExtension(file.getOriginalFilename());
            String targetFilePath = finalUploadPath + separator + System.currentTimeMillis() +"."+ fileExtension;
            Path path = Paths.get(targetFilePath);
            try{
                Files.write(path,file.getBytes());
                log.info("File uploaded successfully{}", targetFilePath);
                return targetFilePath;

            } catch (IOException e) {
                log.error("File was not saved");
            }


        return "";
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex == -1) {

            return "";
        }
        return originalFilename.substring(dotIndex+1).toLowerCase();
    }
}
