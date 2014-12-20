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

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyKey implements Serializable, Comparable<MyKey> {

  private String val1;
  private String val2;

  @Override
  public int compareTo(MyKey o) {
    int compareTo = val1.compareTo(o.val1);
    if (compareTo == 0) {
      return val2.compareTo(o.val2);
    }
    return compareTo;
  }

  public String getVal1() {
    return val1;
  }

  public void setVal1(String val1) {
    this.val1 = val1;
  }

  public String getVal2() {
    return val2;
  }

  public void setVal2(String val2) {
    this.val2 = val2;
  }

  @Override
  public String toString() {
    return "MyKey [val1=" + val1 + ", val2=" + val2 + "]";
  }

}
