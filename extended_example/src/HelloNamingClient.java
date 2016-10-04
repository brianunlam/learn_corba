import org.omg.CORBA.*;

import org.omg.CosNaming.*;

import java.util.Properties;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.NotFound;


 class HelloNamingClient {

     public static void main(String[] args1) {

         ORB orb = null;

/*
        System.out.println("Begin to get Name Service object");
        System.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.49.16");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "5032");
        System.setProperty("ORBInitRef", "NameService=http://192.168.49.16:5032/NameService");
  */

         try {
             String[] args = new String[2];
             args[0] = "-ORBInitRef";
             args[1] = "NameService=corbaloc::" + "192.168.49.29" + ":" + "52486" + "/NameService";

              orb = org.omg.CORBA.ORB.init(args, null);

         } catch (SystemException ex) {
             System.out.println("chino ORB chino");
             System.out.println(ex.getMessage());
         }
         if (orb == null) {
             System.out.println("orb == null");
         }



         try {
             org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
             System.out.println(objRef);
             NamingContextExt rootContext = NamingContextExtHelper.narrow(objRef);
             System.out.println("chino Get Nameservice reference");
             BindingListHolder blsoh = new BindingListHolder(new Binding[0]);
             BindingIteratorHolder bioh = new BindingIteratorHolder();
             rootContext.list(0, blsoh,bioh);

             BindingHolder bh = new BindingHolder();

             if (bioh.value == null)
                 return;

             while (bioh.value.next_one(bh)){
                 String stringName = rootContext.to_string(bh.value.binding_name);
                 System.out.println( stringName);
             }



         } catch (Exception e) {
             System.out.println("errores: " + e);

         }
     }
 }