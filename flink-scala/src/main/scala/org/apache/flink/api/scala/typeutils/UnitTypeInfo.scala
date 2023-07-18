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
package org.apache.flink.api.scala.typeutils

import org.apache.flink.annotation.{Public, PublicEvolving}
import org.apache.flink.api.common.ExecutionConfig
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.common.typeutils.TypeSerializer

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
class UnitTypeInfo extends TypeInformation[Unit] {

  @Deprecated
  @PublicEvolving
  override def isBasicType(): Boolean = false

  @Deprecated
  @PublicEvolving
  override def isTupleType(): Boolean = false

  @Deprecated
  @PublicEvolving
  override def getArity(): Int = 0

  @Deprecated
  @PublicEvolving
  override def getTotalFields(): Int = 1

  @Deprecated
  @PublicEvolving
  override def getTypeClass(): Class[Unit] = classOf[Unit]

  @Deprecated
  @PublicEvolving
  override def isKeyType(): Boolean = false

  @Deprecated
  @PublicEvolving
  override def createSerializer(config: ExecutionConfig): TypeSerializer[Unit] =
    (new UnitSerializer).asInstanceOf[TypeSerializer[Unit]]

  override def canEqual(obj: scala.Any): Boolean = {
    obj.isInstanceOf[UnitTypeInfo]
  }

  override def toString() = "UnitTypeInfo"

  override def equals(obj: scala.Any) = {
    obj.isInstanceOf[UnitTypeInfo]
  }

  override def hashCode() = classOf[UnitTypeInfo].hashCode
}
