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

package org.apache.flink.api.scala.typeutils;

import org.apache.flink.api.common.typeutils.CompositeTypeSerializerSnapshot;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.common.typeutils.TypeSerializerSnapshot;

import scala.Option;

/**
 * A {@link TypeSerializerSnapshot} for the Scala {@link OptionSerializer}.
 *
 * @deprecated All Flink Scala APIs are deprecated and will be removed in a future Flink major
 *     version. You can still build your application in Scala, but you should move to the Java
 *     version of either the DataStream and/or Table API.
 * @see <a
 *     href="https://cwiki.apache.org/confluence/display/FLINK/FLIP-265+Deprecate+and+remove+Scala+API+support">
 *     FLIP-265 Deprecate and remove Scala API support</a>
 */
@Deprecated
public final class ScalaOptionSerializerSnapshot<E>
        extends CompositeTypeSerializerSnapshot<Option<E>, OptionSerializer<E>> {

    private static final int VERSION = 2;

    @SuppressWarnings("WeakerAccess")
    public ScalaOptionSerializerSnapshot() {
        super(OptionSerializer.class);
    }

    public ScalaOptionSerializerSnapshot(OptionSerializer<E> serializerInstance) {
        super(serializerInstance);
    }

    @Override
    protected int getCurrentOuterSnapshotVersion() {
        return VERSION;
    }

    @Override
    protected TypeSerializer<?>[] getNestedSerializers(OptionSerializer<E> outerSerializer) {
        return new TypeSerializer[] {outerSerializer.elemSerializer()};
    }

    @Override
    protected OptionSerializer<E> createOuterSerializerWithNestedSerializers(
            TypeSerializer<?>[] nestedSerializers) {
        @SuppressWarnings("unchecked")
        TypeSerializer<E> nestedSerializer = (TypeSerializer<E>) nestedSerializers[0];
        return new OptionSerializer<>(nestedSerializer);
    }
}
