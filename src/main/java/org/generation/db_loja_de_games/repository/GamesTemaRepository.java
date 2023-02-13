package org.generation.db_loja_de_games.repository;
import java.util.List;

import org.generation.db_loja_de_games.model.temaGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GamesTemaRepository extends JpaRepository<temaGames, Long> {
	public List<temaGames> findAllByDescricaoContainingIgnoreCase(@Param("Descricao")String descricao );
}