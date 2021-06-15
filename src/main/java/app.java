import testscases.FirstTest;

import java.util.concurrent.ExecutionException;

public class app {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FirstTest test = new FirstTest();
        test.appendSomeData();
    }
}
