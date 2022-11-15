package testGUI;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  GuiTestConjuntoConDatosVLogin.class, GuiTestEnabledDisabledVLogin.class,
		GuiTestEnabledDisabledVOperario.class, GuiTestOperacionesVOperario.class, GuiTestEnabledDisabledVProducto.class, 
		GuiTestConjuntoConDatosVProducto.class})
public class AllTests {

}
