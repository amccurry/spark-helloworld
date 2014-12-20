/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark;

import java.io.IOException;
import java.util.Random;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class HelloWorld {

  @SuppressWarnings("serial")
  public static void main(String[] args) throws IOException {
    String appName = "HelloWorld";
    String master = "spark://127.0.0.1:7077";
    SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
    JavaSparkUtil.packProjectJars(conf);
    JavaSparkContext sc = new JavaSparkContext(conf);
    
    JavaRDD<String> distFile = sc.textFile("file:///Users/amccurry/Downloads/rat.txt");
    JavaRDD<MyKey> rdd = distFile.map(new Function<String, MyKey>() {

      private Random random = new Random();

      @Override
      public MyKey call(String line) throws Exception {
        MyKey myKey = new MyKey();
        myKey.setVal1(Long.toString(random.nextInt(500)));
        myKey.setVal2(line);
        return myKey;
      }
    });

    JavaRDD<MyKey> sortBy = rdd.sortBy(new Function<MyKey, MyKey>() {
      @Override
      public MyKey call(MyKey l) throws Exception {
        return l;
      }
    }, true, 10);
    
    sortBy.saveAsTextFile("file:///Users/amccurry/Development/spark-output/test1");

    sc.stop();
  }

}
