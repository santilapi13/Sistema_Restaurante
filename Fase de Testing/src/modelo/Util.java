package modelo;

public abstract class  Util {
    public static String intToDia(int nro){
        String dia="";
        switch (nro){
            case 0: dia= "DOMINGO";
                break;
            case 1: dia= "LUNES";
                break;
            case 2: dia= "MARTES";
                break;
            case 3: dia= "MIERCOLES";
                break;
            case 4: dia= "JUEVES";
                break;
            case 5: dia= "VIERNES";
                break;
            case 6: dia= "SABADO";
                break;
        }
        return dia;
    }
}
