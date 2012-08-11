package mobi.app.toolkit.aws.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import mobi.app.toolkit.aws.AwsS3Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 3:56 AM
 */
public class DefaultS3Tools implements AwsS3Tools {
    Logger logger = LoggerFactory.getLogger(DefaultS3Tools.class);

    private final AmazonS3Client amazonS3Client;

    public DefaultS3Tools(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    @Override
    public String upload(String bucketName, String key, byte[] data, String contentType, Map<String, String> meta) {
        ObjectMetadata metadata = new ObjectMetadata();
        if (meta != null) {

            for (String k : meta.keySet()) {
                metadata.addUserMetadata(k, meta.get(k));
            }
        }
        metadata.setContentType(contentType);
        PutObjectRequest request = new PutObjectRequest(bucketName, key, new ByteArrayInputStream(data), metadata);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult result =
                amazonS3Client.putObject(request);
        logger.debug("upload to s3 result: {}", result.getServerSideEncryption());
        return String.format("https://s3.amazonaws.com/guessmore_image/%s", key);
    }
}
