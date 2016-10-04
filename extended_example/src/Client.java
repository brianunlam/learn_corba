import chapter49.*;
import java.io.*;
import org.omg.CORBA.*;

public class Client {

    public static void main(String args[]){
        //create Holder objects for out parameters

        ShortHolder minute = new ShortHolder();
        ShortHolder hour = new ShortHolder();
        try{
            //init the ORB

            ORB orb = ORB.init(args, null);

            //get object reference from cmd argument

            org.omg.CORBA.Object obj = orb.string_to_object(args[0]);

            //and narrow it to GoodDay

            GoodDay goodDay = GoodDayHelper.narrow(obj);

            if (goodDay == null){
                System.out.println("stringified object reference is of wrong type");
                System.exit(-1);
            }

            //invoke the operation

            String location = goodDay.hello( hour, minute);

            //print results
            System.out.println("Hello world!");
            if (minute.value < 10)
                System.out.println("The local time in " + location + " is " + hour.value + ":0"+ minute.value + ".");
            else
                System.out.println("The local time in " + location + " is " + hour.value + ":"+ minute.value + ".");


        }
        //catch
        catch(SystemException ex){
            System.err.println(ex);
        }



    }
}