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
import org.apache.flink.api.common.typeinfo.{AtomicType, TypeInformation}
import org.apache.flink.api.common.typeutils.{TypeComparator, TypeSerializer}

import scala.collection.JavaConverters._

/**
 * TypeInformation for [[Enumeration]] values.
 *
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
class EnumValueTypeInfo[E <: Enumeration](val enum: E, val clazz: Class[E#Value])
  extends TypeInformation[E#Value]
  with AtomicType[E#Value] {

  type T = E#Value

  @Deprecated
  @PublicEvolving
  override def isBasicType: Boolean = false

  @Deprecated
  @PublicEvolving
  override def isTupleType: Boolean = false

  @Deprecated
  @PublicEvolving
  override def isKeyType: Boolean = true

  @Deprecated
  @PublicEvolving
  override def getTotalFields: Int = 1

  @Deprecated
  @PublicEvolving
  override def getArity: Int = 1

  @Deprecated
  @PublicEvolving
  override def getTypeClass = clazz

  @Deprecated
  @PublicEvolving
  override def getGenericParameters = Map.empty[String, TypeInformation[_]].asJava

  @Deprecated
  @PublicEvolving
  def createSerializer(executionConfig: ExecutionConfig): TypeSerializer[T] = {
    new EnumValueSerializer[E](enum)
  }

  @Deprecated
  @PublicEvolving
  override def createComparator(ascOrder: Boolean, config: ExecutionConfig): TypeComparator[T] = {
    new EnumValueComparator[E](ascOrder)
  }

  override def toString = clazz.getCanonicalName

  override def hashCode(): Int = {
    31 * enum.hashCode() + clazz.hashCode()
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case enumValueTypeInfo: EnumValueTypeInfo[E] =>
        enumValueTypeInfo.canEqual(this) &&
        enum.equals(enumValueTypeInfo.enum) &&
        clazz.equals(enumValueTypeInfo.clazz)
      case _ => false
    }
  }

  override def canEqual(obj: Any): Boolean = {
    obj.isInstanceOf[EnumValueTypeInfo[E]]
  }
}
