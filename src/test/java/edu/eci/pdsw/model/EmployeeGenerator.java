package edu.eci.pdsw.model;

import static org.quicktheories.QuickTheory.*;
import static org.quicktheories.generators.SourceDSL.*;

import org.junit.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

public class EmployeeGenerator{
	public static Gen<Employee> employee() {
		return identificador().zip(salario(), tipoSeguridadSocial(), (identificador, salario, tipoSeguridadSocial) -> new Employee(identificador, salario, tipoSeguridadSocial));
	}
	
	private static Gen<Integer> identificador(){
		return  integers().between(1000,100000);
	}
	private static Gen<Integer> salario(){
		return integers().between(100, 50000);
	}
	private static Gen<SocialSecurityType> tipoSeguridadSocial(){
		return Generate.enumValues(SocialSecurityType.class);
	}
}
