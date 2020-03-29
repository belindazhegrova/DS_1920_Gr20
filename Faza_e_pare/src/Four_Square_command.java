import java.util.ArrayList;
import java.util.List;


public class Four_Square_command {

  
    private static final char[] ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
  
    private static final char[][] ALPHABET_SQUARE = new char[5][5];

    static 
    {
        int x = 0, y = 0;
        for (char c : ALPHABET) {
            ALPHABET_SQUARE[x][y] = c;
            x++;
            if (x == 5) 
            {
                x = 0;
                y++;
            }
        }
    }
//The method that removes the whitespaces and converts all "j" to "i"
 
    private static String clean(String input)
    {
        input = input.trim().replace(" ", "").replace("J", "I").toUpperCase();
        StringBuilder clean = new StringBuilder();
        for (char c : input.toCharArray())
        {
            if (Character.getType(c) == Character.UPPERCASE_LETTER) 
            {
                clean.append(c);
            }
        }
        return clean.toString();
    }

   //the method that generates the keywords and puts them into matrix
    private static char[][] keywords(String keyword)
    {
        keyword = clean(keyword);
        char[][] keys = new char[5][5];
        List<Character> used = new ArrayList<Character>();
        int x = 0, y = 0;
        for (char c : keyword.toCharArray())
        {
            if (!used.contains(c))
            {
                keys[x][y] = c;
                used.add(c);
                x++;
                if (x == 5)
                {
                    x = 0;
                    y++;
                    if (y == 5) 
                    {
                        return keys;
                    }
                }
            }
        }
        for (char c : ALPHABET)
        {
            if (!used.contains(c))
            {
                keys[x][y] = c;
                x++;
                if (x == 5)
                {
                    x = 0;
                    y++;
                    if (y == 5)
                    {
                        return keys;
                    }
                }
            }
        }
        return keys;
    }

    private static String[] split(String plaintext) 
    {
        if (plaintext.length() % 2 != 0) 
        {
            plaintext = plaintext + "X";
        }
        String[] pairs = new String[plaintext.length() / 2];
        int count = 0;
        for (int i = 0; i < (plaintext.length() / 2); i++) 
        {
            pairs[i] = plaintext.substring(count, count + 2);
            count = count + 2;
        }
        return pairs;
    }
//The method that encrypts the plaintext with Four Square cipher 

    public static String encrypt(String keyword1, String keyword2,String plaintext) 
    {
        plaintext = clean(plaintext);
        String[] pairs = split(plaintext);
        char[][] keys1 = keywords(keyword1);
        char[][] keys2 = keywords(keyword2);
        char first, second;
        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
        StringBuilder ciphertext = new StringBuilder();
        for (String s : pairs)
        {
            first = s.charAt(0);
            second = s.charAt(1);
            for (int y = 0; y < 5; y++)
            {
                for (int x = 0; x < 5; x++) 
                {
                    if (ALPHABET_SQUARE[x][y] == first)
                    {
                        xFirst = x;
                        yFirst = y;
                    } else if (ALPHABET_SQUARE[x][y] == second)
                    {
                        xSecond = x;
                        ySecond = y;
                    }
                }
            }
            ciphertext.append(keys1[xSecond][yFirst]).append(keys2[xFirst][ySecond]);
        }
        return ciphertext.toString();
    }
//The method that decrypts the ciphertext with Four Square cipher 
    public static String decrypt( String keyword1, String keyword2,String ciphertext)
    {
        String[] pairs = split(ciphertext);
        char[][] keys1 = keywords(keyword1);
        char[][] keys2 = keywords(keyword2);
        char first, second;
        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
        StringBuilder plaintext = new StringBuilder();
        for (String s : pairs)
        {
            first = s.charAt(0);
            second = s.charAt(1);
            for (int y = 0; y < 5; y++)
            {
                for (int x = 0; x < 5; x++)
                {
                    if (keys1[x][y] == first)
                    {
                        xFirst = x;
                        yFirst = y;
                    } else if (keys2[x][y] == second)
                    {
                        xSecond = x;
                        ySecond = y;
                    }
                }
            }
            plaintext.append(ALPHABET_SQUARE[xSecond][yFirst]).append(ALPHABET_SQUARE[xFirst][ySecond]);
        }
        return plaintext.toString();
    }   
}
