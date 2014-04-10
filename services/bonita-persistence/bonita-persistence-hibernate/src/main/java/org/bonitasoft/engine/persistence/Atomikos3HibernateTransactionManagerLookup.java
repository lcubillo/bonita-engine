/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.engine.persistence;

import java.util.Properties;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.hibernate.HibernateException;
import org.hibernate.transaction.TransactionManagerLookup;

public class Atomikos3HibernateTransactionManagerLookup implements TransactionManagerLookup {

    private final String userTransactionJndiName;

    private final TransactionManager transactionManager;

    public Atomikos3HibernateTransactionManagerLookup() throws Exception {
        final Class<?> userTransactionManagerClass = Class.forName("com.atomikos.icatch.jta.UserTransactionManager");
        this.transactionManager = (TransactionManager) userTransactionManagerClass.newInstance();

        // WARNING: this is hard coded and should not.
        // java:com/env/UserTransaction is the standard value but it may defer from one App Server to another
        // this value is compliant with the default Tomcat jndi binding
        this.userTransactionJndiName = "java:com/env/UserTransaction";

        // System.err.println("Configured userTransactionJndiName:" + userTransactionJndiName);
        // System.err.println("Configured transactionManager:" + transactionManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionManager getTransactionManager(Properties props) throws HibernateException {
        return this.transactionManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getTransactionIdentifier(Transaction transaction) {
        // for sane JEE/JTA containers, the transaction itself functions as its identifier...
        return transaction;
    }

    @Override
    public String getUserTransactionName() {
        return userTransactionJndiName;
    }

}
