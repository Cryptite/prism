/*
 * prism
 *
 * Copyright (c) 2022 M Botsko (viveleroi)
 *                    Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.prism_mc.prism.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.prism_mc.prism.api.actions.types.ActionTypeRegistry;
import org.prism_mc.prism.api.activities.Activity;
import org.prism_mc.prism.api.activities.ActivityQuery;
import org.prism_mc.prism.api.services.recording.RecordingService;
import org.prism_mc.prism.api.storage.StorageAdapter;

public interface Prism {
    /**
     * Get the storage adapter.
     *
     * @return Storage adapter
     */
    StorageAdapter storageAdapter();

    /**
     * Get the action type registry.
     *
     * <p>Use this to register custom action types.</p>
     *
     * @return The action type registry
     */
    ActionTypeRegistry actionTypeRegistry();

    /**
     * Get the recording service.
     *
     * <p>Use this to queue custom activities for recording.</p>
     *
     * @return The recording service
     */
    RecordingService recordingService();

    /**
     * Perform an activity lookup query.
     *
     * <p>This method executes asynchronously and returns a CompletableFuture.</p>
     *
     * @param query The activity query
     * @return A CompletableFuture containing the list of matching activities
     */
    CompletableFuture<List<Activity>> performLookup(ActivityQuery query);
}
