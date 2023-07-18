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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.api.scala.hadoop.mapreduce

import org.apache.flink.annotation.Public
import org.apache.flink.api.java.hadoop.mapreduce.HadoopOutputFormatBase

import org.apache.hadoop.mapreduce.{Job, OutputFormat}

/**
 * @deprecated
 *   All Flink Scala APIs are deprecated and will be removed in a future Flink major version. You
 *   can still build your application in Scala, but you should move to the Java version of either
 *   the DataStream and/or Table API.
 * @see
 *   <a
 *   href="https://cwiki.apache.org/confluence/display/FLINK/FLIP-265+Deprecate+and+remove+Scala+API+support">
 *   FLIP-265 Deprecate and remove Scala API support</a>
 */
@Deprecated
@Public
class HadoopOutputFormat[K, V](mapredOutputFormat: OutputFormat[K, V], job: Job)
  extends HadoopOutputFormatBase[K, V, (K, V)](mapredOutputFormat, job) {

  def writeRecord(record: (K, V)) {
    this.recordWriter.write(record._1, record._2)
  }
}
