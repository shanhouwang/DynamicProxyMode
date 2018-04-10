/**
 * @author Devin
 */
public class MyService implements Service {

    @Override
    public void hello() {
        sleep(1000);
    }

    @Override
    public void hi() {
        sleep(100);
    }

    private void sleep(long millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
