package com.mattias.plushies_plus;

import net.fabricmc.api.ClientModInitializer;

public class PlushiesPlusFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // This method is invoked by the Fabric mod loader when the client is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        PlushiesPlus.init();
    }
}
