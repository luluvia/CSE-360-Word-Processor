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

        return null; // TODO Use the return to display the output in the Output Tab.
    }

    private static void addCurrentLineToOutput() {
        if (firstLine)
        {
            firstLine = false;
        }
        else
        {
            output.append('\n');
        }
        output.append(currentLine);
    }

    private static void restructureLine(boolean usingStarterLine)
    {
        boolean startOfCurrentLine = true;
        String token;
        currentLine = "";

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

        // TODO Apply formatting to lines.
        // Apply spacing
        if (!singleSpacing) 
        {
            applyDoubleSpacing();
        }
            	
        // Apply indent
        if (blockIndent) 
        {
            applyBlockIndent();
        }
            	
        if (paragraphIndent) 
        {
            /*
            if (blockIndent) {
            	// TODO: Define custom error?
            	//throw new InvalidFormatSetting("Block indent and paragraph indent flags cannot be set simultaneously.");
            } else {
            	lineIn = applyParagraphIndent(lineIn);
            }
            */
            		
            applyParagraphIndent();
            paragraphIndent = false; // paragraphIndent is only applied for the first line encountered.
        }
            	
        // TODO: apply justify
            	
        // TODO: buffer in the line according to column setting
            	
        // This may be a useful line
        // buffer = lineIn.substring(0, Math.min(lineIn.length(), charsPerLine)); // Get 1st 35/80 chars

        if (usingStarterLine)
        {
            nextLine = starterLine.substring(currentLine.length() + 1);
        }
        else
        {
            nextLine = nextLine.substring(currentLine.length() + 1);
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
    
    /*
     * Make an input string single spaced, or return it if it's already single spaced.
     * @param Input string, may be single spaced or double spaced
     * @return Output a string that's single spaced
     */

     // For now this is not being used because the default formatting is single spacing.
     // May be used later if we decide to change the logic.
    private static String applySingleSpacing(String lineIn) 
    {
    	String spaced = lineIn;
    	
    	// TODO: Make spaced single spaced
    	
    	return spaced;
    }
    
    /*
     * Make an input string double spaced, or return it if it's already double spaced.
     * @param Input string, may be single spaced or double spaced
     * @return Output a string that's double spaced
     */

     // Uses currentLine, the formatted text.
    private static String applyDoubleSpacing()
    {
    	String spaced = currentLine;
    	
    	// TODO: Make spaced double spaced
    	
    	return spaced;
    }
    
    /*
     * Indent the input by 10 spaces
     * @param Input string, expect it not to be indented already
     * @return Output a string that's indented
     */
    private static String applyBlockIndent() 
    {
    	String indented;
    	String tenSpaces = "          ";
    	
    	indented = tenSpaces + currentLine;
    	
    	return indented;
    }
    
    /*
     * Indent the input by 5 spaces
     * @param Input string, expect it not to be indented already
     * @return Output a string that's indented
     */
    private static String applyParagraphIndent() {
    	String indented;
    	String fiveSpaces = "     ";
    	
    	indented = fiveSpaces + currentLine;
    	
    	return indented;
    }
    
    /**
     * Method Description
     * @param mStr
     * @param isSize80
     * @return ArrayList<String>
     */
    public static ArrayList<String> intoArrayList2Col(String mStr, boolean isSize80) 
	{
		
		//Local variables
		int iterator = 0;
		String toArrList = "0";
		int columnSize = 80;
		ArrayList<String> strList = new ArrayList<String>();
		String tempStr = "";
		int strLength = 0;
		int arrSize = 0;
		String[] words = mStr.split("\\s");//splits the string with whitespace as the delimeter.
		
		if(isSize80 != true)
		{
			columnSize = 35;
		}
		
		arrSize = words.length; //gets size of the array
		
		tempStr = words[iterator]; //gives a proper value from the array to the list
		
		strLength = tempStr.length(); //gets length of the string to later check it doen't go over 35
		
		toArrList = tempStr; //gives a value to the string that will get added to the arrayList
		
		iterator = iterator + 1; //increments iterator
		
		while( (strLength <= columnSize)  && (arrSize > iterator) )//
		{
			
			tempStr +=  " " + words[iterator]; //appends another word
			
			strLength = tempStr.length(); //gets the size of the string
			
			if(strLength <= columnSize) //if length is not too big modify toArrList string
			{
				toArrList = tempStr;
				
				iterator = iterator + 1;
			}
			
			if(strLength >= columnSize) //if the toArrList is as big as it can get without adding another word then it is good.
			{
				
				strList.add(toArrList);
				
				tempStr = words[iterator];
				
				strLength = tempStr.length();
				
				toArrList = tempStr;
				
				iterator = iterator + 1;
				
			}
			
		}//ends while( (strLength <= columnSize)  && (arrSize > iterator) )
		
		strList.add(toArrList); //adds the last string
		
		/*
		for(int i = 0; i < strList.size(); i++)//DEBUGGING
		{
			
			System.out.print(strList.get(i) + "\n\n");//debugging
			
		}
		*/
		
		return(strList);
		
	}//ends toArrayList
    
    /**
	 * Method Description oneColumn
	 * @param  ArrayList<String> arrList 
	 * @param boolean singleSpace
	 * @return String
	 */
	public static String oneColumn(ArrayList<String> arrList, boolean singleSpace)
	{
		
		//Local Variables
		String retVal = "";
		
		//Local Code
		int iterator = 0;
		int arrListSize = (arrList.size()) - 1;//gets size to stop the loop
		String spacing = "\n";
		
		if(singleSpace == false)
		{
			spacing = "\n\n";
		}
		
		do
		{
			
			retVal += arrList.get(iterator) + spacing;
			
			iterator = iterator + 1;
			
		}while(iterator <= arrListSize);
		
		return(retVal);
		
	}//ends oneColumnFunc
    
    /**
     * 
     * @param arrList
     * @return
     */
	public static String twoColumnFunc(ArrayList<String> arrList) 
	{
		//local variables
		String retVal = "";
		String strTemp = ""; 
		int strTempSize = 0;
		int arrListSize = arrList.size();
		int iterator = 0;
		int iterator2 = 0;
		int mid = 0;
		
		mid = (int) Math.floor((double) (arrListSize) / (2.0));
		
		iterator2 = mid;
		
		mid = mid - 1;//done so that the loop ends in the appropriate place
		
		do 
		{
			
			strTemp = arrList.get(iterator);//
			
			strTempSize += strTemp.length();
			
			while(strTempSize < 35)
			{
				strTemp += " ";
				
				strTempSize = strTemp.length();
			}
			
			strTemp += "          ";
			
			retVal += strTemp;
			
			if(iterator2 < arrListSize)
			{
				
				strTemp = "";
				
				strTemp = arrList.get(iterator2);
				
				strTempSize = strTemp.length();
				
				while(strTempSize < 35)
				{
					strTemp += " ";
					
					strTempSize = strTemp.length();
				}
				
				retVal += strTemp + "\n";
				
				strTemp = "";
				
			}
				
			iterator = iterator + 1;
			
			iterator2 = iterator2 + 1;
			
			strTempSize = 0;
			
			//System.out.print(retVal);//DEBUGGING
			
			//System.out.print("\n\n\n");//DEBUGGING
			
		}while(iterator <= mid);
		
		return(retVal);
		
	}//twoColumnFunc end
	
}
