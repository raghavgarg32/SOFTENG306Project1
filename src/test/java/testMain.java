import org.junit.Test;

public class testMain {
    @Test
    public void testInvalidInput() {
        System.out.println("Test Main");
        Main.main("data/inputnoExist.dot 4".split(" "));
    }
    @Test
    public void testMultipleCores() {
        System.out.println("Test Main");
        Main.main("data/input.dot 4".split(" "));
    }
    public static void main(String[] args) {
        System.out.println("Test Main");
        Main.main("data/input.dot 4 -p 2".split(" "));
    }
}
