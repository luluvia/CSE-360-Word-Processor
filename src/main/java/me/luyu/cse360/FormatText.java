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
    private static boolean flagLine = true;
    private static boolean firstLine = true;

    /*
     * Format the text
     */
    static String formatText()
    {
    	String buffer;
    	
        for (String lineIn : (Iterable<String>) PrimaryController.inputLines::iterator)
        {
        	lineIn = lineIn.trim();
            flagParser(lineIn);
            
            if (!flagLine)
            {
                // TODO Apply formatting to lines.
            	// Apply spacing
            	if (singleSpacing) {
            		lineIn = applySingleSpacing(lineIn);
            	} else {
            		lineIn = applyDoubleSpacing(lineIn);
            	}
            	
            	// Apply indent
            	if (blockIndent) {
            		lineIn = applyBlockIndent(lineIn);
            	}
            	
            	if (paragraphIndent && firstLine) {
            		/*
            		if (blockIndent) {
            			// TODO: Define custom error?
            			//throw new InvalidFormatSetting("Block indent and paragraph indent flags cannot be set simultaneously.");
            		} else {
            			lineIn = applyParagraphIndent(lineIn);
            		}
            		*/
            		
            		lineIn = applyParagraphIndent(lineIn);
            	}
            	
            	// TODO: apply justify
            	
            	// TODO: buffer in the line according to column setting
            	
            	// This may be a useful line
            	// buffer = lineIn.substring(0, Math.min(lineIn.length(), charsPerLine)); // Get 1st 35/80 chars
            	
            	firstLine = false; // This will trip to false the first time
            }
        }

        return null; // TODO
    }

    /*
     * If the input string is a flag, read the flag and update the appropriate setting.
     * Has the side effect of updating flagLine : boolean
     * @param A line, if it's a flag it has format '-[flag]', [flag] is a char
     */
    static void flagParser(String lineIn)
    {
        flagLine = true;
        if ((lineIn.length() == 2) && (lineIn.charAt(0) == '-'))
        {
            switch (lineIn.toLowerCase().charAt(1))
            {
                case('r'):
                    alignment = Alignment.RIGHT;
                    break;
                case('c'):
                    alignment = Alignment.CENTER;
                    break;
                case('l'):
                    alignment = Alignment.LEFT;
                    break;
                case('t'):
                    alignment = Alignment.CENTER_NO_JUSTIFY;
                    break;
                case('d'):
                    singleSpacing = false;
                    break;
                case('s'):
                    singleSpacing = true;
                    break;
                case('i'):
                    paragraphIndent = true;
                    break;
                case('b'):
                    blockIndent = true;
                    break;
                case('n'):
                    paragraphIndent = false;
                    blockIndent = false;
                    break;
                case('1'):
                    singleColumn = true;
                    break;
                case('2'):
                    singleColumn = false;
                    break;
                default:
                    flagLine = false;
            }
        }
        else
        {
            flagLine = false;
        }
    }
    
    /*
     * Make an input string single spaced, or return it if it's already single spaced.
     * @param Input string, may be single spaced or double spaced
     * @return Output a string that's single spaced
     */
    static String applySingleSpacing(String lineIn) {
    	String spaced = lineIn;
    	
    	// TODO: Make spaced single spaced
    	
    	return spaced;
    }
    
    /*
     * Make an input string double spaced, or return it if it's already double spaced.
     * @param Input string, may be single spaced or double spaced
     * @return Output a string that's double spaced
     */
    static String applyDoubleSpacing(String lineIn) {
    	String spaced = lineIn;
    	
    	// TODO: Make spaced double spaced
    	
    	return spaced;
    }
    
    /*
     * Indent the input by 10 spaces
     * @param Input string, expect it not to be indented already
     * @return Output a string that's indented
     */
    static String applyBlockIndent(String lineIn) {
    	String indented;
    	String tenSpaces = "          ";
    	
    	indented = tenSpaces + lineIn;
    	
    	return indented;
    }
    
    /*
     * Indent the input by 5 spaces
     * @param Input string, expect it not to be indented already
     * @return Output a string that's indented
     */
    static String applyParagraphIndent(String lineIn) {
    	String indented;
    	String fiveSpaces = "     ";
    	
    	indented = fiveSpaces + lineIn;
    	
    	return indented;
    }
}
