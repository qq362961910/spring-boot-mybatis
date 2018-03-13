import org.springframework.data.domain.Sort;

public class SortTest {

    public static void main(String[] args) {
        Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"));
        for(Sort.Order order: sort) {
            System.out.println(order.getProperty() + " " + order.getDirection().name());
        }
    }
}
