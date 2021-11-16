package ru.alishev.springcourse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.alishev.springcourse.models.Postuniv;

public interface PostunivRepository extends CrudRepository<Postuniv,Long>{
	List<Postuniv> findTop5ByTheme(String theme);
	
	@Query(value="SELECT * from Postuniv where theme=:theme order by id DESC limit 5 offset :page",
			nativeQuery = true)
	List<Postuniv> ownFindTeme(@Param("theme")String theme,@Param("page")int page);
}
