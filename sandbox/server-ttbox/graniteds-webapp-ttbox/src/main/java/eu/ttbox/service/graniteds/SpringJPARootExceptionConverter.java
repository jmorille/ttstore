package eu.ttbox.service.graniteds;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.granite.messaging.service.ServiceException;
import org.granite.tide.validation.BeanValidation;
import org.springframework.transaction.TransactionException;

public class SpringJPARootExceptionConverter // implements ExceptionConverter 
{

    public static final String ENTITY_EXISTS = "Persistence.EntityExists";

    public static final String ENTITY_NOT_FOUND = "Persistence.EntityNotFound";

    public static final String NON_UNIQUE_RESULT = "Persistence.NonUnique";

    public static final String NO_RESULT = "Persistence.NoResult";

    public static final String OPTIMISTIC_LOCK = "Persistence.OptimisticLock";

    public static final String TRANSACTION_REQUIRED = "Persistence.TransactionRequired";

    public static final String ROLLBACK = "Persistence.Rollback";

    public static final String OTHER = "Persistence.Error";

    public static final String VALIDATION_FAILED = "Validation.Failed";

    
    public boolean accepts(Throwable t) {
        return t.getClass().equals(EntityExistsException.class) || t.getClass().equals(EntityNotFoundException.class)
                        || t.getClass().equals(NonUniqueResultException.class)
                        || t.getClass().equals(NoResultException.class)
                        || t.getClass().equals(OptimisticLockException.class)
                        || t.getClass().equals(TransactionRequiredException.class)
                        || t.getClass().equals(RollbackException.class)
                        || PersistenceException.class.isAssignableFrom(t.getClass())
                        || ConstraintViolationException.class.isAssignableFrom(t.getClass())
                        || TransactionException.class.isAssignableFrom(t.getClass());
    }

    private Throwable getRootCause(Throwable t) {
        Throwable rootCause = t.getCause();
        if (rootCause != null) {
            if (this.accepts(rootCause)) {
                return getRootCause(rootCause);
            }
        }
        return t;
    }

    public ServiceException convert(Throwable originalException, String detail, Map<String, Object> extendedData) {
        System.err.println("--------------------------------------");
        Throwable t = getRootCause(originalException);
        System.err.println("--- " + originalException.getClass() + " ==> " + t.getClass() );

        String error = null;
        Map<String, Object> ex = null; 
        if (t.getClass().equals(ConstraintViolationException.class)) {
            error = VALIDATION_FAILED;
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException)t).getConstraintViolations();
            extendedData.put("invalidValues", BeanValidation.convertConstraintViolations(constraintViolations));
            ex = new HashMap<String, Object>();
            ex.putAll(extendedData); 
        } else if (t.getClass().equals(EntityExistsException.class)) {
            error = ENTITY_EXISTS;
        } else if (t.getClass().equals(EntityNotFoundException.class)) {
            error = ENTITY_NOT_FOUND;
        } else if (t.getClass().equals(NonUniqueResultException.class)) {
            error = NON_UNIQUE_RESULT;
        } else if (t.getClass().equals(NoResultException.class)) {
            error = NO_RESULT;
        } else if (t.getClass().equals(OptimisticLockException.class)) {
            error = OPTIMISTIC_LOCK;
            ex = new HashMap<String, Object>();
            ex.put("entity", ((OptimisticLockException ) t).getEntity());
        } else if (t.getClass().equals(TransactionRequiredException.class)) {
            error = TRANSACTION_REQUIRED;
        }  else if (t.getClass().equals(RollbackException.class)) {
            error = ROLLBACK;
        } else {
            error = OTHER;
        }
        ServiceException se = new ServiceException(error, t.getMessage(), detail, t);
        if (ex != null && !ex.isEmpty()) {
            se.getExtendedData().putAll(ex);
        }
        System.err.println("Throw ServiceException : " + se);
        if (se.getExtendedData()!=null && !se.getExtendedData().isEmpty()) {
            for (String key : se.getExtendedData().keySet()) {
                Object keyVal = se.getExtendedData().get(key);
                System.err.println(" * " + key +  "  /   " + keyVal);
            }
        }
        return se;
    }

}
