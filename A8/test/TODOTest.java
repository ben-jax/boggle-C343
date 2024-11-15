import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TODOTest {
    //creating my test board
    private Board<Character> testBoard;

    //method to run before each test, add values to test board
    @BeforeEach
    public void setUp() {
        Tile<Character>[] @NotNull[] input = new Tile[3][3];
        input[0][0] = new Tile('a', 0, 0);
        input[0][1] = new Tile('b', 0, 1);
        input[0][2] = new Tile('c', 0, 2);

        input[1][0] = new Tile('d', 1, 0);
        input[1][1] = new Tile('e', 1, 1);
        input[1][2] = new Tile('f', 1, 2);

        input[2][0] = new Tile('g', 2, 0);
        input[2][1] = new Tile('h', 2, 1);
        input[2][2] = new Tile('i', 2, 2);

        testBoard = new Board<>(input);
    }

    @Test
    public void test1() {
        // write test cases that thoroughly check the 'getNeighbors' method in
        // the 'Board' class. You should have cases for the corners, edges, and
        // middle of the board. You should also have cases for boards of size 1,

        //test edge
        ArrayList<Tile<Character>> neighbors = testBoard.getNeighbors(0,0).collect
                (Collectors.toCollection(ArrayList :: new));

        assertEquals(3, neighbors.size());
        assertTrue(neighbors.stream().anyMatch(t -> t.toString().equals("b")));
        assertTrue(neighbors.stream().anyMatch(t -> t.toString().equals("d")));
        assertTrue(neighbors.stream().anyMatch(t -> t.toString().equals("e")));

        //test middle
        ArrayList<Tile<Character>> neighborsTwo = testBoard.getNeighbors(1,1).collect
                (Collectors.toCollection(ArrayList :: new));

        assertEquals(8, neighborsTwo.size());
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("b")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("d")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("f")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("h")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("a")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("c")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("g")));
        assertTrue(neighborsTwo.stream().anyMatch(t -> t.toString().equals("i")));
    }

    @Test
    public void test2() {
        // write test cases that thoroughly check the 'getFreshNeighbors' method in
        // the 'Board' class. You should have cases for when none of the neighbors
        // are fresh, all the neighbors are fresh, and some of the neighbors are fresh.

        ArrayList<Tile<Character>> freshNeighbors = testBoard.getFreshNeighbors(1, 1)
                .collect(Collectors.toCollection(ArrayList::new));

        //since no tiles are visited yet, all 8 neighbors are fresh
        assertEquals(8, freshNeighbors.size());
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("b")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("d")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("f")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("h")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("a")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("c")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("g")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("i")));

        //visit some tiles
        Optional<Tile<Character>> tileOne = testBoard.get(0, 1);
        tileOne.ifPresent(Tile :: setVisited);
        Optional<Tile<Character>> tileTwo = testBoard.get(1, 0);
        tileTwo.ifPresent(Tile :: setVisited);
        Optional<Tile<Character>> tileThree = testBoard.get(2, 2);
        tileThree.ifPresent(Tile :: setVisited);

        //get fresh neighbors now, should be less than 5 (visited three tiles)
        freshNeighbors = testBoard.getFreshNeighbors(1, 1)
                .collect(Collectors.toCollection(ArrayList::new));

        //test new fresh neighbors
        assertEquals(5, freshNeighbors.size());
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("f")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("h")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("a")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("c")));
        assertTrue(freshNeighbors.stream().anyMatch(t -> t.toString().equals("g")));

        //make sure no fresh tiles are here
        assertTrue(freshNeighbors.stream().noneMatch(t -> t.toString().equals("b")));
        assertTrue(freshNeighbors.stream().noneMatch(t -> t.toString().equals("d")));
        assertTrue(freshNeighbors.stream().noneMatch(t -> t.toString().equals("i")));
    }

    @Test
    public void test3() {
        // write test cases that for 'getNeighbors' and 'getFreshNeighbors' that
        // collect the neighbors 'k' steps away from a given tile. You should have
        // cases for when 'k' is 0, 1, 2, and 3. You should also have cases for when
        // 'k' is greater than the size of the board.

        //k = 1
        ArrayList<Tile<Character>> testNeighbors = testBoard.getNeighbors(1, 1)
                .filter(tile -> tile.getRow() == 1 || tile.getCol() == 1)
                .collect(Collectors.toCollection(ArrayList :: new));

        assertEquals(4, testNeighbors.size());

        //k = 2
        ArrayList<Tile<Character>> testNeighborsTwo = testBoard.getNeighbors(1, 1)
                .filter(tile -> tile.getRow() != 1 && tile.getCol() != 1)
                .collect(Collectors.toCollection(ArrayList :: new));

        assertEquals(4, testNeighbors.size());
        
    }

    @Test
    public void test4() {
        // write test cases for the methods 'contains' and 'possiblePrefix' in the
        // 'WordList' class. You should have cases for when the word is in the list,
        // when the word is not in the list, when the word is a prefix of a word in
        // the list, and when the word is not a prefix of any word in the list. You
        // should empirically observe that the performance is O(N) where N is the size
        // of the list.
        ArrayList<String> inputWords = new ArrayList<>();
        inputWords.add("card");
        inputWords.add("bar");

        WordList words = new WordList(inputWords);
        assertFalse(words.contains("car"));
        assertTrue(words.possiblePrefix("car"));
        assertFalse(words.contains("ba"));
        assertFalse(words.possiblePrefix("th"));
        assertTrue(words.contains("bar"));
        assertTrue(words.possiblePrefix("bar"));
    }

    @Test
    public void test5() {
        // write tests for the method 'insert' in the 'Trie' class. You should have
        // cases for when the word is already in the trie, when the word is not in the
        // trie, when the word is a prefix of a word in the trie, and when the word is
        // not a prefix of any word in the trie. You should empirically observe that
        // the performance is O(L) where L is the length of the word (the corresponding
        // height of the trie).
        @NotNull Trie trieOne = new Trie("car cat card cars bar bat bats");

        trieOne.insert("cards");

        assertTrue(trieOne.contains("cards"));
        assertTrue(trieOne.possiblePrefix("card"));

        trieOne.insert("car");

        trieOne.insert("mat");

        assertTrue(trieOne.contains("mat"));
        assertTrue(trieOne.possiblePrefix("ma"));

    }


    @Test
    public void test6() {
        // write test cases for the methods 'contains' and
        // 'possiblePrefix' in the 'Trie' class. You should have cases for when
        // the word is in the trie, when the word is not in the trie, when the
        // word is a prefix of a word in the trie, and when the word is not a
        // prefix of any word in the trie. You should empirically observe that
        // the performance is O(L) where L is the length of the word (the
        // corresponding height of the trie).

        @NotNull Trie trieOne = new Trie("car cat card cars bar bat bats");

        //contains test
        assertTrue(trieOne.contains("car"));
        assertFalse(trieOne.contains("carrot"));
        assertFalse(trieOne.contains(""));

        //prefix test
        assertTrue(trieOne.possiblePrefix("ca"));
        assertFalse(trieOne.possiblePrefix("br"));

    }

    @Test
    public void test7() throws IOException {
        // write test cases for the 'findWordsFromPos' method in the 'Boggle' class.
        // You should construct boards of different sizes and different configurations
        // of letters and special dictionaries to test the following scenarios.
        // The string 's' + the letter at current position form a word in
        // the dictionary, the string 's' + the letter at current position
        // do not form a word in the dictionary, the string 's'
        // + the letter at current position form a prefix of a word in the dictionary,
        // and the string 's' + the letter at current position do not form a prefix of
        // any word in the dictionary.
        @NotNull WordList words = new WordList("badge bad bed bade");
        Boggle boggleOne = new Boggle(testBoard, words);

        Optional<Tile<Character>> start = testBoard.get(0, 1);

        boggleOne.findWordsFromPos(start.orElse(new Tile<>('b', 0, 0)), "");

        assertEquals(4, boggleOne.getFoundWords().size());
    }

}
