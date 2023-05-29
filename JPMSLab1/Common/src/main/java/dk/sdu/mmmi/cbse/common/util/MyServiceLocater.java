package dk.sdu.mmmi.cbse.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class MyServiceLocater {


    private static final Map<Class, ServiceLoader> map = new HashMap<Class, ServiceLoader>();



    private MyServiceLocater() {

    }

    public static <T> List<T> locateAll(Class<T> service) {
        boolean print = false;
        ServiceLoader<T> serviceLoader = map.get(service);
        if(serviceLoader==null){
            serviceLoader=ServiceLoader.load(service);
            map.put(service,serviceLoader);
            print= true;

        }
        List<T> list =new ArrayList<T>();
        if(serviceLoader!=null){
            try{
                for(T instance : serviceLoader){
                    list.add(instance);

                }
            }catch (ServiceConfigurationError serviceError){
                serviceError.printStackTrace();

            }

            if(print){
                System.out.println(list.size());
            }


        }

        return list;
    }
}
