package servicios;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/servicios") //Esto provoca que el servidor Tomcat lo ejecute al arrancar
public class AplicacionSubastas extends Application{
	private Set<Object> singletons = new HashSet<Object>(); //Aquí almacenamos todos los servicios web que van a funcionar como singletons
	
	public AplicacionSubastas() {
	      singletons.add(new RecursoSubastas()); //Un servicio proporcionado por un único objeto Java para todos los clientes --> singleton
	   }

	   @Override
	   public Set<Object> getSingletons() {
	      return singletons;
	   }
	}
