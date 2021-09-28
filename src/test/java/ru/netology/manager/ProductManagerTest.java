package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Product book1 = new Book(1, "Harry Potter and the Chamber of Secrets", 350, "J.K. Rowling");
    Product book2 = new Book(2, "The Little Prince", 350, "Antoine de Saint-Exupery");
    Product smartPhone1 = new Smartphone(3, "Samsung A52", 350, "Samsung");
    Product smartPhone2 = new Smartphone(4, "Iphone X", 350, "Apple");
    Product product1 = new Product(5, "Harry Potter and the Half-Blood Prince", 350);
    Product product2 = new Product(6, "Iphone SE", 350);

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartPhone1);
        manager.add(smartPhone2);
        manager.add(product1);
        manager.add(product2);
    }


    @Test
    void shouldRemoveById() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{
                book1,
                smartPhone1, smartPhone2,
                product1, product2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdNotExist() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(10);
        });
    }
}