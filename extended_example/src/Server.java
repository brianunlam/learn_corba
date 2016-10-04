import chapter49.*;


import org.omg.CORBA.*;
import org.omg.CORBA.ORBPackage.*;
import org.omg.PortableServer.*;

public class Server {

    public static void main(String[] args){
        try{
            //init orb
            ORB orb = ORB.init(args,null);

            //init basic Object adapter.

            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            //create a GoodDay object
            GoodDayImpl goodDayImpl = new GoodDayImpl( args[0]);

            // export the object reference

            org.omg.CORBA.Object obj =
                    poa.servant_to_reference(goodDayImpl);
            System.out.println(orb.object_to_string(obj));

            //wait for requests
            orb.run();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }


}