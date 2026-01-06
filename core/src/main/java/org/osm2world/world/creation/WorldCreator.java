package org.osm2world.world.creation;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.osm2world.conversion.O2WConfig;
import org.osm2world.map_data.data.MapData;
import org.osm2world.world.network.NetworkCalculator;

public class WorldCreator {

	private final List<WorldModule> networkModules;
	private final List<WorldModule> otherModules;

	public WorldCreator(@Nullable O2WConfig config,
			List<WorldModule> networkModules, List<WorldModule> otherModules) {

		this.networkModules = networkModules;
		this.otherModules = otherModules;

		if (config == null) {
			config = new O2WConfig();
		}

		for (WorldModule module : networkModules) {
			module.setConfiguration(config);
		}

		for (WorldModule module : otherModules) {
			module.setConfiguration(config);
		}

	}

	public void addRepresentationsTo(MapData mapData) {

		for (WorldModule module : networkModules) {
			module.applyTo(mapData);
		}

		NetworkCalculator.calculateNetworkInformationInMapData(mapData);

		for (WorldModule module : otherModules) {
			module.applyTo(mapData);
		}

	}

}
