/*
 * Copyright 2014-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.store.serializers;

import org.onosproject.net.DeviceId;
import org.onosproject.net.HostLocation;
import org.onosproject.net.PortNumber;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
* Kryo Serializer for {@link HostLocation}.
*/
public class HostLocationSerializer extends Serializer<HostLocation> {

    /**
     * Creates {@link HostLocation} serializer instance.
     */
    public HostLocationSerializer() {
        // non-null, immutable
        super(false, true);
    }

    @Override
    public void write(Kryo kryo, Output output, HostLocation object) {
        kryo.writeClassAndObject(output, object.deviceId());
        kryo.writeClassAndObject(output, object.port());
        output.writeLong(object.time());
    }

    @Override
    public HostLocation read(Kryo kryo, Input input, Class<HostLocation> type) {
        DeviceId deviceId = (DeviceId) kryo.readClassAndObject(input);
        PortNumber portNumber = (PortNumber) kryo.readClassAndObject(input);
        long time = input.readLong();
        return new HostLocation(deviceId, portNumber, time);
    }

}
