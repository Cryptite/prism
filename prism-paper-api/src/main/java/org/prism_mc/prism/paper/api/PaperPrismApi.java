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

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;
import org.prism_mc.prism.api.Prism;

/**
 * Paper-specific implementation of the Prism API.
 *
 * <p>Third-party plugins should obtain an instance via {@link #getInstance()}.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * PaperPrismApi api = PaperPrismApi.getInstance();
 * if (api != null) {
 *     // Register a custom action type
 *     api.actionTypeRegistry().registerAction(myCustomActionType);
 *
 *     // Perform a lookup
 *     api.performLookup(query).thenAccept(activities -> {
 *         // Handle results
 *     });
 * }
 * }</pre>
 */
public interface PaperPrismApi extends Prism {

    /**
     * Get the PaperPrismApi instance.
     *
     * <p>Returns null if Prism is not loaded or not yet initialized.</p>
     *
     * @return The API instance, or null if unavailable
     */
    @Nullable
    static PaperPrismApi getInstance() {
        RegisteredServiceProvider<PaperPrismApi> provider = Bukkit
                .getServicesManager()
                .getRegistration(PaperPrismApi.class);
        if (provider != null) {
            return provider.getProvider();
        }
        return null;
    }
}