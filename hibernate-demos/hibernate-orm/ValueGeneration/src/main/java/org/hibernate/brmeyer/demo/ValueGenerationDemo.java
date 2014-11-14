/* 
 * Hibernate, Relational Persistence for Idiomatic Java
 * 
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.hibernate.brmeyer.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

/**
 * @author Brett Meyer
 */
public class ValueGenerationDemo {
	
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.addAnnotatedClass( Project.class );
		
		// necessary for a known bug, to be fixed in 4.2.9.Final
		configuration.setProperty( AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
		
		final SessionFactory sessionFactory = configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().build() );
		Session s = sessionFactory.openSession();
		
		final Project project = new Project();
		
		s.getTransaction().begin();
		s.persist(project);
		s.getTransaction().commit();
		s.clear();
		
		System.out.println(project.toString());
		
		s.getTransaction().begin();
		s.update(project );
		s.getTransaction().commit();
		s.close();
		
		System.out.println(project.toString());
		
		System.exit(0);
	}
}
