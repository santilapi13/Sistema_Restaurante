package testCajaNegra;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAsignacionMesaMozo.class, TestCerrarComanda.class, TestCerveceriaEliminacion.class,
		TestCerveceriaIngresosConDatos.class, TestCerveceriaIngresosSinDatos.class, TestEstados.class,
		TestTomarComandas.class })
public class AllTests {

}
