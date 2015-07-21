package services;

import android.location.Location;

/**
 * Created by FRAMGIA\pham.xuan.lu on 21/07/2015.
 */
public class LocationData {

    public Location location;


    private static LocationData _instance;
    private LocationData(){

    }

    public static synchronized LocationData getInstance(){
        if(_instance==null)
            _instance = new LocationData();

        return _instance;
    }
}
