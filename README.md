# PScout 1 parser and smali converter

PScout is a tool to analyze the Android system and to create a mapping for system calls and their respective permissions. As PScout 2.0 creates corrupt mappings (tracked in this [issue](https://github.com/zd2100/PScout/issues/3)), the only viable mappings are from PScout 1, supporting Android 2.2.3-5.1 . The official PScout mappings can be downloaded [here](http://pscout.csl.toronto.edu/downloads.php).

The output format of the PScout 1 parser is quite strange, so this parser aims to convert PScout 1 output files to CSV files with [smali](https://github.com/JesusFreke/smali) compatible types.

## Current Mappings:
- [PScout API 22, Android 5.1, CSV](https://raw.githubusercontent.com/philipphager/pscout-parser/master/export/protected-methods.csv)

## Build the project
1. Clone / Download the project
2. cd into the project directory
3. Run `./gradlew assemble` to build the project. The executable .jar will be located in `/build/libs`
4. Run the tool with `java -jar ./build/libs/pscout-parser-0.1.jar -i path/to/your/pscout1/file.txt -o /path/to/the/output/directory`. Just provide the parameters -i for the pscout input file and -o for the output directory for the parsed .csv file.
