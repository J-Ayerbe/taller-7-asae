package co.edu.unicauca.asae.cleanarquitecture.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarProductoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.ProductoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarDocenteGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.DocenteFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarFranjaHorariaGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.EspacioFisicoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.CursoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.GestionarProductoCUAdapter;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.GestionarDocenteCUAdapter;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.GestionarFranjaHorariaCUAdapter;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.GestionarEspacioFisicoCUAdapter;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.GestionarCursoCUAdapter;

@Configuration
public class BeanConfigurations {

    @Bean
    public GestionarProductoCUAdapter crearGestionarProductoCUInt(
            GestionarProductoGatewayIntPort objGestionarProductoGateway,
            ProductoFormateadorResultadosIntPort objProductoFormateadorResultados) {
        GestionarProductoCUAdapter objGestionarProductoCU = new GestionarProductoCUAdapter(objGestionarProductoGateway,
                objProductoFormateadorResultados);
        return objGestionarProductoCU;
    }

    @Bean
    public GestionarDocenteCUAdapter crearGestionarDocenteCUInt(
            GestionarDocenteGatewayIntPort docenteGateway,
            DocenteFormateadorResultadosIntPort formateador) {
        return new GestionarDocenteCUAdapter(docenteGateway, formateador);
    }

    @Bean
    public GestionarFranjaHorariaCUAdapter crearGestionarFranjaHorariaCUInt(
            GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway,
            GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway,
            GestionarCursoGatewayIntPort cursoGateway,
            FranjaHorariaFormateadorResultadosIntPort formateador) {
        return new GestionarFranjaHorariaCUAdapter(franjaHorariaGateway, espacioFisicoGateway,
                cursoGateway, formateador);
    }

    @Bean
    public GestionarEspacioFisicoCUAdapter crearGestionarEspacioFisicoCUInt(
            GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway,
            EspacioFisicoFormateadorResultadosIntPort formateador) {
        return new GestionarEspacioFisicoCUAdapter(espacioFisicoGateway, formateador);
    }

    @Bean
    public GestionarCursoCUAdapter crearGestionarCursoCUInt(
            GestionarCursoGatewayIntPort cursoGateway,
            CursoFormateadorResultadosIntPort formateador) {
        return new GestionarCursoCUAdapter(cursoGateway, formateador);
    }
}