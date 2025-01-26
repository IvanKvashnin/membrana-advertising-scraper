package tech.membrana.advertising.files;

import java.io.File;

public class HarFile {
    private final static String ORIGINAL_HAR = "original.har";
    private final static String ADBLOCK_HAR = "adBlock.har";
    private final static String ADGUARD_HAR = "adGuard.har";

    public static File originalHarFile() {
        return new File(ORIGINAL_HAR);
    }

    public static File adBlockHarFile() {
        return new File(ADBLOCK_HAR);
    }

    public static File adGuardHarFile() {
        return new File(ADGUARD_HAR);
    }
}
