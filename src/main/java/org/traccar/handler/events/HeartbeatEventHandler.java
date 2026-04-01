/*
 * Copyright 2024 Anton Tananaev (anton@traccar.org)
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
package org.traccar.handler.events;

import jakarta.inject.Inject;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class HeartbeatEventHandler extends BaseEventHandler {

    @Inject
    public HeartbeatEventHandler() {
    }

    @Override
    public void onPosition(Position position, Callback callback) {
        Object messageType = position.getAttributes().get("messageType");
        if ("heartbeat".equals(messageType)) {
            Event event = new Event(Event.TYPE_HEARTBEAT, position);
            event.set("batteryLevel", position.getAttributes().get("batteryLevel"));
            event.set("charge", position.getAttributes().get("charge"));
            event.set("motion", position.getAttributes().get("motion"));
            event.set("dailyExerciseTime", position.getAttributes().get("dailyExerciseTime"));
            event.set("soundSwitch", position.getAttributes().get("soundSwitch"));
            event.set("lightSwitch", position.getAttributes().get("lightSwitch"));
            callback.eventDetected(event);
        }
    }
}
