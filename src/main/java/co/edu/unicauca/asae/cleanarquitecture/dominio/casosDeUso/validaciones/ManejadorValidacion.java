package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones;

public abstract class ManejadorValidacion<T> {
    private ManejadorValidacion<T> siguienteManejador;

    public ManejadorValidacion<T> setSiguienteManejador(ManejadorValidacion<T> siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
        return siguienteManejador;
    }

    public void manejar(T dato) {
        if (this.validar(dato)) {
            if (this.siguienteManejador != null) {
                this.siguienteManejador.manejar(dato);
            }
        }
    }

    protected abstract boolean validar(T dato);
}
