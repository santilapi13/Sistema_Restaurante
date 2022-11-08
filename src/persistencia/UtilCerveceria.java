package persistencia;

import modelo.Cerveceria;

public class UtilCerveceria {

    public static CerveceriaDTO cerveceriaToCerveceriaDTO(Cerveceria cerveceria) {
        CerveceriaDTO respuesta = new CerveceriaDTO();
        respuesta.setNombreLocal(cerveceria.getNombreLocal());
        respuesta.setMozos(cerveceria.getMozos());
        respuesta.setMesas(cerveceria.getMesas());
        respuesta.setCarta(cerveceria.getCarta());
        respuesta.setOperarios(cerveceria.getOperarios());
        respuesta.setComandasAbiertas(cerveceria.getComandasAbiertas());
        respuesta.setVentas(cerveceria.getVentas());
        respuesta.setAdministrador(cerveceria.getAdministrador());
        respuesta.setPromosProductos(cerveceria.getPromosProductos());
        respuesta.setPromosTemporales(cerveceria.getPromosTemporales());
        respuesta.setOperarioLogueado(cerveceria.getOperarioLogueado());
        return respuesta;
    }

    public static void cerveceriaDTOToCerveceria(CerveceriaDTO cerveceriaDTO, Cerveceria cerveceria) {
        cerveceria.setNombreLocal(cerveceriaDTO.getNombreLocal());
        cerveceria.setMozos(cerveceriaDTO.getMozos());
        cerveceria.setMesas(cerveceriaDTO.getMesas());
        cerveceria.setCarta(cerveceriaDTO.getCarta());
        cerveceria.setOperarios(cerveceriaDTO.getOperarios());
        cerveceria.setComandasAbiertas(cerveceriaDTO.getComandasAbiertas());
        cerveceria.setVentas(cerveceriaDTO.getVentas());
        cerveceria.setAdministrador(cerveceriaDTO.getAdministrador());
        cerveceria.setPromosProductos(cerveceriaDTO.getPromosProductos());
        cerveceria.setPromosTemporales(cerveceriaDTO.getPromosTemporales());
        cerveceria.setOperarioLogueado(cerveceriaDTO.getOperarioLogueado());
    }

}
