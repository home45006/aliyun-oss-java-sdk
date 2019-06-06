/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package samples;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This sample demonstrates how to delete objects under specfied bucket 
 * from Aliyun OSS using the OSS SDK for Java.
 */
public class DeleteObjectsSample {

    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIEQzeEsyi11nw";
    private static String accessKeySecret = "Eas5yx2p4qon00n5wJ6bLTVhBDdJQy";
    private static String bucketName = "rice";
    
    public static void main(String[] args) throws IOException {        
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        
        try {
            /*
             * Batch put objects into the bucket
             */
//            final String content = "Thank you for using Aliyun Object Storage Service";
//            final String keyPrefix = "MyObjectKey";
            List<String> keys = new ArrayList<String>();
//            for (int i = 0; i < 100; i++) {
//                String key = keyPrefix + i;
//                InputStream instream = new ByteArrayInputStream(content.getBytes());
//                client.putObject(bucketName, key, instream);
//                System.out.println("Succeed to put object " + key);
//                keys.add(key);
//            }

            keys.add("README.md");
            System.out.println();
            
            /*
             * Delete all objects uploaded recently under the bucket
             */
            System.out.println("\nDeleting all objects:");
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucketName).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                System.out.println("\t" + object);
            }
            System.out.println();

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }
}
