package mobi.app.toolkit.apple;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 3:43 AM.  Apple's in app pay tools
 */
public interface IapTools {
    boolean isProduction();
    IapReceipt validate(String receipt);
}
