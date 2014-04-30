package org.bonitasoft.engine.persistence;

import java.util.Properties;

import javax.transaction.UserTransaction;

import org.hibernate.HibernateException;
import org.hibernate.transaction.JTATransactionFactory;

public class Atomikos3HibernateJTATransactionFactory extends JTATransactionFactory {

    private UserTransaction userTransaction;

    @Override
    public void configure(Properties props) throws HibernateException {
        // fix for case 32252: hibernate config init
        super.configure(props);
    }

    @Override
    protected UserTransaction getUserTransaction() {
        if (this.userTransaction == null) {
            try {
                this.userTransaction = (UserTransaction) Class.forName("com.atomikos.icatch.jta.UserTransactionImp").newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return this.userTransaction;
    }
}
