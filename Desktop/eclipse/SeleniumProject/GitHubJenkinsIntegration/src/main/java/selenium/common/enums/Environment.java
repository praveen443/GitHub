package selenium.common.enums;

import selenium.common.Constants;
import selenium.common.Log;

/**
 * 
 * @author SonHuy.Pham@Disney.com, Michael.Yardley@Disney.com
 */
public enum Environment {

    LATEST("latest"),
    STAGE("stage"),
    SHADOW("shadow"),
    PROD_C("prod c"),
    SOFT_LAUNCH("soft launch"),
    PRODUCTION("production"),
    HILTON_HEAD("hilton head"),
    VERO_BEACH("vero beach"),
    ENV4("qa3"),
    ENV2("qa5"),
    LOAD1("lt2"),
    LOAD2("lt1"),
    UNKNOWN("unknown");
    
    final private static Environment environment = create();
    private String name = null;
    
    private Environment(String name) {
        this.name = name;
    }
    
    final public static String toStr() {
        return environment.toString();
    }
    
    public static boolean isLatest() {
        return environment.equals(LATEST);
    }
    
    public static boolean isStage() {
        return environment.equals(STAGE);
    }
    
    public static boolean isHiltonHead() {
        return environment.equals(HILTON_HEAD);
    }
    
    public static boolean isVeroBeach() {
        return environment.equals(VERO_BEACH);
    }
    
    public static boolean isShadow() {
        return environment.equals(SHADOW);
    }
    
    public static boolean isSoftLaunch() {
        return environment.equals(SOFT_LAUNCH);
    }
    
    public static boolean isProdC() {
        return environment.equals(PROD_C);
    }
    
    public static boolean isProduction() {
        return environment.equals(PRODUCTION);
    }
    
    public static boolean isENV2() {
    	return environment.equals(ENV2);
    }
    
    public static boolean isENV4() {
    	return environment.equals(ENV4);
    }
    
    public static boolean isLOAD1() {
    	return environment.equals(LOAD1);
    }
    
    public static boolean isLOAD2() {
    	return environment.equals(LOAD2);
    }
    
    public static boolean isUnknown() {
        return environment.equals(UNKNOWN);
    }
    
    public static boolean canPurchase() {
        return isLatest() 
                || isStage() 
                || isHiltonHead() 
                || isVeroBeach() 
                || isShadow();
    }
    
    /**
     * Returns the name of the enumeration value as specified 
     * by the constructor.
     */
    @Override
    public String toString() {
        return name;
    }
    
    public static Environment create() {
        
        // Comments from original constants file.
        // By SL we mean going to Prod URL but make sure to execute opt-in cookies: runBookmarklet()
        // These switches set during test, they serve to:
        // a) Pull correct user from JSON if using JSON
        // b) Not to pass Review page, click Purchase if in SL/Prod
        // c) Take care of page verification if in SL: URL/homepage in SL shows as URL
        
        String str = Constants.TEST_ENVIRONMENT.toLowerCase().trim();
        
        if(str.contains("latest")) {
            return LATEST;
            
        } else if(str.contains("stage")) {
            return STAGE;
            
        } else if(str.contains("shadow")) {
            return SHADOW;
            
        } else if(str.contains("prodc")) {
            return PROD_C;
            
        } else if(str.contains("sl")) {
            return SOFT_LAUNCH;
            
        } else if(str.contains("prod")) {
            return PRODUCTION;
            
        } else if(str.contains("hh")) {
            return HILTON_HEAD;
            
        } else if(str.contains("vb")) {
            return VERO_BEACH;
            
        } else if(str.contains("qa3")) {
            return ENV4;
            
        } else if(str.contains("qa5")) {
            return ENV2;
            
        } else if(str.contains("lt1")) {
        	return LOAD2;
        	
        } else if(str.contains("lt2")) {
        	return LOAD1;
            
        } else {
            
            // If we can't pick up anything that matches from the 
            // TEST_ENVIRONMENT property, we'll determine the environment from 
            // the BASE_URL.
            
            if(Constants.BASE_URL_LC.contains("hh")) {
                return HILTON_HEAD;
                
            } else if(Constants.BASE_URL_LC.contains("vb")) {
                return VERO_BEACH;
                
            } else if(Constants.BASE_URL_LC.contains("latest")) {
                return LATEST;
                
            } else if(Constants.BASE_URL_LC.contains("stage")) {
                return STAGE;
                
            } else if(Constants.BASE_URL_LC.contains("shadow")) {
                return SHADOW;
                
            } else if(Constants.BASE_URL_LC.contains("pep-qa3")) {
                return ENV4;

            } else if(Constants.BASE_URL_LC.contains("pep-qa5")) {
                return ENV2;
                
            } else if(Constants.BASE_URL_LC.contains("pep-lt1")) {
                return LOAD2;
                
            } else if(Constants.BASE_URL_LC.contains("pep-lt2")) {
                return LOAD1;
                
            } else if(Constants.BASE_URL_LC.contains("disneyworld") || 
                      Constants.BASE_URL_LC.contains("disneyland")) {
                
                if(Constants.IS_LIGHT_DARK_OVERRIDE)  {
                    return SOFT_LAUNCH;
                    
                } else {
                    
                    Log.getDefaultLogger().warning("Defaulting to PRODUCTION "
                            + "since we CANNOT determine if it's PROD-C");
                    
                    // Note that we don't have PRODC in here because it's  
                    // difficult to tell.
                    return PRODUCTION;
                }
            }
        }
        
        return UNKNOWN;
    }
}