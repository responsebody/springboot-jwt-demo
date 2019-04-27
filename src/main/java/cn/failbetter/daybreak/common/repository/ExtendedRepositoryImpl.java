package cn.failbetter.daybreak.common.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

import static cn.failbetter.daybreak.common.holder.Checker.expressionIsTrue;

public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements ExtendedRepository<T, ID> {

	private final EntityManager entityManager;
	private final JpaEntityInformation entityInformation;

	public ExtendedRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public <S extends T> S persist(S entity, String errorMessage) {

		expressionIsTrue(!existsById((ID) entityInformation.getId(entity)), errorMessage);
		return super.save(entity);
	}

	@Override
	public T getOne(ID id, String errorMessage) {

		Optional<T> resultById = findById(id);
		expressionIsTrue(resultById.isPresent(), errorMessage);
		return resultById.get();
	}
}
