package com.siudek.courses.storage;

import java.io.IOException;
import java.io.InputStream;

public interface ImageStorageClient {
    String uploadImage(String containerName, String originalImageName ,InputStream data, long length)throws IOException;
}
