import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class ProductDao {

    private SessionFactory sessionFactory;

    public ProductDao(){

        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/productmanagement");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "Rares2010");
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(ProductModel.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);


    }

    public void addProduct(ProductModel productModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(productModel);
        transaction.commit();
        session.close();

    }

    public List<ProductModel> getProducts(){
        Session session = sessionFactory.openSession();
        List<ProductModel> productModelList = session.createQuery("from ProductModel").getResultList();
        session.close();
        return productModelList;
    }

    public void removeProduct(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductModel productModel = session.find(ProductModel.class, id);
        if(productModel != null){
            session.delete(productModel);
        }
        transaction.commit();
        session.close();
    }

    public void editPrice(int id , double newPrice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductModel productModel = session.find(ProductModel.class, id);
        productModel.setPrice(newPrice);
        session.saveOrUpdate(productModel);
        transaction.commit();
        session.close();

    }




}
