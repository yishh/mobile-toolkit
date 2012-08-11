package mobi.app.toolkit.aws;

import java.util.Map;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 3:54 AM. Amazon aws s3 tools
 */
public interface AwsS3Tools {

    String upload(String bucketName, String key, byte[] data, String contentType);
    String upload(String bucketName, String key, byte[] data, String contentType, Map<String, String> meta);

}
