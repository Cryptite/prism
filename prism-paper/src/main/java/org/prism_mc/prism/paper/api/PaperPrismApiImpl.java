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

package org.prism_mc.prism.paper.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.prism_mc.prism.api.actions.types.ActionTypeRegistry;
import org.prism_mc.prism.api.activities.Activity;
import org.prism_mc.prism.api.activities.ActivityQuery;
import org.prism_mc.prism.api.services.recording.RecordingService;
import org.prism_mc.prism.api.storage.StorageAdapter;
import org.prism_mc.prism.loader.services.logging.LoggingService;

/**
 * Implementation of the Paper Prism API.
 */
@Singleton
public class PaperPrismApiImpl implements PaperPrismApi {

    private final ActionTypeRegistry actionTypeRegistry;
    private final RecordingService recordingService;
    private final StorageAdapter storageAdapter;
    private final LoggingService loggingService;

    /**
     * Construct the API implementation.
     *
     * @param actionTypeRegistry The action type registry
     * @param recordingService The recording service
     * @param storageAdapter The storage adapter
     * @param loggingService The logging service
     */
    @Inject
    public PaperPrismApiImpl(
            ActionTypeRegistry actionTypeRegistry,
            RecordingService recordingService,
            StorageAdapter storageAdapter,
            LoggingService loggingService
    ) {
        this.actionTypeRegistry = actionTypeRegistry;
        this.recordingService = recordingService;
        this.storageAdapter = storageAdapter;
        this.loggingService = loggingService;
    }

    @Override
    public StorageAdapter storageAdapter() {
        return storageAdapter;
    }

    @Override
    public ActionTypeRegistry actionTypeRegistry() {
        return actionTypeRegistry;
    }

    @Override
    public RecordingService recordingService() {
        return recordingService;
    }

    @Override
    public CompletableFuture<List<Activity>> performLookup(ActivityQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return storageAdapter.queryActivities(query);
            } catch (Exception e) {
                loggingService.handleException(e);
                return List.of();
            }
        });
    }
}