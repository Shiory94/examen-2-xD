package jar.pe.edu.upeu.servis;


import java.util.List;


import jar.pe.edu.upeu.to.AsistenciaTO;
import jar.pe.edu.upeu.to.EventoTO;
import jar.pe.edu.upeu.to.UsuarioTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Update by Joselin on 05/06/2018;
 */

public interface UsuarioServices {
    @GET("/EventoUPeU/user/all")
    Call<List<UsuarioTO>> listarUsuario();

    @GET("/EventoUPeU/event/all")
    Call<List<EventoTO>> listarEvento();

    @GET("/EventoUPeU/asistencia/all")
    Call<List<AsistenciaTO>> listarAsistencia();

    @POST("/EventoUPeU/user/add")
    Call<UsuarioTO> guardarUsuario(@Body UsuarioTO usuario);

    @POST("/EventoUPeU/user/login")
    Call<UsuarioTO> login(@Body UsuarioTO usuario);





}
