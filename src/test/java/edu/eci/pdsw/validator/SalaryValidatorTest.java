package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Optional;

import org.junit.Test;
import org.quicktheories.core.Gen;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.EmployeeGenerator;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {
	private SalaryValidator validator = new SalaryValidator();
	@Test
	public void validateTest() {
		qt()
		.forAll(EmployeeGenerator.employee()).check(employee -> {
			validator = new SalaryValidator();
			Optional<ErrorType> errorValidador = validator.validate(employee);
			Optional<ErrorType> error = Optional.empty();
			if((employee).getSocialSecurityType()==SocialSecurityType.EPS) {
				if((employee).getSalary()>=10000) {
					error.of(ErrorType.INVALID_EPS_AFFILIATION);
				}else error.empty();
				
				
			}else if((employee).getSocialSecurityType()==SocialSecurityType.SISBEN) {
				if(( employee).getSalary()>=1500) {
					error.of(ErrorType.INVALID_SISBEN_AFFILIATION);
				}else error.empty();
				
				
			}else if(( employee).getSocialSecurityType()==SocialSecurityType.PREPAID) {
				if(( employee).getSalary()>=100) {
					error.empty();
				}
				else error.of(ErrorType.INVALID_SALARY);
			}
			else {
				error.of(ErrorType.INVALID_TYPE_OF_SOCIAL_SECURITY);
			}
			return (errorValidador.toString()).equals(error.toString());
		});
	}
}
