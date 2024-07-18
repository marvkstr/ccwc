# Coding Challenge 1
## Build Your Own wc Tool

This challenge is to build your own version of the Unix command line tool wc!

The Unix command line tools are a great metaphor for good software engineering and they follow the Unix Philosophies of:

* Writing simple parts connected by clean interfaces - each tool does just one thing and provides a simple CLI that handles text input from either files or file streams.
* Design programs to be connected to other programs - each tool can be easily connected to other tools to create incredibly powerful compositions.
* Following these philosophies has made the simple unix command line tools some of the most widely used software engineering tools - allowing us to create very complex text data processing pipelines from simple command line tools. There’s even a Coursera course on Linux and Bash for Data Engineering.

You can read more about the Unix Philosophy in the excellent book The Art of Unix Programming.

https://codingchallenges.fyi/challenges/challenge-wc#help-others-by-sharing-your-solutions

## Build
```mvn package -Dpackaging=native-image``` builds the image as a native binary
For this GraalVM is used – see their Documentation on how to install GraalVm


## Usage
```
Usage: ccwc [-chlmVw] [<file>]
Replicates wc capabilities
      [<file>]       The file to be counted.
  -c, --bytes        The number of bytes in each input file is written to the
                       standard output.
  -h, --help         Show this help message and exit.
  -l, --lines        The number of lines in each input file is written to the
                       standard output.
  -m, --characters   The number of characters in each input file is written to
                       the standard output.
  -V, --version      Print version information and exit.
  -w, --words        The number of words in each input file is written to the
                       standard output.
```

Also supports STDIN via ```cat someTxtFile | ccwc```