package com.mkoe;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

@Command(name = "ccwc", mixinStandardHelpOptions = true, version = "ccwc 0.0.1ALPHA",
    description = "Replicates wc capabilities")
public class CCWC implements Callable<Integer> {
    @Parameters(index = "0", description = "The file to be counted.", arity = "0..1")
    private File file;

    @Option(names = {"-l", "--lines"}, description = "The number of lines in each input file is written to the standard output.")
    private boolean lines;

    @Option(names = {"-w", "--words"}, description = "The number of words in each input file is written to the standard output.")
    private boolean words;

    @Option(names = {"-c", "--bytes"}, description = "The number of bytes in each input file is written to the standard output.")
    private boolean bytes;

    @Option(names = {"-m", "--characters"}, description = "The number of characters in each input file is written to the standard output.")
    private boolean characters;

    @Override
    public Integer call() throws Exception { // your business logic goes here...

        String fileContent;
        String fileName = "";

        if (this.file == null) {
            Scanner scanner = new Scanner(System.in);
            fileContent = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            fileName = "";
            scanner.close();
        } else {
            fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
            fileName = this.file.getName();
        }

        FileHandler handler = new FileHandler(fileContent);

        List<Operation> operations = new ArrayList<>();

        if (this.lines) {
            operations.add(Operation.LINES);
        }
        if (this.words) {
            operations.add(Operation.WORDS);
        }
        if (this.bytes) {
            operations.add(Operation.BYTES);
        }
        if (this.characters) {
            operations.add(Operation.CHARACTERS);
        }

        if (operations.isEmpty()) {
            operations.add(Operation.DEFAULT);
        }

        System.out.println(handler.processFile(operations.getFirst()) + " " + fileName);

        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new CCWC()).execute(args);
        System.exit(exitCode);
    }
}