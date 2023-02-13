package org.generation.db_loja_de_games.repository;

import java.util.List;

import org.generation.db_loja_de_games.model.gameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface gameRepository extends JpaRepository<gameModel, Long> {
	public List<gameModel>findAllByNomeContainingIgnoreCase(@Param("nome") String titulo);
}