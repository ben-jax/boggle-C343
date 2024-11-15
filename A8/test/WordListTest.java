import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordListTest {

    @Test
    public void simple () {
        @NotNull WordList t = new WordList("to tea ted ten in inn A");

        assertTrue(t.contains("ten"));
        assertTrue(t.contains("in"));
        assertTrue(t.contains("inn"));
        assertFalse(t.contains("tenn"));
        assertFalse(t.contains("te"));
        assertTrue(t.possiblePrefix(""));
        assertTrue(t.possiblePrefix("t"));
        assertTrue(t.possiblePrefix("to"));
        assertTrue(t.possiblePrefix("te"));
        assertTrue(t.possiblePrefix("i"));
        assertFalse(t.possiblePrefix("tu"));
    }

    @Test
    public void dict () throws IOException {
        @NotNull WordList words = new WordList(new File("commonwords.txt"));

        assertTrue(words.contains("abandon"));
        assertTrue(words.contains("abandoned"));
        assertTrue(words.contains("abandonment"));
        assertFalse(words.contains("abandonmenth"));
        assertFalse(words.contains("abando"));
        assertFalse(words.contains("aband"));
        assertFalse(words.contains("aban"));
        assertFalse(words.contains("aba"));
        assertFalse(words.contains("ab"));
        assertFalse(words.contains("a"));
        assertTrue(words.possiblePrefix("abando"));
        assertTrue(words.possiblePrefix("aband"));
        assertTrue(words.possiblePrefix("aban"));
        assertTrue(words.possiblePrefix("aba"));
        assertTrue(words.possiblePrefix("ab"));
        assertTrue(words.possiblePrefix("a"));
        assertTrue(words.possiblePrefix("abandon"));
    }
}