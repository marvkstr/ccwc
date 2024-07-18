package com.mkoe;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

public class FileHandler {

    String fileContent;
    String fileLocation;


    public FileHandler(String fileContent) throws IOException {
        this.fileContent = fileContent;
    }

    public String processFile(Operation operationIdentifier) {

        return switch (operationIdentifier) {
            case LINES -> String.valueOf(this.fileContent.split("\n").length);
            case WORDS -> {
                if (this.fileContent == null || this.fileContent.isEmpty()) {
                    yield "0";
                }
                StringTokenizer tokens = new StringTokenizer(this.fileContent);
                yield String.valueOf(tokens.countTokens());
            }
            case CHARACTERS -> String.valueOf(this.fileContent.length());
            case BYTES -> {
                final byte[] utf8Bytes = this.fileContent.getBytes(Charset.defaultCharset());
                yield String.valueOf(utf8Bytes.length);
            }
            case DEFAULT -> new StringBuilder().append(processFile(Operation.LINES))
                .append(" ")
                .append(processFile(Operation.WORDS))
                .append(" ")
                .append(processFile(Operation.BYTES))
                .toString();
        };
    }
}
