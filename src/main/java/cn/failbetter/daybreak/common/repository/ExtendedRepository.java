package cn.failbetter.daybreak.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * dong
 * @author sun 2019/4/25
 */
@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * @param entity save
	 * @param errorMessage the exception message to use if the DO has exists;
	 * @param <S> DO
	 * @return DO
	 */
	<S extends T> S persist(S entity, String errorMessage);

	/**
	 * @param id PK
	 * @param errorMessage the exception message to use if the DO not exists;
	 * @return DO
	 */
	T getOne(ID id, String errorMessage);
}
