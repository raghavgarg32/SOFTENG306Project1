import org.junit.Test;

public class testMain {
    @Test
    public void testRandomParameter() {
        Main.main("data/input.dot 4 z".split(" "));
    }
    @Test
    public void testInvalidNumber() {
        Main.main("data/input.dot a".split(" "));
    }
    @Test
    public void testInvalidInput() {
        Main.main("data/inputnoExist.dot 4".split(" "));
    }
    @Test
    public void testMultipleCores() {
        Main.main("data/input.dot 4".split(" "));
    }
    public static void main(String[] args) {
        Main.main("data/input.dot 4 -p 2".split(" "));
    }
}
