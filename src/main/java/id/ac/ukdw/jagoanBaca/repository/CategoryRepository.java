package id.ac.ukdw.jagoanBaca.repository;

import id.ac.ukdw.jagoanBaca.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
