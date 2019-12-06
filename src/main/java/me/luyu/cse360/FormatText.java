package me.luyu.cse360;

import java.util.StringTokenizer;
import java.util.Scanner;

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

    private static StringBuilder output;
    private static String starterLine;
    private static String currentLine;
    private static String nextLine = "";

    static String formatText()
    {
        Scanner scan = new Scanner(PrimaryController.inputString);
        output = new StringBuilder();
        nextLine = "";
        String lineIn;
        // String lineIn is the line we'll be using as input.
        do
        {
            lineIn = scan.nextLine();
            lineIn = lineIn.trim();
            flagParser(lineIn);
            
            if (!flagLine)
            {
                starterLine = nextLine + " " + lineIn;
                starterLine.trim();
                if (starterLine.length() > charsPerLine)
                {
                    restructureLine(true);
                    addCurrentLineToOutput();

                    while (nextLine.length() > charsPerLine)
                    {
                        restructureLine(false);
                        addCurrentLineToOutput();
                    }

                    if (nextLine.length() > 0)
                    {
                        restructureLine(false);
                        addCurrentLineToOutput();
                    }
                }
                else
                {
                    currentLine = starterLine;
                    addCurrentLineToOutput();
                }            	
            }
        } while (scan.hasNextLine());

        if (Scribe.DEBUG)
        {
            System.out.print(output.toString());
        }

        return output.toString();
    }

    private static void addCurrentLineToOutput() {
        if (firstLine)
        {
            firstLine = false;
        }
        else
        {
            // Apply double or single spacing
            if (singleSpacing) {
              output.append('\n');
            } else {
              output.append('\n');
              output.append('\n');
            }
        }
        output.append(currentLine);
    }

    private static void restructureLine(boolean usingStarterLine)
    {
        boolean startOfCurrentLine = true;
        String token;
        currentLine = "";
        
        // Indentation
        if (blockIndent)
        {
            currentLine = "          "; // 10 char indent
        }
        else if (firstLine && paragraphIndent)
        {
            currentLine = "     "; // 5 char indent
        }
        else
        {
            currentLine = ""; // default no indent
        }

        // Indentation
        if (blockIndent)
        {
            currentLine = "          "; // 10 char indent
        }
        else if (firstLine && paragraphIndent)
        {
            currentLine = "     "; // 5 char indent
        }
        else
        {
            currentLine = ""; // default no indent
        }

        StringTokenizer tokenizer;

        if (usingStarterLine)
        {
            tokenizer = new StringTokenizer(starterLine);
        }
        else
        {
            tokenizer = new StringTokenizer(nextLine);
        }

        while (tokenizer.hasMoreTokens())
        {
            token = tokenizer.nextToken();
            if ((currentLine.length() + token.length()) < charsPerLine)
            {
                if (startOfCurrentLine)
                {
                    startOfCurrentLine = false;
                }
                else
                {
                    currentLine = currentLine + ' ';
                }
                currentLine = currentLine + token;
            }
            else
            {
                break;
            }
        }
            	
        // TODO: check if justify plays nice with indent
        if (alignment == Alignment.LEFT) {
        	currentLine = applyLeftFlush(currentLine, singleColumn);
        } else if (alignment == Alignment.RIGHT) {
        	currentLine = applyRightFlush(currentLine, singleColumn);
        } else if (alignment == Alignment.CENTER_NO_JUSTIFY) {
        	currentLine = applyCenteredNoJustify(currentLine, singleColumn);
        } else if (alignment == Alignment.CENTER) {
        	currentLine = applyCentered(currentLine, singleColumn);
        }
        
        // Nick: what does this do?
        if (usingStarterLine)
        {
            nextLine = starterLine.substring(currentLine.length() + 1);
        }
        else
        {
            if (nextLine.trim().length() > currentLine.trim().length())
            {
                nextLine = nextLine.substring(currentLine.length() + 1);
            }
            else
            {
                if (blockIndent)
                {
                    nextLine = nextLine.substring(5, currentLine.trim().length());
                }
                else
                {
                    nextLine = nextLine.substring(currentLine.trim().length());
                }
            }
        }
    }

    /*
     * If the input string is a flag, read the flag and update the appropriate setting.
     * Has the side effect of updating flagLine : boolean
     * @param A line, if it's a flag it has format '-[flag]', [flag] is a char
     */
    private static void flagParser(String lineIn)
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
    
    /**
    * Method Description applyLeftFlush formats string to the left side
    * @param isIt80 boolean
    * @return String retVal
    */
    static String applyLeftFlush(String line, boolean isIt80)
    {
         // Local variables
         String retVal = "";
         String space =  "";
         int columnSize = 80;
         int strSize = 0;

         // Local code
         if(isIt80 == false)
         {
             columnSize = 35;
         }

         strSize = line.length();

         while(strSize < columnSize) // loop will add the necessary spaces
                                     // to make the line left justified
         {

            space += " ";

            strSize = strSize + 1;

         }

         retVal = line + space;

         return(retVal);
   	 
    } //ends applyLeftFlush
    
    /**
     * Method Description applyRightFlush formats string to the right side
     * @param isIt80 boolean
     * @return String retVal
     */
    static String applyRightFlush(String line, boolean isIt80)
    {
        // Local variables
        String retVal = "";
        String space =  "";
        int columnSize = 80;
        int strSize = 0;

        // Local code
        if(isIt80 == false)
        {
            columnSize = 35;
        }

        strSize = line.length();

        while(strSize < columnSize) //loop will add the necessary spaces
                                    // to make the line left justified
         {
            space += " ";

            strSize = strSize + 1;

         }

         retVal = space + line;

         return(retVal);
   	 
    } //ends applyRightFlush

    static String applyCentered(String currentLine, boolean isIt80)
    {
        //Local variables
        String retVal = "";
        String spaces = "";
        int counter = 0;
        int columnSize = 80;
        int strSize = 0;
        int sizeToAdd = 0;
        String[] split = currentLine.split(" ");

        //Local Variables
        if(isIt80 == false)
        {
            columnSize = 35;
        }

        strSize = currentLine.length();
        sizeToAdd = (columnSize - currentLine.length()) / (split.length + 2);

        while (counter < sizeToAdd) {
            counter++;
            spaces = " ";
        }

        for ( int i = 0; i <= split.length - 1; i++)
        {
            retVal = spaces + split[i];
        }

        retVal += spaces;

        if (retVal.length() != columnSize) {
            // TODO: throw error
        }

        return(retVal);
    } // ends applyCentered
    
    /**
     * Method Description applyCenterNoJustify formats string to the right side
     * @param isIt80 boolean
     * @return String retVal
     */
    static String applyCenteredNoJustify(String line, boolean isIt80)
    {
        // Local variables
        String retVal = "";
        String space =  "";
        int counter = 0;
        int columnSize = 80;
        int strSize = 0;
        int spacesLeft;
        int spacesToPad;

        // Local code
        if(isIt80 == false)
        {
            columnSize = 35;
        }

        strSize = line.length();
        spacesLeft = columnSize - strSize;
        spacesToPad = spacesLeft / 2;
        
        while(counter < spacesToPad)//get sizes appropriate size
  		{
        	counter++;
        	space += " ";
  		}
        
        retVal = space + line + space;
        
        if (retVal.length() != columnSize) {
        	// TODO: throw error
        }

        return(retVal);
   	 
    } //ends applyCenteredNoJustify
    
}
