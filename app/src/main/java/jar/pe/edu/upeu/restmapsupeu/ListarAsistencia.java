package jar.pe.edu.upeu.restmapsupeu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import jar.pe.edu.upeu.servis.UsuarioServices;
import jar.pe.edu.upeu.to.AsistenciaTO;
import jar.pe.edu.upeu.util.AsistenciaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListarAsistencia extends Fragment {
    List<AsistenciaTO> asistencia;
    RecyclerView recyclerView;
    RecyclerView.Adapter<AsistenciaAdapter.AsistenciaViewHolder> adapter;
    RecyclerView.LayoutManager layoutManager;
    public final String TAG=this.getClass().getSimpleName();
    Retrofit retrofit;
    UsuarioServices usuarioServis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myFragmentView= inflater.inflate(R.layout.fragment_listar_asistencia, container, false);

        this.recyclerView=(RecyclerView)myFragmentView.findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this.getContext());

        // Rest
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.43.13:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioServis=retrofit.create(UsuarioServices.class);
        Call<List<AsistenciaTO>> listarAsistenciaTodos=usuarioServis.listarAsistencia();
        listarAsistenciaTodos.enqueue(new Callback<List<AsistenciaTO>>() {
            @Override
            public void onResponse(Call<List<AsistenciaTO>> call, Response<List<AsistenciaTO>> response) {

                asistencia = response.body();
                adapter=new AsistenciaAdapter(asistencia);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), "Llego la sistencia.......!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AsistenciaTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al recuperar rest asistencia", Toast.LENGTH_SHORT).show();
            }
        });


        return myFragmentView;
    }



}
