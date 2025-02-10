package tech.membrana.advertising.harfile;


import java.io.File;

public class HarFile {
    private final static String ORIGINAL_HAR = "original.har";
    private final static String ADBLOCK_HAR = "adBlock.har";
    private final static String ADGUARD_HAR = "adGuard.har";

    public static File originalFile() {
        return new File(ORIGINAL_HAR);
    }

    public static File adBlockFile() {
        return new File(ADBLOCK_HAR);
    }

    public static File adGuardFile() {
        return new File(ADGUARD_HAR);
    }
}
