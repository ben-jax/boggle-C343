import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    
    @Test
    public void trie () {
        @NotNull Trie t = new Trie("to tea ted ten in inn A");

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
        @NotNull Trie trie = new Trie(new File("commonwords.txt"));

        assertTrue(trie.contains("abandon"));
        assertTrue(trie.contains("abandoned"));
        assertTrue(trie.contains("abandonment"));
        assertFalse(trie.contains("abandonmenth"));
        assertFalse(trie.contains("abando"));
        assertFalse(trie.contains("aband"));
        assertFalse(trie.contains("aban"));
        assertFalse(trie.contains("aba"));
        assertFalse(trie.contains("ab"));
        assertFalse(trie.contains("a"));
        assertTrue(trie.possiblePrefix("abando"));
        assertTrue(trie.possiblePrefix("aband"));
        assertTrue(trie.possiblePrefix("aban"));
        assertTrue(trie.possiblePrefix("aba"));
        assertTrue(trie.possiblePrefix("ab"));
        assertTrue(trie.possiblePrefix("a"));
        assertTrue(trie.possiblePrefix("abandon"));
    }
}