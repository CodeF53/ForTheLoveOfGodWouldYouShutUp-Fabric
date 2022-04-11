package net.f53.revertnoteblock;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class initRevertNoteblock implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("revertnoteblock");

	@Override
	public void onInitialize() {
		LOGGER.info("For the Love of God Would You Shut Up Initialized!");
	}
}
