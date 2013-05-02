package eu.ttbox.server.admin.client;

import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import eu.ttbox.server.admin.shared.proxy.SalespointProxy;

/**
 * {@link AbstractGwtValidatorFactory} that creates the specified {@link GwtValidator}.
 */
public class SampleValidatorFactory extends AbstractGwtValidatorFactory {

	/**
	   * Validator marker for the Validation Sample project. Only the classes listed
	   * in the {@link GwtValidation} annotation can be validated.
	   */
	  @GwtValidation(value = SalespointProxy.class
	  //    ,groups = {Default.class }
	  )
	  public interface GwtValidator extends Validator {
	  }

	  @Override
	  public AbstractGwtValidator createValidator() {
	    return GWT.create(GwtValidator.class);
	  }
	  
}
