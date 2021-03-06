/*
 * TODO Produto $(product_name} - ${product_description}<br>
 *
 * Data de Criação: 31 de mar de 2018<br>
 * <br>
 * Todos os direitos reservados.
 */
package br.com.casadocodigo.DAO;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.models.Produto;
import br.com.casadocodigo.models.TipoPreco;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 31 de mar de 2018 - @author jorge - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Repository
@Transactional
public class ProdutoDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * 
     * Método resposável por persistir os dados.
     * 
     * @param produto
     */
    public void gravar ( Produto produto ) {
        em.persist( produto );
    }
    
    
    /**
     * Método que retorna todos os produtos e preços. 
     * (Lazy) - os preços só serão buscados no banco, quando houver necessidade em utilizá-los, 
     * mas deve haver uma transação aberta. 
     * Para obter os preços utilizando este método, pode ser obtido inserindo um 
     * filtro( OpenEntityManagerInViewFilter() ) na classe ServletSpringMVC.
     */
    /*public List < Produto > find () {
        return em.createQuery( "select p from Produto p" , Produto.class ).getResultList();
    }*/
    
    /**
     * Método que retorna todos os produtos e seus preços 
     */
    public List < Produto > find () {
        return em.createQuery( "select distinct(p) from Produto p join fetch p.precos" , Produto.class ).getResultList();
    }

    /**
     * Método que retorna um produto atraves do seu id.
     * 
     * @param id
     * @return
     */
    public Produto find ( Integer id ) {
        return em.createQuery( "select distinct(p) from Produto p join fetch p.precos precos where p.id = :id" , Produto.class )
                .setParameter( "id" , id )
                .getSingleResult();
    }

	public void update( Produto produto ) {
		em.merge( produto ); 
		em.close();
		
		
	}

	public Produto findById(Integer id) {
		return em.createQuery( "select distinct(p) from Produto p where p.id = :id" , Produto.class )
                .setParameter( "id" , id )
                .getSingleResult();
	}
	
	public BigDecimal somaPrecoPorTipo( TipoPreco tipoPreco ) {
		TypedQuery<BigDecimal> query = em.createQuery(
				"select sum( preco.valor ) from Produto p " + "join p.precos preco where preco.tipo = :tipoPreco",
				BigDecimal.class );
		query.setParameter( "tipoPreco", tipoPreco );

		return query.getSingleResult();
		
		
	}
}
