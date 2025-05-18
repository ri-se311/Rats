package testers;

/**
 * This class is responsible for solving the MOTD puzzle and returning a retrieved message.
 *
 * @version 1.1
 */
public class MOTDPuzzleSolver {

    private static final String PUZZLE_STRING_SUFFIX = "CS-230";
    private static final char[] ALPHABET_ARRAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Static method which retrieves and solves the current MOTD puzzle,
     * returning the message of the day.
     *
     * @return The string containing the message of the day.
     * @throws Exception This exception is thrown if the URL for the website is incorrect.
     */
    public static String solvePuzzle() throws Exception {
        PuzzleGetRequest puzzleGR = new PuzzleGetRequest();
        // Retrieve and split the puzzle string into an array.
        String puzzleString = puzzleGR.getStringFromSite("http://cswebcat.swansea.ac.uk/puzzle");
        char[] puzzleStringToCharArray = puzzleString.toCharArray();
        // Calculate the total length of the puzzle string to append to the start.
        int charCount = puzzleStringToCharArray.length + PUZZLE_STRING_SUFFIX.length();

        // Iterate through the puzzle string's character array
        for (int x = 0; x < puzzleStringToCharArray.length; x++) {

            int alphabetIndex = 0;
            int counter = 0;
            boolean matched = false;

            // Match the indexes of the puzzle string array to the index of the alphabet.
            while (!matched) {
                if (puzzleStringToCharArray[x] == ALPHABET_ARRAY[counter]) {
                    matched = true;
                    alphabetIndex = counter;
                    counter++;
                } else {
                    counter++;
                }
            }

            // If the index is even (it is the 1st, 3rd... element of the array), shift backwards.
            if (x % 2 == 0) {
                alphabetIndex -= (x + 1);
                // Loop the array back around if it goes under
                if (alphabetIndex < 0) {
                    alphabetIndex += 26;
                }
            } else { // If the index is odd, shift forwards
                alphabetIndex += (x + 1);
                // Loop the array back to a lower value if it goes over
                if (alphabetIndex > 25) {
                    alphabetIndex -= 26;
                }
            }
            // Replace the values in the puzzle array with the corrected values (with respect to the puzzle)
            puzzleStringToCharArray[x] = ALPHABET_ARRAY[alphabetIndex];
        }
        // Create the final solved string, then incorporate it into the URL of a pull request, and return the message.
        String solvedPuzzle = (charCount + new String(puzzleStringToCharArray) + PUZZLE_STRING_SUFFIX);
        return puzzleGR.getStringFromSite("http://cswebcat.swansea.ac.uk/message?solution=" + solvedPuzzle);
    }
}
