package jar.repository;

import jar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 1. Search customers by name or email
    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Customer> searchByNameOrEmail(@Param("search") String search);

    // 2. Filter customers by status
    List<Customer> findByStatus(String status);

    // 3. Search AND Filter combined
    @Query("SELECT c FROM Customer c WHERE (LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))) AND c.status = :status")
    List<Customer> searchAndFilter(@Param("search") String search, @Param("status") String status);

    // 4. Counts for Dashboard Statistics
    long countByStatus(String status);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.followUpDate <= :date")
    long countFollowUpsDue(@Param("date") LocalDate date);
}