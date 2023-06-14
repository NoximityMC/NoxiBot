package com.noximity.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Resources {
    public static void saveDefaultResource(String resourceName, String outputPath) throws IOException {
        // Retrieve the input stream of the resource
        InputStream inputStream = Resources.class.getResourceAsStream("/" + resourceName);

        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourceName);
        }

        // Create the output stream
        OutputStream outputStream = new FileOutputStream(outputPath);

        // Read from the input stream and write to the output stream
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Close the streams
        inputStream.close();
        outputStream.close();
    }
}
