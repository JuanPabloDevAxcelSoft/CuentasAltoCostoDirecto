package com.savia.app.constants;

import org.springframework.core.io.FileSystemResource;

public class PathFileUpload {

    /* Ruta del File server para alojar los .Csv */
    public static final String PATH_FILE_UPLOAD = new FileSystemResource("").getFile().getAbsolutePath()
            + "\\src\\main\\resources\\tmp\\";

}
