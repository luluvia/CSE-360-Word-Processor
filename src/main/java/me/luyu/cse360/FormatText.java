package me.luyu.cse360;

import java.util.stream.Stream;

/*
 * FormatText handles the formatting functions for the input text file.
 * All of the formatting-related code should go here.
 * inputLines in formatText is the input text.
 */
class FormatText {

    // Enum declarations.

    enum Alignment
    {
        LEFT, CENTER, CENTER_NO_JUSTIFY, RIGHT
    }


    // Default format settings.

    private static final int DEFAULT_CHARS_PER_LINE = 80;
    /*
     * The following two "split column" integer values determine the size in chars of the two
     * columns when the "single column" flag is false.
     */
    private static final int DEFAULT_SPLIT_COLUMN_CHARS_FIRST = 35;
    private static final int DEFAULT_SPLIT_COLUMN_CHARS_SECOND = 35;
    private static final int DEFAULT_SPLIT_COLUMN_CHARS_SPACES = 10;
    private static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;
    private static final boolean DEFAULT_SINGLE_SPACING = true;
    private static final boolean DEFAULT_PARAGRAPH_INDENT = false;
    private static final boolean DEFAULT_BLOCK_INDENT = false;
    private static final boolean DEFAULT_SINGLE_COLUMN = true;

    // Runtime format settings.

    private static int charsPerLine = DEFAULT_CHARS_PER_LINE;
    private static int charsSplitColumnFirst = DEFAULT_SPLIT_COLUMN_CHARS_FIRST;
    private static int charsSplitColumnSecond = DEFAULT_SPLIT_COLUMN_CHARS_SECOND;
    private static int charsSplitColumnSpaces = DEFAULT_SPLIT_COLUMN_CHARS_SPACES;
    private static Alignment alignment = DEFAULT_ALIGNMENT;
    // Double spacing would override single spacing, which is why this is a boolean.
    private static boolean singleSpacing = DEFAULT_SINGLE_SPACING;
    private static boolean paragraphIndent = DEFAULT_PARAGRAPH_INDENT;
    private static boolean blockIndent = DEFAULT_BLOCK_INDENT;
    private static boolean singleColumn = DEFAULT_SINGLE_COLUMN;

    static String formatText(Stream<String> inputLines)
    {
        return null; // TODO
    }

}
