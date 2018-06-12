package jar.pe.edu.upeu.restmapsupeu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import jar.pe.edu.upeu.servis.UsuarioServices;
import jar.pe.edu.upeu.to.EventoTO;
import jar.pe.edu.upeu.util.EventoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReporteFragment extends Fragment {
    List<EventoTO> evento;
    RecyclerView recyclerView;
    RecyclerView.Adapter<EventoAdapter.EventoViewHolder> adapter;
    RecyclerView.LayoutManager layoutManager;
    public final String TAG=this.getClass().getSimpleName();
    Retrofit retrofit;
    UsuarioServices usuarioServis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myFragmentView= inflater.inflate(R.layout.fragment_reporte, container, false);

        this.recyclerView=(RecyclerView)myFragmentView.findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this.getContext());

        // Rest
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.43.13:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioServis=retrofit.create(UsuarioServices.class);
        Call<List<EventoTO>> listarEventoTodos=usuarioServis.listarEvento();
        listarEventoTodos.enqueue(new Callback<List<EventoTO>>() {
            @Override
            public void onResponse(Call<List<EventoTO>> call, Response<List<EventoTO>> response) {

                evento = response.body();
                adapter=new EventoAdapter(evento);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), "Llego.......!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<EventoTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al recuperar rest", Toast.LENGTH_SHORT).show();
            }
        });


        return myFragmentView;
    }


}
