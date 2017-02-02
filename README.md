# PScout 1 parser and smali converter

PScout is a tool to analyze the Android system and to create a mapping for system calls and their respective permissions. As PScout 2.0 creates corrupt mappings (tracked in this [issue](https://github.com/zd2100/PScout/issues/3)), the only viable mappings are from PScout 1, supporting Android 2.2.3-4.1.1 . The official PScout mappings can be downloaded [here](http://pscout.csl.toronto.edu/downloads.php).

The output format of the PScout 1 parser is quite strange, so this parser aims to convert PScout 1 output files to CSV files with [smali](https://github.com/JesusFreke/smali) compatible types.

## Current Mappings:
- [PScout Android 4.1.1, CSV](https://raw.githubusercontent.com/philipphager/pscout-parser/master/export/protected-methods.csv?token=AIuzK9-5NGPaxedyStaIdjUINWAXLE0Cks5YnFJQwA%3D%3D)
