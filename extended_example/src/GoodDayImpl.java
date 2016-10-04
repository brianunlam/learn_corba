import java.util.Date;
import org.omg.CORBA.*;
import chapter49.*;

public class GoodDayImpl extends GoodDayPOA {
    private String location;

    //constructor

    GoodDayImpl( String location){
        this.location = location;
    }

    //method

    public String hello( ShortHolder hour, ShortHolder minute ){
        //get local time of the server
        Date date = new Date();
        hour.value = (short) date.getHours();
        minute.value = (short) date.getMinutes();
        return location;
    }
}